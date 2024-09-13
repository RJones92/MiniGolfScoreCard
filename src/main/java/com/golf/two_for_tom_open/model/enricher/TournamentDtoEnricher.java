package com.golf.two_for_tom_open.model.enricher;

import com.golf.two_for_tom_open.model.dto.*;
import com.golf.two_for_tom_open.service.ScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static java.util.Objects.compare;

@Component
@RequiredArgsConstructor
@Slf4j
public class TournamentDtoEnricher implements DtoEnricher<TournamentDto> {

    private final ScoreService scoreService;

    @Override
    public void enrich(TournamentDto tournament) {
        setTournamentWinner(tournament);
    }

    private void setTournamentWinner(TournamentDto tournament) {
        List<ScoreDto> tournamentScores = scoreService.getScoresForTournamentById(tournament.getId());
        tournament.getCourses()
                .forEach(course -> {
                    List<ScoreDto> courseScores = getCourseScores(course, tournamentScores);
                    course.getWinnerByTournamentId().put(tournament.getId(), getCourseWinner(courseScores, tournament.getPlayers()));
                });

        tournament.setWinner(getTournamentWinner(tournament, tournamentScores));
    }

    private List<ScoreDto> getCourseScores(CourseDto course, List<ScoreDto> scores) {
        List<Integer> courseHoleIds = course.getHoles().stream()
                .map(HoleDto::getId)
                .toList();
        return scores.stream()
                .filter(score -> courseHoleIds.contains(score.getHole().getId()))
                .collect(Collectors.toList());
    }

    private PlayerDto getCourseWinner(List<ScoreDto> courseScores, List<PlayerDto> tournamentPlayers) {

        Map<Integer, Integer> playerId_countOfStrokes = getStrokesForPlayers(courseScores, tournamentPlayers);
        List<Integer> lowestStrokePlayerIds = getPlayerIdsWithLowestStroke(playerId_countOfStrokes);
        List<PlayerDto> lowestStrokePlayers = tournamentPlayers.stream()
                .filter(tournamentPlayer -> lowestStrokePlayerIds.contains(tournamentPlayer.getId()))
                .collect(Collectors.toList());

        if (lowestStrokePlayers.size() > 1) {
            return playerWithMostHolesInOne(courseScores, lowestStrokePlayers);
        }

        return lowestStrokePlayers.get(0);
    }

    private Map<Integer, Integer> getStrokesForPlayers(List<ScoreDto> scores, List<PlayerDto> players) {
        Map<Integer, Integer> playerId_countOfStrokes = new HashMap<>();
        players.forEach(player -> playerId_countOfStrokes.put(player.getId(), 0));

        for (PlayerDto player : players) {
            int playerStrokes = scores.stream()
                    .filter(score -> score.getPlayer().getId() == player.getId())
                    .mapToInt(ScoreDto::getStrokes)
                    .sum();
            playerId_countOfStrokes.put(player.getId(), playerStrokes);
        }
        return playerId_countOfStrokes;
    }

    private List<Integer> getPlayerIdsWithLowestStroke(Map<Integer, Integer> playerId_countOfStrokes) {

        int lowestNumberOfStrokes = playerId_countOfStrokes.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .orElseThrow()
                .getValue();

        return playerId_countOfStrokes.entrySet().stream()
                .filter(entry -> entry.getValue() == lowestNumberOfStrokes)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

    }

    private PlayerDto playerWithMostHolesInOne(List<ScoreDto> courseScores, List<PlayerDto> players) {

        Map<PlayerDto, Long> player_CountOfHolesInOne = new HashMap<>();

        for (PlayerDto player : players) {
            long countOfHoleInOneForPlayer = courseScores.stream()
                    .filter(score -> score.getPlayer().getId() == player.getId())
                    .filter(score -> score.getStrokes() == 1)
                    .count();

            player_CountOfHolesInOne.put(player, countOfHoleInOneForPlayer);
        }

        return player_CountOfHolesInOne.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow();
    }

    private PlayerDto getTournamentWinner(TournamentDto tournament, List<ScoreDto> tournamentScores) {
        Map<PlayerDto, Integer> numberOfCoursesWonForEachPlayer = calculateNumberOfCoursesWonForEachPlayer(tournament);

        return numberOfCoursesWonForEachPlayer.entrySet().stream()
                .max(((o1, o2) -> compare(o1, o2, compareCoursesWonForPlayer(tournamentScores))))
                .orElseThrow().getKey();
    }

    private Map<PlayerDto, Integer> calculateNumberOfCoursesWonForEachPlayer(TournamentDto tournament) {
        Map<Integer, Integer> playerId_CountOfCourseWins = new HashMap<>();
        tournament.getPlayers().forEach(player -> playerId_CountOfCourseWins.put(player.getId(), 0));

        tournament.getCourses().stream()
                .map(course -> course.getWinnerByTournamentId().get(tournament.getId()))
                .forEach(courseWinner -> {
                    Integer currentCountOfCourseWins = playerId_CountOfCourseWins.get(courseWinner.getId());
                    playerId_CountOfCourseWins.put(courseWinner.getId(), currentCountOfCourseWins + 1);
                });

        return playerId_CountOfCourseWins.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> tournament.getPlayers().stream()
                                .filter(player -> player.getId() == entry.getKey())
                                .findAny()
                                .orElseThrow(),
                        Map.Entry::getValue
                ));
    }

    private Comparator<Map.Entry<PlayerDto, Integer>> compareCoursesWonForPlayer(List<ScoreDto> tournamentScores) {

        return Comparator.comparingInt((ToIntFunction<Map.Entry<PlayerDto, Integer>>) Map.Entry::getValue)
                .thenComparing(o -> getTotalTournamentStrokes(o.getKey(), tournamentScores), (int1, int2) -> int2-int1);
    }

    private int getTotalTournamentStrokes(PlayerDto player, List<ScoreDto> tournamentScores) {
        Map<Integer, Integer> playerId_strokes = getStrokesForPlayers(tournamentScores, singletonList(player));
        return playerId_strokes.get(player.getId());
    }

}

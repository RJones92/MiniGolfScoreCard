package com.golf.two_for_tom_open.model.enricher;

import com.golf.two_for_tom_open.model.dto.*;
import com.golf.two_for_tom_open.service.ScoreService;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class TournamentDtoEnricher implements DtoEnricher<TournamentDto> {

    private final ScoreService scoreService;

    public TournamentDtoEnricher(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @Override
    public void enrich(TournamentDto tournament) {
        setWinners(tournament);
    }

    private void setWinners(TournamentDto tournament) {
        setCourseWinners(tournament);
        setTournamentWinner(tournament);
    }

    private void setCourseWinners(TournamentDto tournament) {
        List<ScoreDto> tournamentScores = scoreService.getScoresForTournamentById(tournament.getId());

        tournament.getCourses().forEach(course -> {
            List<ScoreDto> courseScores = getCourseScores(course, tournamentScores);
            PlayerDto courseWinner = calculateCourseWinner(courseScores, tournament.getPlayers());
            course.getWinnersByTournamentId().put(tournament.getId(), courseWinner);
        });
    }

    private List<ScoreDto> getCourseScores(CourseDto course, List<ScoreDto> scores) {
        List<Integer> courseHoleIds = course.getHoles().stream().map(HoleDto::getId).collect(Collectors.toList());
        return scores.stream()
                .filter(score -> courseHoleIds.contains(score.getHole().getId()))
                .collect(Collectors.toList());
    }

    private PlayerDto calculateCourseWinner(List<ScoreDto> courseScores, List<PlayerDto> tournamentPlayers) {

        Map<Integer, Integer> playerId_countOfStrokes = setStrokesPerPlayer(courseScores, tournamentPlayers);
        List<Integer> lowestStrokePlayerIds = findPlayerIdWithLowestStroke(playerId_countOfStrokes);
        List<PlayerDto> lowestStrokePlayers = tournamentPlayers.stream()
                .filter(tournamentPlayer -> lowestStrokePlayerIds.contains(tournamentPlayer.getId()))
                .collect(Collectors.toList());

        if (isMoreThanOnePlayerWithLowestStrokes(lowestStrokePlayers)) {
            return playerWithMostHolesInOne(courseScores, lowestStrokePlayers);
        } else {
            return lowestStrokePlayers.get(0);
        }
    }

    private Map<Integer, Integer> setStrokesPerPlayer(List<ScoreDto> scores, List<PlayerDto> players) {
        Map<Integer, Integer> playerId_countOfStrokes = new HashMap<>();
        players.forEach(player -> playerId_countOfStrokes.put(player.getId(), 0));

        scores.forEach(score -> {
            int currentStrokesForPlayer = playerId_countOfStrokes.get(score.getPlayer().getId());
            int newNumberOfStrokesForPlayer = currentStrokesForPlayer + score.getStrokes();
            playerId_countOfStrokes.put(score.getPlayer().getId(), newNumberOfStrokesForPlayer);
        });

        return playerId_countOfStrokes;
    }

    private List<Integer> findPlayerIdWithLowestStroke(Map<Integer, Integer> playerId_countOfStrokes) {

        int lowestNumberOfStrokes = playerId_countOfStrokes.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .orElseThrow()
                .getValue();

        return playerId_countOfStrokes.entrySet().stream()
                .filter(entry -> entry.getValue() == lowestNumberOfStrokes)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

    }

    private boolean isMoreThanOnePlayerWithLowestStrokes(List<PlayerDto> players) {
        return players.size() > 1;
    }

    private PlayerDto playerWithMostHolesInOne(List<ScoreDto> courseScores, List<PlayerDto> players) {

        Map<PlayerDto, Long> player_CountOfHolesInOne = new HashMap<>();

        for (PlayerDto player : players) {
            Long countOfHoleInOneForPlayer = courseScores.stream()
                    .filter(score -> score.getPlayer() == player)
                    .filter(score -> score.getStrokes() == 1)
                    .count();

            player_CountOfHolesInOne.put(player, countOfHoleInOneForPlayer);
        }

        return player_CountOfHolesInOne.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow();

    }

    private void setTournamentWinner(TournamentDto tournament) {
        PlayerDto winner = calculateTournamentWinner(tournament);
        tournament.setWinner(winner);
    }

    private PlayerDto calculateTournamentWinner(TournamentDto tournament) {
        Map<PlayerDto, Integer> numberOfCoursesWonForEachPlayer = calculateNumberOfCoursesWonForEachPlayer(tournament);

        return numberOfCoursesWonForEachPlayer.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow().getKey();
    }

    private Map<PlayerDto, Integer> calculateNumberOfCoursesWonForEachPlayer(TournamentDto tournament) {
        Map<Integer, Integer> playerId_CountOfCourseWins = new HashMap<>();
        tournament.getPlayers().forEach(player -> playerId_CountOfCourseWins.put(player.getId(), 0));

        List<ScoreDto> tournamentScores = new ArrayList<>();
        if (!isTournamentCourseWinnersCalculated(tournament)) {
            tournamentScores = scoreService.getScoresForTournamentById(tournament.getId());
        }
        List<ScoreDto> finalTournamentScores = tournamentScores;

        tournament.getCourses().forEach(course -> {
            PlayerDto courseWinner;
            if (!isTournamentCourseWinnersCalculated(tournament)) {
                List<ScoreDto> courseScores = getCourseScores(course, finalTournamentScores);
                courseWinner = calculateCourseWinner(courseScores, tournament.getPlayers());
            } else {
                courseWinner = course.getWinnersByTournamentId().get(tournament.getId());
            }
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

    private Boolean isTournamentCourseWinnersCalculated(TournamentDto tournament) {

        for (CourseDto course : tournament.getCourses()) {
            if (!course.getWinnersByTournamentId().isEmpty()) return true;
        }
        return false;
    }
}

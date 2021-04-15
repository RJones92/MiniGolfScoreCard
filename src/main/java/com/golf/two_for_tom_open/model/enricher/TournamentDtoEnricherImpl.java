package com.golf.two_for_tom_open.model.enricher;

import com.golf.two_for_tom_open.model.dto.*;
import com.golf.two_for_tom_open.service.ScoreService;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class TournamentDtoEnricherImpl implements TournamentDtoEnricher {

    private final ScoreService scoreService;

    public TournamentDtoEnricherImpl(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @Override
    public void enrich(TournamentDto tournament) {
        setWinner(tournament);
    }

    private void setWinner(TournamentDto tournament) {
        List<ScoreDto> scoresForTournament = scoreService.getScoresForTournamentById(tournament.getId());

        PlayerDto winner = calculateTournamentWinner(tournament, scoresForTournament);

        tournament.setWinner(winner);
    }

    private PlayerDto calculateTournamentWinner (TournamentDto tournament, List<ScoreDto> tournamentScores) {
        Map<PlayerDto, Integer> numberOfCoursesWonForEachPlayer = calculateNumberOfCoursesWonForEachPlayer(tournament, tournamentScores);

        return numberOfCoursesWonForEachPlayer.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();
    }

    private Map<PlayerDto, Integer> calculateNumberOfCoursesWonForEachPlayer(TournamentDto tournament, List<ScoreDto> tournamentScores) {
        Map<PlayerDto, Integer> numberOfPlayerCourseWins = new HashMap<>();
        tournament.getPlayers().forEach(player -> numberOfPlayerCourseWins.put(player, 0));

        tournament.getCourses().forEach(course -> {
            List<ScoreDto> courseScores = getCourseScores(course, tournamentScores);
            PlayerDto courseWinner = calculateCourseWinner(course, courseScores, tournament.getPlayers());
            Integer currentScoreForCourseWinner = numberOfPlayerCourseWins.get(courseWinner);
            numberOfPlayerCourseWins.put(courseWinner, currentScoreForCourseWinner + 1);
        });

        return numberOfPlayerCourseWins;
    }

    private List<ScoreDto> getCourseScores(CourseDto course, List<ScoreDto> scores) {
        List<HoleDto> courseHoles = course.getHoles();
        return scores.stream()
                .filter(score -> courseHoles.contains(score.getHole()))
                .collect(Collectors.toList());
    }

    private PlayerDto calculateCourseWinner(CourseDto course, List<ScoreDto> courseScores, List<PlayerDto> tournamentPlayers) {

        Map<PlayerDto, Integer> countOfStrokesForEachPlayer = setStrokesPerPlayer(courseScores, tournamentPlayers);

        List<PlayerDto> lowestStrokePlayers = findPlayersWithLowestStroke(countOfStrokesForEachPlayer);
        if (isMoreThanOnePlayerWithLowestStrokes(lowestStrokePlayers)) {
            return playerWithMostHolesInOne(course, courseScores, lowestStrokePlayers);
        } else {
            return lowestStrokePlayers.get(0);
        }

    }

    private Map<PlayerDto, Integer> setStrokesPerPlayer(List<ScoreDto> courseScores, List<PlayerDto> tournamentPlayers) {
        Map<PlayerDto, Integer> countOfStrokesForEachPlayer = new HashMap<>();
        tournamentPlayers.forEach(player -> countOfStrokesForEachPlayer.put(player, 0));

        courseScores.forEach(score -> {
            int currentStrokesForPlayer = countOfStrokesForEachPlayer.get(score.getPlayer());
            int newNumberOfStrokesForPlayer = currentStrokesForPlayer + score.getStrokes();
            countOfStrokesForEachPlayer.put(score.getPlayer(), newNumberOfStrokesForPlayer);
        });

        return countOfStrokesForEachPlayer;
    }

    private List<PlayerDto> findPlayersWithLowestStroke(Map<PlayerDto, Integer> countOfStrokesForEachPlayer) {

        int lowestNumberOfStrokes = countOfStrokesForEachPlayer.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .get()
                .getValue();

        return countOfStrokesForEachPlayer.entrySet().stream()
                .filter(entry -> entry.getValue() == lowestNumberOfStrokes)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

    }

    private boolean isMoreThanOnePlayerWithLowestStrokes(List<PlayerDto> players) {
        return players.size() > 1;
    }

    private PlayerDto playerWithMostHolesInOne(CourseDto course, List<ScoreDto> courseScores, List<PlayerDto> players) {

        Map<PlayerDto, Long> countOfHolesInOneForPlayers = new HashMap<>();

        for (PlayerDto player : players) {
            Long countOfHoleInOneForPlayer = courseScores.stream()
                    .filter(score -> score.getPlayer() == player)
                    .filter(score -> score.getStrokes() == 1)
                    .count();

            countOfHolesInOneForPlayers.put(player, countOfHoleInOneForPlayer);
        }

        return countOfHolesInOneForPlayers.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .get();

    }
}

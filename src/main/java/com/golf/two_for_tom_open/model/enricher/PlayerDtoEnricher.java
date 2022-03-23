package com.golf.two_for_tom_open.model.enricher;

import com.golf.two_for_tom_open.model.dto.*;
import com.golf.two_for_tom_open.service.ScoreService;
import com.golf.two_for_tom_open.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PlayerDtoEnricher implements DtoEnricher<PlayerDto> {

    private List<TournamentDto> enrichedTournaments;
    private final TournamentService tournamentService;
    private final ScoreService scoreService;

    @Override
    public void enrich(PlayerDto player) {
        enrichedTournaments = tournamentService.getAllTournamentDtos();
        calculateStatistics(player);
    }

    private void calculateStatistics(PlayerDto player) {
        player.setCountOfTournamentsPlayed(countTournamentsPlayed(player));
        player.setCountOfTournamentsWon(countTournamentsWon(player));

        player.setCountOfCoursesPlayed(countCoursesPlayed(player));
        player.setCountOfCoursesWon(countCoursesWon(player));

        player.setCountOfHolesPlayed(countHolesPlayed(player));
        player.setCountOfHolesWon(countHolesWon(player));
    }

    private long countTournamentsPlayed(PlayerDto player) {
        return tournamentsPlayed(player).size();
    }

    private List<TournamentDto> tournamentsPlayed(PlayerDto player) {
        return enrichedTournaments.stream()
                .filter(tournament -> isPlayerInTournament.test(tournament, player))
                .collect(Collectors.toList());
    }

    private final BiPredicate<TournamentDto, PlayerDto> isPlayerInTournament = (tournament, player) -> tournament.getPlayers().stream()
            .anyMatch(tournamentPlayer -> tournamentPlayer.equals(player));

    private long countTournamentsWon(PlayerDto player) {
        return enrichedTournaments.stream()
                .filter(tournament -> tournament.getWinner().equals(player))
                .count();
    }

    private long countCoursesPlayed(PlayerDto player) {
        return coursesPlayed(player).size();
    }

    private List<CourseDto> coursesPlayed(PlayerDto player) {
        return enrichedTournaments.stream()
                .filter(tournament -> isPlayerInTournament.test(tournament, player))
                .map(TournamentDto::getCourses)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private long countCoursesWon(PlayerDto player) {
        return coursesPlayed(player).stream()
                .mapToLong(course -> countOfPlayerWinsForCourse(course, player))
                .sum();
    }

    private long countOfPlayerWinsForCourse(CourseDto course, PlayerDto player) {
        Collection<PlayerDto> courseWinners = course.getWinnerByTournamentId().values();
        return courseWinners.stream()
                .filter(winner -> winner.equals(player))
                .count();
    }

    private long countHolesPlayed(PlayerDto player) {
        return holesPlayed(player).size();
    }

    private List<HoleDto> holesPlayed(PlayerDto player) {
        List<CourseDto> coursesPlayed = coursesPlayed(player);

        return coursesPlayed.stream()
                .map(CourseDto::getHoles)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private long countHolesWon(PlayerDto player) throws NoSuchElementException {
        List<ScoreDto> allScoresForTournamentsThisPlayerPlayed = scoresForTournamentsPlayerPlayedIn(player);

        List<ScoreDto> scoresForThisPlayer = allScoresForTournamentsThisPlayerPlayed.stream()
                .filter(score -> score.getPlayer().equals(player))
                .collect(Collectors.toList());

        return scoresForThisPlayer.stream()
                .filter(playerScore -> {
                    List<ScoreDto> holeScoresForThisTourn = getScoresWithSameHoleAndSameTournament(
                            allScoresForTournamentsThisPlayerPlayed, playerScore);
                    if (holeScoresForThisTourn.isEmpty()) return false;

                    return findHoleWinners(holeScoresForThisTourn).stream()
                            .anyMatch(winner -> winner.equals(player));
                })
                .count();
    }

    private List<ScoreDto> scoresForTournamentsPlayerPlayedIn(PlayerDto player) {
        List<Integer> tournamentIdsOfTournamentsPlayed = tournamentsPlayed(player).stream()
                .map(TournamentDto::getId)
                .collect(Collectors.toList());
        return scoreService.getScoresForTournamentsById(tournamentIdsOfTournamentsPlayed);
    }

    private List<ScoreDto> getScoresWithSameHoleAndSameTournament(List<ScoreDto> allScores, ScoreDto scoreToTest) {
        return allScores.stream()
                .filter(score -> isScoreSameHoleAndSameTournament.test(score, scoreToTest))
                .collect(Collectors.toList());
    }

    private final BiPredicate<ScoreDto, ScoreDto> isScoreSameHoleAndSameTournament = (ScoreDto score1, ScoreDto score2) ->
            ((score1.getHole().equals(score2.getHole()))
                    && (score1.getTournament().getId() == score2.getTournament().getId()));

    private List<PlayerDto> findHoleWinners(List<ScoreDto> scoresForHole) {
        int lowestStrokesForHole = scoresForHole.stream()
                .min(Comparator.comparing(ScoreDto::getStrokes))
                .orElseThrow()
                .getStrokes();

        return scoresForHole.stream()
                .filter(score -> score.getStrokes() == lowestStrokesForHole)
                .map(ScoreDto::getPlayer)
                .collect(Collectors.toList());
    }

}

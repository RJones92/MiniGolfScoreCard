package com.golf.two_for_tom_open.model.enricher.calculator;

import com.golf.two_for_tom_open.model.dto.PlayerDto;
import com.golf.two_for_tom_open.model.dto.ScoreDto;
import com.golf.two_for_tom_open.model.dto.TournamentDto;
import com.golf.two_for_tom_open.service.ScoreService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

import static com.golf.two_for_tom_open.model.util.HoleUtil.findHoleWinners;
import static com.golf.two_for_tom_open.model.util.PlayerUtil.countOfPlayerWinsForCourse;
import static com.golf.two_for_tom_open.model.util.PlayerUtil.coursesPlayed;
import static com.golf.two_for_tom_open.model.util.PlayerUtil.holesPlayed;
import static com.golf.two_for_tom_open.model.util.PlayerUtil.tournamentsPlayed;
import static com.golf.two_for_tom_open.model.util.ScoreUtil.getScoresWithSameHoleAndSameTournament;
import static com.golf.two_for_tom_open.model.util.ScoreUtil.scoresForPlayer;

@Component
public class PlayerStatCalculator {

    private final ScoreService scoreService;

    public PlayerStatCalculator(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    public long countTournamentsPlayed(PlayerDto player, List<TournamentDto> tournaments) {
        return tournamentsPlayed(player, tournaments).size();
    }

    public long countTournamentsWon(PlayerDto player, List<TournamentDto> tournaments) {
        return tournaments.stream()
                .filter(tournament -> tournament.getWinner().equals(player))
                .count();
    }

    public long countCoursesPlayed(PlayerDto player, List<TournamentDto> tournaments) {
        return coursesPlayed(player, tournaments).size();
    }

    public long countCoursesWon(PlayerDto player, List<TournamentDto> tournamentDtos) {
        return coursesPlayed(player, tournamentDtos).stream()
                .mapToLong(course -> countOfPlayerWinsForCourse(course, player))
                .sum();
    }

    public long countHolesPlayed(PlayerDto player, List<TournamentDto> tournaments) {
        return holesPlayed(player, tournaments).size();
    }

    public long countHolesWon(PlayerDto player, List<TournamentDto> tournaments) throws NoSuchElementException {
        List<ScoreDto> allScoresForTournamentsThisPlayerPlayed = scoresForTournamentsPlayerPlayedIn(player, tournaments);

        return scoresForPlayer(allScoresForTournamentsThisPlayerPlayed, player).stream()
                .filter(playerScore -> {
                    List<ScoreDto> holeScoresForThisTourn = getScoresWithSameHoleAndSameTournament(
                            allScoresForTournamentsThisPlayerPlayed, playerScore);
                    if (holeScoresForThisTourn.isEmpty()) return false;

                    return findHoleWinners(holeScoresForThisTourn).stream()
                            .anyMatch(winner -> winner.equals(player));
                })
                .count();
    }

    private List<ScoreDto> scoresForTournamentsPlayerPlayedIn(PlayerDto player, List<TournamentDto> tournaments) {
        List<Integer> tournamentIdsOfTournamentsPlayed = tournamentsPlayed(player, tournaments).stream()
                .map(TournamentDto::getId)
                .toList();
        return scoreService.getScoresForTournamentsById(tournamentIdsOfTournamentsPlayed);
    }

    public long countHolesInOne(PlayerDto player, List<TournamentDto> tournaments) {
        List<ScoreDto> allScoresForTournamentsThisPlayerPlayed = scoresForTournamentsPlayerPlayedIn(player, tournaments);

        return scoresForPlayer(allScoresForTournamentsThisPlayerPlayed, player).stream()
                .filter(scores -> scores.getStrokes() == 1)
                .count();
    }

}

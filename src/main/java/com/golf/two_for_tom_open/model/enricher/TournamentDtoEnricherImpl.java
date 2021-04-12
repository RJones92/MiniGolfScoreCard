package com.golf.two_for_tom_open.model.enricher;

import com.golf.two_for_tom_open.model.dto.PlayerDto;
import com.golf.two_for_tom_open.model.dto.ScoreDto;
import com.golf.two_for_tom_open.model.dto.TournamentDto;
import com.golf.two_for_tom_open.service.ScoreService;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

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
        List<ScoreDto> scoresForTournament = scoreService.getScoresForTournamentByYear(tournament.getYear());

        PlayerDto winner = calculatePlayerWithLowestStrokes(tournament.getPlayers(), scoresForTournament);

        tournament.setWinner(winner);
    }

    private PlayerDto calculatePlayerWithLowestStrokes(List<PlayerDto> players, List<ScoreDto> scores) {
        return players.stream()
                .min(Comparator.comparing(player -> totalStrokesForPlayer(player, scores)))
                .orElseThrow(NoSuchElementException::new);
    }

    private int totalStrokesForPlayer(PlayerDto player, List<ScoreDto> scores) {
        return scores.stream()
                .filter(score -> player.getId() == score.getPlayer().getId())
                .map(ScoreDto::getStrokes)
                .mapToInt(Integer::intValue)
                .sum();
    }
}

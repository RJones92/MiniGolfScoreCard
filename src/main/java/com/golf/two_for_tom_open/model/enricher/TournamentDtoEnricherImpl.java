package com.golf.two_for_tom_open.model.enricher;

import com.golf.two_for_tom_open.model.dto.PlayerDto;
import com.golf.two_for_tom_open.model.dto.ScoreDto;
import com.golf.two_for_tom_open.model.dto.TournamentDto;
import com.golf.two_for_tom_open.model.entity.Score;
import com.golf.two_for_tom_open.model.mapper.ScoreMapper;
import com.golf.two_for_tom_open.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
public class TournamentDtoEnricherImpl implements TournamentDtoEnricher {

    @Autowired
    ScoreService scoreService;

    @Autowired
    ScoreMapper scoreMapper;

    @Override
    public void enrich(TournamentDto tournament) {
        setwinner(tournament);
    }

    private void setwinner(TournamentDto tournament) {
        List<Score> scoresForTournament = scoreService.getScoresForTournamentByYear(tournament.getYear());
        List<ScoreDto> scoreDtosForTournament = scoresForTournament.stream()
                .map(score -> scoreMapper.scoreEntityToDto(score))
                .collect(Collectors.toList());

        PlayerDto winner = calculatePlayerWithLowestStrokes(tournament.getPlayers(), scoreDtosForTournament);

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

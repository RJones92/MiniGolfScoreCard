package com.golf.two_for_tom_open.model.util;

import com.golf.two_for_tom_open.model.dto.PlayerDto;
import com.golf.two_for_tom_open.model.dto.ScoreDto;

import java.util.List;
import java.util.function.BiPredicate;

public final class ScoreUtil {
    // TODO unit test
    private ScoreUtil() {}

    public static final BiPredicate<ScoreDto, ScoreDto> isScoreSameHoleAndSameTournament = (ScoreDto score1, ScoreDto score2) ->
            ((score1.getHole().equals(score2.getHole()))
                    && (score1.getTournament().getId() == score2.getTournament().getId()));

    public static List<ScoreDto> getScoresWithSameHoleAndSameTournament(List<ScoreDto> allScores, ScoreDto scoreToTest) {
        return allScores.stream()
                .filter(score -> isScoreSameHoleAndSameTournament.test(score, scoreToTest))
                .toList();
    }

    public static List<ScoreDto> scoresForPlayer(List<ScoreDto> scores, PlayerDto player) {
        return scores.stream()
                .filter(score -> score.getPlayer().equals(player))
                .toList();
    }
}

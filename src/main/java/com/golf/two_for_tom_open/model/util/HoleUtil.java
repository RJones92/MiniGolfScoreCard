package com.golf.two_for_tom_open.model.util;

import com.golf.two_for_tom_open.model.dto.PlayerDto;
import com.golf.two_for_tom_open.model.dto.ScoreDto;

import java.util.Comparator;
import java.util.List;

public final class HoleUtil {

    // TODO unit test
    private HoleUtil() {}

    public static List<PlayerDto> findHoleWinners(List<ScoreDto> scoresForHole) {
        int lowestStrokesForHole = scoresForHole.stream()
                .min(Comparator.comparing(ScoreDto::getStrokes))
                .orElseThrow()
                .getStrokes();

        return scoresForHole.stream()
                .filter(score -> score.getStrokes() == lowestStrokesForHole)
                .map(ScoreDto::getPlayer)
                .toList();
    }

}

package com.golf.two_for_tom_open.dataInitialiser.prod.score;

import com.golf.two_for_tom_open.model.entity.Player;
import com.golf.two_for_tom_open.model.entity.Score;
import com.golf.two_for_tom_open.model.entity.Tournament;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

public class JadeScoreCreator extends ScoreCreator {
    
    public List<Score> create(Tournament tournament, Player player) {

        List<List<Integer>> scoresForPlayerForYear = switch (tournament.get_year().getValue()) {
            case 2020 -> buildScoreJade2020();
            default -> emptyList();
        };

         return scoresForPlayerForYear.isEmpty() ?
                 emptyList() :
                 createScores(scoresForPlayerForYear, tournament, player);
    }

    private List<List<Integer>> buildScoreJade2020() {
        var course1 = List.of(5,4,5,3,4,2,3,2,2,3,6,3,5,3,5,6,3,2);
        var course2 = List.of(2,5,2,4,2,3,3,2,4,2,4,5,1,2,5,3,2,4);
        var course3 = List.of(3,6,6,2,4,6,4,2,6,3,6,3,4,6,5,4,2,2,2);
        var course4 = List.of(3,2,4,3,2,3,2,2,2,2,4,2,2,2,2,3,3,4);
        var course5 = List.of(3,3,3,3,3,2,3,2,2,3,2,3,2,4,4,3,2,4);
        return Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5).toList();
    }

}

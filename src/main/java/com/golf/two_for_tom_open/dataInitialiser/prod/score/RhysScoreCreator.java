package com.golf.two_for_tom_open.dataInitialiser.prod.score;

import com.golf.two_for_tom_open.model.entity.Player;
import com.golf.two_for_tom_open.model.entity.Score;
import com.golf.two_for_tom_open.model.entity.Tournament;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

public class RhysScoreCreator extends ScoreCreator {

    public List<Score> create(Tournament tournament, Player player) {
        var year = tournament.get_year().getValue();

        List<List<Integer>> scoresForPlayerForYear = switch (year) {
            case 2016 -> buildScoreRhys2016();
            case 2017 -> buildScoreRhys2017();
            case 2018 -> buildScoreRhys2018();
            case 2019 -> buildScoreRhys2019();
            case 2020 -> buildScoreRhys2020();
            case 2021 -> buildScoreRhys2021();
            case 2022 -> buildScoreRhys2022();
            case 2023 -> buildScoreRhys2023();
            case 2024 -> buildScoreRhys2024();
            default -> Collections.emptyList();
        };

        return scoresForPlayerForYear.isEmpty() ?
                emptyList() :
                createScores(scoresForPlayerForYear, tournament, player);
    }

    private List<List<Integer>> buildScoreRhys2016() {
        var course1 = List.of(2,4,5,5,4,5,2,2,5,6,4,5,5,2,6,2,6,1);//71
        var course2 = List.of(3,2,2,3,3,3,4,2,2,5,2,2,2,2,3,5,2,2);//49
        var course3 = List.of(4,3,3,3,4,2,5,3,2,2,5,3,2,3,3,1,3,2);
        var course4 = List.of(2,2,4,6,2,2,3,3,2,5,2,3,3,2,3,3,2,1);
        var course5 = List.of(2,3,2,4,6,2,5,2,3,2,2,2,5,3,2,3,2,4);
        return List.of(
                course1, course2, course3,
                course4, course5);
        
    }

    private List<List<Integer>> buildScoreRhys2017() {
        var course1 = List.of(4,6,5,2,5,4,5,2,2,5,2,2,3,2,1,3,7,5);
        var course2 = List.of(3,2,4,2,3,3,2,2,2,2,3,3,3,2,5,2,3,3);
        var course3 = List.of(2,2,4,5,3,4,2,2,4);
        var course4 = List.of(2,2,2,2,3,3,3,3,2,3,3,2,3,5,2,2,2,7);
        var course5 = List.of(3,2,2,3,3,4,2,2,3,3,2,2,3,4,2,2,2,4);
        var course6 = List.of(3,2,2,2,4,3,3,2,3,3,4,2,2,3,2,3,2,3);
        var course7 = List.of(2,4,4,2,3,7,7,4,3,2,3,5,4,2,3,2,3,2);
        return List.of(
                course1, course2, course3,
                course4, course5, course6,
                course7);
    }

    private List<List<Integer>> buildScoreRhys2018() {
        var course1 = List.of(6,2,2,6,6,2,4,5,4,2,2,2);
        var course2 = List.of(5,4,3,2,4,6,3,3,6,3,6,6,6);
        var course3 = List.of(4,5,4,2,2,2,2,2,5);
        var course4 = List.of(3,2,3,2,2,3,2,2,4,1,2,3);
        var course5 = List.of(3,2,2,3,2,3,2,2,3,3,1,2,3,2,3,4,3,2);
        var course6 = List.of(4,2,3,2,4,6,2,3,6);
        var course7 = List.of(6,1,1,6,2,2,3,2,2,2,2,5,2,3,4,3,2,4);
        var course8 = List.of(2,3,2,3,2,2,3,2,3,2,1,2,1,1,4,2,2,5);
        return List.of(
                course1, course2, course3,
                course4, course5, course6,
                course7, course8);
        
    }

    private List<List<Integer>> buildScoreRhys2019() {
        var course1 = List.of(2,2,2,4,1,1,3,3,4,2,3,2,2,2,3,3,2,3);
        var course2 = List.of(3,6,3,3,5,3,2,3,4);
        var course3 = List.of(2,3,3,2,3,3,3,3,3,4,2,4,4,3,2,2,3,3);
        var course4 = List.of(3,3,2,3,3,2,4,3,1,3,2,2,2,4,2,3,2,3);
        var course5 = List.of(2,3,5,3,4,3,2,3,3,2,3,4,3,3,4,2,3,3);
        var course6 = List.of(3,2,2,4,5,2,2,2,4,1,2,2,2,2,2,3,2,2);
        var course7 = List.of(1,3,4,2,3,2,2,2,4,2,6,2,3,2,3,2,4,3);
        var course8 = List.of(2,3,2,2,2,4,1,2,2,6,2,3,2,3,2,2,2,2);
        var course9 = List.of(2,6,3,5,2,3,2,3,2,2,4,2);
        return List.of(
                course1, course2, course3,
                course4, course5, course6,
                course7, course8, course9);
        
    }

    private List<List<Integer>> buildScoreRhys2020() {
        var course1 = List.of(4,2,3,4,2,2,3,3,3,3,4,3,6,3,2,3,6,2);
        var course2 = List.of(3,4,2,1,2,3,3,2,4,2,4,3,2,1,3,1,2,2);
        var course3 = List.of(2,3,3,5,2,2,2,3,6,2,5,3,2,4,2,3,3,3,1);
        var course4 = List.of(2,2,3,2,3,2,2,2,2,2,2,2,3,2,2,2,2,3);
        var course5 = List.of(2,2,2,2,2,5,2,2,3,3,4,3,3,2,3,2,3,2);
        return List.of(
                course1, course2, course3,
                course4, course5);
        
    }

    private List<List<Integer>> buildScoreRhys2021() {
        var course1 = List.of(2,2,2,1,2,3,4,2,2,2,2,3,2,2,2,3,3,3);
        var course2 = List.of(3,2,3,2,3,2,2,3,1,2,3,3,2,2,2,3,3,3);
        var course3 = List.of(2,2,2,2,4,3,6,6,5,2,3,3,3,2,2,6,2,4);
        var course4 = List.of(2,2,3,3,2,4,3,3,2,2,3,2,3,2,2,2,2,3);
        var course5 = List.of(3,2,2,5,2,2,1,2,2,5,3,1);
        var course6 = List.of(2,5,4,2,2,2,2,2,5,3,3,4,4,4,3,3,4,1);
        return List.of(
                course1, course2, course3,
                course4, course5, course6);
        
    }

    private List<List<Integer>> buildScoreRhys2022() {
        var course1 = List.of(3,2,3,2,2,3,3,2,3,3,3,1,3,3,2,3,3,2);
        var course2 = List.of(3,3,3,1,3,3,1,3,2,2,2,4,2,3,3,2,2,2);
        var course3 = List.of(2,2,2,2,4,2,1,3,6,2,1,2);
        var course4 = List.of(2,2,4,2,2,2,3,2,2,3,2,3,4,2,3,3,2,2);
        var course5 = List.of(2,3,1,2,3,3,2,2,3,2,2,3,2,3,3,2,1,3);
        var course6 = List.of(2,2,2,2,2,3,3,2,4,3,2,2);
        var course7 = List.of(2,4,2,3,2,1,2,2,2,3,2,5,2,2,1,3,2,4);
        return List.of(
                course1, course2, course3,
                course4, course5, course6,
                course7);
        
    }

    private List<List<Integer>> buildScoreRhys2023() {
        var course1 = List.of(2,2,3,3,3,2,5,3,4,2,2,2,2,4,3,4,3,2);
        var course2 = List.of(2,4,2,4,3,4,2,3,3,2,3,2,2,2,2,4,2,4);
        var course3 = List.of(5,3,2,2,3,3,4,3,2,3,3,1);
        var course4 = List.of(3,3,2,4,2,2,1,2,2,2,2,2);
        var course5 = List.of(2,5,2,6,2,3,4,4,3,3,3,4,3,3,4,2,6,2);
        var course6 = List.of(3,2,3,2,2,2,4,3,2,2,2,2,2,2,2,5);
        var course7 = List.of(3,4,3,3,3,2,2,3,6,2,2,2,2,4,2,2,3,6,1);
        var course8 = List.of(2,1,3,4,2,2,2,4,2,3,2,3,2,3,2,4,3,4);
        return List.of(
                course1, course2, course3,
                course4, course5, course6,
                course7, course8);
    }

    private List<List<Integer>> buildScoreRhys2024() {
        var course1 = List.of(2,2,2,2,2,2,3,2,2,3,2,2,2,3,3,2,2,3);
        var course2 = List.of(2,2,3,3,3,3,2,2,3,3,3,2,6,2,3,2,2,3);
        var course3 = List.of(2,2,2,1,2,2,2,2,2,2,2,3,2,2,2,1,3,3);
        var course4 = List.of(3,4,4,4,3,3,4,4,3,4,5,3,3,4,3,2,3,4);
        var course5 = List.of(2,3,2,3,4,2,3,2,2,2,2,2,3,2,3,1,2,3);
        var course6 = List.of(3,2,3,4,6,3,2,5,3);
        var course7 = List.of(2,3,4,3,2,3,2,2,2,2,2,1,1,2,3,2,3,1);
        return List.of(
                course1, course2, course3,
                course4, course5, course6,
                course7);
    }

}

package com.golf.two_for_tom_open.dataInitialiser.prod.score;

import com.golf.two_for_tom_open.model.entity.Player;
import com.golf.two_for_tom_open.model.entity.Score;
import com.golf.two_for_tom_open.model.entity.Tournament;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

public class TomScoreCreator extends ScoreCreator {
    
    public List<Score> create(Tournament tournament, Player player) {

        List<List<Integer>> scoresForPlayerForYear = switch (tournament.get_year().getValue()) {
            case 2016 -> buildScoreTom2016();
            case 2017 -> buildScoreTom2017();
            case 2018 -> buildScoreTom2018();
            case 2019 -> buildScoreTom2019();
            case 2020 -> buildScoreTom2020();
            case 2021 -> buildScoreTom2021();
            case 2022 -> buildScoreTom2022();
            case 2023 -> buildScoreTom2023();
            case 2024 -> buildScoreTom2024();
            case 2025 -> buildScoreTom2025();
            default -> Collections.emptyList();
        };

        return scoresForPlayerForYear.isEmpty() ?
                emptyList() : createScores(scoresForPlayerForYear, tournament, player);
    }

    private List<List<Integer>> buildScoreTom2016() {
        var course1 = List.of(5,2,3,3,2,3,3,2,3,4,3,2,6,6,4,6,3,6); //66
        var course2 = List.of(3,1,2,2,2,3,2,3,3,2,3,2,3,2,2,4,2,3);//44
        var course3 = List.of(2,4,4,2,3,2,2,4,1,3,3,2,3,2,1,2,3,4);
        var course4 = List.of(2,3,3,4,4,2,2,4,3,3,2,6,3,3,2,4,3,4);
        var course5 = List.of(2,2,3,3,4,4,2,2,2,3,2,3,3,6,2,2,2,2);
        return List.of(
                course1, course2, course3,
                course4, course5);
    }

    private List<List<Integer>> buildScoreTom2017() {
        var course1 = List.of(7,3,3,2,7,4,3,4,4,3,3,2,4,3,3,3,4,3);
        var course2 = List.of(3,3,3,3,2,2,3,2,2,2,3,2,3,3,3,4,2,5);
        var course3 = List.of(2,2,4,5,2,3,2,2,4);
        var course4 = List.of(4,3,2,2,5,2,3,4,3,3,5,2,2,4,2,2,3,7);
        var course5 = List.of(3,4,4,2,3,3,3,2,3,3,3,3,2,2,3,4,3,2);
        var course6 = List.of(2,2,3,3,3,3,2,3,3,2,5,2,3,3,4,3,3,2);
        var course7 = List.of(4,2,4,2,2,2,7,2,3,2,2,4,2,2,2,2,7,3);
        return List.of(
                course1, course2, course3,
                course4, course5, course6,
                course7);
    }

    private List<List<Integer>> buildScoreTom2018() {
        var course1 = List.of(3,4,2,4,4,1,4,4,2,2,2,6);
        var course2 = List.of(3,3,4,3,3,4,3,2,5,2,3,2,3);
        var course3 = List.of(2,4,5,4,3,3,2,4,6);
        var course4 = List.of(3,3,2,2,3,2,2,3,2,3,2,3);
        var course5 = List.of(2,2,2,2,1,6,2,1,1,3,1,2,5,3,3,2,2,3);
        var course6 = List.of(3,6,6,6,3,2,3,4,6);
        var course7 = List.of(1,4,4,5,1,2,2,2,2,2,3,2,2,2,2,2,3,2);
        var course8 = List.of(2,2,2,2,3,2,4,3,2,2,4,2,2,2,5,3,1,4);
        return List.of(
                course1, course2, course3,
                course4, course5, course6,
                course7, course8);
    }

    private List<List<Integer>> buildScoreTom2019() {
        var course1 = List.of(4,3,3,2,1,2,5,4,2,2,3,1,2,2,3,2,2,2);
        var course2 = List.of(3,3,2,1,6,3,5,3,4);
        var course3 = List.of(2,2,2,2,3,4,3,3,2,3,4,4,4,3,3,2,3,4);
        var course4 = List.of(4,3,3,5,3,2,4,3,2,4,3,2,2,2,2,2,2,2);
        var course5 = List.of(2,2,4,5,2,2,3,3,3,3,2,3,2,1,3,2,3,3);
        var course6 = List.of(3,2,3,3,5,4,2,2,2,2,3,3,4,3,2,3,2,2);
        var course7 = List.of(2,2,2,5,4,2,2,2,3,4,6,2,2,2,4,2,2,2);
        var course8 = List.of(2,3,3,5,4,3,3,2,2,3,3,2,3,3,3,4,3,2);
        var course9 = List.of(2,3,4,3,4,5,2,3,5,2,4,2);
        return List.of(
                course1, course2, course3,
                course4, course5, course6,
                course7, course8, course9);
    }

    private List<List<Integer>> buildScoreTom2020() {
        var course1 = List.of(3,2,1,3,2,2,2,2,3,3,5,3,2,2,3,3,4,2);
        var course2 = List.of(3,4,2,3,3,2,2,1,3,2,4,2,2,1,2,2,3,2);
        var course3 = List.of(4,3,3,3,3,2,4,2,4,2,3,3,3,4,3,4,3,2,2);
        var course4 = List.of(2,2,3,4,2,2,3,2,4,5,2,2,2,1,2,3,2,4);
        var course5 = List.of(2,2,2,2,2,2,2,2,3,2,2,2,2,2,2,2,3,2);
        return List.of(
                course1, course2, course3,
                course4, course5);
    }

    private List<List<Integer>> buildScoreTom2021() {
        var course1 = List.of(4,3,4,3,2,4,3,2,2,2,2,3,2,2,4,3,3,2);
        var course2 = List.of(3,3,4,2,3,3,3,3,1,3,3,6,6,2,3,1,2,3);
        var course3 = List.of(2,2,5,5,6,2,4,4,4,3,2,2,4,2,4,2,4,3);
        var course4 = List.of(2,3,3,2,3,2,2,4,2,3,3,3,2,2,3,1,3,3);
        var course5 = List.of(2,2,2,4,2,2,3,2,2,3,5,2);
        var course6 = List.of(3,4,2,4,5,2,2,2,3,3,2,3,5,2,3,2,3,1);
        return List.of(
                course1, course2, course3,
                course4, course5, course6);
    }

    private List<List<Integer>> buildScoreTom2022() {
        var course1 = List.of(1,3,3,2,2,2,2,6,3,4,3,3,2,2,2,3,1,2);
        var course2 = List.of(1,3,2,5,2,2,2,2,3,3,2,2,2,3,2,3,3,2);
        var course3 = List.of(2,1,2,2,2,2,1,2,3,2,1,2);
        var course4 = List.of(2,2,3,2,2,2,2,3,2,3,2,2,4,2,2,2,2,2);
        var course5 = List.of(3,2,3,2,2,2,3,3,3,3,2,2,3,3,2,3,6,3);
        var course6 = List.of(3,2,3,3,2,3,2,2,5,2,3,2);
        var course7 = List.of(2,3,2,2,4,2,2,2,2,2,1,2,2,2,4,2,2,2);
        return List.of(
                course1, course2, course3,
                course4, course5, course6,
                course7);
    }

    private List<List<Integer>> buildScoreTom2023() {
        var course1 = List.of(3,3,2,2,4,2,3,1,6,4,3,2,3,3,2,3,3,3);
        var course2 = List.of(3,3,2,3,4,3,2,4,3,2,1,4,2,3,3,4,2,6);
        var course3 = List.of(6,2,2,3,1,2,5,1,2,2,4,1);
        var course4 = List.of(2,5,2,3,2,2,2,2,3,2,1,1);
        var course5 = List.of(3,2,3,2,4,3,4,2,4,3,3,5,2,5,2,2,3,4);
        var course6 = List.of(2,2,2,2,2,2,3,2,2,2,3,3,2,2,2,2);
        var course7 = List.of(4,4,2,3,5,2,2,4,2,1,1,3,3,2,2,2,3,5,2);
        var course8 = List.of(2,3,2,2,2,4,2,3,6,2,2,3,1,5,2,2,3,2);
        return List.of(
                course1, course2, course3,
                course4, course5, course6,
                course7, course8);
    }

    private List<List<Integer>> buildScoreTom2024() {
        var course1 = List.of(2,3,2,2,2,3,2,2,3,3,4,2,2,2,4,2,2,2);
        var course2 = List.of(2,2,2,2,2,3,3,2,3,3,4,2,3,2,3,2,3,2);
        var course3 = List.of(6,2,2,1,3,1,2,1,2,3,2,2,2,4,2,1,2,2);
        var course4 = List.of(2,5,4,4,3,2,2,2,5,3,3,3,4,4,3,4,3,4);
        var course5 = List.of(2,3,3,3,2,3,4,3,2,3,2,2,2,2,3,1,2,2);
        var course6 = List.of(1,2,6,4,3,4,2,6,2);
        var course7 = List.of(3,3,1,3,2,2,2,3,2,2,1,1,2,2,2,2,3,4);
        return List.of(
                course1, course2, course3,
                course4, course5, course6,
                course7);
    }

    private List<List<Integer>> buildScoreTom2025() {
        var course1 = List.of(2,3,2,2,2,1,3,3,2,2,3,2,2,1,3,2,3,1);
        var course2 = List.of(4,3,2,3,2,3,6,4,5,5,2,4,5,2,6,2,3,2);
        var course3 = List.of(1,2,3,2,4,3,2,2,3,3,5,3,4,3,3,2,4,4);
        var course4 = List.of(2,2,3,2,1,4,2,2,2,2,3,6,2,2,3,3,2,3);
        var course5 = List.of(3,1,4,4,2,1,2,3,3,3,3,2,2,4,5,3,2,2);
        var course6 = List.of(2,2,3,4,3,3,2,2,2,2,2,4,3,2,3,2,2,2);
        var course7 = List.of(3,3,2,3,3,2,3,6,3,2,2,1,2,2,3,2,2,2);
        var course8 = List.of(2,2,3,2,2,4,3,3,3,3,2,6,3,2,2,2,4,2);
        var course9 = List.of(3,3,5,2,2,4,2,2,3,1,2,2,2,2,2,3,3,2);
        return List.of(
                course1, course2, course3,
                course4, course5, course6,
                course7, course8, course9);
    }

}

package com.golf.two_for_tom_open.dataInitialiser.prod.score;

import com.golf.two_for_tom_open.model.entity.Player;
import com.golf.two_for_tom_open.model.entity.Score;
import com.golf.two_for_tom_open.model.entity.Tournament;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

public class JamieScoreCreator extends ScoreCreator {

    public List<Score> create(Tournament tournament, Player player) {

        List<List<Integer>> scoresForPlayerForYear = switch (tournament.get_year().getValue()) {
            case 2016 -> buildScoreJamie2016();
            case 2017 -> buildScoreJamie2017();
            case 2018 -> buildScoreJamie2018();
            case 2019 -> buildScoreJamie2019();
            case 2020 -> buildScoreJamie2020();
            case 2021 -> buildScoreJamie2021();
            case 2022 -> buildScoreJamie2022();
            case 2023 -> buildScoreJamie2023();
//                case 2024 -> buildScoreJamie2024(tournament, player);
            default -> Collections.emptyList();
        };

        return scoresForPlayerForYear.isEmpty() ?
                emptyList() :
                createScores(scoresForPlayerForYear, tournament, player);
    }

    private List<List<Integer>> buildScoreJamie2016(){
        var course1 = List.of(3,2,3,3,3,3,4,3,4,4,2,2,4,2,4,2,2,2);//52
        var course2 = List.of(4,2,2,2,2,2,3,2,5,3,3,4,2,3,3,1,1,1);//45
        var course3 = List.of(1,3,3,1,3,2,2,3,2,3,3,2,2,2,2,1,4,2);
        var course4 = List.of(3,2,4,2,4,2,3,3,4,3,3,3,2,1,2,5,3,2);
        var course5 = List.of(2,3,4,3,3,2,2,2,2,2,3,3,3,3,3,3,2,3);
        return Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5).toList();
    }

    private List<List<Integer>> buildScoreJamie2017(){
        var course1 = List.of(7,2,2,2,3,3,2,2,4,3,1,4,2,4,3,3,7,1);
        var course2 = List.of(3,2,2,3,3,2,3,3,2,4,3,2,3,5,4,2,3,6);
        var course3 = List.of(2,2,3,5,2,2,2,2,4);
        var course4 = List.of(2,2,2,2,2,2,2,4,3,3,7,2,2,4,2,2,2,3);
        var course5 = List.of(3,2,3,3,3,5,2,2,3,2,3,2,4,2,3,4,2,3);
        var course6 = List.of(5,2,2,5,5,2,2,2,3,4,6,3,3,3,3,4,2,2);
        var course7 = List.of(2,2,3,2,2,3,7,7,4,2,3,4,2,2,3,2,3,2);
        return Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6,
                course7).toList();
    }

    private List<List<Integer>> buildScoreJamie2018(){
        var course1 = List.of(2,4,2,3,3,1,3,2,2,2,3,4);
        var course2 = List.of(4,3,4,3,3,6,3,2,5,2,3,5,3);
        var course3 = List.of(2,5,3,4,3,4,2,3,5);
        var course4 = List.of(3,5,2,2,2,3,2,2,2,2,2,2);
        var course5 = List.of(2,2,2,2,2,3,2,1,6,3,1,1,3,4,3,2,2,5);
        var course6 = List.of(3,2,3,6,3,4,3,3,6);
        var course7 = List.of(4,2,1,3,2,2,3,2,2,2,2,3,2,2,2,3,3,2);
        var course8 = List.of(3,2,4,2,2,1,2,2,3,5,2,3,3,3,4,4,3,3);
        return Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6,
                course7,
                course8).toList();
    }

    private List<List<Integer>> buildScoreJamie2019(){
        var course1 = List.of(3,3,3,2,2,1,3,3,3,2,2,2,6,2,3,2,2,6);
        var course2 = List.of(3,6,3,1,3,2,3,2,3);
        var course3 = List.of(3,3,2,2,3,3,2,2,2,3,3,2,3,3,2,3,2,2);
        var course4 = List.of(2,2,2,3,2,3,3,3,2,3,3,2,3,2,2,2,2,2);
        var course5 = List.of(2,3,4,3,1,3,1,4,2,2,2,2,2,1,3,3,2,5);
        var course6 = List.of(3,3,2,3,2,2,3,3,2,3,2,2,4,2,2,4,6,2);
        var course7 = List.of(3,3,2,2,3,3,2,3,4,4,5,2,3,2,3,2,5,3);
        var course8 = List.of(3,2,5,3,3,3,3,2,2,4,2,2,2,3,3,3,3,3);
        var course9 = List.of(2,2,3,3,5,3,4,3,4,2,4,5);
        return Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6,
                course7,
                course8,
                course9).toList();
    }

    private List<List<Integer>> buildScoreJamie2020() {
        var course1 = List.of(2,3,2,2,4,4,2,3,2,3,6,3,4,3,2,3,3,2);
        var course2 = List.of(3,4,3,3,2,2,3,1,3,4,3,2,2,1,2,2,3,2);
        var course3 = List.of(3,4,3,3,2,6,4,3,6,4,3,3,2,4,2,1,2,2,2);
        var course4 = List.of(2,3,2,2,2,3,2,2,4,3,3,2,2,1,3,2,3,2);
        var course5 = List.of(2,2,4,2,3,1,2,5,2,2,2,2,3,2,3,3,2,2);
        return Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5).toList();
    }

    private List<List<Integer>> buildScoreJamie2021() {
        var course1 = List.of(2,2,2,2,2,3,4,2,3,2,2,3,2,2,2,2,2,1);
        var course2 = List.of(3,4,3,5,6,6,2,6,2,2,3,3,3,4,2,6,4,6);
        var course3 = List.of(2,3,4,2,2,2,6,3,4,2,6,2,3,4,3,2,3,2);
        var course4 = List.of(2,2,3,2,3,3,2,2,2,3,2,2,2,1,2,2,2,3);
        var course5 = List.of(4,3,3,2,1,2,5,2,6,3,2,2);
        var course6 = List.of(2,4,2,2,1,2,2,3,3,4,4,3,6,4,2,4,2,1);
        return Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6).toList();
    }

    private List<List<Integer>> buildScoreJamie2022() {
        var course1 = List.of(2,3,4,2,3,3,5,2,2,3,1,3,2,4,2,2,5,2);
        var course2 = List.of(2,3,3,3,4,2,3,3,2,3,2,2,2,2,3,2,2,2);
        var course3 = List.of(3,1,2,1,2,2,3,2,3,2,2,1);
        var course4 = List.of(2,2,3,4,2,3,2,2,2,3,4,2,2,2,2,3,3,2);
        var course5 = List.of(2,3,2,2,3,2,1,3,2,2,2,2,3,2,2,2,5,4);
        var course6 = List.of(3,2,3,3,4,3,2,2,3,3,2,1);
        var course7 = List.of(2,3,3,3,2,2,2,2,2,4,3,2,3,2,1,2,2,2);
        return Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6,
                course7).toList();
    }

    private List<List<Integer>> buildScoreJamie2023() {
        var course1 = List.of(3,3,3,4,3,2,2,3,4,3,2,2,2,2,2,5,3,2);
        var course2 = List.of(3,3,2,2,3,2,2,4,3,2,2,3,2,3,2,4,2,3);
        var course3 = List.of(4,3,3,2,4,5,4,1,2,3,3,2);
        var course4 = List.of(2,5,2,2,3,2,3,2,3,2,2,3);
        var course5 = List.of(2,2,4,3,2,2,3,2,3,3,2,4,2,4,3,2,4,2);
        var course6 = List.of(2,2,3,2,3,2,3,3,2,2,2,1,2,2,4,2);
        var course7 = List.of(2,3,4,4,5,2,2,3,4,2,2,3,3,2,3,3,2,4,2);
        var course8 = List.of(2,2,2,2,2,2,2,3,4,5,2,2,2,3,2,2,3,6);
        return Stream.of(
                course1, course2, course3,
                course4, course5, course6,
                course7, course8).toList();
    }

}

package com.golf.two_for_tom_open.dataInitialiser;

import com.golf.two_for_tom_open.dataInitialiser.prod.CourseCreator;
import com.golf.two_for_tom_open.model.entity.*;
import com.golf.two_for_tom_open.service.CourseService;
import com.golf.two_for_tom_open.service.PlayerService;
import com.golf.two_for_tom_open.service.ScoreService;
import com.golf.two_for_tom_open.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@Profile("dev")
public class DataInitialiserProd implements CommandLineRunner {

    private final PlayerService playerService;
    private final CourseService courseService;
    private final ScoreService scoreService;
    private final TournamentService tournamentService;

    private final CourseCreator courseCreator;

    private Player Rhys;
    private Player Tom;
    private Player Jamie;
    private Player Jade;

    private List<Course> courses_2016;
    private List<Course> courses_2017;
    private List<Course> courses_2018;
    private List<Course> courses_2019;
    private List<Course> courses_2020;
    private List<Course> courses_2021;
    private List<Course> courses_2022;
    private List<Course> courses_2023;
    private List<Course> courses_2024;

    private Tournament tournament2016;
    private Tournament tournament2017;
    private Tournament tournament2018;
    private Tournament tournament2019;
    private Tournament tournament2020;
    private Tournament tournament2021;
    private Tournament tournament2022;
    private Tournament tournament2023;
    private Tournament tournament2024;

    private static final Map<Year, List<Course>> COURSES_BY_YEAR;

    static {
        COURSES_BY_YEAR = Map.of(
                Year.of(2016), new ArrayList<>(),
                Year.of(2017), new ArrayList<>(),
                Year.of(2018), new ArrayList<>(),
                Year.of(2019), new ArrayList<>(),
                Year.of(2020), new ArrayList<>(),
                Year.of(2021), new ArrayList<>(),
                Year.of(2022), new ArrayList<>(),
                Year.of(2023), new ArrayList<>(),
                Year.of(2024), new ArrayList<>());
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            if (CollectionUtils.isEmpty(playerService.getAll())) {
                loadData();
            }
        } catch (Exception e) {
            loadData();
        }

    }

    private void loadData() {

        // +++++ Players +++++
        createPlayers();

        // +++++ Courses +++++
        createCourses();

        // +++++ Tournaments +++++
        createTournaments();

        // +++++ Scores +++++

        //Rhys's scores
        buildScoreRhys2016();
        buildScoreRhys2017();
        buildScoreRhys2018();
        buildScoreRhys2019();
        buildScoreRhys2020();
        buildScoreRhys2021();
        buildScoreRhys2022();
        buildScoreRhys2023();
        //Jamie's scores
        buildScoreJamie2016();
        buildScoreJamie2017();
        buildScoreJamie2018();
        buildScoreJamie2019();
        buildScoreJamie2020();
        buildScoreJamie2021();
        buildScoreJamie2022();
        buildScoreJamie2023();
        //Tom's scores
        buildScoreTom2016();
        buildScoreTom2017();
        buildScoreTom2018();
        buildScoreTom2019();
        buildScoreTom2020();
        buildScoreTom2021();
        buildScoreTom2022();
        buildScoreTom2023();
        //Jade's scores
        buildScoreJade2020();

    }

    // ++++++++++++++++ PLAYERS ++++++++++++++++
    private void buildScoreRhys2016(){
        var course1 = List.of(2,4,5,5,4,5,2,2,5,6,4,5,5,2,6,2,6,1);//71
        var course2 = List.of(3,2,2,3,3,3,4,2,2,5,2,2,2,2,3,5,2,2);//49
        var course3 = List.of(4,3,3,3,4,2,5,3,2,2,5,3,2,3,3,1,3,2);
        var course4 = List.of(2,2,4,6,2,2,3,3,2,5,2,3,3,2,3,3,2,1);
        var course5 = List.of(2,3,2,4,6,2,5,2,3,2,2,2,5,3,2,3,2,4);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5).toList();
        createScores(total, tournament2016, Rhys);
    }

    private void buildScoreRhys2017() {
        var course1 = List.of(4,6,5,2,5,4,5,2,2,5,2,2,3,2,1,3,7,5);
        var course2 = List.of(3,2,4,2,3,3,2,2,2,2,3,3,3,2,5,2,3,3);
        var course3 = List.of(2,2,4,5,3,4,2,2,4);
        var course4 = List.of(2,2,2,2,3,3,3,3,2,3,3,2,3,5,2,2,2,7);
        var course5 = List.of(3,2,2,3,3,4,2,2,3,3,2,2,3,4,2,2,2,4);
        var course6 = List.of(3,2,2,2,4,3,3,2,3,3,4,2,2,3,2,3,2,3);
        var course7 = List.of(2,4,4,2,3,7,7,4,3,2,3,5,4,2,3,2,3,2);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6,
                course7).toList();
        createScores(total, tournament2017, Rhys);
    }

    private void buildScoreRhys2018() {
        var course1 = List.of(6,2,2,6,6,2,4,5,4,2,2,2);
        var course2 = List.of(5,4,3,2,4,6,3,3,6,3,6,6,6);
        var course3 = List.of(4,5,4,2,2,2,2,2,5);
        var course4 = List.of(3,2,3,2,2,3,2,2,4,1,2,3);
        var course5 = List.of(3,2,2,3,2,3,2,2,3,3,1,2,3,2,3,4,3,2);
        var course6 = List.of(4,2,3,2,4,6,2,3,6);
        var course7 = List.of(6,1,1,6,2,2,3,2,2,2,2,5,2,3,4,3,2,4);
        var course8 = List.of(2,3,2,3,2,2,3,2,3,2,1,2,1,1,4,2,2,5);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6,
                course7,
                course8).toList();
        createScores(total, tournament2018, Rhys);
    }

    private void buildScoreRhys2019(){
        var course1 = List.of(2,2,2,4,1,1,3,3,4,2,3,2,2,2,3,3,2,3);
        var course2 = List.of(3,6,3,3,5,3,2,3,4);
        var course3 = List.of(2,3,3,2,3,3,3,3,3,4,2,4,4,3,2,2,3,3);
        var course4 = List.of(3,3,2,3,3,2,4,3,1,3,2,2,2,4,2,3,2,3);
        var course5 = List.of(2,3,5,3,4,3,2,3,3,2,3,4,3,3,4,2,3,3);
        var course6 = List.of(3,2,2,4,5,2,2,2,4,1,2,2,2,2,2,3,2,2);
        var course7 = List.of(1,3,4,2,3,2,2,2,4,2,6,2,3,2,3,2,4,3);
        var course8 = List.of(2,3,2,2,2,4,1,2,2,6,2,3,2,3,2,2,2,2);
        var course9 = List.of(2,6,3,5,2,3,2,3,2,2,4,2);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6,
                course7,
                course8,
                course9).toList();
        createScores(total, tournament2019, Rhys);
    }

    private void buildScoreRhys2020() {
        var course1 = List.of(4,2,3,4,2,2,3,3,3,3,4,3,6,3,2,3,6,2);
        var course2 = List.of(3,4,2,1,2,3,3,2,4,2,4,3,2,1,3,1,2,2);
        var course3 = List.of(2,3,3,5,2,2,2,3,6,2,5,3,2,4,2,3,3,3,1);
        var course4 = List.of(2,2,3,2,3,2,2,2,2,2,2,2,3,2,2,2,2,3);
        var course5 = List.of(2,2,2,2,2,5,2,2,3,3,4,3,3,2,3,2,3,2);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5).toList();
        createScores(total, tournament2020, Rhys);
    }

    private void buildScoreRhys2021() {
        var course1 = List.of(2,2,2,1,2,3,4,2,2,2,2,3,2,2,2,3,3,3);
        var course2 = List.of(3,2,3,2,3,2,2,3,1,2,3,3,2,2,2,3,3,3);
        var course3 = List.of(2,2,2,2,4,3,6,6,5,2,3,3,3,2,2,6,2,4);
        var course4 = List.of(2,2,3,3,2,4,3,3,2,2,3,2,3,2,2,2,2,3);
        var course5 = List.of(3,2,2,5,2,2,1,2,2,5,3,1);
        var course6 = List.of(2,5,4,2,2,2,2,2,5,3,3,4,4,4,3,3,4,1);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6).toList();
        createScores(total, tournament2021, Rhys);
    }

    private void buildScoreRhys2022() {
        var course1 = List.of(3,2,3,2,2,3,3,2,3,3,3,1,3,3,2,3,3,2);
        var course2 = List.of(3,3,3,1,3,3,1,3,2,2,2,4,2,3,3,2,2,2);
        var course3 = List.of(2,2,2,2,4,2,1,3,6,2,1,2);
        var course4 = List.of(2,2,4,2,2,2,3,2,2,3,2,3,4,2,3,3,2,2);
        var course5 = List.of(2,3,1,2,3,3,2,2,3,2,2,3,2,3,3,2,1,3);
        var course6 = List.of(2,2,2,2,2,3,3,2,4,3,2,2);
        var course7 = List.of(2,4,2,3,2,1,2,2,2,3,2,5,2,2,1,3,2,4);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6,
                course7).toList();
        createScores(total, tournament2022, Rhys);
    }

    private void buildScoreRhys2023() {
        var course1 = List.of(2,2,3,3,3,2,5,3,4,2,2,2,2,4,3,4,3,2);
        var course2 = List.of(2,4,2,4,3,4,2,3,3,2,3,2,2,2,2,4,2,4);
        var course3 = List.of(5,3,2,2,3,3,4,3,2,3,3,1);
        var course4 = List.of(3,3,2,4,2,2,1,2,2,2,2,2);
        var course5 = List.of(2,5,2,6,2,3,4,4,3,3,3,4,3,3,4,2,6,2);
        var course6 = List.of(3,2,3,2,2,2,4,3,2,2,2,2,2,2,2,5);
        var course7 = List.of(3,4,3,3,3,2,2,3,6,2,2,2,2,4,2,2,3,6,1);
        var course8 = List.of(2,1,3,4,2,2,2,4,2,3,2,3,2,3,2,4,3,4);
        List<List<Integer>> total = Stream.of(
                course1, course2, course3,
                course4, course5, course6,
                course7, course8).toList();
        createScores(total, tournament2023, Rhys);
    }

    private void buildScoreJamie2016(){
        var course1 = List.of(3,2,3,3,3,3,4,3,4,4,2,2,4,2,4,2,2,2);//52
        var course2 = List.of(4,2,2,2,2,2,3,2,5,3,3,4,2,3,3,1,1,1);//45
        var course3 = List.of(1,3,3,1,3,2,2,3,2,3,3,2,2,2,2,1,4,2);
        var course4 = List.of(3,2,4,2,4,2,3,3,4,3,3,3,2,1,2,5,3,2);
        var course5 = List.of(2,3,4,3,3,2,2,2,2,2,3,3,3,3,3,3,2,3);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5).toList();
        createScores(total, tournament2016, Jamie);
    }

    private void buildScoreJamie2017(){
        var course1 = List.of(7,2,2,2,3,3,2,2,4,3,1,4,2,4,3,3,7,1);
        var course2 = List.of(3,2,2,3,3,2,3,3,2,4,3,2,3,5,4,2,3,6);
        var course3 = List.of(2,2,3,5,2,2,2,2,4);
        var course4 = List.of(2,2,2,2,2,2,2,4,3,3,7,2,2,4,2,2,2,3);
        var course5 = List.of(3,2,3,3,3,5,2,2,3,2,3,2,4,2,3,4,2,3);
        var course6 = List.of(5,2,2,5,5,2,2,2,3,4,6,3,3,3,3,4,2,2);
        var course7 = List.of(2,2,3,2,2,3,7,7,4,2,3,4,2,2,3,2,3,2);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6,
                course7).toList();
        createScores(total, tournament2017, Jamie);
    }

    private void buildScoreJamie2018(){
        var course1 = List.of(2,4,2,3,3,1,3,2,2,2,3,4);
        var course2 = List.of(4,3,4,3,3,6,3,2,5,2,3,5,3);
        var course3 = List.of(2,5,3,4,3,4,2,3,5);
        var course4 = List.of(3,5,2,2,2,3,2,2,2,2,2,2);
        var course5 = List.of(2,2,2,2,2,3,2,1,6,3,1,1,3,4,3,2,2,5);
        var course6 = List.of(3,2,3,6,3,4,3,3,6);
        var course7 = List.of(4,2,1,3,2,2,3,2,2,2,2,3,2,2,2,3,3,2);
        var course8 = List.of(3,2,4,2,2,1,2,2,3,5,2,3,3,3,4,4,3,3);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6,
                course7,
                course8).toList();
        createScores(total, tournament2018, Jamie);
    }

    private void buildScoreJamie2019(){
        var course1 = List.of(3,3,3,2,2,1,3,3,3,2,2,2,6,2,3,2,2,6);
        var course2 = List.of(3,6,3,1,3,2,3,2,3);
        var course3 = List.of(3,3,2,2,3,3,2,2,2,3,3,2,3,3,2,3,2,2);
        var course4 = List.of(2,2,2,3,2,3,3,3,2,3,3,2,3,2,2,2,2,2);
        var course5 = List.of(2,3,4,3,1,3,1,4,2,2,2,2,2,1,3,3,2,5);
        var course6 = List.of(3,3,2,3,2,2,3,3,2,3,2,2,4,2,2,4,6,2);
        var course7 = List.of(3,3,2,2,3,3,2,3,4,4,5,2,3,2,3,2,5,3);
        var course8 = List.of(3,2,5,3,3,3,3,2,2,4,2,2,2,3,3,3,3,3);
        var course9 = List.of(2,2,3,3,5,3,4,3,4,2,4,5);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6,
                course7,
                course8,
                course9).toList();
        createScores(total, tournament2019, Jamie);
    }

    private void buildScoreJamie2020() {
        var course1 = List.of(2,3,2,2,4,4,2,3,2,3,6,3,4,3,2,3,3,2);
        var course2 = List.of(3,4,3,3,2,2,3,1,3,4,3,2,2,1,2,2,3,2);
        var course3 = List.of(3,4,3,3,2,6,4,3,6,4,3,3,2,4,2,1,2,2,2);
        var course4 = List.of(2,3,2,2,2,3,2,2,4,3,3,2,2,1,3,2,3,2);
        var course5 = List.of(2,2,4,2,3,1,2,5,2,2,2,2,3,2,3,3,2,2);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5).toList();
        createScores(total, tournament2020, Jamie);
    }

    private void buildScoreJamie2021() {
        var course1 = List.of(2,2,2,2,2,3,4,2,3,2,2,3,2,2,2,2,2,1);
        var course2 = List.of(3,4,3,5,6,6,2,6,2,2,3,3,3,4,2,6,4,6);
        var course3 = List.of(2,3,4,2,2,2,6,3,4,2,6,2,3,4,3,2,3,2);
        var course4 = List.of(2,2,3,2,3,3,2,2,2,3,2,2,2,1,2,2,2,3);
        var course5 = List.of(4,3,3,2,1,2,5,2,6,3,2,2);
        var course6 = List.of(2,4,2,2,1,2,2,3,3,4,4,3,6,4,2,4,2,1);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6).toList();
        createScores(total, tournament2021,Jamie);
    }

    private void buildScoreJamie2022() {
        var course1 = List.of(2,3,4,2,3,3,5,2,2,3,1,3,2,4,2,2,5,2);
        var course2 = List.of(2,3,3,3,4,2,3,3,2,3,2,2,2,2,3,2,2,2);
        var course3 = List.of(3,1,2,1,2,2,3,2,3,2,2,1);
        var course4 = List.of(2,2,3,4,2,3,2,2,2,3,4,2,2,2,2,3,3,2);
        var course5 = List.of(2,3,2,2,3,2,1,3,2,2,2,2,3,2,2,2,5,4);
        var course6 = List.of(3,2,3,3,4,3,2,2,3,3,2,1);
        var course7 = List.of(2,3,3,3,2,2,2,2,2,4,3,2,3,2,1,2,2,2);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6,
                course7).toList();
        createScores(total, tournament2022,Jamie);
    }

    private void buildScoreJamie2023() {
        var course1 = List.of(3,3,3,4,3,2,2,3,4,3,2,2,2,2,2,5,3,2);
        var course2 = List.of(3,3,2,2,3,2,2,4,3,2,2,3,2,3,2,4,2,3);
        var course3 = List.of(4,3,3,2,4,5,4,1,2,3,3,2);
        var course4 = List.of(2,5,2,2,3,2,3,2,3,2,2,3);
        var course5 = List.of(2,2,4,3,2,2,3,2,3,3,2,4,2,4,3,2,4,2);
        var course6 = List.of(2,2,3,2,3,2,3,3,2,2,2,1,2,2,4,2);
        var course7 = List.of(2,3,4,4,5,2,2,3,4,2,2,3,3,2,3,3,2,4,2);
        var course8 = List.of(2,2,2,2,2,2,2,3,4,5,2,2,2,3,2,2,3,6);
        List<List<Integer>> total = Stream.of(
                course1, course2, course3,
                course4, course5, course6,
                course7, course8).toList();
        createScores(total, tournament2023, Jamie);
    }

    private void buildScoreTom2016(){
        var course1 = List.of(5,2,3,3,2,3,3,2,3,4,3,2,6,6,4,6,3,6); //66
        var course2 = List.of(3,1,2,2,2,3,2,3,3,2,3,2,3,2,2,4,2,3);//44
        var course3 = List.of(2,4,4,2,3,2,2,4,1,3,3,2,3,2,1,2,3,4);
        var course4 = List.of(2,3,3,4,4,2,2,4,3,3,2,6,3,3,2,4,3,4);
        var course5 = List.of(2,2,3,3,4,4,2,2,2,3,2,3,3,6,2,2,2,2);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5).toList();
        createScores(total, tournament2016, Tom);
    }

    private void buildScoreTom2017(){
        var course1 = List.of(7,3,3,2,7,4,3,4,4,3,3,2,4,3,3,3,4,3);
        var course2 = List.of(3,3,3,3,2,2,3,2,2,2,3,2,3,3,3,4,2,5);
        var course3 = List.of(2,2,4,5,2,3,2,2,4);
        var course4 = List.of(4,3,2,2,5,2,3,4,3,3,5,2,2,4,2,2,3,7);
        var course5 = List.of(3,4,4,2,3,3,3,2,3,3,3,3,2,2,3,4,3,2);
        var course6 = List.of(2,2,3,3,3,3,2,3,3,2,5,2,3,3,4,3,3,2);
        var course7 = List.of(4,2,4,2,2,2,7,2,3,2,2,4,2,2,2,2,7,3);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6,
                course7).toList();
        createScores(total, tournament2017, Tom);
    }

    private void buildScoreTom2018(){
        var course1 = List.of(3,4,2,4,4,1,4,4,2,2,2,6);
        var course2 = List.of(3,3,4,3,3,4,3,2,5,2,3,2,3);
        var course3 = List.of(2,4,5,4,3,3,2,4,6);
        var course4 = List.of(3,3,2,2,3,2,2,3,2,3,2,3);
        var course5 = List.of(2,2,2,2,1,6,2,1,1,3,1,2,5,3,3,2,2,3);
        var course6 = List.of(3,6,6,6,3,2,3,4,6);
        var course7 = List.of(1,4,4,5,1,2,2,2,2,2,3,2,2,2,2,2,3,2);
        var course8 = List.of(2,2,2,2,3,2,4,3,2,2,4,2,2,2,5,3,1,4);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6,
                course7,
                course8).toList();
        createScores(total, tournament2018, Tom);
    }

    private void buildScoreTom2019(){
        var course1 = List.of(4,3,3,2,1,2,5,4,2,2,3,1,2,2,3,2,2,2);
        var course2 = List.of(3,3,2,1,6,3,5,3,4);
        var course3 = List.of(2,2,2,2,3,4,3,3,2,3,4,4,4,3,3,2,3,4);
        var course4 = List.of(4,3,3,5,3,2,4,3,2,4,3,2,2,2,2,2,2,2);
        var course5 = List.of(2,2,4,5,2,2,3,3,3,3,2,3,2,1,3,2,3,3);
        var course6 = List.of(3,2,3,3,5,4,2,2,2,2,3,3,4,3,2,3,2,2);
        var course7 = List.of(2,2,2,5,4,2,2,2,3,4,6,2,2,2,4,2,2,2);
        var course8 = List.of(2,3,3,5,4,3,3,2,2,3,3,2,3,3,3,4,3,2);
        var course9 = List.of(2,3,4,3,4,5,2,3,5,2,4,2);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6,
                course7,
                course8,
                course9).toList();
        createScores(total, tournament2019, Tom);
    }

    private void buildScoreTom2020() {
        var course1 = List.of(3,2,1,3,2,2,2,2,3,3,5,3,2,2,3,3,4,2);
        var course2 = List.of(3,4,2,3,3,2,2,1,3,2,4,2,2,1,2,2,3,2);
        var course3 = List.of(4,3,3,3,3,2,4,2,4,2,3,3,3,4,3,4,3,2,2);
        var course4 = List.of(2,2,3,4,2,2,3,2,4,5,2,2,2,1,2,3,2,4);
        var course5 = List.of(2,2,2,2,2,2,2,2,3,2,2,2,2,2,2,2,3,2);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5).toList();
        createScores(total, tournament2020, Tom);
    }

    private void buildScoreTom2021() {
        var course1 = List.of(4,3,4,3,2,4,3,2,2,2,2,3,2,2,4,3,3,2);
        var course2 = List.of(3,3,4,2,3,3,3,3,1,3,3,6,6,2,3,1,2,3);
        var course3 = List.of(2,2,5,5,6,2,4,4,4,3,2,2,4,2,4,2,4,3);
        var course4 = List.of(2,3,3,2,3,2,2,4,2,3,3,3,2,2,3,1,3,3);
        var course5 = List.of(2,2,2,4,2,2,3,2,2,3,5,2);
        var course6 = List.of(3,4,2,4,5,2,2,2,3,3,2,3,5,2,3,2,3,1);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6).toList();
        createScores(total, tournament2021, Tom);
    }

    private void buildScoreTom2022() {
        var course1 = List.of(1,3,3,2,2,2,2,6,3,4,3,3,2,2,2,3,1,2);
        var course2 = List.of(1,3,2,5,2,2,2,2,3,3,2,2,2,3,2,3,3,2);
        var course3 = List.of(2,1,2,2,2,2,1,2,3,2,1,2);
        var course4 = List.of(2,2,3,2,2,2,2,3,2,3,2,2,4,2,2,2,2,2);
        var course5 = List.of(3,2,3,2,2,2,3,3,3,3,2,2,3,3,2,3,6,3);
        var course6 = List.of(3,2,3,3,2,3,2,2,5,2,3,2);
        var course7 = List.of(2,3,2,2,4,2,2,2,2,2,1,2,2,2,4,2,2,2);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6,
                course7).toList();
        createScores(total, tournament2022, Tom);
    }

    private void buildScoreTom2023() {
        var course1 = List.of(3,3,2,2,4,2,3,1,6,4,3,2,3,3,2,3,3,3);
        var course2 = List.of(3,3,2,3,4,3,2,4,3,2,1,4,2,3,3,4,2,6);
        var course3 = List.of(6,2,2,3,1,2,5,1,2,2,4,1);
        var course4 = List.of(2,5,2,3,2,2,2,2,3,2,1,1);
        var course5 = List.of(3,2,3,2,4,3,4,2,4,3,3,5,2,5,2,2,3,4);
        var course6 = List.of(2,2,2,2,2,2,3,2,2,2,3,3,2,2,2,2);
        var course7 = List.of(4,4,2,3,5,2,2,4,2,1,1,3,3,2,2,2,3,5,2);
        var course8 = List.of(2,3,2,2,2,4,2,3,6,2,2,3,1,5,2,2,3,2);
        List<List<Integer>> total = Stream.of(
                course1, course2, course3,
                course4, course5, course6,
                course7, course8).toList();
        createScores(total, tournament2023, Tom);
    }

    private void buildScoreJade2020() {
        var course1 = List.of(5,4,5,3,4,2,3,2,2,3,6,3,5,3,5,6,3,2);
        var course2 = List.of(2,5,2,4,2,3,3,2,4,2,4,5,1,2,5,3,2,4);
        var course3 = List.of(3,6,6,2,4,6,4,2,6,3,6,3,4,6,5,4,2,2,2);
        var course4 = List.of(3,2,4,3,2,3,2,2,2,2,4,2,2,2,2,3,3,4);
        var course5 = List.of(3,3,3,3,3,2,3,2,2,3,2,3,2,4,4,3,2,4);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5).toList();
        createScores(total, tournament2020, Jade);
    }

    private void createScores(List<List<Integer>> strokesPerCourse, Tournament tournament, Player player) {
        int countOfTournamentHoles = tournament.getCourses().stream()
                .mapToInt(course -> course.getHoles().size())
                .sum();
        int countOfHolesTaken = strokesPerCourse.stream()
                .map(List::size)
                .mapToInt(Integer::intValue)
                .sum();
        if (countOfHolesTaken != countOfTournamentHoles) {
            throw new RuntimeException("The size of the strokes list (" + countOfHolesTaken +
                    ") does not match the number of holes (" +
                    countOfTournamentHoles + ")");
        }
        List<Score> scores = new ArrayList<>();

        for (int i = 0; i < tournament.getCourses().size(); i++) {
            Course course = tournament.getCourses().get(i);
            List<Integer> strokesTakenForCourse = strokesPerCourse.get(i);

            for (int x = 0; x < course.getHoles().size(); x++) {
                Hole hole = course.getHoles().get(x);
                Integer strokesTakenForHole = strokesTakenForCourse.get(x);
                scores.add(createScore(strokesTakenForHole, tournament, hole, player));
            }
        }

        saveScores(scores);
    }

    private Score createScore(int strokes, Tournament tournament, Hole hole, Player player) {
        return Score.builder()
                .strokes(strokes)
                .player(player)
                .tournament(tournament)
                .hole(hole)
                .build();
    }

    private void saveScores(List<Score> scores) {
        for (Score score : scores) {
            scoreService.save(score);
        }
    }

    // ++++++++++++++++ PLAYERS ++++++++++++++++
    private void createPlayers() {
        Rhys = Player.builder().firstName("Rhys").lastName("Jones").build();
        Tom = Player.builder().firstName("Thomas").lastName("Millican").build();
        Jamie = Player.builder().firstName("Jamie").lastName("Acres").build();
        Jade = Player.builder().firstName("Jade").lastName("Richmond").build();
        savePlayers(List.of(Rhys, Tom, Jamie, Jade));
    }

    private void savePlayers(List<Player> players) {
        for (Player player : players) {
            playerService.save(player);
        }
    }

    // ++++++++++++++++ COURSES ++++++++++++++++
    private void createCourses() {
        COURSES_BY_YEAR
                .entrySet()
                .stream()
                .filter(entry -> !isCourseListAlreadyPopulated(entry.getValue()))
                .map(entry -> courseCreator.create(entry.getKey()))
                .flatMap(List::stream)
                .forEach(courseService::save);
    }

    private boolean isCourseListAlreadyPopulated(List<Course> courseList) {
        return ObjectUtils.isNotEmpty(courseList);
    }

    // ++++++++++++++++ TOURNAMENTS ++++++++++++++++
    private void createTournaments() {
        COURSES_BY_YEAR
                .entrySet()
                .stream()
                .map(entry -> Tournament.builder()
                        ._year(entry.getKey())
                        .courses(entry.getValue())
                        .players(List.of(Rhys, Tom, Jamie))
                        .build())
                .map(tournament -> {
                    if (tournament.get_year().equals(Year.of(2020))) {
                        tournament.getPlayers().add(Jade);
                    }
                    return tournament;
                })
                .forEach(tournamentService::save);

    }

}

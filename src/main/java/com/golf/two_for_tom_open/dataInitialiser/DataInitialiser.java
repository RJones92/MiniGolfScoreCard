package com.golf.two_for_tom_open.dataInitialiser;

import com.golf.two_for_tom_open.model.entity.*;
import com.golf.two_for_tom_open.service.CourseService;
import com.golf.two_for_tom_open.service.PlayerService;
import com.golf.two_for_tom_open.service.ScoreService;
import com.golf.two_for_tom_open.service.TournamentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitialiser implements CommandLineRunner {
    private static final String COURSE_A = "Course A";
    private static final String COURSE_B = "Course B";

    private final PlayerService playerService;
    private final CourseService courseService;
    private final ScoreService scoreService;
    private final TournamentService tournamentService;

    Player playerA;
    Player playerB;

    Course courseA;
    Course courseB;

    Tournament tournament2015;
    Tournament tournament2016;

    public DataInitialiser(PlayerService playerService, CourseService courseService,
                           ScoreService scoreService, TournamentService tournamentService) {
        this.playerService = playerService;
        this.courseService = courseService;
        this.scoreService = scoreService;
        this.tournamentService = tournamentService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {

        // +++++ Players +++++
        List<Player> players = createPlayers();
        savePlayers(players);

        // +++++ Courses +++++
        List<Course> courses = createCourses();
        saveCourses(courses);

        // +++++ Tournaments +++++
        tournament2015 = Tournament.builder()
                .year(Year.of(2015))
                .courses(courses)
                .players(players)
                .build();
        tournamentService.save(tournament2015);

        tournament2016 = Tournament.builder()
                .year(Year.of(2016))
                .courses(courses)
                .players(players)
                .build();
        tournamentService.save(tournament2016);

        // +++++ Scores +++++
        Hole courseAHole1 = courseA.getHoleByCourseHoleNumber(1).orElse(null);
        Hole courseAHole2 = courseA.getHoleByCourseHoleNumber(2).orElse(null);

        Hole courseBHole1 = courseB.getHoleByCourseHoleNumber(1).orElse(null);
        Hole courseBHole2 = courseB.getHoleByCourseHoleNumber(2).orElse(null);

        //Player A's scores
        scoreService.save(createScore(3, tournament2015, courseAHole1, playerA));
        scoreService.save(createScore(3, tournament2015, courseAHole2, playerA));
        scoreService.save(createScore(1, tournament2015, courseBHole1, playerA));
        scoreService.save(createScore(8, tournament2015, courseBHole2, playerA));

        //Player B's scores
        scoreService.save(createScore(6, tournament2015, courseAHole1, playerB));
        scoreService.save(createScore(2, tournament2015, courseAHole2, playerB));
        scoreService.save(createScore(11, tournament2015, courseBHole1, playerB));
        scoreService.save(createScore(12, tournament2015, courseBHole2, playerB));

    }

    private Score createScore(int strokes, Tournament tournament, Hole hole, Player player) {
        Score score = Score.builder()
                .strokes(strokes)
                .player(player)
                .tournament(tournament)
                .hole(hole)
                .build();
        return score;
    }

    private List<Player> createPlayers() {
        playerA = Player.builder().firstName("Player").lastName("A").build();
        playerB = Player.builder().firstName("Player").lastName("B").build();
        return Arrays.asList(playerA, playerB);
    }

    private void savePlayers(List<Player> players) {
        for (Player player : players) {
            playerService.save(player);
        }
    }

    private List<Course> createCourses() {
        courseA = createCourse(COURSE_A, createHolesForCourseA());
        courseB = createCourse(COURSE_B, createHolesForCourseB());
        return Arrays.asList(courseA, courseB);
    }

    private List<Hole> createHolesForCourseA() {
        return Arrays.asList(
                Hole.builder().holeNumber(1).par(3).build(),
                Hole.builder().holeNumber(2).par(2).build()
        );
    }

    private List<Hole> createHolesForCourseB() {
        return Arrays.asList(
                Hole.builder().holeNumber(1).par(2).build(),
                Hole.builder().holeNumber(2).par(2).build()
        );
    }

    private Course createCourse(String courseName, List<Hole> holesForCourse) {
        return Course.builder()
                .courseName(courseName)
                .holes(holesForCourse)
                .build();
    }

    private void saveCourses(List<Course> courses) {
        for (Course course : courses) {
            courseService.save(course);
        }
    }
}

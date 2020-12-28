package com.golf.two_for_tom_open.dataInitialiser;

import com.golf.two_for_tom_open.model.entity.*;
import com.golf.two_for_tom_open.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitialiser implements CommandLineRunner {

    private final PlayerService playerService;
    private final CourseService courseService;
    private final ScoreService scoreService;
    private final TournamentService tournamentService;

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
        Player playerA = Player.builder().firstName("Player").lastName("A").build();
        Player playerB = Player.builder().firstName("Player").lastName("B").build();

        playerService.save(playerA);
        playerService.save(playerB);

        // +++++ Holes +++++
        List<Hole> holesForCourseA = Arrays.asList(
                Hole.builder().holeNumber(1).par(3).build(),
                Hole.builder().holeNumber(2).par(2).build(),
                Hole.builder().holeNumber(3).par(3).build(),
                Hole.builder().holeNumber(4).par(3).build(),
                Hole.builder().holeNumber(5).par(4).build()
        );
        List<Hole> holesForCourseB = Arrays.asList(
                Hole.builder().holeNumber(1).par(2).build(),
                Hole.builder().holeNumber(2).par(2).build(),
                Hole.builder().holeNumber(3).par(3).build(),
                Hole.builder().holeNumber(4).par(3).build(),
                Hole.builder().holeNumber(5).par(4).build()
        );

        // +++++ Courses +++++
        Course courseA = Course.builder()
                .courseName("Course A")
                .holes(holesForCourseA)
                .build();

        Course courseB = Course.builder()
                .courseName("Course B")
                .holes(holesForCourseB)
                .build();

        //cascade type all, so holes save too
        courseService.save(courseA);
        courseService.save(courseB);

        // +++++ Tournaments +++++
        Tournament tournament2015 = Tournament.builder()
                .year(Year.of(2015))
                .courses(Arrays.asList(courseA, courseB))
                .players(Arrays.asList(playerA, playerB))
                .build();
        tournamentService.save(tournament2015);

        // +++++ Scores +++++
        Hole courseAHole1 = courseA.getHoleByCourseHoleNumber(1).orElse(null);
        Hole courseAHole2 = courseA.getHoleByCourseHoleNumber(2).orElse(null);

        scoreService.save(createScore(3, tournament2015, courseAHole1, playerA));
        scoreService.save(createScore(6, tournament2015, courseAHole1, playerB));
        scoreService.save(createScore(3, tournament2015, courseAHole2, playerA));
        scoreService.save(createScore(2, tournament2015, courseAHole2, playerB));

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
}

package com.golf.two_for_tom_open.dataInitialiser;

import com.golf.two_for_tom_open.model.Course;
import com.golf.two_for_tom_open.model.Hole;
import com.golf.two_for_tom_open.model.Player;
import com.golf.two_for_tom_open.model.Tournament;
import com.golf.two_for_tom_open.service.CourseService;
import com.golf.two_for_tom_open.service.HoleService;
import com.golf.two_for_tom_open.service.PlayerService;
import com.golf.two_for_tom_open.service.TournamentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitialiser implements CommandLineRunner {

    private final PlayerService playerService;
    private final CourseService courseService;
    private final HoleService holeService;
    private final TournamentService tournamentService;

    public DataInitialiser(PlayerService playerService, CourseService courseService, HoleService holeService,
                           TournamentService tournamentService) {
        this.playerService = playerService;
        this.courseService = courseService;
        this.holeService = holeService;
        this.tournamentService = tournamentService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {

        // +++++ Players +++++
        Player playerA = Player.builder()
                .firstName("Player")
                .lastName("A")
                .build();

        Player playerB = Player.builder()
                .firstName("Player")
                .lastName("B")
                .build();

        playerService.save(playerA);
        playerService.save(playerB);

        // +++++ Courses +++++
        List<Hole> holesForCourseA = Arrays.asList(
                Hole.builder().holeNumber((byte) 1).par((byte) 3).build(),
                Hole.builder().holeNumber((byte) 2).par((byte) 2).build(),
                Hole.builder().holeNumber((byte) 3).par((byte) 3).build(),
                Hole.builder().holeNumber((byte) 4).par((byte) 3).build(),
                Hole.builder().holeNumber((byte) 5).par((byte) 4).build()
        );
        Course courseA = Course.builder()
                .courseName("Course A")
                .holes(holesForCourseA)
                .build();
        courseService.save(courseA);

        List<Hole> holesForCourseB = Arrays.asList(
                Hole.builder().holeNumber((byte) 1).par((byte) 2).build(),
                Hole.builder().holeNumber((byte) 2).par((byte) 2).build(),
                Hole.builder().holeNumber((byte) 3).par((byte) 3).build(),
                Hole.builder().holeNumber((byte) 4).par((byte) 3).build(),
                Hole.builder().holeNumber((byte) 5).par((byte) 4).build()
        );
        Course courseB = Course.builder()
                .courseName("Course B")
                .holes(holesForCourseB)
                .build();
        courseService.save(courseB);

        // +++++ Tournaments +++++
        Tournament tournament2015 = Tournament.builder()
                .year(Year.of(2015))
                .courses(Arrays.asList(courseA, courseB))
                .players(Arrays.asList(playerA, playerB))
                .build();
        tournamentService.save(tournament2015);

    }
}

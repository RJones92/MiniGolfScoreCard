package com.golf.two_for_tom_open.dataInitialiser;

import com.golf.two_for_tom_open.dataInitialiser.prod.CourseCreator;
import com.golf.two_for_tom_open.dataInitialiser.prod.score.ScoreFactory;
import com.golf.two_for_tom_open.model.entity.*;
import com.golf.two_for_tom_open.service.CourseService;
import com.golf.two_for_tom_open.service.PlayerService;
import com.golf.two_for_tom_open.service.ScoreService;
import com.golf.two_for_tom_open.service.TournamentService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
@Profile("dev")
public class DataInitialiserProd implements CommandLineRunner {

    private final PlayerService playerService;
    private final CourseService courseService;
    private final ScoreService scoreService;
    private final TournamentService tournamentService;
    private final ScoreFactory scoreFactory;
    private final CourseCreator courseCreator;

    public DataInitialiserProd(PlayerService playerService,
                               CourseService courseService,
                               ScoreService scoreService,
                               TournamentService tournamentService) {
        this.playerService = playerService;
        this.courseService = courseService;
        this.scoreService = scoreService;
        this.tournamentService = tournamentService;
        this.scoreFactory = new ScoreFactory();
        this.courseCreator = new CourseCreator();
    }

    private Player Rhys;
    private Player Tom;
    private Player Jamie;
    private Player Jade;
    private List<Tournament> tournaments = new ArrayList<>();

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
                Year.of(2023), new ArrayList<>());
//                Year.of(2024), new ArrayList<>());
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
        createScores();
    }

    // ++++++++++++++++ PLAYERS ++++++++++++++++
    private void createPlayers() {
        Rhys = Player.builder().firstName("Rhys").lastName("Jones").build();
        Tom = Player.builder().firstName("Thomas").lastName("Millican").build();
        Jamie = Player.builder().firstName("Jamie").lastName("Acres").build();
        Jade = Player.builder().firstName("Jade").lastName("Richmond").build();
        List.of(Rhys, Tom, Jamie, Jade).forEach(playerService::save);
    }

    // ++++++++++++++++ COURSES ++++++++++++++++
    private void createCourses() {
        COURSES_BY_YEAR
                .entrySet()
                .stream()
                .filter(entry -> !isCourseListAlreadyPopulated(entry.getValue()))
                .map(entry -> {
                    var year = entry.getKey();
                    var courseList = entry.getValue();
                    var courseForYear = courseCreator.create(year);
                    courseList.addAll(courseForYear);
                    return courseList;
                })
                .flatMap(List::stream)
                .forEach(courseService::save);
    }

    private boolean isCourseListAlreadyPopulated(List<Course> courseList) {
        return ObjectUtils.isNotEmpty(courseList);
    }

    // ++++++++++++++++ TOURNAMENTS ++++++++++++++++
    private void createTournaments() {
        COURSES_BY_YEAR.entrySet().stream()
                .map(entry -> {
                    var year = entry.getKey();

                    if (year.equals(Year.of(2020))) {
                        return Tournament.builder()
                                ._year(entry.getKey())
                                .courses(entry.getValue())
                                .players(List.of(Rhys, Tom, Jamie, Jade))
                                .build();
                    }

                    return Tournament.builder()
                            ._year(entry.getKey())
                            .courses(entry.getValue())
                            .players(List.of(Rhys, Tom, Jamie))
                            .build();
                })
                .forEach(tournament -> {
                    tournaments.add(tournament);
                    tournamentService.save(tournament);
                });
    }

    // ++++++++++++++++ SCORES ++++++++++++++++
    private void createScores() {
        tournaments.forEach(tournament ->
                Stream.of(Rhys, Tom, Jamie)
                        .map(player -> scoreFactory.createScoresFor(tournament, player))
                        .flatMap(Collection::stream)
                        .forEach(scoreService::save));

        // Jade
        tournaments.stream()
                .filter(t -> t.get_year().getValue() == 2020)
                .map(t -> scoreFactory.createScoresFor(t, Jade))
                .flatMap(Collection::stream)
                .forEach(scoreService::save);
    }

}

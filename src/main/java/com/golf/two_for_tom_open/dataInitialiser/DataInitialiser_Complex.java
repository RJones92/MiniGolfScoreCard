package com.golf.two_for_tom_open.dataInitialiser;

import com.golf.two_for_tom_open.model.entity.*;
import com.golf.two_for_tom_open.service.CourseService;
import com.golf.two_for_tom_open.service.PlayerService;
import com.golf.two_for_tom_open.service.ScoreService;
import com.golf.two_for_tom_open.service.TournamentService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Profile("tbc")
public class DataInitialiser_Complex implements CommandLineRunner {

    private final PlayerService playerService;
    private final CourseService courseService;
    private final ScoreService scoreService;
    private final TournamentService tournamentService;

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

    private Tournament tournament2016;
    private Tournament tournament2017;
    private Tournament tournament2018;
    private Tournament tournament2019;
    private Tournament tournament2020;
    private Tournament tournament2021;

    public DataInitialiser_Complex(PlayerService playerService, CourseService courseService,
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
        //Jamie's scores
        buildScoreJamie2016();
        buildScoreJamie2017();
        buildScoreJamie2018();
        //Tom's scores
        buildScoreTom2016();
        buildScoreTom2017();
        buildScoreTom2018();

        //Expect Jamie, Jamie, Rhys - as winners

    }

    // ++++++++++++++++ PLAYERS ++++++++++++++++
    private void buildScoreRhys2016(){
        List<Integer> course1 = Arrays.asList(2,4,5,5,4,5,2,2,5,0,4,5,5,2,6,2,6,1); //64
        List<Integer> course2 = Arrays.asList(3,2,2,3,3,3,4,2,2,5,2,2,2,2,3,5,2,2);
        List<Integer> course3 = Arrays.asList(4,3,3,3,4,2,5,3,2,2,5,3,2,3,3,1,3,2);
        List<Integer> course4 = Arrays.asList(2,2,4,6,2,2,3,3,2,5,2,3,3,2,3,3,2,1);
        List<Integer> course5 = Arrays.asList(2,3,2,4,6,2,5,2,3,2,2,2,5,3,2,3,2,4);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5).collect(Collectors.toList());
        createScores(total, tournament2016, Rhys);
    }

    private void buildScoreRhys2017(){
        List<Integer> course1 = Arrays.asList(4,6,5,2,5,4,5,2,2,5,2,2,3,2,1,3,7,5);
        List<Integer> course2 = Arrays.asList(3,2,4,2,3,3,2,2,2,2,3,3,3,2,5,2,3,3);
        List<Integer> course3 = Arrays.asList(2,2,4,5,3,4,2,2,4);
        List<Integer> course4 = Arrays.asList(2,2,2,2,3,3,3,3,2,3,3,2,3,5,2,2,2,7);
        List<Integer> course5 = Arrays.asList(3,2,2,3,3,4,2,2,3,3,2,2,3,4,2,2,2,4);
        List<Integer> course6 = Arrays.asList(3,2,2,2,4,3,3,2,3,3,4,2,2,3,2,3,2,3);
        List<Integer> course7 = Arrays.asList(2,4,4,2,3,7,7,4,3,2,3,5,4,2,3,2,3,2);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6,
                course7).collect(Collectors.toList());
        createScores(total, tournament2017, Rhys);
    }

    private void buildScoreRhys2018(){
        List<Integer> course1 = Arrays.asList(6,2,2,6,6,2,4,5,4,2,2,2);
        List<Integer> course2 = Arrays.asList(5,4,3,2,4,6,3,3,6,3,6,6,6);
        List<Integer> course3 = Arrays.asList(4,5,4,2,2,2,2,2,5);
        List<Integer> course4 = Arrays.asList(3,2,3,2,2,3,2,2,4,1,2,3);
        List<Integer> course5 = Arrays.asList(3,2,2,3,2,3,2,2,3,3,1,2,3,2,3,4,3,2);
        List<Integer> course6 = Arrays.asList(4,2,3,2,4,6,2,3,6);
        List<Integer> course7 = Arrays.asList(6,1,1,6,2,2,3,2,2,2,2,5,2,3,4,3,2,4);
        List<Integer> course8 = Arrays.asList(3,2,4,2,2,1,2,2,3,5,2,3,3,3,4,4,3,3);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6,
                course7,
                course8).collect(Collectors.toList());
        createScores(total, tournament2018, Rhys);
    }

    private void buildScoreJamie2016(){
        List<Integer> course1 = Arrays.asList(3,2,3,3,3,3,4,3,4,4,2,2,4,2,4,2,2,2);
        List<Integer> course2 = Arrays.asList(4,2,2,2,2,2,3,2,5,3,3,4,2,3,3,1,1,1);
        List<Integer> course3 = Arrays.asList(1,3,3,1,3,2,2,3,2,3,3,2,2,2,2,1,4,2);
        List<Integer> course4 = Arrays.asList(3,2,4,2,4,2,3,3,4,3,3,3,2,1,2,5,3,2);
        List<Integer> course5 = Arrays.asList(2,3,4,3,3,2,2,2,2,2,3,3,3,3,3,3,2,3);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5).collect(Collectors.toList());
        createScores(total, tournament2016, Jamie);
    }

    private void buildScoreJamie2017(){
        List<Integer> course1 = Arrays.asList(7,2,2,2,3,3,2,2,4,3,1,4,2,4,3,3,7,1);
        List<Integer> course2 = Arrays.asList(3,2,2,3,3,2,3,3,2,4,3,2,3,5,4,2,3,6);
        List<Integer> course3 = Arrays.asList(2,2,3,5,2,2,2,2,4);
        List<Integer> course4 = Arrays.asList(2,2,2,2,2,2,2,4,3,3,7,2,2,4,2,2,2,3);
        List<Integer> course5 = Arrays.asList(3,2,3,3,3,5,2,2,3,2,3,2,4,2,3,4,2,3);
        List<Integer> course6 = Arrays.asList(5,2,2,5,5,2,2,2,3,4,6,3,3,3,3,4,2,2);
        List<Integer> course7 = Arrays.asList(2,2,3,2,2,3,7,7,4,2,3,4,2,2,3,2,3,2);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6,
                course7).collect(Collectors.toList());
        createScores(total, tournament2017, Jamie);
    }

    private void buildScoreJamie2018(){
        List<Integer> course1 = Arrays.asList(2,4,2,3,3,1,3,2,2,2,3,4);
        List<Integer> course2 = Arrays.asList(4,3,4,3,3,6,3,2,5,2,3,5,3);
        List<Integer> course3 = Arrays.asList(2,5,3,4,3,4,2,3,5);
        List<Integer> course4 = Arrays.asList(3,5,2,2,2,3,2,2,2,2,2,2);
        List<Integer> course5 = Arrays.asList(2,2,2,2,2,3,2,1,6,3,1,1,3,4,3,2,2,6);
        List<Integer> course6 = Arrays.asList(3,2,3,6,3,4,3,3,6);
        List<Integer> course7 = Arrays.asList(4,2,1,3,2,2,3,2,2,2,2,3,2,2,2,3,3,2);
        List<Integer> course8 = Arrays.asList(3,2,4,2,2,1,2,2,3,5,2,3,3,3,4,4,3,3);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6,
                course7,
                course8).collect(Collectors.toList());
        createScores(total, tournament2018, Jamie);
    }

    private void buildScoreTom2016(){
        List<Integer> course1 = Arrays.asList(5,2,3,3,2,3,3,2,3,4,3,2,6,6,4,6,3,6);
        List<Integer> course2 = Arrays.asList(3,1,2,2,2,3,2,3,3,2,3,2,3,2,2,4,2,3);
        List<Integer> course3 = Arrays.asList(2,4,4,2,3,2,2,4,1,3,3,2,3,2,1,2,3,4);
        List<Integer> course4 = Arrays.asList(2,3,3,4,4,2,2,4,3,3,2,6,3,3,2,4,3,4);
        List<Integer> course5 = Arrays.asList(2,2,3,3,4,4,2,2,2,3,2,3,3,6,2,2,2,2);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5).collect(Collectors.toList());
        createScores(total, tournament2016, Tom);
    }

    private void buildScoreTom2017(){
        List<Integer> course1 = Arrays.asList(7,3,3,2,7,4,3,4,4,3,3,2,4,3,3,3,4,3);
        List<Integer> course2 = Arrays.asList(3,3,3,3,2,2,3,2,2,2,3,2,3,3,3,4,2,5);
        List<Integer> course3 = Arrays.asList(2,2,4,5,2,3,2,2,4);
        List<Integer> course4 = Arrays.asList(4,3,2,2,5,2,3,4,3,3,5,2,2,4,2,2,3,7);
        List<Integer> course5 = Arrays.asList(3,4,4,2,3,3,3,2,3,3,3,3,2,2,3,4,3,2);
        List<Integer> course6 = Arrays.asList(2,2,3,3,3,3,2,3,3,2,5,2,3,3,4,3,3,2);
        List<Integer> course7 = Arrays.asList(4,2,4,2,2,2,7,2,3,2,2,4,2,2,2,2,7,3);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6,
                course7).collect(Collectors.toList());
        createScores(total, tournament2017, Tom);
    }

    private void buildScoreTom2018(){
        List<Integer> course1 = Arrays.asList(3,4,2,4,4,1,4,4,2,2,2,6);
        List<Integer> course2 = Arrays.asList(3,3,4,3,3,4,3,2,5,2,3,2,3);
        List<Integer> course3 = Arrays.asList(2,4,5,4,3,3,2,4,6);
        List<Integer> course4 = Arrays.asList(3,3,2,2,3,2,2,3,2,3,2,3);
        List<Integer> course5 = Arrays.asList(2,2,2,2,1,6,2,1,1,3,1,2,5,3,3,2,2,3);
        List<Integer> course6 = Arrays.asList(3,6,6,6,3,2,3,4,6);
        List<Integer> course7 = Arrays.asList(1,4,4,5,1,2,2,2,2,2,3,2,2,2,2,2,3,2);
        List<Integer> course8 = Arrays.asList(2,2,2,2,3,2,4,3,2,2,3,2,2,2,5,3,1,4);
        List<List<Integer>> total = Stream.of(
                course1,
                course2,
                course3,
                course4,
                course5,
                course6,
                course7,
                course8).collect(Collectors.toList());
        createScores(total, tournament2018, Tom);
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
        Rhys = Player.builder().firstName("Rhys").lastName("A").build();
        Tom = Player.builder().firstName("Thomas").lastName("B").build();
        Jamie = Player.builder().firstName("Jamie").lastName("C").build();
        Jade = Player.builder().firstName("Jade").lastName("D").build();
        savePlayers(Arrays.asList(Rhys, Tom, Jamie, Jade));
    }

    private void savePlayers(List<Player> players) {
        for (Player player : players) {
            playerService.save(player);
        }
    }

    // ++++++++++++++++ COURSES ++++++++++++++++
    private void createCourses() {
        buildCourses_2016();
        buildCourses_2017();
        buildCourses_2018();

        saveCourses(Stream.of(
                courses_2016,
                courses_2017,
                courses_2018).flatMap(List::stream).collect(Collectors.toList()));
    }

    private void saveCourses(List<Course> courses) {
        for (Course course : courses) {
            courseService.save(course);
        }
    }

    private void buildCourses_2016() {
        if (isCourseListAlreadyPopulated(courses_2016)) return;
        Map<String, Integer> courseNameWithNumberOfHoles = new LinkedHashMap<>();
        courseNameWithNumberOfHoles.put("2016 course 1", 18);
        courseNameWithNumberOfHoles.put("2016 course 2", 18);
        courseNameWithNumberOfHoles.put("2016 course 3", 18);
        courseNameWithNumberOfHoles.put("2016 course 4", 18);
        courseNameWithNumberOfHoles.put("2016 course 5", 18);
        courses_2016 = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : courseNameWithNumberOfHoles.entrySet()) {
            courses_2016.add(createCourse(entry.getKey(), createHolesForCourseWithNoPar(entry.getValue())));
        }
    }

    private void buildCourses_2017() {
        if (isCourseListAlreadyPopulated(courses_2017)) return;
        courses_2017 = new LinkedList<>();
        courses_2017.add(createCourse("2017 course 1",
                createHolesForCourseWithNoPar(18)));
        courses_2017.add(createCourse("2017 course 2",
                createHoles(Arrays.asList(2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2,2,2,2,2,2,3,4))));
        courses_2017.add(createCourse("2017 course 3",
                createHoles(Arrays.asList(2,2,3,4,2,2,2,3,4))));
        courses_2017.add(createCourse("2017 course 4",
                createHoles(Arrays.asList(3,2,3,3,4, 3,3,4,3, 3,4,3,3, 4,2,3,2,4))));
        courses_2017.add(createCourse("2017 course 5",
                createHoles(Arrays.asList(2,2,3,2,2,3,3,2,3,2,3,3,2,3,2,3,2,3))));
        courses_2017.add(createCourse("2017 course 6",
                createHoles(Arrays.asList(2,3,3,3,4,3,2,2,3,3, 5,3,2,3,2,3,3,3))));
        courses_2017.add(createCourse("2017 course 7",
                createHolesForCourseWithNoPar(18)));
    }

    private void buildCourses_2018() {
        if (isCourseListAlreadyPopulated(courses_2018)) return;
        courses_2018 = new LinkedList<>();
        courses_2018.add(createCourse("2018 course 1",
                createHolesForCourseWithNoPar(12)));
        courses_2018.add(createCourse("2018 course 2",
                createHoles(Arrays.asList(2,3,4,3,4,4,4,3,3,3,3,4,4))));
        courses_2018.add(createCourse("2018 course 3",
                createHolesForCourseWithNoPar(9)));
        courses_2018.add(createCourse("2018 course 4",
                createHolesForCourseWithNoPar(12)));
        courses_2018.add(createCourse("2018 course 5",
                createHoles(Arrays.asList(2,2,2,3,2,2,3,3,4,2,2,3,3,2,3,3,3,3))));
        courses_2018.add(createCourse("2018 course 6",
                createHolesForCourseWithNoPar(9)));
        courses_2018.add(createCourse("2018 course 7",
                createHoles(Arrays.asList(2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2))));
        courses_2018.add(createCourse("2018 course 8",
                createHoles(Arrays.asList(2,3,3,2,3,2,2,3,2,3,2,3,3,3,5,2,3,4))));
    }

    private boolean isCourseListAlreadyPopulated(List<Course> courseList) {
        return ObjectUtils.isNotEmpty(courseList);
    }

    private Course createCourse(String courseName, List<Hole> holesForCourse) {
        return Course.builder()
                .courseName(courseName)
                .holes(holesForCourse)
                .build();
    }

    private List<Hole> createHoles(List<Integer> orderedListOfPars) {
        List<Hole> holes = new LinkedList<>();
        for (int i = 0; i < orderedListOfPars.size(); i++) {
            holes.add(createHole(i, orderedListOfPars.get(i)));
        }
        return holes;
    }

    private List<Hole> createHolesForCourseWithNoPar(Integer numberOfHoles) {
        List<Hole> holes = new ArrayList<>();
        for (int i = 0; i < numberOfHoles; i++) {
            holes.add(createHole(i + 1, 0));
        }
        return holes;
    }

    private Hole createHole(Integer holeNumber, Integer par) {
        return Hole.builder().holeNumber(holeNumber).par(par).build();
    }

    // ++++++++++++++++ TOURNAMENTS ++++++++++++++++
    private void createTournaments() {
        tournament2016 = Tournament.builder()
                .year(Year.of(2016))
                .courses(courses_2016)
                .players(Arrays.asList(Rhys, Tom, Jamie))
                .build();

        tournament2017 = Tournament.builder()
                .year(Year.of(2017))
                .courses(courses_2017)
                .players(Arrays.asList(Rhys, Tom, Jamie))
                .build();

        tournament2018 = Tournament.builder()
                .year(Year.of(2018))
                .courses(courses_2018)
                .players(Arrays.asList(Rhys, Tom, Jamie))
                .build();
        
        saveTournaments(Arrays.asList(tournament2016,
                tournament2017,
                tournament2018));
    }

    private void saveTournaments(List<Tournament> tournaments) {
        for (Tournament tournament : tournaments) {
            tournamentService.save(tournament);
        }
    }

}

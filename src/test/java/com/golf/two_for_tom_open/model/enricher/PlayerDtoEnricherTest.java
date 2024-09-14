package com.golf.two_for_tom_open.model.enricher;

import com.golf.two_for_tom_open.model.dto.*;
import com.golf.two_for_tom_open.service.ScoreService;
import com.golf.two_for_tom_open.service.TournamentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Year;
import java.util.*;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
class PlayerDtoEnricherTest {

    @Mock
    TournamentService tournamentService;
    @Mock
    ScoreService scoreService;
    PlayerDtoEnricher playerDtoEnricher;

    private static final int PLAYER_A_ID = 1;
    private static final String PLAYER_A_FIRST_NAME = "John";
    private static final String PLAYER_A_LAST_NAME = "Smith";
    private static final int PLAYER_B_ID = 2;
    private static final String PLAYER_B_FIRST_NAME = "Jessica";
    private static final String PLAYER_B_LAST_NAME = "Simpson";

    private PlayerDto playerA;
    private PlayerDto playerB;

    @BeforeEach
    void setUp() {
        initialisePlayers();
        playerDtoEnricher = new PlayerDtoEnricher(tournamentService, scoreService);
    }

    private void initialisePlayers() {
        playerA = PlayerDto.builder()
                .id(PLAYER_A_ID)
                .firstName(PLAYER_A_FIRST_NAME)
                .lastName(PLAYER_A_LAST_NAME)
                .build();
        playerB = PlayerDto.builder()
                .id(PLAYER_B_ID)
                .firstName(PLAYER_B_FIRST_NAME)
                .lastName(PLAYER_B_LAST_NAME)
                .build();
    }

    @Test
    void testCountTournamentsPlayed() {
        //GIVEN
        TournamentDto tournament2015 = TournamentDto.builder()
                .id(1)
                .players(singletonList(playerA))
                .courses(Collections.emptyList())
                ._year(Year.of(2015))
                .winner(playerA)
                .build();
        TournamentDto tournament2016 = TournamentDto.builder()
                .id(2)
                .players(Arrays.asList(playerA, playerB))
                .courses(Collections.emptyList())
                ._year(Year.of(2016))
                .winner(playerB)
                .build();
        List<TournamentDto> allTournaments = new ArrayList<>();
        allTournaments.add(tournament2015);
        allTournaments.add(tournament2016);

        when(tournamentService.getAll()).thenReturn(allTournaments);

        //WHEN
        playerDtoEnricher.enrich(playerA);
        playerDtoEnricher.enrich(playerB);

        //THEN
        assertThat(playerA.getPlayerStats().countOfTournamentsPlayed(), equalTo(2L));
        assertThat(playerB.getPlayerStats().countOfTournamentsPlayed(), equalTo(1L));

    }

    @Test
    void testCountTournamentsWon() {
        //GIVEN
        TournamentDto tournament2015 = TournamentDto.builder()
                .id(1)
                .players(singletonList(playerA))
                .courses(Collections.emptyList())
                ._year(Year.of(2015))
                .winner(playerA)
                .build();
        TournamentDto tournament2016 = TournamentDto.builder()
                .id(2)
                .players(Arrays.asList(playerA, playerB))
                .courses(Collections.emptyList())
                ._year(Year.of(2016))
                .winner(playerB)
                .build();
        List<TournamentDto> allTournaments = new ArrayList<>();
        allTournaments.add(tournament2015);
        allTournaments.add(tournament2016);

        when(tournamentService.getAll()).thenReturn(allTournaments);

        //WHEN
        playerDtoEnricher.enrich(playerA);

        //THEN
        assertThat(playerA.getPlayerStats().countOfTournamentsWon(), equalTo(1L));
    }

    @Test
    void testCountCoursesPlayed() {
        //GIVEN
        CourseDto courseOne = CourseDto.builder()
                .id(1)
                .courseName("Course One")
                .holes(Collections.emptyList())
                .build();
        CourseDto courseTwo = CourseDto.builder()
                .id(2)
                .courseName("Course Two")
                .holes(Collections.emptyList())
                .build();
        CourseDto courseThree = CourseDto.builder()
                .id(3)
                .courseName("Course Three")
                .holes(Collections.emptyList())
                .build();
        CourseDto courseFour = CourseDto.builder()
                .id(4)
                .courseName("Course Four")
                .holes(Collections.emptyList())
                .build();

        TournamentDto tournament2015 = TournamentDto.builder()
                .id(1)
                .players(Arrays.asList(playerA, playerB))
                .courses(Arrays.asList(courseOne, courseTwo, courseThree))
                ._year(Year.of(2015))
                .winner(playerA)
                .build();
        TournamentDto tournament2016 = TournamentDto.builder()
                .id(2)
                .players(singletonList(playerA))
                .courses(singletonList(courseFour))
                ._year(Year.of(2016))
                .winner(playerA)
                .build();

        List<TournamentDto> allTournaments = new ArrayList<>();
        allTournaments.add(tournament2015);
        allTournaments.add(tournament2016);

        when(tournamentService.getAll()).thenReturn(allTournaments);

        //WHEN
        playerDtoEnricher.enrich(playerA);

        //THEN
        assertThat(playerA.getPlayerStats().countOfCoursesPlayed(), equalTo(4L));
    }

    @Test
    void testCountCoursesWon() {
        //GIVEN
        final int TOURNAMENT_2015_ID = 1;
        final int TOURNAMENT_2016_ID = 2;

        CourseDto courseOne = CourseDto.builder()
                .id(1)
                .courseName("Course One")
                .holes(Collections.emptyList())
                .winnerByTournamentId(Map.of(TOURNAMENT_2015_ID, playerA))
                .build();
        CourseDto courseTwo = CourseDto.builder()
                .id(2)
                .courseName("Course Two")
                .holes(Collections.emptyList())
                .winnerByTournamentId(Map.of(TOURNAMENT_2015_ID, playerA))
                .build();
        CourseDto courseThree = CourseDto.builder()
                .id(3)
                .courseName("Course Three")
                .holes(Collections.emptyList())
                .winnerByTournamentId(Map.of(TOURNAMENT_2015_ID, playerB))
                .build();
        CourseDto courseFour = CourseDto.builder()
                .id(4)
                .courseName("Course Four")
                .holes(Collections.emptyList())
                .winnerByTournamentId(Map.of(TOURNAMENT_2016_ID, playerA))
                .build();

        TournamentDto tournament2015 = TournamentDto.builder()
                .id(TOURNAMENT_2015_ID)
                .players(Arrays.asList(playerA, playerB))
                .courses(Arrays.asList(courseOne, courseTwo, courseThree))
                ._year(Year.of(2015))
                .winner(playerA)
                .build();
        TournamentDto tournament2016 = TournamentDto.builder()
                .id(TOURNAMENT_2016_ID)
                .players(singletonList(playerA))
                .courses(singletonList(courseFour))
                ._year(Year.of(2016))
                .winner(playerA)
                .build();

        List<TournamentDto> allTournaments = new ArrayList<>();
        allTournaments.add(tournament2015);
        allTournaments.add(tournament2016);

        when(tournamentService.getAll()).thenReturn(allTournaments);

        //WHEN
        playerDtoEnricher.enrich(playerA);

        //THEN
        assertThat(playerA.getPlayerStats().countOfCoursesWon(), equalTo(3L));
    }

    @Test
    void testCountHolesPlayed() {
        //GIVEN
        CourseDto courseOne = CourseDto.builder()
                .id(1)
                .courseName("Course One")
                .holes(Arrays.asList(
                        HoleDto.builder().id(1).par(2).holeNumber(1).build(),
                        HoleDto.builder().id(2).par(3).holeNumber(2).build()
                ))
                .build();
        CourseDto courseTwo = CourseDto.builder()
                .id(2)
                .courseName("Course Two")
                .holes(Arrays.asList(
                        HoleDto.builder().id(3).par(2).holeNumber(1).build(),
                        HoleDto.builder().id(4).par(3).holeNumber(2).build(),
                        HoleDto.builder().id(5).par(3).holeNumber(3).build()
                ))
                .build();
        CourseDto courseThree = CourseDto.builder()
                .id(3)
                .courseName("Course Three")
                .holes(singletonList(
                        HoleDto.builder().id(6).par(3).holeNumber(1).build()
                ))
                .build();
        CourseDto courseFour = CourseDto.builder()
                .id(4)
                .courseName("Course Four")
                .holes(Arrays.asList(
                        HoleDto.builder().id(7).par(3).holeNumber(1).build(),
                        HoleDto.builder().id(8).par(3).holeNumber(2).build()
                ))
                .build();

        TournamentDto tournament2015 = TournamentDto.builder()
                .id(1)
                .players(Arrays.asList(playerA, playerB))
                .courses(Arrays.asList(courseOne, courseTwo, courseThree))
                ._year(Year.of(2015))
                .winner(playerA)
                .build();
        TournamentDto tournament2016 = TournamentDto.builder()
                .id(2)
                .players(singletonList(playerA))
                .courses(singletonList(courseFour))
                ._year(Year.of(2016))
                .winner(playerA)
                .build();

        List<TournamentDto> allTournaments = new ArrayList<>();
        allTournaments.add(tournament2015);
        allTournaments.add(tournament2016);

        when(tournamentService.getAll()).thenReturn(allTournaments);

        //WHEN
        playerDtoEnricher.enrich(playerA);

        //THEN
        assertThat(playerA.getPlayerStats().countOfHolesPlayed(), equalTo(8L));
    }

    @Test
    void testCountHolesWon() {
        //GIVEN
        final int TOURNAMENT_2015_ID = 1;

        HoleDto hole1Course1 = HoleDto.builder().id(1).par(2).holeNumber(1).build();
        HoleDto hole2Course1 = HoleDto.builder().id(2).par(3).holeNumber(2).build();
        HoleDto hole1Course2 = HoleDto.builder().id(3).par(2).holeNumber(1).build();
        HoleDto hole2Course2 = HoleDto.builder().id(4).par(3).holeNumber(2).build();
        HoleDto hole3Course2 = HoleDto.builder().id(5).par(3).holeNumber(3).build();

        CourseDto courseOne = CourseDto.builder()
                .id(1)
                .courseName("Course One")
                .holes(Arrays.asList(hole1Course1, hole2Course1))
                .build();
        CourseDto courseTwo = CourseDto.builder()
                .id(2)
                .courseName("Course Two")
                .holes(Arrays.asList(hole1Course2, hole2Course2, hole3Course2))
                .build();

        TournamentDto tournament2015 = TournamentDto.builder()
                .id(TOURNAMENT_2015_ID)
                .players(Arrays.asList(playerA, playerB))
                .courses(Arrays.asList(courseOne, courseTwo))
                ._year(Year.of(2015))
                .winner(playerA)
                .build();

        List<TournamentDto> allTournaments = new ArrayList<>();
        allTournaments.add(tournament2015);

        when(tournamentService.getAll()).thenReturn(allTournaments);

        List<ScoreDto> scores = new ArrayList<>();
        scores.add(ScoreDto.builder().id(1).player(playerA).tournament(tournament2015).hole(hole1Course1).strokes(2).build());
        scores.add(ScoreDto.builder().id(1).player(playerB).tournament(tournament2015).hole(hole1Course1).strokes(8).build());
        scores.add(ScoreDto.builder().id(1).player(playerA).tournament(tournament2015).hole(hole2Course1).strokes(1).build());
        scores.add(ScoreDto.builder().id(1).player(playerB).tournament(tournament2015).hole(hole2Course1).strokes(8).build());

        scores.add(ScoreDto.builder().id(1).player(playerA).tournament(tournament2015).hole(hole1Course2).strokes(8).build());
        scores.add(ScoreDto.builder().id(1).player(playerB).tournament(tournament2015).hole(hole1Course2).strokes(3).build());
        scores.add(ScoreDto.builder().id(1).player(playerA).tournament(tournament2015).hole(hole2Course2).strokes(2).build());
        scores.add(ScoreDto.builder().id(1).player(playerB).tournament(tournament2015).hole(hole2Course2).strokes(8).build());
        scores.add(ScoreDto.builder().id(1).player(playerA).tournament(tournament2015).hole(hole3Course2).strokes(1).build());
        scores.add(ScoreDto.builder().id(1).player(playerB).tournament(tournament2015).hole(hole3Course2).strokes(1).build());

        when(scoreService.getScoresForTournamentsById(anyList())).thenReturn(scores);

        //WHEN
        playerDtoEnricher.enrich(playerA);

        //THEN
        assertThat(playerA.getPlayerStats().countOfHolesWon(), equalTo(4L));
    }
}
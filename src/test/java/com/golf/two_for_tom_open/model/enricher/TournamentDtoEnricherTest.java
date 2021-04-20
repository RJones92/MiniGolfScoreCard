package com.golf.two_for_tom_open.model.enricher;

import com.golf.two_for_tom_open.model.dto.*;
import com.golf.two_for_tom_open.service.ScoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TournamentDtoEnricherTest {

    @Mock
    ScoreService scoreService;
    @InjectMocks
    TournamentDtoEnricher tournamentEnricher;

    HoleDto hole1;
    HoleDto hole2;
    HoleDto hole3;
    HoleDto hole4;
    HoleDto hole5;
    HoleDto hole6;
    HoleDto hole7;
    HoleDto hole8;
    HoleDto hole9;

    PlayerDto playerA;
    PlayerDto playerB;
    PlayerDto playerC;

    @BeforeEach
    void setUp() {
        holeSetUp();
        playersSetUp();
    }

    @Test
    void testSettingWinnersWithOneTournamentAndThreeCourse() {
        //GIVEN
        CourseDto course1 = CourseDto.builder().courseName("Course 1").holes(Arrays.asList(hole1, hole2, hole3)).build();
        CourseDto course2 = CourseDto.builder().courseName("Course 2").holes(Arrays.asList(hole4, hole5, hole6)).build();
        CourseDto course3 = CourseDto.builder().courseName("Course 3").holes(Arrays.asList(hole8, hole8, hole9)).build();

        TournamentDto tournament = TournamentDto.builder()
                .courses(Arrays.asList(course1, course2, course3))
                .players(Arrays.asList(playerA, playerB, playerC))
                .build();

        List<ScoreDto> scores = Arrays.asList(
                //Course 1
                buildScore(tournament, playerA, hole1, 1),
                buildScore(tournament, playerA, hole2, 1),
                buildScore(tournament, playerA, hole3, 1),

                buildScore(tournament, playerB, hole1, 8),
                buildScore(tournament, playerB, hole2, 8),
                buildScore(tournament, playerB, hole3, 8),

                buildScore(tournament, playerC, hole1, 8),
                buildScore(tournament, playerC, hole2, 8),
                buildScore(tournament, playerC, hole3, 8),

                //Course 2
                buildScore(tournament, playerA, hole4, 8),
                buildScore(tournament, playerA, hole5, 8),
                buildScore(tournament, playerA, hole6, 8),

                buildScore(tournament, playerB, hole4, 1),
                buildScore(tournament, playerB, hole5, 1),
                buildScore(tournament, playerB, hole6, 1),

                buildScore(tournament, playerC, hole4, 8),
                buildScore(tournament, playerC, hole5, 8),
                buildScore(tournament, playerC, hole6, 8),

                //Course 3
                buildScore(tournament, playerA, hole7, 8),
                buildScore(tournament, playerA, hole8, 8),
                buildScore(tournament, playerA, hole9, 8),

                buildScore(tournament, playerB, hole7, 1),
                buildScore(tournament, playerB, hole8, 1),
                buildScore(tournament, playerB, hole9, 1),

                buildScore(tournament, playerC, hole7, 8),
                buildScore(tournament, playerC, hole8, 8),
                buildScore(tournament, playerC, hole9, 8)
        );

        when(scoreService.getScoresForTournamentById(anyInt())).thenReturn(scores);

        //WHEN
        tournamentEnricher.enrich(tournament);

        //THEN
        assertThat(course1.getWinnersByTournamentId().get(tournament.getId()).getId(), equalTo(playerA.getId()));
        assertThat(course2.getWinnersByTournamentId().get(tournament.getId()).getId(), equalTo(playerB.getId()));
        assertThat(course3.getWinnersByTournamentId().get(tournament.getId()).getId(), equalTo(playerB.getId()));
        assertThat(tournament.getWinner(), equalTo(playerB));
        verify(scoreService, times(1)).getScoresForTournamentById(anyInt());
    }

    @Test
    void testSettingTournamentWinnerWhenPlayersHaveEqualStrokesOnACourse() {
        //GIVEN
        CourseDto course1 = CourseDto.builder().courseName("Course 1").holes(Arrays.asList(hole1, hole2, hole3)).build();

        TournamentDto tournament = TournamentDto.builder()
                .courses(Arrays.asList(course1))
                .players(Arrays.asList(playerA, playerB))
                .build();

        course1.setHoles(Arrays.asList(hole1, hole2, hole3));

        List<ScoreDto> scores = Arrays.asList(
                buildScore(tournament, playerA, hole1, 3),
                buildScore(tournament, playerA, hole2, 3),
                buildScore(tournament, playerA, hole3, 2),

                buildScore(tournament, playerB, hole1, 3),
                buildScore(tournament, playerB, hole2, 4),
                buildScore(tournament, playerB, hole3, 1)
        );

        when(scoreService.getScoresForTournamentById(anyInt())).thenReturn(scores);

        //WHEN
        tournamentEnricher.enrich(tournament);

        //THEN
        assertThat(course1.getWinnersByTournamentId().get(tournament.getId()).getId(), equalTo(playerB.getId()));
        assertThat(tournament.getWinner().getId(), equalTo(playerB.getId()));
        verify(scoreService, times(1)).getScoresForTournamentById(anyInt());
    }

    private void holeSetUp() {
        hole1 = HoleDto.builder().id(1).par(2).holeNumber(1).build();
        hole2 = HoleDto.builder().id(2).par(3).holeNumber(2).build();
        hole3 = HoleDto.builder().id(3).par(3).holeNumber(3).build();
        hole4 = HoleDto.builder().id(4).par(2).holeNumber(4).build();
        hole5 = HoleDto.builder().id(5).par(3).holeNumber(5).build();
        hole6 = HoleDto.builder().id(6).par(2).holeNumber(6).build();
        hole7 = HoleDto.builder().id(7).par(2).holeNumber(7).build();
        hole8 = HoleDto.builder().id(8).par(3).holeNumber(8).build();
        hole9 = HoleDto.builder().id(9).par(3).holeNumber(9).build();
    }

    private void playersSetUp() {
        playerA = PlayerDto.builder().id(1).firstName("Player").lastName("A").build();
        playerB = PlayerDto.builder().id(2).firstName("Player").lastName("B").build();
        playerC = PlayerDto.builder().id(3).firstName("Player").lastName("C").build();
    }

    private ScoreDto buildScore(TournamentDto tournament, PlayerDto player, HoleDto hole, int strokes) {
        return ScoreDto.builder()
                .tournament(tournament)
                .player(player)
                .hole(hole)
                .strokes(strokes)
                .build();
    }

}
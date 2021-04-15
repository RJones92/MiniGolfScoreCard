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
class TournamentDtoEnricherImplTest {

    @Mock
    ScoreService scoreService;
    @InjectMocks
    TournamentDtoEnricherImpl tournamentEnricher;

    CourseDto course1;
    CourseDto course2;
    CourseDto course3;
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

    //Scenario's to test:
    //1. clear winner: each course has a clear winner with lowest number of strokes
    //2. a course equal lowest strokes, but with a hole-in-one winner


    @BeforeEach
    void setUp() {
        coursesSetUp();
        holeSetUp();
        playersSetUp();
    }

    @Test
    void testSettingWinner() {
        //GIVEN
        TournamentDto tournament = TournamentDto.builder()
                .courses(Arrays.asList(course1, course2, course3))
                .players(Arrays.asList(playerA, playerB, playerC))
                .build();

        course1.setHoles(Arrays.asList(hole1, hole2, hole3));
        course2.setHoles(Arrays.asList(hole4, hole5, hole6));
        course3.setHoles(Arrays.asList(hole7, hole8, hole9));

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
                buildScore(tournament, playerA, hole1, 8),
                buildScore(tournament, playerA, hole2, 8),
                buildScore(tournament, playerA, hole3, 8),

                buildScore(tournament, playerB, hole1, 1),
                buildScore(tournament, playerB, hole2, 1),
                buildScore(tournament, playerB, hole3, 1),

                buildScore(tournament, playerC, hole1, 8),
                buildScore(tournament, playerC, hole2, 8),
                buildScore(tournament, playerC, hole3, 8),

                //Course 3
                buildScore(tournament, playerA, hole1, 8),
                buildScore(tournament, playerA, hole2, 8),
                buildScore(tournament, playerA, hole3, 8),

                buildScore(tournament, playerB, hole1, 1),
                buildScore(tournament, playerB, hole2, 1),
                buildScore(tournament, playerB, hole3, 1),

                buildScore(tournament, playerC, hole1, 8),
                buildScore(tournament, playerC, hole2, 8),
                buildScore(tournament, playerC, hole3, 8)
        );

        when(scoreService.getScoresForTournamentById(anyInt())).thenReturn(scores);

        //WHEN
        tournamentEnricher.enrich(tournament);

        //THEN
        assertThat(tournament.getWinner(), equalTo(playerB));
        verify(scoreService, times(1)).getScoresForTournamentById(anyInt());
    }

    @Test
    void testPlayersWithEqualStrokesOnACourse() {
        //GIVEN
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
        assertThat(tournament.getWinner(), equalTo(playerB));
        verify(scoreService, times(1)).getScoresForTournamentById(anyInt());

    }

    private void coursesSetUp() {
        course1 = CourseDto.builder().build();
        course2 = CourseDto.builder().build();
        course3 = CourseDto.builder().build();
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
        playerA = PlayerDto.builder().build();
        playerB = PlayerDto.builder().build();
        playerC = PlayerDto.builder().build();
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
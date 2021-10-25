package com.golf.two_for_tom_open.model.enricher;

import com.golf.two_for_tom_open.model.dto.*;
import com.golf.two_for_tom_open.service.ScoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Year;
import java.util.ArrayList;
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

    @InjectMocks
    TournamentDtoEnricher tournamentEnricher;
    @Mock
    ScoreService scoreService;

    TournamentDto tournament1;

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

    @BeforeEach
    void setUp() {
        holeSetUp();
        courseSetUp();
        playersSetUp();
        tournamentSetUp();
    }

    @Test
    void testCourseWinner_Simple() {
        // GIVEN
        List<ScoreDto> playerAScores = Arrays.asList(
                buildScore(tournament1, playerA, hole1, 2),
                buildScore(tournament1, playerA, hole2, 2),
                buildScore(tournament1, playerA, hole3, 2),
                buildScore(tournament1, playerA, hole4, 2),
                buildScore(tournament1, playerA, hole5, 2),
                buildScore(tournament1, playerA, hole6, 2),
                buildScore(tournament1, playerA, hole7, 2),
                buildScore(tournament1, playerA, hole8, 2),
                buildScore(tournament1, playerA, hole9, 2)
                );
        List<ScoreDto> playerBScores = Arrays.asList(
                buildScore(tournament1, playerB, hole1, 1),
                buildScore(tournament1, playerB, hole2, 1),
                buildScore(tournament1, playerB, hole3, 1),
                buildScore(tournament1, playerB, hole4, 1),
                buildScore(tournament1, playerB, hole5, 1),
                buildScore(tournament1, playerB, hole6, 1),
                buildScore(tournament1, playerB, hole7, 1),
                buildScore(tournament1, playerB, hole8, 1),
                buildScore(tournament1, playerB, hole9, 1)
        );
        List<ScoreDto> playerCScores = Arrays.asList(
                buildScore(tournament1, playerC, hole1, 3),
                buildScore(tournament1, playerC, hole2, 3),
                buildScore(tournament1, playerC, hole3, 3),
                buildScore(tournament1, playerC, hole4, 3),
                buildScore(tournament1, playerC, hole5, 3),
                buildScore(tournament1, playerC, hole6, 3),
                buildScore(tournament1, playerC, hole7, 3),
                buildScore(tournament1, playerC, hole8, 3),
                buildScore(tournament1, playerC, hole9, 3)
        );

        List<ScoreDto> scores = new ArrayList<>();
        scores.addAll(playerAScores);
        scores.addAll(playerBScores);
        scores.addAll(playerCScores);

        when(scoreService.getScoresForTournamentById(1)).thenReturn(scores);

        // WHEN
        tournamentEnricher.enrich(tournament1);

        // THEN
        assertThat(course1.getWinnersByTournamentId().get(tournament1.getId()).getId(), equalTo(playerB.getId()));
        assertThat(course2.getWinnersByTournamentId().get(tournament1.getId()).getId(), equalTo(playerB.getId()));
        assertThat(course3.getWinnersByTournamentId().get(tournament1.getId()).getId(), equalTo(playerB.getId()));
        assertThat(tournament1.getWinner(), equalTo(playerB));
        verify(scoreService, times(1)).getScoresForTournamentById(anyInt());

    }

    @Test
    void testCourseWinner_Complex() {
        // GIVEN
        List<ScoreDto> playerAScores = Arrays.asList(
                buildScore(tournament1, playerA, hole1, 3),
                buildScore(tournament1, playerA, hole2, 4),
                buildScore(tournament1, playerA, hole3, 3),
                buildScore(tournament1, playerA, hole4, 7),
                buildScore(tournament1, playerA, hole5, 7),
                buildScore(tournament1, playerA, hole6, 7),
                buildScore(tournament1, playerA, hole7, 2),
                buildScore(tournament1, playerA, hole8, 2),
                buildScore(tournament1, playerA, hole9, 2) //37
        );
        List<ScoreDto> playerBScores = Arrays.asList(
                buildScore(tournament1, playerB, hole1, 3),
                buildScore(tournament1, playerB, hole2, 4),
                buildScore(tournament1, playerB, hole3, 4),
                buildScore(tournament1, playerB, hole4, 1),
                buildScore(tournament1, playerB, hole5, 1),
                buildScore(tournament1, playerB, hole6, 2),
                buildScore(tournament1, playerB, hole7, 6),
                buildScore(tournament1, playerB, hole8, 1),
                buildScore(tournament1, playerB, hole9, 5)//27
        );
        List<ScoreDto> playerCScores = Arrays.asList(
                buildScore(tournament1, playerC, hole1, 6),
                buildScore(tournament1, playerC, hole2, 7),
                buildScore(tournament1, playerC, hole3, 8),
                buildScore(tournament1, playerC, hole4, 3),
                buildScore(tournament1, playerC, hole5, 3),
                buildScore(tournament1, playerC, hole6, 3),
                buildScore(tournament1, playerC, hole7, 3),
                buildScore(tournament1, playerC, hole8, 3),
                buildScore(tournament1, playerC, hole9, 3) //39
        );

        List<ScoreDto> scores = new ArrayList<>();
        scores.addAll(playerAScores);
        scores.addAll(playerBScores);
        scores.addAll(playerCScores);

        when(scoreService.getScoresForTournamentById(1)).thenReturn(scores);

        // WHEN
        tournamentEnricher.enrich(tournament1);

        // THEN
        assertThat(course1.getWinnersByTournamentId().get(tournament1.getId()).getId(), equalTo(playerA.getId()));
        assertThat(course2.getWinnersByTournamentId().get(tournament1.getId()).getId(), equalTo(playerB.getId()));
        assertThat(course3.getWinnersByTournamentId().get(tournament1.getId()).getId(), equalTo(playerA.getId()));
        assertThat(tournament1.getWinner(), equalTo(playerA));
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

    private void courseSetUp() {
        course1 = CourseDto.builder()
                .courseName("course1")
                .holes(Arrays.asList(hole1, hole2, hole3))
                .build();
        course2 = CourseDto.builder()
                .courseName("course2")
                .holes(Arrays.asList(hole4, hole5, hole6))
                .build();
        course3 = CourseDto.builder()
                .courseName("course3")
                .holes(Arrays.asList(hole7, hole8, hole9))
                .build();
    }

    private void tournamentSetUp() {
        tournament1 = TournamentDto.builder()
                .id(1)
                .year(Year.of(2015))
                .courses(Arrays.asList(course1, course2, course3))
                .players(Arrays.asList(playerA, playerB, playerC))
                .build();
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
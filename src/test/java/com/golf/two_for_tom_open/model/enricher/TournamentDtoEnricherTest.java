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
import static org.hamcrest.Matchers.is;
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

    HoleDto hole1_course1;
    HoleDto hole2_course1;
    HoleDto hole3_course1;
    HoleDto hole4_course2;
    HoleDto hole5_course2;
    HoleDto hole6_course2;
    HoleDto hole7_course3;
    HoleDto hole8_course3;
    HoleDto hole9_course3;

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

    // TODO test when 2 players have same scores on one course but difering holes-in-one's

    @Test
    void testCourseWinner_Simple() {
        // GIVEN
        List<ScoreDto> playerAScores = Arrays.asList(
                buildScore(tournament1, playerA, hole1_course1, 2),
                buildScore(tournament1, playerA, hole2_course1, 2),
                buildScore(tournament1, playerA, hole3_course1, 2),
                buildScore(tournament1, playerA, hole4_course2, 2),
                buildScore(tournament1, playerA, hole5_course2, 2),
                buildScore(tournament1, playerA, hole6_course2, 2),
                buildScore(tournament1, playerA, hole7_course3, 2),
                buildScore(tournament1, playerA, hole8_course3, 2),
                buildScore(tournament1, playerA, hole9_course3, 2)
                );
        List<ScoreDto> playerBScores = Arrays.asList(
                buildScore(tournament1, playerB, hole1_course1, 1),
                buildScore(tournament1, playerB, hole2_course1, 1),
                buildScore(tournament1, playerB, hole3_course1, 1),
                buildScore(tournament1, playerB, hole4_course2, 1),
                buildScore(tournament1, playerB, hole5_course2, 1),
                buildScore(tournament1, playerB, hole6_course2, 1),
                buildScore(tournament1, playerB, hole7_course3, 1),
                buildScore(tournament1, playerB, hole8_course3, 1),
                buildScore(tournament1, playerB, hole9_course3, 1)
        );
        List<ScoreDto> playerCScores = Arrays.asList(
                buildScore(tournament1, playerC, hole1_course1, 3),
                buildScore(tournament1, playerC, hole2_course1, 3),
                buildScore(tournament1, playerC, hole3_course1, 3),
                buildScore(tournament1, playerC, hole4_course2, 3),
                buildScore(tournament1, playerC, hole5_course2, 3),
                buildScore(tournament1, playerC, hole6_course2, 3),
                buildScore(tournament1, playerC, hole7_course3, 3),
                buildScore(tournament1, playerC, hole8_course3, 3),
                buildScore(tournament1, playerC, hole9_course3, 3)
        );

        List<ScoreDto> scores = new ArrayList<>();
        scores.addAll(playerAScores);
        scores.addAll(playerBScores);
        scores.addAll(playerCScores);

        when(scoreService.getScoresForTournamentById(1)).thenReturn(scores);

        // WHEN
        tournamentEnricher.enrich(tournament1);

        // THEN
        assertThat(course1.getWinnerByTournamentId().get(tournament1.getId()).getId(), equalTo(playerB.getId()));
        assertThat(course2.getWinnerByTournamentId().get(tournament1.getId()).getId(), equalTo(playerB.getId()));
        assertThat(course3.getWinnerByTournamentId().get(tournament1.getId()).getId(), equalTo(playerB.getId()));
        assertThat(tournament1.getWinner(), equalTo(playerB));
        verify(scoreService, times(1)).getScoresForTournamentById(anyInt());

    }

    @Test
    void testCourseWinner_Complex() {
        // GIVEN
        List<ScoreDto> playerAScores = Arrays.asList(
                buildScore(tournament1, playerA, hole1_course1, 3),
                buildScore(tournament1, playerA, hole2_course1, 4),
                buildScore(tournament1, playerA, hole3_course1, 3),
                buildScore(tournament1, playerA, hole4_course2, 7),
                buildScore(tournament1, playerA, hole5_course2, 7),
                buildScore(tournament1, playerA, hole6_course2, 7),
                buildScore(tournament1, playerA, hole7_course3, 2),
                buildScore(tournament1, playerA, hole8_course3, 2),
                buildScore(tournament1, playerA, hole9_course3, 2) //37
        );
        List<ScoreDto> playerBScores = Arrays.asList(
                buildScore(tournament1, playerB, hole1_course1, 3),
                buildScore(tournament1, playerB, hole2_course1, 4),
                buildScore(tournament1, playerB, hole3_course1, 4),
                buildScore(tournament1, playerB, hole4_course2, 1),
                buildScore(tournament1, playerB, hole5_course2, 1),
                buildScore(tournament1, playerB, hole6_course2, 2),
                buildScore(tournament1, playerB, hole7_course3, 6),
                buildScore(tournament1, playerB, hole8_course3, 1),
                buildScore(tournament1, playerB, hole9_course3, 5)//27
        );
        List<ScoreDto> playerCScores = Arrays.asList(
                buildScore(tournament1, playerC, hole1_course1, 6),
                buildScore(tournament1, playerC, hole2_course1, 7),
                buildScore(tournament1, playerC, hole3_course1, 8),
                buildScore(tournament1, playerC, hole4_course2, 3),
                buildScore(tournament1, playerC, hole5_course2, 3),
                buildScore(tournament1, playerC, hole6_course2, 3),
                buildScore(tournament1, playerC, hole7_course3, 3),
                buildScore(tournament1, playerC, hole8_course3, 3),
                buildScore(tournament1, playerC, hole9_course3, 3) //39
        );

        List<ScoreDto> scores = new ArrayList<>();
        scores.addAll(playerAScores);
        scores.addAll(playerBScores);
        scores.addAll(playerCScores);

        when(scoreService.getScoresForTournamentById(1)).thenReturn(scores);

        // WHEN
        tournamentEnricher.enrich(tournament1);

        // THEN
        assertThat(course1.getWinnerByTournamentId().get(tournament1.getId()).getId(), equalTo(playerA.getId()));
        assertThat(course2.getWinnerByTournamentId().get(tournament1.getId()).getId(), equalTo(playerB.getId()));
        assertThat(course3.getWinnerByTournamentId().get(tournament1.getId()).getId(), equalTo(playerA.getId()));
        assertThat(tournament1.getWinner(), equalTo(playerA));
        verify(scoreService, times(1)).getScoresForTournamentById(anyInt());

    }

    @Test
    void testCourseWinner_JointCourseWins() {
        // GIVEN
        List<ScoreDto> playerAScores = Arrays.asList(
                buildScore(tournament1, playerA, hole1_course1, 3),
                buildScore(tournament1, playerA, hole2_course1, 4),
                buildScore(tournament1, playerA, hole3_course1, 5), //(12)

                buildScore(tournament1, playerA, hole4_course2, 7),
                buildScore(tournament1, playerA, hole5_course2, 7),
                buildScore(tournament1, playerA, hole6_course2, 7), //(21)

                buildScore(tournament1, playerA, hole7_course3, 2),
                buildScore(tournament1, playerA, hole8_course3, 2),
                buildScore(tournament1, playerA, hole9_course3, 2) //(6), 39
        );
        List<ScoreDto> playerBScores = Arrays.asList(
                buildScore(tournament1, playerB, hole1_course1, 3),
                buildScore(tournament1, playerB, hole2_course1, 4),
                buildScore(tournament1, playerB, hole3_course1, 4), //(11)

                buildScore(tournament1, playerB, hole4_course2, 7),
                buildScore(tournament1, playerB, hole5_course2, 7),
                buildScore(tournament1, playerB, hole6_course2, 7), //(21)

                buildScore(tournament1, playerB, hole7_course3, 6),
                buildScore(tournament1, playerB, hole8_course3, 1),
                buildScore(tournament1, playerB, hole9_course3, 5) //(12), 44
        );
        List<ScoreDto> playerCScores = Arrays.asList(
                buildScore(tournament1, playerC, hole1_course1, 9),
                buildScore(tournament1, playerC, hole2_course1, 9),
                buildScore(tournament1, playerC, hole3_course1, 9), //(27)

                buildScore(tournament1, playerC, hole4_course2, 9),
                buildScore(tournament1, playerC, hole5_course2, 9),
                buildScore(tournament1, playerC, hole6_course2, 9), //(27)

                buildScore(tournament1, playerC, hole7_course3, 9),
                buildScore(tournament1, playerC, hole8_course3, 9),
                buildScore(tournament1, playerC, hole9_course3, 9) //(27), 81
        );

        List<ScoreDto> scores = new ArrayList<>();
        scores.addAll(playerAScores);
        scores.addAll(playerBScores);
        scores.addAll(playerCScores);

        when(scoreService.getScoresForTournamentById(1)).thenReturn(scores);

        // WHEN
        tournamentEnricher.enrich(tournament1);

        // THEN
        assertThat(tournament1.getWinner(), is(playerA));
    }

    private void holeSetUp() {
        hole1_course1 = HoleDto.builder().id(1).par(2).holeNumber(1).build();
        hole2_course1 = HoleDto.builder().id(2).par(3).holeNumber(2).build();
        hole3_course1 = HoleDto.builder().id(3).par(3).holeNumber(3).build();
        hole4_course2 = HoleDto.builder().id(4).par(2).holeNumber(1).build();
        hole5_course2 = HoleDto.builder().id(5).par(3).holeNumber(2).build();
        hole6_course2 = HoleDto.builder().id(6).par(2).holeNumber(3).build();
        hole7_course3 = HoleDto.builder().id(7).par(2).holeNumber(1).build();
        hole8_course3 = HoleDto.builder().id(8).par(3).holeNumber(2).build();
        hole9_course3 = HoleDto.builder().id(9).par(3).holeNumber(3).build();
    }

    private void playersSetUp() {
        playerA = PlayerDto.builder().id(1).firstName("Player").lastName("A").build();
        playerB = PlayerDto.builder().id(2).firstName("Player").lastName("B").build();
        playerC = PlayerDto.builder().id(3).firstName("Player").lastName("C").build();
    }

    private void courseSetUp() {
        course1 = CourseDto.builder()
                .courseName("course1")
                .holes(Arrays.asList(hole1_course1, hole2_course1, hole3_course1))
                .build();
        course2 = CourseDto.builder()
                .courseName("course2")
                .holes(Arrays.asList(hole4_course2, hole5_course2, hole6_course2))
                .build();
        course3 = CourseDto.builder()
                .courseName("course3")
                .holes(Arrays.asList(hole7_course3, hole8_course3, hole9_course3))
                .build();
    }

    private void tournamentSetUp() {
        tournament1 = TournamentDto.builder()
                .id(1)
                ._year(Year.of(2015))
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
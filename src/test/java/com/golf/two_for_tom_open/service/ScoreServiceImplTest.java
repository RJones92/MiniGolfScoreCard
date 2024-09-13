package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.dto.ScoreDto;
import com.golf.two_for_tom_open.model.entity.Player;
import com.golf.two_for_tom_open.model.entity.Score;
import com.golf.two_for_tom_open.model.mapper.ScoreMapper;
import com.golf.two_for_tom_open.repository.ScoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScoreServiceImplTest {
    private static final String PLAYER_X_FIRST_NAME = "John";
    private static final String PLAYER_X_LAST_NAME = "Smith";

    @InjectMocks
    ScoreServiceImpl scoreService;
    @Mock
    ScoreRepository scoreRepository;
    ScoreMapper scoreMapper = Mappers.getMapper(ScoreMapper.class);

    private final Player playerX = Player.builder().firstName(PLAYER_X_FIRST_NAME).lastName(PLAYER_X_LAST_NAME).build();
    private final Score score1ForPlayerX = Score.builder().player(playerX).strokes(2).build();
    private final Score score2ForPlayerX = Score.builder().player(playerX).strokes(5).build();

    @BeforeEach
    void setUp() {
        scoreService = new ScoreServiceImpl(scoreRepository, scoreMapper);
    }

    @Test
    void testGetAll() {
        when(scoreRepository.findAll()).thenReturn(Arrays.asList(score1ForPlayerX, score2ForPlayerX));

        List<ScoreDto> scores = scoreService.getAll();

        assertThat(scores, contains(scoreMapper.scoreEntityToDto(score1ForPlayerX), scoreMapper.scoreEntityToDto(score2ForPlayerX)));
        assertThat(scores, hasSize(2));
        verify(scoreRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        when(scoreRepository.save(score1ForPlayerX)).thenReturn(score1ForPlayerX);
        Score score = scoreService.save(score1ForPlayerX);
        assertThat(score, is(score1ForPlayerX));
    }

    @Test
    void getScoresForPlayerById() {
        int playerId = 0;
        when(scoreRepository.findScoresForPlayerById(playerId)).thenReturn(Arrays.asList(score1ForPlayerX, score2ForPlayerX));

        List<ScoreDto> scores = scoreService.getScoresForPlayerById(playerId);

        assertThat(scores, hasSize(2));
        assertThat(scores.get(0).getStrokes(), is(score1ForPlayerX.getStrokes()));
        assertThat(scores.get(0).getPlayer().getFirstName(), is(score1ForPlayerX.getPlayer().getFirstName()));
        assertThat(scores.get(0).getPlayer().getLastName(), is(score1ForPlayerX.getPlayer().getLastName()));
        assertThat(scores.get(1).getStrokes(), is(score2ForPlayerX.getStrokes()));
        assertThat(scores.get(1).getPlayer().getFirstName(), is(score2ForPlayerX.getPlayer().getFirstName()));
        assertThat(scores.get(1).getPlayer().getLastName(), is(score2ForPlayerX.getPlayer().getLastName()));
        verify(scoreRepository, times(1)).findScoresForPlayerById(anyInt());
    }

    @Test
    void getScoresForPlayerByName() {
        when(scoreRepository.findScoresForPlayerByName(PLAYER_X_FIRST_NAME, PLAYER_X_LAST_NAME)).thenReturn(Arrays.asList(score1ForPlayerX, score2ForPlayerX));

        List<ScoreDto> scores = scoreService.getScoresForPlayerByName(PLAYER_X_FIRST_NAME, PLAYER_X_LAST_NAME);

        assertThat(scores, hasSize(2));
        assertThat(scores.get(0).getStrokes(), is(score1ForPlayerX.getStrokes()));
        assertThat(scores.get(0).getPlayer().getFirstName(), is(score1ForPlayerX.getPlayer().getFirstName()));
        assertThat(scores.get(0).getPlayer().getLastName(), is(score1ForPlayerX.getPlayer().getLastName()));
        assertThat(scores.get(1).getStrokes(), is(score2ForPlayerX.getStrokes()));
        assertThat(scores.get(1).getPlayer().getFirstName(), is(score2ForPlayerX.getPlayer().getFirstName()));
        assertThat(scores.get(1).getPlayer().getLastName(), is(score2ForPlayerX.getPlayer().getLastName()));
        verify(scoreRepository, times(1)).findScoresForPlayerByName(anyString(), anyString());
    }

    @Test
    void testGetScoresForTournamentByYear() {
        when(scoreRepository.findScoresForTournamentByYear(any(Year.class))).thenReturn(Arrays.asList(score1ForPlayerX, score2ForPlayerX));

        List<ScoreDto> scores = scoreService.getScoresForTournamentByYear(Year.of(2000));

        assertThat(scores, hasSize(2));
        assertThat(scores.get(0).getStrokes(), is(score1ForPlayerX.getStrokes()));
        assertThat(scores.get(0).getPlayer().getFirstName(), is(score1ForPlayerX.getPlayer().getFirstName()));
        assertThat(scores.get(0).getPlayer().getLastName(), is(score1ForPlayerX.getPlayer().getLastName()));
        assertThat(scores.get(1).getStrokes(), is(score2ForPlayerX.getStrokes()));
        assertThat(scores.get(1).getPlayer().getFirstName(), is(score2ForPlayerX.getPlayer().getFirstName()));
        assertThat(scores.get(1).getPlayer().getLastName(), is(score2ForPlayerX.getPlayer().getLastName()));
        verify(scoreRepository, times(1)).findScoresForTournamentByYear(any(Year.class));
    }

    @Test
    void testGetScoresForTournamentById() {
        when(scoreRepository.findAllByTournamentId(anyInt())).thenReturn(Arrays.asList(score1ForPlayerX, score2ForPlayerX));

        List<ScoreDto> scores = scoreService.getScoresForTournamentById(1);

        assertThat(scores, hasSize(2));
        assertThat(scores.get(0).getStrokes(), is(score1ForPlayerX.getStrokes()));
        assertThat(scores.get(0).getPlayer().getFirstName(), is(score1ForPlayerX.getPlayer().getFirstName()));
        assertThat(scores.get(0).getPlayer().getLastName(), is(score1ForPlayerX.getPlayer().getLastName()));
        assertThat(scores.get(1).getStrokes(), is(score2ForPlayerX.getStrokes()));
        assertThat(scores.get(1).getPlayer().getFirstName(), is(score2ForPlayerX.getPlayer().getFirstName()));
        assertThat(scores.get(1).getPlayer().getLastName(), is(score2ForPlayerX.getPlayer().getLastName()));
        verify(scoreRepository, times(1)).findAllByTournamentId(anyInt());
    }
}
package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.entity.Player;
import com.golf.two_for_tom_open.model.entity.Score;
import com.golf.two_for_tom_open.model.mapper.ScoreMapper;
import com.golf.two_for_tom_open.repository.ScoreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScoreServiceImplTest {

    @InjectMocks
    ScoreService scoreService = new ScoreServiceImpl();

    @Mock
    ScoreRepository scoreRepository;

    @Spy
    ScoreMapper scoreMapper = Mappers.getMapper(ScoreMapper.class);

    private static final String PLAYER_X_FIRST_NAME = "John";
    private static final String PLAYER_X_LAST_NAME = "Smith";
    private final Player playerX = Player.builder().firstName(PLAYER_X_FIRST_NAME).lastName(PLAYER_X_LAST_NAME).build();
    private final Score score1ForPlayerX = Score.builder().player(playerX).strokes(2).build();
    private final Score score2ForPlayerX = Score.builder().player(playerX).strokes(5).build();

    @Test
    void getAll() {
        when(scoreRepository.findAll()).thenReturn(Arrays.asList(score1ForPlayerX, score2ForPlayerX));
        List<Score> scores = scoreService.getAll();
        assertThat(scores, contains(score1ForPlayerX, score2ForPlayerX));
        assertThat(scores, hasSize(2));
    }

    @Test
    void save() {
        when(scoreRepository.save(score1ForPlayerX)).thenReturn(score1ForPlayerX);
        Score score = scoreService.save(score1ForPlayerX);
        assertThat(score, is(score1ForPlayerX));
    }

    @Test
    void getScoresForPlayerById() {
        int playerId = 0;
        when(scoreRepository.findScoresForPlayerById(playerId)).thenReturn(Arrays.asList(score1ForPlayerX, score2ForPlayerX));

        List<Score> scores = scoreService.getScoresForPlayerById(playerId);

        assertThat(scores, hasSize(2));
        assertThat(scores.get(0).getStrokes(), is(score1ForPlayerX.getStrokes()));
        assertThat(scores.get(0).getPlayer().getFirstName(), is(score1ForPlayerX.getPlayer().getFirstName()));
        assertThat(scores.get(0).getPlayer().getLastName(), is(score1ForPlayerX.getPlayer().getLastName()));
        assertThat(scores.get(1).getStrokes(), is(score2ForPlayerX.getStrokes()));
        assertThat(scores.get(1).getPlayer().getFirstName(), is(score2ForPlayerX.getPlayer().getFirstName()));
        assertThat(scores.get(1).getPlayer().getLastName(), is(score2ForPlayerX.getPlayer().getLastName()));
    }

    @Test
    void getScoresForPlayerByName() {
        when(scoreRepository.findScoresForPlayerByName(PLAYER_X_FIRST_NAME, PLAYER_X_LAST_NAME)).thenReturn(Arrays.asList(score1ForPlayerX, score2ForPlayerX));

        List<Score> scores = scoreService.getScoresForPlayerByName(PLAYER_X_FIRST_NAME, PLAYER_X_LAST_NAME);

        assertThat(scores, hasSize(2));
        assertThat(scores.get(0).getStrokes(), is(score1ForPlayerX.getStrokes()));
        assertThat(scores.get(0).getPlayer().getFirstName(), is(score1ForPlayerX.getPlayer().getFirstName()));
        assertThat(scores.get(0).getPlayer().getLastName(), is(score1ForPlayerX.getPlayer().getLastName()));
        assertThat(scores.get(1).getStrokes(), is(score2ForPlayerX.getStrokes()));
        assertThat(scores.get(1).getPlayer().getFirstName(), is(score2ForPlayerX.getPlayer().getFirstName()));
        assertThat(scores.get(1).getPlayer().getLastName(), is(score2ForPlayerX.getPlayer().getLastName()));
    }
}
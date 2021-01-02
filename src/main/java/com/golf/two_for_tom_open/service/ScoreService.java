package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.entity.Score;

public interface ScoreService extends BaseService<Score> {

    List<Score> getScoresForPlayerById(int playerId);

    List<Score> getScoresForPlayerByName(String firstName, String lastName);

    ScoresDto getScoresForPlayerByName(String firstName, String lastName);

    }

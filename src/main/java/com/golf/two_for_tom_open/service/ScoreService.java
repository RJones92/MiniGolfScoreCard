package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.dto.ScoresDto;
import com.golf.two_for_tom_open.model.entity.Score;

public interface ScoreService extends BaseService<Score> {

    ScoresDto getScoresForPlayerById(int playerId);

    ScoresDto getScoresForPlayerByName(String firstName, String lastName);

    }

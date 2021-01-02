package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.entity.Score;

import java.time.Year;
import java.util.List;

public interface ScoreService extends BaseService<Score> {

    List<Score> getScoresForPlayerById(int playerId);

    List<Score> getScoresForPlayerByName(String firstName, String lastName);

    List<Score> getScoresForTournamentByYear(Year tournamentYear);

    }

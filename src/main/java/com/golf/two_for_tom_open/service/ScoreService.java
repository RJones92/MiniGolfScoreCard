package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.dto.ScoreDto;
import com.golf.two_for_tom_open.model.entity.Score;

import java.time.Year;
import java.util.Collection;
import java.util.List;

public interface ScoreService extends BaseService<Score> {

    List<ScoreDto> getScoresForPlayerById(int playerId);

    List<ScoreDto> getScoresForPlayerByName(String firstName, String lastName);

    List<ScoreDto> getScoresForTournamentByYear(Year tournamentYear);

    List<ScoreDto> getScoresForTournamentById(int tournamentId);

    List<ScoreDto> getScoresForTournamentsById(Collection<Integer> tournamentIds);

    List<ScoreDto> getAllScoreDto();

    }

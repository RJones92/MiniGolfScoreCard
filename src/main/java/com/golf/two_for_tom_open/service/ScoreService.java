package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.dto.ScoreDto;
import com.golf.two_for_tom_open.model.entity.Score;

import java.time.Year;
import java.util.Collection;
import java.util.List;

public abstract class ScoreService implements BaseRepositoryService<Score>, BaseDtoService<ScoreDto> {

    abstract List<ScoreDto> getScoresForPlayerById(int playerId);

    abstract List<ScoreDto> getScoresForPlayerByName(String firstName, String lastName);

    abstract List<ScoreDto> getScoresForTournamentByYear(Year tournamentYear);

    public abstract List<ScoreDto> getScoresForTournamentById(int tournamentId);

    public abstract List<ScoreDto> getScoresForTournamentsById(Collection<Integer> tournamentIds);

}

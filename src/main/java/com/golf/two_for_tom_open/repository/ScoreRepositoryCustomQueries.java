package com.golf.two_for_tom_open.repository;

import com.golf.two_for_tom_open.model.entity.Score;
import com.golf.two_for_tom_open.model.entity.Tournament;

import java.time.Year;
import java.util.List;

public interface ScoreRepositoryCustomQueries {

    List<Score> findScoresForPlayerById(int playerId);

    List<Score> findScoresForPlayerByName(String firstName, String lastName);

    List<Score> findScoresForTournament(Tournament tournament);

    List<Score> findScoresForTournamentByYear(Year year);

    }

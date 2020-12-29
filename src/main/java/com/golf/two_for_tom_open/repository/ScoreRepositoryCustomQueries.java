package com.golf.two_for_tom_open.repository;

import com.golf.two_for_tom_open.model.entity.Score;

import java.util.List;

public interface ScoreRepositoryCustomQueries {

    List<Score> findScoresForPlayerById(int playerId);

    List<Score> findScoresForPlayerByName(String firstName, String lastName);

}

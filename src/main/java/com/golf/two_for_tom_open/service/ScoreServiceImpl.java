package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.controller.ScoreController;
import com.golf.two_for_tom_open.model.entity.Score;
import com.golf.two_for_tom_open.model.mapper.ScoreMapper;
import com.golf.two_for_tom_open.repository.ScoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Year;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {
    private static Logger logger = LoggerFactory.getLogger(ScoreController.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    ScoreMapper scoreMapper;

    @Override
    public List<Score> getAll() {
        return scoreRepository.findAll();
    }

    @Override
    public Score save(Score score) {
        return scoreRepository.save(score);
    }

    @Override
    public List<Score> getScoresForPlayerById(int playerId) {
        try {
            return scoreRepository.findScoresForPlayerById(playerId);
        } catch (Exception e) {
            logger.error("Error retrieving scores for player ID: {}", playerId);
            logger.error("Error message thrown is: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<Score> getScoresForPlayerByName(String firstName, String lastName) {
        try {
            return scoreRepository.findScoresForPlayerByName(firstName, lastName);
        } catch (Exception e) {
            logger.error("Error retrieving scores for player name: {} {}", firstName, lastName);
            logger.info("Error message thrown is: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<Score> getScoresForTournamentByYear(Year tournamentYear) {
        //TODO test me
        try {
            return scoreRepository.findScoresForTournamentByYear(tournamentYear);
        } catch (Exception e) {
            logger.error("Error retrieving scores for tournament with year: {}", tournamentYear.getValue());
            logger.info("Error message thrown is: {}", e.getMessage());
            return null;
        }

    }

    @Override
    public List<Score> getScoresForTournamentById(int tournamentId) {
        //TODO implement me
        try {
            List<Score> scores = scoreRepository.findAllByTournamentId(tournamentId);
            return scores;
        } catch (Exception e) {
            logger.error("Error retrieving scores for tournament ID: {}", tournamentId);
            logger.error(e.getMessage());
        }
        return null;
    }
}

package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.controller.ScoreController;
import com.golf.two_for_tom_open.model.dto.ScoreDto;
import com.golf.two_for_tom_open.model.entity.Score;
import com.golf.two_for_tom_open.model.mapper.ScoreMapper;
import com.golf.two_for_tom_open.repository.ScoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Year;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreServiceImpl implements ScoreService {
    private static Logger logger = LoggerFactory.getLogger(ScoreController.class);

    @PersistenceContext
    private EntityManager entityManager;
    private final ScoreRepository scoreRepository;
    private final ScoreMapper scoreMapper;

    public ScoreServiceImpl(ScoreRepository scoreRepository, ScoreMapper scoreMapper) {
        this.scoreRepository = scoreRepository;
        this.scoreMapper = scoreMapper;
    }

    @Override
    public List<Score> getAll() {
        return scoreRepository.findAll();
    }

    @Override
    public List<ScoreDto> getAllScoreDto() {
        List<Score> scores = this.getAll();
        return map(scores);
    }

    @Override
    public Score save(Score score) {
        return scoreRepository.save(score);
    }

    @Override
    public List<ScoreDto> getScoresForPlayerById(int playerId) {
        try {
            List<Score> scores = scoreRepository.findScoresForPlayerById(playerId);
            return map(scores);
        } catch (Exception e) {
            logger.error("Error retrieving scores for player ID: {}", playerId);
            logger.error("Error message thrown is: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<ScoreDto> getScoresForPlayerByName(String firstName, String lastName) {
        try {
            List<Score> scores = scoreRepository.findScoresForPlayerByName(firstName, lastName);
            return map(scores);
        } catch (Exception e) {
            logger.error("Error retrieving scores for player name: {} {}", firstName, lastName);
            logger.info("Error message thrown is: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<ScoreDto> getScoresForTournamentByYear(Year tournamentYear) {
        try {
            List<Score> scores = scoreRepository.findScoresForTournamentByYear(tournamentYear);
            return map(scores);
        } catch (Exception e) {
            logger.error("Error retrieving scores for tournament with year: {}", tournamentYear.getValue());
            logger.info("Error message thrown is: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<ScoreDto> getScoresForTournamentById(int tournamentId) {
        try {
            List<Score> scores = scoreRepository.findAllByTournamentId(tournamentId);
            return map(scores);
        } catch (Exception e) {
            logger.error("Error retrieving scores for tournament ID: {}", tournamentId);
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<ScoreDto> getScoresForTournamentsById(Collection<Integer> tournamentIds) {
        try {
            List<Score> scores = scoreRepository.findDistinctScoresByTournamentIdIn(tournamentIds);
            return map(scores);
        } catch (Exception e) {
            logger.error("Error retrieving scores for tournament ID's.");
            logger.error(e.getMessage());
        }
        return null;
    }

    private List<ScoreDto> map(List<Score> scores) {
        return scores.stream()
                .map(scoreMapper::scoreEntityToDto)
                .collect(Collectors.toList());
    }

}

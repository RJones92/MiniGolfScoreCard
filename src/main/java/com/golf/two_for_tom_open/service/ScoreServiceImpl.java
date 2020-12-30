package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.controller.ScoreController;
import com.golf.two_for_tom_open.model.dto.ScoreDto;
import com.golf.two_for_tom_open.model.dto.ScoresDto;
import com.golf.two_for_tom_open.model.entity.Score;
import com.golf.two_for_tom_open.model.mapper.ScoreMapper;
import com.golf.two_for_tom_open.repository.ScoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

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
    public ScoresDto getScoresForPlayerById(int playerId) {
        try {
            List<Score> scoreEntities = scoreRepository.findScoresForPlayerById(playerId);
            List<ScoreDto> scoreDtos = scoreEntities.stream()
                    .map(scoreEntity -> scoreMapper.scoreEntityToScoreDto(scoreEntity))
                    .collect(Collectors.toList());
            return ScoresDto.builder().scores(scoreDtos).build();
        } catch (Exception e) {
            logger.error("Error retrieving scores for player ID: {}", playerId);
            logger.info("Error message thrown is: {}", e.getMessage());
            return null;
        }

    }

    @Override
    public ScoresDto getScoresForPlayerByName(String firstName, String lastName) {
        try {
            List<Score> scoreEntities = scoreRepository.findScoresForPlayerByName(firstName, lastName);
            List<ScoreDto> scoreDtos = scoreEntities.stream()
                    .map(scoreEntity -> scoreMapper.scoreEntityToScoreDto(scoreEntity))
                    .collect(Collectors.toList());
            return ScoresDto.builder().scores(scoreDtos).build();
        } catch (Exception e) {
            logger.error("Error retrieving scores for player name: {} {}", firstName, lastName);
            logger.info("Error message thrown is: {}", e.getMessage());
            return null;
        }    }
}

package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.entity.Score;
import com.golf.two_for_tom_open.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ScoreRepository scoreRepository;

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
        return scoreRepository.findScoresForPlayerById(playerId);
    }

    @Override
    public List<Score> getScoresForPlayerByName(String firstName, String lastName) {
        return scoreRepository.findScoresForPlayerByName(firstName, lastName);
    }
}

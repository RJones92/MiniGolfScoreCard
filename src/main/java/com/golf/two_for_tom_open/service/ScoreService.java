package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.Score;
import com.golf.two_for_tom_open.repository.ScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {

    private ScoreRepository scoreRepository;

    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public List<Score> getAll() {
        return scoreRepository.findAll();
    }

    public Score save(Score score) {
        return scoreRepository.save(score);
    }

}

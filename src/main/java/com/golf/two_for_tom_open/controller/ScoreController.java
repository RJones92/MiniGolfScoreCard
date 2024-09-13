package com.golf.two_for_tom_open.controller;

import com.golf.two_for_tom_open.model.dto.ScoreDto;
import com.golf.two_for_tom_open.service.ScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") //required for local development
@RequestMapping("/api/scores")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<List<ScoreDto>> getScores() {
        List<ScoreDto> scores = scoreService.getAll();
        return scores.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(scores);
    }

}

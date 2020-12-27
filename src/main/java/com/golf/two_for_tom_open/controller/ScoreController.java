package com.golf.two_for_tom_open.controller;

import com.golf.two_for_tom_open.model.Score;
import com.golf.two_for_tom_open.service.ScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/scores/v1")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @RequestMapping(value = "/player/{playerId}")
    public ResponseEntity<?> getScoresForPlayerId(@PathVariable int playerId) {
        List<Score> scores = scoreService.getScoresForPlayerId(playerId);
        return ResponseEntity.ok(scores);
    }

}

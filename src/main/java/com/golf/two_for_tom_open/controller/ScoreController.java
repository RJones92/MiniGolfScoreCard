package com.golf.two_for_tom_open.controller;

import com.golf.two_for_tom_open.model.entity.Score;
import com.golf.two_for_tom_open.service.ScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/scores/v1")
public class ScoreController {
    private static Logger logger = LoggerFactory.getLogger(ScoreController.class);
    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @RequestMapping(value = "/player/{playerId}")
    public ResponseEntity<?> getScoresForPlayerId(@PathVariable int playerId) {
        List<Score> scores = scoreService.getScoresForPlayerId(playerId);

        if (!scores.isEmpty()) {
            logger.info("There are {} results returned for player ID {}.", scores.size(), playerId);
            return ResponseEntity.ok(scores);
        } else {
            logger.info("There are no results for player ID {}.", playerId);
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value = "/player")
    public ResponseEntity<?> getScoresForPlayerName(@RequestParam String firstName,
                                                    @RequestParam String lastName) {
        List<Score> scores = scoreService.getScoresForPlayerName(firstName, lastName);

        if (!scores.isEmpty()) {
            logger.info("There are {} results returned for player {} {}.", scores.size(), firstName, lastName);
            return ResponseEntity.ok(scores);
        } else {
            logger.info("There are no results for player {} {}.", firstName, lastName);
            return ResponseEntity.noContent().build();
        }
    }

}
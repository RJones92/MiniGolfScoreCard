package com.golf.two_for_tom_open.controller;

import com.golf.two_for_tom_open.model.dto.ScoreDto;
import com.golf.two_for_tom_open.model.entity.Score;
import com.golf.two_for_tom_open.model.mapper.ScoreMapper;
import com.golf.two_for_tom_open.service.ScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {
    private static Logger logger = LoggerFactory.getLogger(ScoreController.class);
    private final ScoreService scoreService;
    private final ScoreMapper scoreMapper;

    public ScoreController(ScoreService scoreService, ScoreMapper scoreMapper) {
        this.scoreService = scoreService;
        this.scoreMapper = scoreMapper;
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<?> getScores() {
        List<Score> scores = scoreService.getAll();

        List<ScoreDto> scoresDto = scores.stream()
                .map(scoreMapper::scoreEntityToDto)
                .collect(Collectors.toList());

        return getResponseEntityFromListOfScores(scoresDto, " all scores");
    }

    @GetMapping(value = "/player/{playerId}")
    public ResponseEntity<?> getScoresForPlayerId(@PathVariable int playerId) {

        List<Score> scores = scoreService.getScoresForPlayerById(playerId);
        List<ScoreDto> scoresDto = scores.stream()
                .map(scoreMapper::scoreEntityToDto)
                .collect(Collectors.toList());

        return getResponseEntityFromListOfScores(scoresDto, "player ID " + playerId);
    }

    @GetMapping(value = "/player")
    public ResponseEntity<?> getScoresForPlayerName(@RequestParam String firstName, @RequestParam String lastName) {

        List<Score> scores = scoreService.getScoresForPlayerByName(firstName, lastName);
        List<ScoreDto> scoresDto = scores.stream()
                .map(scoreMapper::scoreEntityToDto)
                .collect(Collectors.toList());

        return getResponseEntityFromListOfScores(scoresDto, "player " + firstName + " " + lastName);
    }

    @GetMapping(value = "/tournaments/{tournamentYear}")
    public ResponseEntity<?> getScoresForTournamentYear(@PathVariable int tournamentYear) {
        List<Score> scores = scoreService.getScoresForTournamentByYear(Year.of(tournamentYear));
        List<ScoreDto> scoresDto = scores.stream()
                .map(scoreMapper::scoreEntityToDto)
                .collect(Collectors.toList());

        return getResponseEntityFromListOfScores(scoresDto, "tournament year " + tournamentYear);

    }

    private ResponseEntity<?> getResponseEntityFromListOfScores(List<ScoreDto> scores,String textToIdentifyRequestedObjects) {
        if (!scores.isEmpty()) {
            logger.info("There are {} results returned for {}.", scores.size(), textToIdentifyRequestedObjects);
            return ResponseEntity.ok(scores);
        } else {
            logger.info("There are no results for {}.", textToIdentifyRequestedObjects);
            return ResponseEntity.noContent().build();
        }
    }

}

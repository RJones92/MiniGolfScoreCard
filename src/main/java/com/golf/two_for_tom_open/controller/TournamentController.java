package com.golf.two_for_tom_open.controller;

import com.golf.two_for_tom_open.model.dto.TournamentDto;
import com.golf.two_for_tom_open.service.TournamentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/tournaments")
public class TournamentController {
    private static Logger logger = LoggerFactory.getLogger(TournamentController.class);

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<?> getAllTournaments() {
        logger.info("Request to retrieve all tournaments received.");

        List<TournamentDto> tournaments = tournamentService.getAllTournamentDto();

        logger.info("All tournaments received and being returned to consumer");
        return ResponseEntity.ok(tournaments);
    }
}

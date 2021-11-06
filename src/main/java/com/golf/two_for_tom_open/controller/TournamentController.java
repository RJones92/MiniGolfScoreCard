package com.golf.two_for_tom_open.controller;

import com.golf.two_for_tom_open.service.TournamentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/tournaments")
public class TournamentController {
    private static Logger logger = LoggerFactory.getLogger(TournamentController.class);

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<?> getAllTournaments(@RequestParam(defaultValue = "true", required = false) boolean simple) {
        logger.info("Request to retrieve all tournaments received.");

        return simple ?
               ResponseEntity.ok(tournamentService.getAllTournamentLiteDtos()) :
               ResponseEntity.ok(tournamentService.getAllTournamentDtos());
    }
}

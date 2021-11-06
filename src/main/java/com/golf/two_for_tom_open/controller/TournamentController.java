package com.golf.two_for_tom_open.controller;

import com.golf.two_for_tom_open.service.TournamentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<?> getAllTournaments(@RequestParam(defaultValue = "true", required = false) boolean complex) {
        log.info("Request to retrieve all tournaments received.");

        return complex ?
                ResponseEntity.ok(tournamentService.getAllTournamentDtos()) :
                ResponseEntity.ok(tournamentService.getAllTournamentLiteDtos());
    }
}

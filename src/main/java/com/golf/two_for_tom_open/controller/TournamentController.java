package com.golf.two_for_tom_open.controller;

import com.golf.two_for_tom_open.model.dto.TournamentDto;
import com.golf.two_for_tom_open.service.TournamentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") //required for local development
@Slf4j
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    @GetMapping(value = {"/", ""})
    public ResponseEntity<List<TournamentDto>> getAllTournaments() {
        log.info("Request to retrieve all tournaments received.");
        return ResponseEntity.ok(tournamentService.getAllTournamentDtos());
    }
}

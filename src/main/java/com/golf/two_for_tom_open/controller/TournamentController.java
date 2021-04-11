package com.golf.two_for_tom_open.controller;

import com.golf.two_for_tom_open.model.dto.TournamentDto;
import com.golf.two_for_tom_open.model.enricher.TournamentDtoEnricher;
import com.golf.two_for_tom_open.model.entity.Tournament;
import com.golf.two_for_tom_open.model.mapper.TournamentMapper;
import com.golf.two_for_tom_open.service.TournamentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/tournaments")
public class TournamentController {
    private static Logger logger = LoggerFactory.getLogger(TournamentController.class);

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private TournamentMapper tournamentMapper;

    @Autowired
    private TournamentDtoEnricher tournamentDtoEnricher;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public ResponseEntity<?> getAllTournaments() {
        logger.info("Request to retrieve all tournaments received.");
        //TODO test me

        List<Tournament> tournaments = tournamentService.getAll();
        List<TournamentDto> tournamentDtos = tournaments.stream()
                .map(tournament -> {
                    TournamentDto tournamentDto = tournamentMapper.tournamentEntityToDto(tournament);
                    tournamentDtoEnricher.enrich(tournamentDto);
                    return tournamentDto;
                })
                .collect(Collectors.toList());

        logger.info("All tournaments received and being returned to consumer");
        return ResponseEntity.ok(tournamentDtos);
    }
}

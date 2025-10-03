package com.golf.two_for_tom_open.controller;

import com.golf.two_for_tom_open.model.dto.PlayerDto;
import com.golf.two_for_tom_open.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000") //required for local development
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<List<PlayerDto>> getAllPlayers() {
        log.info("Getting all players");
        List<PlayerDto> players = playerService.getAll();
        log.info("All players received and being returned to consumer");
        return ResponseEntity.ok(players);
    }
}

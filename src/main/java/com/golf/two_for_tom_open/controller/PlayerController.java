package com.golf.two_for_tom_open.controller;

import com.golf.two_for_tom_open.model.dto.PlayerDto;
import com.golf.two_for_tom_open.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<?> getAllPlayers() {
        log.info("Getting all players");
        List<PlayerDto> players = playerService.getAllPlayerDtos();
        log.info("All tournaments received and being returned to consumer");
        return ResponseEntity.ok(players);
    }
}

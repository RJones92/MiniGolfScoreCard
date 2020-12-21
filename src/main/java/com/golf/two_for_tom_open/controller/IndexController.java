package com.golf.two_for_tom_open.controller;

import com.golf.two_for_tom_open.model.Player;
import com.golf.two_for_tom_open.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class IndexController {

    @Autowired
    PlayerService playerService;

    @RequestMapping(value = "/players", method = RequestMethod.GET)
    public ResponseEntity<?> getPlayers(){

        List<Player> players = playerService.getPlayers();
        return ResponseEntity.ok(players);
    }

}

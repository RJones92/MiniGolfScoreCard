package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.Player;
import com.golf.two_for_tom_open.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }
}

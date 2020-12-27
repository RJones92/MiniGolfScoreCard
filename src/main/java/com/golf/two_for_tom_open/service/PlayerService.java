package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.entity.Player;
import com.golf.two_for_tom_open.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements BaseService<Player> {

    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }
}

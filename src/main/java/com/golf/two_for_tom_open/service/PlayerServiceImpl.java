package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.dto.PlayerDto;
import com.golf.two_for_tom_open.model.enricher.DtoEnricher;
import com.golf.two_for_tom_open.model.entity.Player;
import com.golf.two_for_tom_open.model.mapper.PlayerMapper;
import com.golf.two_for_tom_open.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl extends PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final DtoEnricher<PlayerDto> playerDtoEnricher;

    @Override
    public List<PlayerDto> getAll() {
        List<Player> players = playerRepository.findAll();
        return players.stream()
                .map(playerMapper::playerEntityToDto)
                .peek(playerDtoEnricher::enrich)
                .toList();
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }
}

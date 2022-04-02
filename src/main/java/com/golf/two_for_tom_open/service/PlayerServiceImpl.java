package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.dto.PlayerDto;
import com.golf.two_for_tom_open.model.enricher.DtoEnricher;
import com.golf.two_for_tom_open.model.entity.Player;
import com.golf.two_for_tom_open.model.mapper.PlayerMapper;
import com.golf.two_for_tom_open.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final DtoEnricher<PlayerDto> playerDtoEnricher;

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @Override
    public List<PlayerDto> getAllPlayerDtos() {
        List<Player> players = this.getAll();
        return players.stream()
                .map(playerMapper::playerEntityToDto)
                .peek(playerDtoEnricher::enrich)
                .collect(Collectors.toList());
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }
}

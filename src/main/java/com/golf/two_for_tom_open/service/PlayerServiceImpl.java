package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.dto.PlayerDto;
import com.golf.two_for_tom_open.model.enricher.DtoEnricher;
import com.golf.two_for_tom_open.model.entity.Player;
import com.golf.two_for_tom_open.model.mapper.PlayerMapper;
import com.golf.two_for_tom_open.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final DtoEnricher playerDtoEnricher;

    public PlayerServiceImpl(PlayerRepository playerRepository, PlayerMapper playerMapper, DtoEnricher playerDtoEnricher) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
        this.playerDtoEnricher = playerDtoEnricher;
    }

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @Override
    public List<PlayerDto> getAllPlayerDtos() {
        List<Player> players = this.getAll();
        return players.stream()
                .map(player -> {
                    PlayerDto playerDto = playerMapper.playerEntityToDto(player);
                    playerDtoEnricher.enrich(playerDto);
                    return  playerDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }
}

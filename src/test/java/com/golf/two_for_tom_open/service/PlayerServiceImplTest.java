package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.dto.PlayerDto;
import com.golf.two_for_tom_open.model.enricher.DtoEnricher;
import com.golf.two_for_tom_open.model.entity.Player;
import com.golf.two_for_tom_open.model.mapper.PlayerMapper;
import com.golf.two_for_tom_open.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    PlayerServiceImpl playerService;
    @Mock
    PlayerRepository playerRepository;
    @Mock
    DtoEnricher<PlayerDto> playerDtoEnricher;
    PlayerMapper playerMapper = Mappers.getMapper(PlayerMapper.class);

    private final Player playerX = Player.builder().firstName("John").lastName("Smith").build();
    private final Player playerY = Player.builder().firstName("Jane").lastName("Doe").build();

    @BeforeEach
    void setUp() {
        playerService = new PlayerServiceImpl(playerRepository, playerMapper, playerDtoEnricher);
    }

    @Test
    void getAll() {
        when(playerRepository.findAll()).thenReturn(List.of(playerX, playerY));

        List<PlayerDto> players = playerService.getAll();

        assertThat(players, hasSize(2));
        verify(playerRepository, times(1)).findAll();
    }

    @Test
    void save() {
        when(playerRepository.save(playerX)).thenReturn(playerX);
        Player player = playerService.save(playerX);
        assertThat(player, is(playerX));
    }
}
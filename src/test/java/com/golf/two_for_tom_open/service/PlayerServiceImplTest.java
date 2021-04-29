package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.entity.Player;
import com.golf.two_for_tom_open.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    @InjectMocks
    PlayerServiceImpl playerService;

    @Mock
    PlayerRepository playerRepository;

    private final Player playerX = Player.builder().firstName("John").lastName("Smith").build();
    private final Player playerY = Player.builder().firstName("Jane").lastName("Doe").build();

    @Test
    void getAll() {
        when(playerRepository.findAll()).thenReturn(Arrays.asList(playerX, playerY));
        List<Player> players = playerService.getAll();
        assertThat(players, contains(playerX, playerY));
        assertThat(players, hasSize(2));
    }

    @Test
    void save() {
        when(playerRepository.save(playerX)).thenReturn(playerX);
        Player player = playerService.save(playerX);
        assertThat(player, is(playerX));
    }
}
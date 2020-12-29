package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.entity.Player;
import com.golf.two_for_tom_open.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = PlayerServiceImpl.class)
class PlayerServiceImplTest {

    @TestConfiguration
    static class PlayerServiceImplTestContextConfiguration {

        @Bean
        public PlayerService playerService() {
            return new PlayerServiceImpl();
        }
    }

    @Autowired
    PlayerService playerService;

    @MockBean
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
package com.golf.two_for_tom_open.dataInitialiser;

import com.golf.two_for_tom_open.model.Player;
import com.golf.two_for_tom_open.service.PlayerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DataInitialiser implements CommandLineRunner {

    private final PlayerService playerService;

    public DataInitialiser(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        Player playerA = Player.builder()
                .firstName("Player")
                .lastName("A")
                .build();
        playerA.setId(new Random().nextLong());

        Player playerB = Player.builder()
                .firstName("Player")
                .lastName("B")
                .build();
        playerB.setId(new Random().nextLong());

        playerService.savePlayer(playerA);
        playerService.savePlayer(playerB);

    }
}

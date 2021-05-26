package com.golf.two_for_tom_open.model.enricher;

import com.golf.two_for_tom_open.model.dto.PlayerDto;
import com.golf.two_for_tom_open.model.dto.TournamentDto;
import com.golf.two_for_tom_open.service.ScoreService;
import com.golf.two_for_tom_open.service.TournamentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
class PlayerDtoEnricherTest {

    @Mock
    TournamentService tournamentService;
    @Mock
    ScoreService scoreService;
    PlayerDtoEnricher playerDtoEnricher;

    private static final int PLAYER_A_ID = 1;
    private static final String PLAYER_A_FIRST_NAME = "John";
    private static final String PLAYER_A_LAST_NAME = "Smith";
    private static final int PLAYER_B_ID = 2;
    private static final String PLAYER_B_FIRST_NAME = "Jessica";
    private static final String PLAYER_B_LAST_NAME = "Simpson";

    private PlayerDto playerA;
    private PlayerDto playerB;

    @BeforeEach
    void setUp() {
        initialisePlayers();
        playerDtoEnricher = new PlayerDtoEnricher(tournamentService, scoreService);
    }

    private void initialisePlayers() {
        playerA = PlayerDto.builder()
                .id(PLAYER_A_ID)
                .firstName(PLAYER_A_FIRST_NAME)
                .lastName(PLAYER_A_LAST_NAME)
                .build();
        playerB = PlayerDto.builder()
                .id(PLAYER_B_ID)
                .firstName(PLAYER_B_FIRST_NAME)
                .lastName(PLAYER_B_LAST_NAME)
                .build();
    }

    @Test
    void testCountTournamentsPlayed() {
        //GIVEN
        final int TOURNAMENT_2015_ID = 1;
        final int TOURNAMENT_2016_ID = 2;
        TournamentDto tournament_2015 = TournamentDto.builder()
                .id(TOURNAMENT_2015_ID)
                .players(Arrays.asList(playerA))
                .courses(Collections.emptyList())
                .year(Year.of(2015))
                .winner(playerA)
                .build();
        TournamentDto tournament_2016 = TournamentDto.builder()
                .id(TOURNAMENT_2016_ID)
                .players(Arrays.asList(playerA, playerB))
                .courses(Collections.emptyList())
                .year(Year.of(2016))
                .winner(playerB)
                .build();
        List<TournamentDto> allTournaments = new ArrayList<>();
        allTournaments.add(tournament_2015);
        allTournaments.add(tournament_2016);

        when(tournamentService.getAllTournamentDtos()).thenReturn(allTournaments);

        //WHEN
        playerDtoEnricher.enrich(playerA);
        playerDtoEnricher.enrich(playerB);

        //THEN
        assertThat(playerA.getCountOfTournamentsPlayed(), equalTo(2L));
        assertThat(playerB.getCountOfTournamentsPlayed(), equalTo(1L));

    }

    @Test
    void testCountTournamentsWon() {

    }

    @Test
    void testCountCoursesPlayed() {

    }

    @Test
    void testCountCoursessWon() {

    }

    @Test
    void testCountHolesPlayed() {

    }

    @Test
    void testCountHolesWon() {

    }
}
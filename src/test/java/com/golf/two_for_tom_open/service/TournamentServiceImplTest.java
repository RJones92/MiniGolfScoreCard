package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.entity.Tournament;
import com.golf.two_for_tom_open.repository.TournamentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Year;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TournamentServiceImplTest {

    @InjectMocks
    TournamentService tournamentService = new TournamentServiceImpl();

    @Mock
    TournamentRepository tournamentRepository;

    private final Tournament tournament2015 = Tournament.builder()
            .year(Year.of(2015))
            .courses(Collections.EMPTY_LIST)
            .players(Collections.EMPTY_LIST)
            .build();
    private final Tournament tournament2016 = Tournament.builder()
            .year(Year.of(2016))
            .courses(Collections.EMPTY_LIST)
            .players(Collections.EMPTY_LIST)
            .build();

    @Test
    void getAll() {
        when(tournamentRepository.findAll()).thenReturn(Arrays.asList(tournament2015, tournament2016));
        List<Tournament> tournaments = tournamentService.getAll();
        assertThat(tournaments, contains(tournament2015, tournament2016));
        assertThat(tournaments, hasSize(2));
    }

    @Test
    void save() {
        when(tournamentRepository.save(tournament2015)).thenReturn(tournament2015);
        Tournament tournament = tournamentService.save(tournament2015);
        assertThat(tournament, is(tournament2015));
    }

}
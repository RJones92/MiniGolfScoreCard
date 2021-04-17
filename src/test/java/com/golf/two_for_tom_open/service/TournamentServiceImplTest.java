package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.enricher.DtoEnricher;
import com.golf.two_for_tom_open.model.entity.Tournament;
import com.golf.two_for_tom_open.model.mapper.TournamentMapper;
import com.golf.two_for_tom_open.repository.TournamentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TournamentServiceImplTest {

    TournamentService tournamentService;
    @Mock
    TournamentRepository tournamentRepository;
    @Mock
    DtoEnricher tournamentDtoEnricher;
    TournamentMapper tournamentMapper = Mappers.getMapper(TournamentMapper.class);


    private final Tournament tournament2015 = Tournament.builder()
            .year(Year.of(2015))
            .courses(new ArrayList<>())
            .players(new ArrayList<>())
            .build();
    private final Tournament tournament2016 = Tournament.builder()
            .year(Year.of(2016))
            .courses(new ArrayList<>())
            .players(new ArrayList<>())
            .build();

    @BeforeEach
    void setUp() {
        tournamentService = new TournamentServiceImpl(tournamentRepository, tournamentMapper, tournamentDtoEnricher);
    }

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
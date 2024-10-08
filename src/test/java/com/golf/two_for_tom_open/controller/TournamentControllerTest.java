package com.golf.two_for_tom_open.controller;

import com.golf.two_for_tom_open.model.dto.TournamentDto;
import com.golf.two_for_tom_open.service.TournamentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TournamentControllerTest {

    @InjectMocks
    TournamentController tournamentController;

    @Mock
    TournamentService tournamentService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(tournamentController).build();
    }

    @Test
    void testGetAllTournaments() throws Exception {
        List<TournamentDto> tournamentList = new ArrayList<>();
        tournamentList.add(new TournamentDto());

        when(tournamentService.getAll()).thenReturn(tournamentList);

        mockMvc.perform(get("/api/tournaments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.dto.TournamentDto;
import com.golf.two_for_tom_open.model.enricher.DtoEnricher;
import com.golf.two_for_tom_open.model.entity.Tournament;
import com.golf.two_for_tom_open.model.mapper.TournamentMapper;
import com.golf.two_for_tom_open.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TournamentServiceImpl extends TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;
    private final DtoEnricher<TournamentDto> tournamentDtoEnricher;

    @Override
    public List<TournamentDto> getAll() {
        List<Tournament> tournaments = tournamentRepository.findAll();
        return tournaments.stream()
                .map(tournamentMapper::tournamentEntityToDto)
                .peek(tournamentDtoEnricher::enrich)
                .toList();
    }

    @Override
    public Tournament save(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

}

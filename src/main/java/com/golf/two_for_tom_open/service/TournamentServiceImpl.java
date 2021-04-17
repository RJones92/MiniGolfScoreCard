package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.dto.TournamentDto;
import com.golf.two_for_tom_open.model.enricher.DtoEnricher;
import com.golf.two_for_tom_open.model.entity.Tournament;
import com.golf.two_for_tom_open.model.mapper.TournamentMapper;
import com.golf.two_for_tom_open.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;
    private final DtoEnricher tournamentDtoEnricher;

    public TournamentServiceImpl(TournamentRepository tournamentRepository, TournamentMapper tournamentMapper, DtoEnricher tournamentDtoEnricher) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentMapper = tournamentMapper;
        this.tournamentDtoEnricher = tournamentDtoEnricher;
    }

    @Override
    public List<Tournament> getAll() {
        return tournamentRepository.findAll();
    }

    @Override
    public List<TournamentDto> getAllTournamentDtos() {
        List<Tournament> tournaments = this.getAll();
        return tournaments.stream()
                .map(tournament -> {
                    TournamentDto tournamentDto = tournamentMapper.tournamentEntityToDto(tournament);
                    tournamentDtoEnricher.enrich(tournamentDto);
                    return tournamentDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Tournament save(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

}

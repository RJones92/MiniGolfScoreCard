package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.dto.TournamentDto;
import com.golf.two_for_tom_open.model.dto.TournamentLiteDto;
import com.golf.two_for_tom_open.model.enricher.DtoEnricher;
import com.golf.two_for_tom_open.model.entity.Tournament;
import com.golf.two_for_tom_open.model.mapper.TournamentMapper;
import com.golf.two_for_tom_open.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;
    private final DtoEnricher tournamentDtoEnricher;

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
    public List<TournamentLiteDto> getAllTournamentLiteDtos() {
        return getAllTournamentDtos().stream()
                .map(tournamentMapper::tournamentDtoToLiteDto)
                .collect(Collectors.toList());
    }

    @Override
    public Tournament save(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

}

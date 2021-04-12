package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.dto.TournamentDto;
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

    public TournamentServiceImpl(TournamentRepository tournamentRepository, TournamentMapper tournamentMapper) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentMapper = tournamentMapper;
    }

    @Override
    public List<Tournament> getAll() {
        return tournamentRepository.findAll();
    }

    @Override
    public List<TournamentDto> getAllTournamentDto() {
        List<Tournament> tournaments = this.getAll();
        return convertListTournamentToListTournamentDto(tournaments);
    }

    @Override
    public Tournament save(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    private List<TournamentDto> convertListTournamentToListTournamentDto(List<Tournament> tournaments) {
        return tournaments.stream()
                .map(tournamentMapper::tournamentEntityToDto)
                .collect(Collectors.toList());
    }

}

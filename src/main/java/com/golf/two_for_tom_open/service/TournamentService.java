package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.entity.Tournament;
import com.golf.two_for_tom_open.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService implements BaseService<Tournament> {

    private TournamentRepository tournamentRepository;

    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public List<Tournament> getAll() {
        return tournamentRepository.findAll();
    }

    @Override
    public Tournament save(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

}

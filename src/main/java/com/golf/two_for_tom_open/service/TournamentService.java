package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.dto.TournamentDto;
import com.golf.two_for_tom_open.model.entity.Tournament;

import java.util.List;

public interface TournamentService extends BaseService<Tournament> {

    List<TournamentDto> getAllTournamentDtos();

}

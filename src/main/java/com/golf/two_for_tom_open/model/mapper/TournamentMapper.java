package com.golf.two_for_tom_open.model.mapper;

import com.golf.two_for_tom_open.model.dto.TournamentDto;
import com.golf.two_for_tom_open.model.dto.TournamentLiteDto;
import com.golf.two_for_tom_open.model.entity.Tournament;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TournamentMapper {

    TournamentDto tournamentEntityToDto(Tournament tournamentEntity);

    TournamentLiteDto tournamentDtoToLiteDto(TournamentDto tournamentDto);

    Tournament tournamentDtoToEntity(TournamentDto tournamentDto);
}

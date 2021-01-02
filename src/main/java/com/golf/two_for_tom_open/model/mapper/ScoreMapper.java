package com.golf.two_for_tom_open.model.mapper;

import com.golf.two_for_tom_open.model.dto.ScoreDto;
import com.golf.two_for_tom_open.model.entity.Score;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScoreMapper {
    ScoreDto scoreEntityToDto(Score scoreEntity);
}

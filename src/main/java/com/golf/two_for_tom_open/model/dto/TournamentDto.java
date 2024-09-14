package com.golf.two_for_tom_open.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TournamentDto {

    private int id;
    private Year _year;
    private List<CourseDto> courses;
    private List<PlayerDto> players;

    private PlayerDto winner;
}

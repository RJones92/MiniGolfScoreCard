package com.golf.two_for_tom_open.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    private int id;
    private String courseName;
    private List<HoleDto> holes;
    @Builder.Default
    private Map<Integer, PlayerDto> winnerByTournamentId = new HashMap<>();

}

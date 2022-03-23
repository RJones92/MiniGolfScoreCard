package com.golf.two_for_tom_open.model.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CourseDto {

    private int id;
    private String courseName;
    private List<HoleDto> holes;
    @Builder.Default
    private Map<Integer, PlayerDto> winnerByTournamentId = new HashMap<>();

}

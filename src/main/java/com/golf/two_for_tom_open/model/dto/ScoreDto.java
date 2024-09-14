package com.golf.two_for_tom_open.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDto {

    private int id;
    private PlayerDto player;
    private TournamentDto tournament;
    private HoleDto hole;
    private int strokes;
}

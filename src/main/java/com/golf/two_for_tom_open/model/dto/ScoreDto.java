package com.golf.two_for_tom_open.model.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ScoreDto {

    private int id;
    private PlayerDto player;
    private TournamentDto tournament;
    private HoleDto hole;
    private int strokes;
}

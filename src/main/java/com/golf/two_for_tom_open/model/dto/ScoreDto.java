package com.golf.two_for_tom_open.model.dto;

import com.golf.two_for_tom_open.model.entity.Hole;
import com.golf.two_for_tom_open.model.entity.Player;
import com.golf.two_for_tom_open.model.entity.Tournament;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ScoreDto {

    private Player player;
    private Tournament tournament;
    private Hole hole;
    private int strokes;
}

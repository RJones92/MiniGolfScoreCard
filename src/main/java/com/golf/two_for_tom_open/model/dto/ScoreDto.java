package com.golf.two_for_tom_open.model.dto;

import com.golf.two_for_tom_open.model.entity.Hole;
import com.golf.two_for_tom_open.model.entity.Player;
import com.golf.two_for_tom_open.model.entity.Tournament;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Builder
@Component
public class ScoreDto {

    private Player player;
    private Tournament tournament;
    private Hole hole;
    private int strokes;
}

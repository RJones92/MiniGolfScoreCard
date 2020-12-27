package com.golf.two_for_tom_open.model.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class TournamentsDto {

    private List<TournamentDto> tournaments;
}

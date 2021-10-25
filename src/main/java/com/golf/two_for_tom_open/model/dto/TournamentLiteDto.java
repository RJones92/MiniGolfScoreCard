package com.golf.two_for_tom_open.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Year;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class TournamentLiteDto {

    private int id;
    private Year year;
    private PlayerDto winner;
}

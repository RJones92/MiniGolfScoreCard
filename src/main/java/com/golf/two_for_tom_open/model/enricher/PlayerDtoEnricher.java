package com.golf.two_for_tom_open.model.enricher;

import com.golf.two_for_tom_open.model.dto.PlayerDto;
import com.golf.two_for_tom_open.model.dto.TournamentDto;
import com.golf.two_for_tom_open.model.dto.stat.Stat;
import com.golf.two_for_tom_open.model.enricher.calculator.PlayerStatCalculator;
import com.golf.two_for_tom_open.service.TournamentService;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.golf.two_for_tom_open.model.dto.stat.StatBuilder.COURSES_PLAYED;
import static com.golf.two_for_tom_open.model.dto.stat.StatBuilder.COURSES_WON;
import static com.golf.two_for_tom_open.model.dto.stat.StatBuilder.HOLES_IN_ONE;
import static com.golf.two_for_tom_open.model.dto.stat.StatBuilder.HOLES_PLAYED;
import static com.golf.two_for_tom_open.model.dto.stat.StatBuilder.HOLES_WON;
import static com.golf.two_for_tom_open.model.dto.stat.StatBuilder.TOURNAMENTS_PLAYED;
import static com.golf.two_for_tom_open.model.dto.stat.StatBuilder.TOURNAMENTS_WON;

@Component
public class PlayerDtoEnricher implements DtoEnricher<PlayerDto> {

    private final TournamentService tournamentService;
    private final PlayerStatCalculator playerStatCalculator;

    public PlayerDtoEnricher(TournamentService tournamentService,
                             PlayerStatCalculator playerStatCalculator) {
        this.tournamentService = tournamentService;
        this.playerStatCalculator = playerStatCalculator;
    }

    @Override
    public void enrich(PlayerDto player) {
        var tournaments = tournamentService.getAll();
        player.setPlayerStats(calculateStatistics(player, tournaments));
    }

    private List<Stat> calculateStatistics(PlayerDto player, List<TournamentDto> tournaments) {
        return List.of(
                TOURNAMENTS_PLAYED
                        .buildStat()
                        .apply(playerStatCalculator.countTournamentsPlayed(player, tournaments)),
                TOURNAMENTS_WON
                        .buildStat()
                        .apply(playerStatCalculator.countTournamentsWon(player, tournaments)),
                COURSES_PLAYED
                        .buildStat()
                        .apply(playerStatCalculator.countCoursesPlayed(player, tournaments)),
                COURSES_WON
                        .buildStat()
                        .apply(playerStatCalculator.countCoursesWon(player, tournaments)),
                HOLES_PLAYED
                        .buildStat()
                        .apply(playerStatCalculator.countHolesPlayed(player, tournaments)),
                HOLES_WON
                        .buildStat()
                        .apply(playerStatCalculator.countHolesWon(player, tournaments)),
                HOLES_IN_ONE
                        .buildStat()
                        .apply(playerStatCalculator.countHolesInOne(player, tournaments))
        );
    }

}

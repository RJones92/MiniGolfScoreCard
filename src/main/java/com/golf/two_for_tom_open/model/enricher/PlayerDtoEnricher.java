package com.golf.two_for_tom_open.model.enricher;

import com.golf.two_for_tom_open.model.dto.CourseDto;
import com.golf.two_for_tom_open.model.dto.HoleDto;
import com.golf.two_for_tom_open.model.dto.PlayerDto;
import com.golf.two_for_tom_open.model.dto.TournamentDto;
import com.golf.two_for_tom_open.service.TournamentService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@Component
public class PlayerDtoEnricher implements DtoEnricher<PlayerDto> {

    private List<TournamentDto> enrichedTournaments;

    public PlayerDtoEnricher(TournamentService tournamentService) {
        this.enrichedTournaments = tournamentService.getAllTournamentDtos();
    }

    @Override
    public void enrich(PlayerDto player) {
        calculateStatistics(player);
    }

    private void calculateStatistics(PlayerDto player) {
        player.setCountOfTournamentsPlayed(countTournamentsPlayed(player));
        player.setCountOfTournamentsWon(countTournamentsWon(player));

        player.setCountOfCoursesPlayed(countCoursesPlayed(player));
        player.setCountOfCoursesWon(countCoursesWon(player));

        player.setCountOfHolesPlayed(countHolesPlayed(player));
        player.setCountOfHolesWon(countHolesWon(player));

        //TODO these stat's
//        countHolesInOne(player);
//        averageHoleInOnesInTournaments(player);
//
//        averageStrokesPerHole(player);
//        averageDifferenceBetweenStrokesAndPar(player);
    }

    private long countTournamentsPlayed(PlayerDto player) {
        return enrichedTournaments.stream()
                .filter(tournament -> isPlayerInTournament.test(tournament, player))
                .count();
    }

    private BiPredicate<TournamentDto, PlayerDto> isPlayerInTournament = (tournament, player) -> tournament.getPlayers().stream()
            .anyMatch(tournamentPlayer -> tournamentPlayer.equals(player));

    private long countTournamentsWon(PlayerDto player) {
        return enrichedTournaments.stream()
                .filter(tournament -> tournament.getWinner().equals(player))
                .count();
    }

    private long countCoursesPlayed(PlayerDto player) {
        return coursesPlayed(player).size();
    }

    private List<CourseDto> coursesPlayed(PlayerDto player) {
        return enrichedTournaments.stream()
                .filter(tournament -> isPlayerInTournament.test(tournament, player))
                .map(TournamentDto::getCourses)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private long countCoursesWon(PlayerDto player) {
        return coursesPlayed(player).stream()
                .mapToLong(course -> countOfPlayerWinsForCourse(course, player))
                .sum();
    }

    private long countOfPlayerWinsForCourse (CourseDto course, PlayerDto player) {
        Collection<PlayerDto> courseWinners = course.getWinnersByTournamentId().values();
        return courseWinners.stream()
                .filter(winner -> winner.equals(player))
                .count();
    }

    private long countHolesPlayed(PlayerDto player) {
        return holesPlayed(player).size();
    }

    private List<HoleDto> holesPlayed(PlayerDto player) {
        List<CourseDto> coursesPlayed = coursesPlayed(player);

        return coursesPlayed.stream()
                .map(CourseDto::getHoles)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private long countHolesWon(PlayerDto player) {
        return holesPlayed(player).stream()
                .mapToLong(hole -> countOfPlayerWinsForHole(hole, player))
                .sum();
    }

    private long countOfPlayerWinsForHole(HoleDto hole, PlayerDto player) {
        //TODO - figure out who won the hole...
        //Set hole winners in tournamentDTOEnricher
        Collection<PlayerDto> holeWinners;
        return 1L;
    }

}

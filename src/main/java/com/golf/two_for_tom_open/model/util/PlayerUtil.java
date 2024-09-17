package com.golf.two_for_tom_open.model.util;

import com.golf.two_for_tom_open.model.dto.CourseDto;
import com.golf.two_for_tom_open.model.dto.HoleDto;
import com.golf.two_for_tom_open.model.dto.PlayerDto;
import com.golf.two_for_tom_open.model.dto.TournamentDto;

import java.util.Collection;
import java.util.List;
import java.util.function.BiPredicate;

public final class PlayerUtil {
    // TODO unit test
    private PlayerUtil() {}

    public static List<TournamentDto> tournamentsPlayed(PlayerDto player, List<TournamentDto> tournaments) {
        return tournaments.stream()
                .filter(tournament -> isPlayerInTournament.test(tournament, player))
                .toList();
    }

    public static final BiPredicate<TournamentDto, PlayerDto> isPlayerInTournament = (tournament, player) ->
            tournament.getPlayers()
                    .stream()
                    .anyMatch(tournamentPlayer -> tournamentPlayer.equals(player));

    public static List<CourseDto> coursesPlayed(PlayerDto player, List<TournamentDto> tournaments) {
        return tournaments.stream()
                .filter(tournament -> isPlayerInTournament.test(tournament, player))
                .map(TournamentDto::getCourses)
                .flatMap(Collection::stream)
                .toList();
    }

    public static long countOfPlayerWinsForCourse(CourseDto course, PlayerDto player) {
        Collection<PlayerDto> courseWinners = course.getWinnerByTournamentId().values();
        return courseWinners.stream()
                .filter(winner -> winner.equals(player))
                .count();
    }

    public static List<HoleDto> holesPlayed(PlayerDto player, List<TournamentDto> tournaments) {
        List<CourseDto> coursesPlayed = coursesPlayed(player, tournaments);

        return coursesPlayed.stream()
                .map(CourseDto::getHoles)
                .flatMap(Collection::stream)
                .toList();
    }

}

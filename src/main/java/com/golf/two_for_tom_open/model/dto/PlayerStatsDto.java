package com.golf.two_for_tom_open.model.dto;

public record PlayerStatsDto(
        long countOfTournamentsPlayed,
        long countOfTournamentsWon,
        long countOfCoursesPlayed,
        long countOfCoursesWon,
        long countOfHolesPlayed,
        long countOfHolesWon) {}

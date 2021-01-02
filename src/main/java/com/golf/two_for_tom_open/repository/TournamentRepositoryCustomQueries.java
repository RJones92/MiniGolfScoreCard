package com.golf.two_for_tom_open.repository;

import com.golf.two_for_tom_open.model.entity.Tournament;

import java.time.Year;

public interface TournamentRepositoryCustomQueries {

    Tournament getTournamentByYear(Year year);

}

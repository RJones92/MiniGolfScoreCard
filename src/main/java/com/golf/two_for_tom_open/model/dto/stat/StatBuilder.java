package com.golf.two_for_tom_open.model.dto.stat;

import java.util.function.Function;

import static com.golf.two_for_tom_open.model.dto.stat.Stat.COUNT_OF_PREFIX;

public enum StatBuilder {

    TOURNAMENTS_PLAYED(value -> new AbstractStat(COUNT_OF_PREFIX.concat("TournamentsPlayed"), value) {}),
    TOURNAMENTS_WON(value -> new AbstractStat(COUNT_OF_PREFIX.concat("TournamentsWon"), value) {}),
    COURSES_PLAYED(value -> new AbstractStat(COUNT_OF_PREFIX.concat("CoursesPlayed"), value) {}),
    COURSES_WON(value -> new AbstractStat(COUNT_OF_PREFIX.concat("CoursesWon"), value) {}),
    HOLES_PLAYED(value -> new AbstractStat(COUNT_OF_PREFIX.concat("HolesPlayed"), value) {}),
    HOLES_WON(value -> new AbstractStat(COUNT_OF_PREFIX.concat("HolesWon"), value) {}),
    HOLES_IN_ONE(value -> new AbstractStat(COUNT_OF_PREFIX.concat("HolesInOne"), value) {}),
    TOTAL_STROKES(value -> new AbstractStat(COUNT_OF_PREFIX.concat("Strokes"), value) {}),
    AVG_STROKES_PER_HOLE(value -> new AbstractStat("avgStrokesPerHole", value) {});

    private final Function<Long, Stat> buildStat;

    StatBuilder(Function<Long, Stat> buildStat) {
        this.buildStat = buildStat;
    }

    public Function<Long, Stat> buildStat() {
        return buildStat;
    }
}

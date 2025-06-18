package com.golf.two_for_tom_open.dataInitialiser.prod.score;

import com.golf.two_for_tom_open.model.entity.*;

import java.util.ArrayList;
import java.util.List;

public abstract class ScoreCreator {

    public abstract List<Score> create(Tournament tournament, Player player);

    static List<Score> createScores(List<List<Integer>> strokesPerCourse, Tournament tournament, Player player) {
        int countOfTournamentHoles = tournament.getCourses().stream()
                .mapToInt(course -> course.getHoles().size())
                .sum();
        int countOfHolesTaken = strokesPerCourse.stream()
                .map(List::size)
                .mapToInt(Integer::intValue)
                .sum();
        if (countOfHolesTaken != countOfTournamentHoles) {
            throw new RuntimeException("The size of the strokes list (" + countOfHolesTaken +
                    ") does not match the number of holes (" +
                    countOfTournamentHoles + ")");
        }
        List<Score> scores = new ArrayList<>();

        for (int i = 0; i < tournament.getCourses().size(); i++) {
            Course course = tournament.getCourses().get(i);
            List<Integer> strokesTakenForCourse = strokesPerCourse.get(i);

            for (int x = 0; x < course.getHoles().size(); x++) {
                Hole hole = course.getHoles().get(x);
                Integer strokesTakenForHole = strokesTakenForCourse.get(x);
                scores.add(createScore(strokesTakenForHole, tournament, hole, player));
            }
        }
        return scores;
    }

    private static Score createScore(int strokes, Tournament tournament, Hole hole, Player player) {
        return Score.builder()
                .strokes(strokes)
                .player(player)
                .tournament(tournament)
                .hole(hole)
                .build();
    }

}

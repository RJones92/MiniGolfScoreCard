package com.golf.two_for_tom_open.dataInitialiser.prod.score;

import com.golf.two_for_tom_open.model.entity.Player;
import com.golf.two_for_tom_open.model.entity.Score;
import com.golf.two_for_tom_open.model.entity.Tournament;

import java.util.List;

import static java.util.Collections.emptyList;

public class ScoreFactory {

    private final ScoreCreator rhysScoreCreator;
    private final ScoreCreator tomScoreCreator;
    private final ScoreCreator jamieScoreCreator;
    private final ScoreCreator jadeScoreCreator;

    public ScoreFactory() {
        this.rhysScoreCreator = new RhysScoreCreator();
        this.tomScoreCreator = new TomScoreCreator();
        this.jamieScoreCreator = new JamieScoreCreator();
        this.jadeScoreCreator = new JadeScoreCreator();
    }

    public List<Score> createScoresFor(Tournament tournament, Player player) {
        return switch (player.getFirstName().toUpperCase()) {
            case "RHYS" ->  rhysScoreCreator.create(tournament, player);
            case "THOMAS" -> tomScoreCreator.create(tournament, player);
            case "JAMIE" -> jamieScoreCreator.create(tournament, player);
            case "JADE" -> jadeScoreCreator.create(tournament, player);
            default -> emptyList();
        };
    }

}

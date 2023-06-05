package com.golf.two_for_tom_open.repository;

import com.golf.two_for_tom_open.model.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.Year;
import java.util.List;

public class ScoreRepositoryCustomQueriesImpl implements ScoreRepositoryCustomQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Score> findScoresForPlayerById(int playerId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QScore qScore = QScore.score;
        QPlayer qPlayer = QPlayer.player;

        List<Score> scores = queryFactory.selectFrom(qScore)
                .innerJoin(qScore.player, qPlayer)
                .on(qPlayer.id.eq(playerId))
                .fetch();

        return scores;
    }

    @Override
    public List<Score> findScoresForPlayerByName(String firstName, String lastName) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QScore qScore = QScore.score;
        QPlayer qPlayer = QPlayer.player;

        List<Score> scores = queryFactory.selectFrom(qScore)
                .innerJoin(qScore.player, qPlayer)
                .on(qPlayer.firstName.equalsIgnoreCase(firstName), qPlayer.lastName.equalsIgnoreCase(lastName))
                .fetch();

        return scores;
    }

    @Override
    public List<Score> findScoresForTournament(Tournament tournament) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QScore qScore = QScore.score;
        QTournament qTournament = QTournament.tournament;

        List<Score> scores = queryFactory.selectFrom(qScore)
                .innerJoin(qScore.tournament, qTournament)
                .on(qTournament.eq(tournament))
                .fetch();

        return scores;
    }

    @Override
    public List<Score> findScoresForTournamentByYear(Year year) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QScore qScore = QScore.score;
        QTournament qTournament = QTournament.tournament;

        List<Score> scores = queryFactory.selectFrom(qScore)
                .innerJoin(qScore.tournament, qTournament)
                .on(qTournament._year.eq(year))
                .fetch();

        return scores;
    }

}

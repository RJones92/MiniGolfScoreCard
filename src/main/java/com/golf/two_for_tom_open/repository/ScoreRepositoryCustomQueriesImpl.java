package com.golf.two_for_tom_open.repository;

import com.golf.two_for_tom_open.model.entity.QPlayer;
import com.golf.two_for_tom_open.model.entity.QScore;
import com.golf.two_for_tom_open.model.entity.Score;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}

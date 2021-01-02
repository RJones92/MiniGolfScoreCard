package com.golf.two_for_tom_open.repository;

import com.golf.two_for_tom_open.model.entity.QTournament;
import com.golf.two_for_tom_open.model.entity.Tournament;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Year;

public class TournamentRepositoryCustomQueriesImpl implements TournamentRepositoryCustomQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Tournament getTournamentByYear(Year year) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QTournament qTournament = QTournament.tournament;

        Tournament tournament = queryFactory.selectFrom(qTournament)
                .where(qTournament.year.eq(year))
                .fetchFirst();

        return tournament;
    }
}

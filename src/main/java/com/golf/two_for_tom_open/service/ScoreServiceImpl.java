package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.entity.QPlayer;
import com.golf.two_for_tom_open.model.entity.QScore;
import com.golf.two_for_tom_open.model.entity.Score;
import com.golf.two_for_tom_open.repository.ScoreRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @PersistenceContext
    private EntityManager entityManager;

    private ScoreRepository scoreRepository;

    public ScoreServiceImpl(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @Override
    public List<Score> getAll() {
        return scoreRepository.findAll();
    }

    @Override
    public Score save(Score score) {
        return scoreRepository.save(score);
    }

    @Override
    public List<Score> getScoresForPlayerById(int playerId) {
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
    public List<Score> getScoresForPlayerByName(String firstName, String lastName) {
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

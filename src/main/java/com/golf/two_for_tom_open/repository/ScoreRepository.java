package com.golf.two_for_tom_open.repository;

import com.golf.two_for_tom_open.model.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource
public interface ScoreRepository extends JpaRepository<Score, Integer>, ScoreRepositoryCustomQueries, JpaSpecificationExecutor<Score>, QuerydslPredicateExecutor<Score> {

    List<Score> findAllByTournamentId(int id);

    List<Score> findDistinctScoresByTournamentIdIn(Collection<Integer> ids);
}

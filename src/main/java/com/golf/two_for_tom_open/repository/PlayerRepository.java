package com.golf.two_for_tom_open.repository;

import com.golf.two_for_tom_open.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PlayerRepository extends JpaRepository<Player, Integer>, JpaSpecificationExecutor<Player>, QuerydslPredicateExecutor<Player> {
}

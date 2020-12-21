package com.golf.two_for_tom_open.repository;

import com.golf.two_for_tom_open.model.Hole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HoleRepository extends JpaRepository<Hole, Integer>, JpaSpecificationExecutor<Hole>, QuerydslPredicateExecutor<Hole> {
}

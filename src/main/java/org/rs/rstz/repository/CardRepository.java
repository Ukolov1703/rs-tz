package org.rs.rstz.repository;

import org.rs.rstz.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, Integer> {

    Optional<CardEntity> findByNumber(Integer number);
}

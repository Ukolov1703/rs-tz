package org.rs.rstz.services;

import org.rs.rstz.entity.CardEntity;

import java.util.Optional;

public interface CardService {

    Optional<CardEntity> findById(Integer id);

    void changeBalance(Integer id, Double value);

    Double getBalance(Integer number);

    Optional<CardEntity> findByNumber(Integer number);

    void updateBalance(CardEntity card);
}

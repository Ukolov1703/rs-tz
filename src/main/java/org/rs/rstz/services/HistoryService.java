package org.rs.rstz.services;

import org.rs.rstz.entity.HistoryEntity;

import java.util.List;
import java.util.Optional;

public interface HistoryService {

    Optional<HistoryEntity> findById(Integer id);

    void create(Double value, Double balance, Integer idCard);

    boolean rollback(Integer id);

    List<HistoryEntity> findHistoryByNumber(Integer number);
}

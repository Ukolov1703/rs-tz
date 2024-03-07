package org.rs.rstz.services;

public interface BalanceService {

    boolean addBalance(Integer id, Double value);

    boolean writeOffBalance(Integer id, Double value);
}

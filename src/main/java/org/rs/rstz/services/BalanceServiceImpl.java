package org.rs.rstz.services;

import org.rs.rstz.entity.CardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BalanceServiceImpl implements BalanceService {

    private final CardService cardService;
    private final HistoryService historyService;

    @Autowired
    public BalanceServiceImpl(CardService cardService, HistoryService historyService) {
        this.cardService = cardService;
        this.historyService = historyService;
    }

    @Override
    @Transactional
    public boolean addBalance(Integer id, Double value) {
        Optional<CardEntity> res = cardService.findById(id);
        if (res.isEmpty()) {
            return false;
        }
        CardEntity card = res.get();
        card.setBalance(card.getBalance() + value);
        cardService.updateBalance(card);
        historyService.create(value, card.getBalance(), card.getId());
        return true;
    }

    @Override
    @Transactional
    public boolean writeOffBalance(Integer id, Double value) {
        Optional<CardEntity> res = cardService.findById(id);
        if (res.isEmpty()) {
            return false;
        }
        CardEntity card = res.get();
        card.setBalance(card.getBalance() - value);
        cardService.updateBalance(card);
        historyService.create(-value, card.getBalance(), card.getId());
        return true;
    }
}

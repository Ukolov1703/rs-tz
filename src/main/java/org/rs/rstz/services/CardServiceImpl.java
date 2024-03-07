package org.rs.rstz.services;

import org.rs.rstz.entity.CardEntity;
import org.rs.rstz.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Optional<CardEntity> findById(Integer id) {
        return cardRepository.findById(id);

    }

    @Override
    public void changeBalance(Integer id, Double value) {
        Optional<CardEntity> res = cardRepository.findById(id);
        CardEntity card = res.get();
        card.setBalance(card.getBalance() - value);
        cardRepository.save(card);
    }

    @Override
    public Double getBalance(Integer number) {
        Optional<CardEntity> res = cardRepository.findByNumber(number);
        if (res.isEmpty()) {
            return null;
        }
        return res.get().getBalance();
    }

    @Override
    public Optional<CardEntity> findByNumber(Integer number) {
        return cardRepository.findByNumber(number);
    }

    @Override
    public void updateBalance(CardEntity card) {
        cardRepository.save(card);
    }
}

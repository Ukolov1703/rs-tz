package org.rs.rstz.services;

import org.rs.rstz.entity.CardEntity;
import org.rs.rstz.entity.HistoryEntity;
import org.rs.rstz.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final CardService cardService;

    @Autowired
    public HistoryServiceImpl(HistoryRepository historyRepository, CardService cardService) {
        this.historyRepository = historyRepository;
        this.cardService = cardService;
    }

    @Override
    public Optional<HistoryEntity> findById(Integer id) {
        return historyRepository.findById(id);
    }

    @Override
    public void create(Double value, Double balance, Integer idCard) {
        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setValue(value);
        historyEntity.setBalance(balance);
        historyEntity.setCreateDatetime(new Date());
        historyEntity.setIdCard(idCard);
        historyRepository.save(historyEntity);
    }

    @Transactional
    @Override
    public boolean rollback(Integer id) {
        Optional<HistoryEntity> res = findById(id);
        if (res.isEmpty()) {
            return false;
        }
        HistoryEntity historyEntity = res.get();
        cardService.changeBalance(historyEntity.getIdCard(), historyEntity.getValue());
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public List<HistoryEntity> findHistoryByNumber(Integer number) {
        Optional<CardEntity> res = cardService.findByNumber(number);
        if (res.isEmpty()) {
            return null;
        }
        return res.get().getHistoryEntityList();
    }
}

package org.rs.rstz.controller;

import org.rs.rstz.dto.HistoryDto;
import org.rs.rstz.entity.HistoryEntity;
import org.rs.rstz.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/history", produces = MediaType.APPLICATION_JSON_VALUE)
public class HistoryController {

    private final HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @PutMapping("/change-balance/{id}")
    public ResponseEntity<Void> changeBalance(@PathVariable Integer id) {
        boolean res = historyService.rollback(id);
        if (res) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/find-history-by-number/{number}")
    public ResponseEntity<HistoryDto> findBalanceByNumber(@PathVariable Integer number) {
        List<HistoryEntity> res = historyService.findHistoryByNumber(number);
        if (res != null) {
            return ResponseEntity.ok(new HistoryDto(res));
        }
        return ResponseEntity.notFound().build();
    }
}

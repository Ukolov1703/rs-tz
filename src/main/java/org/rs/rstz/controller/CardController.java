package org.rs.rstz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.rs.rstz.dto.BalanceDto;
import org.rs.rstz.services.BalanceService;
import org.rs.rstz.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/card", produces = MediaType.APPLICATION_JSON_VALUE)
public class CardController {

    private final BalanceService balanceService;
    private final CardService cardService;
    private final ObjectMapper objectMapper;

    @Autowired
    public CardController(BalanceService balanceService, CardService cardService, ObjectMapper objectMapper) {
        this.balanceService = balanceService;
        this.cardService = cardService;
        this.objectMapper = objectMapper;

    }

    @PutMapping("/add/{id}/{value}")
    public ResponseEntity<Void> addBalance(@PathVariable Integer id, @PathVariable Double value) {
        boolean res = balanceService.addBalance(id, value);
        if (res) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/write-off/{id}/{value}")
    public ResponseEntity<Void> writeOffBalance(@PathVariable Integer id, @PathVariable Double value) {
        boolean res = balanceService.writeOffBalance(id, value);
        if (res) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get-balance/{number}")
    public ResponseEntity<BalanceDto> getBalance(@PathVariable Integer number) {
        Double res = cardService.getBalance(number);
        if (res != null) {
            return ResponseEntity.ok(new BalanceDto(res));
        }
        return ResponseEntity.notFound().build();
    }
}

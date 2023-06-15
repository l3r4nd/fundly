package com.fundly.app.controller;

import com.fundly.app.common.Endpoints;
import com.fundly.app.entity.LoanStore;
import com.fundly.app.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(Endpoints.PAYMENT)
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LoanStore> getAll() {
        return paymentService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void store(@RequestBody LoanStore loanStore) {
        paymentService.store(loanStore);
    }

    @GetMapping("/aggregate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Map<Float, Map<String, Long>>>> aggregate() {
       return new ResponseEntity<>(paymentService.aggregate(), HttpStatus.OK);
    }


}

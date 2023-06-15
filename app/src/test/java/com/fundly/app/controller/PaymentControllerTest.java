package com.fundly.app.controller;

import com.fundly.app.entity.LoanStore;
import com.fundly.app.repository.LoanStoreRepository;
import com.fundly.app.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PaymentControllerTest {


    @MockBean
    private PaymentService paymentService;
    @MockBean
    private LoanStoreRepository loanStoreRepository;


    @Autowired
    private PaymentController paymentController;

    @Test
    void getAll() {
        List<LoanStore> collect = Stream.of(new LoanStore(
                        "L1",
                        "C1",
                        "LEN1",
                        10000l,
                        10000l,
                        Date.valueOf(LocalDate.of(2023, 6, 5)),
                        1f,
                        Date.valueOf(LocalDate.of(2023, 7, 5)),
                        0.01F,
                        ""),
                new LoanStore(
                        "L2",
                        "C1",
                        "LEN1",
                        20000l,
                        5000l,
                        Date.valueOf(LocalDate.of(2023, 6, 1)),
                        1f,
                        Date.valueOf(LocalDate.of(2023, 8, 5)),
                        0.01f,
                        ""
                ),
                new LoanStore(
                        "l3",
                        "C2",
                        "LEN2",
                        50000l,
                        30000l,
                        Date.valueOf(LocalDate.of(2023, 4, 4)),
                        2f,
                        Date.valueOf(LocalDate.of(2023, 5, 4)),
                        0.02f,
                        ""),
                new LoanStore(
                        "L4",
                        "C3",
                        "LEN2",
                        50000l,
                        20000l,
                        Date.valueOf(LocalDate.of(2023, 4, 4)),
                        2f,
                        Date.valueOf(LocalDate.of(2023, 4, 4)),
                        0.02f,
                        ""
                )).collect(Collectors.toList());
        when(loanStoreRepository.getLoanStoreList()).thenReturn(collect);

        when(paymentService.getAll()).thenReturn(collect);
        List<LoanStore> all = paymentController.getAll();
        assertEquals(4, all.size());
    }

    @Test
    void store() {
        LoanStore loanStore = new LoanStore(
                "L4",
                "C3",
                "LEN2",
                50000l,
                20000l,
                Date.valueOf(LocalDate.of(2023, 4, 4)),
                2f,
                Date.valueOf(LocalDate.of(2023, 4, 4)),
                0.02f,
                ""
        );

        paymentController.store(loanStore);
    }

    @Test
    void aggregate() {
        ResponseEntity<Map<String, Map<Float, Map<String, Long>>>> aggregate = paymentController.aggregate();
        assertNotNull(aggregate);
    }
}
package com.fundly.app.service.impl;

import com.fundly.app.common.exception.PaymentDateCannotBeGreaterThanDueDateException;
import com.fundly.app.entity.LoanStore;
import com.fundly.app.repository.LoanStoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@SpringBootTest
class DefaultPaymentServiceTest {

    @MockBean
    private LoanStoreRepository loanStoreRepository;

    @Autowired
    private DefaultPaymentService paymentService;

    public DefaultPaymentServiceTest() {

    }

    @Test
    void getAll() {
        when(loanStoreRepository.getLoanStoreList()).thenReturn(
                Stream.of(new LoanStore(
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
                                ""
                        )
                ).collect(Collectors.toList()));

        assertEquals( 3, paymentService.getAll().size());

    }

    @Test
    void storeWhenLoanStoreDoesNotExceedDueDate() {
        LoanStore loanStore = new LoanStore(
                "L1",
                "C1",
                "LEN1",
                10000l,
                10000l,
                Date.valueOf(LocalDate.of(2023, 6, 5)),
                1f,
                Date.valueOf(LocalDate.of(2023, 7, 5)),
                0.01F,
                "");
        paymentService.store(loanStore);
    }

    @Test
    void storeWhenLoanStoreExceedDueDate() {
        LoanStore loanStore = new LoanStore(
                "L1",
                "C1",
                "LEN1",
                10000l,
                10000l,
                Date.valueOf(LocalDate.of(2023, 6, 5)),
                1f,
                Date.valueOf(LocalDate.of(2023, 5, 5)),
                0.01F,
                "");
        assertThrows(PaymentDateCannotBeGreaterThanDueDateException.class, () -> paymentService.store(loanStore));

    }

    @Test
    void aggregate() {

        when(loanStoreRepository.getLoanStoreList()).thenReturn(Stream.of(new LoanStore(
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
                )

        ).collect(Collectors.toList()));
        Map<String, Map<Float, Map<String, Long>>> aggregate = paymentService.aggregate();
        assertEquals(2, aggregate.size());
        assertEquals(1, aggregate.get("LEN1").size());
        assertEquals(1, aggregate.get("LEN2").size());
    }



}
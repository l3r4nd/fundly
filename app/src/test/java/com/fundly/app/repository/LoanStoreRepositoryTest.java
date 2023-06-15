package com.fundly.app.repository;

import com.fundly.app.entity.LoanStore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class LoanStoreRepositoryTest {

    @Autowired
    private LoanStoreRepository loanStoreRepository;


    @Test
    void addValid() {
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
        this.loanStoreRepository.addValid(loanStore);
        assertEquals(1, loanStoreRepository.getLoanStoreList().size());

    }

    @Test
    void addInvalid() {
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

        loanStoreRepository.addInvalid(loanStore);
        assertEquals(0, loanStoreRepository.getLoanStoreList().size());

    }

    @Test
    void getLoanStoreList() {
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

        loanStoreRepository.addValid(loanStore);
        List<LoanStore> loanStoreList = loanStoreRepository.getLoanStoreList();
        assertEquals(false, loanStoreList.get(0) == loanStore);

    }

    @AfterEach
    void clear() {
        loanStoreRepository.clear();
    }
}
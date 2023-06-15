package com.fundly.app.service.impl;

import com.fundly.app.common.exception.PaymentDateCannotBeGreaterThanDueDateException;
import com.fundly.app.entity.LoanStore;
import com.fundly.app.repository.LoanStoreRepository;
import com.fundly.app.service.PaymentService;
import com.fundly.app.util.DateTimeUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultPaymentService implements PaymentService {

    private final LoanStoreRepository loanStoreRepository;

    public void store(LoanStore loanStore) {
        int comparison = DateTimeUtility.comparePaymentAndDueDates(loanStore.getPaymentDate(), loanStore.getDueDate());

        if(comparison >= 0) {
            log.error("{}", String.format(LOAN_DATE_HAS_CROSSED_DUE_DATE, loanStore.getCustomerId()));
            loanStoreRepository.addInvalid(loanStore);
            throw new PaymentDateCannotBeGreaterThanDueDateException(String.format(PAYMENT_DATE_CANNOT_BE_GREATER_THAN_DUE_DATE,
                    loanStore.getPaymentDate(),
                    loanStore.getDueDate()));
        } else {
            loanStoreRepository.addValid(loanStore);
        }
    }

    @Override
    public Map<String, Map<Float, Map<String, Long>>> aggregate() {
        return loanStoreRepository.getLoanStoreList().stream()
                .collect(Collectors.groupingBy(LoanStore::getLenderId,
                        Collectors.groupingBy(LoanStore::getInterestPerDay,
                                Collectors.groupingBy(LoanStore::getCustomerId,
                                        Collectors.summingLong(LoanStore::getRemainingAmount))
                            )
                        )
                );
    }

    @Override
    public List<LoanStore> getAll() {
        return loanStoreRepository.getLoanStoreList();
    }
}

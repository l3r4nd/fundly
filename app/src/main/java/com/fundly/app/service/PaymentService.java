package com.fundly.app.service;

import com.fundly.app.entity.LoanStore;

import java.util.List;
import java.util.Map;

public interface PaymentService {

    String PAYMENT_DATE_CANNOT_BE_GREATER_THAN_DUE_DATE = "%s cannot be than due date";
    String LOAN_DATE_HAS_CROSSED_DUE_DATE = "loan date has crossed due date for customerId: %s";

    void store(LoanStore loanStore);

    List<LoanStore> getAll();

    Map<String, Map<Float, Map<String, Long>>> aggregate();
}

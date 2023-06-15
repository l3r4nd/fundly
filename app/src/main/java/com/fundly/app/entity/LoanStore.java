package com.fundly.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanStore {
    private String loanId;

    private String customerId;

    private String lenderId;

    private Long amount;

    private Long remainingAmount;

    private Date paymentDate;

    private Float interestPerDay;
    private Date dueDate;

    private Float penalty;

    private String cancel;

    public LoanStore(LoanStore loanStore) {
        this.loanId = loanStore.loanId;
        this.customerId = loanStore.customerId;
        this.lenderId = loanStore.lenderId;
        this.amount = loanStore.amount;
        this.remainingAmount = loanStore.remainingAmount;
        this.paymentDate = loanStore.paymentDate;
        this.interestPerDay = loanStore.interestPerDay;
        this.dueDate = loanStore.dueDate;
        this.penalty = loanStore.penalty;
        this.cancel = loanStore.cancel;
    }
}

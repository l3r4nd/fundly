package com.fundly.app.repository;

import com.fundly.app.entity.LoanStore;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Repository
public class LoanStoreRepository {

    private final List<LoanStore> loanStoreList;
    private final List<LoanStore> exceededLoanStoreList;

    public LoanStoreRepository() {
        loanStoreList = new LinkedList<>();
        exceededLoanStoreList = new LinkedList<>();
    }


    public void addValid(LoanStore loanStore) {
        this.loanStoreList.add(loanStore);
    }

    public void addInvalid(LoanStore loanStore) {
        this.exceededLoanStoreList.add(loanStore);
    }


    public List<LoanStore> getLoanStoreList() {
        ArrayList<LoanStore> objects = new ArrayList<>(loanStoreList.size());
        for(LoanStore loanStore : loanStoreList) {
            objects.add(new LoanStore(loanStore));
        }
        return objects;

    }

    public void clear() {
        loanStoreList.removeAll(loanStoreList);
        exceededLoanStoreList.removeAll(exceededLoanStoreList);
    }
}

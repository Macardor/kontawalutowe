package com.kaczmarz.kontawalutowe.model;

import java.math.BigInteger;
import java.util.List;

public class Account {
    private List<SubAccount> subAccounts;

    public Account(){

    }

    public Account(List<SubAccount> subAccounts){
        this.subAccounts = subAccounts;
    }

    public void addSubAccount(SubAccount subAccount){
        subAccounts.add(subAccount);
    }

    public BigInteger getBalance(Currency currency){
        BigInteger balance = new BigInteger("0");
        for (SubAccount subAccount : subAccounts) {
            if(currency.equals(subAccount.getCurrency())) balance.add(subAccount.getBalance());
        }
        return balance;
    }

    public List<SubAccount> getSubAccounts() {
        return subAccounts;
    }
}

package com.kaczmarz.kontawalutowe.model;

import java.math.BigDecimal;

public class SubAccount {
    private Currency currency;
    private BigDecimal balance;

    public SubAccount(Currency currency, BigDecimal balance){
        this.currency = currency;
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

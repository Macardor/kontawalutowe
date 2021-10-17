package com.kaczmarz.kontawalutowe.model;

import java.math.BigInteger;

public class SubAccount {
    private Currency currency;
    private BigInteger balance; //*0.01

    public SubAccount(Currency currency, BigInteger balance){
        this.currency = currency;
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }
}

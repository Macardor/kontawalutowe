package com.kaczmarz.kontawalutowe.model;

import java.math.BigInteger;

public class ExchangeRates {
    private Currency currency;
    private BigInteger bid;
    private BigInteger ask;

    public ExchangeRates(Currency currency, BigInteger bid, BigInteger ask){
        this.currency = currency;
        this.bid = bid;
        this.ask = ask;
    }

    public BigInteger getBid() {
        return bid;
    }

    public BigInteger getAsk() {
        return ask;
    }

    public Currency getCurrency() {
        return currency;
    }
}

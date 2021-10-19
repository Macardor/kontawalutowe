package com.kaczmarz.kontawalutowe.model;

import java.math.BigDecimal;

public class ExchangeRates {
    private Currency currency;
    private BigDecimal bid;
    private BigDecimal ask;

    public ExchangeRates(Currency currency, BigDecimal bid, BigDecimal ask){
        this.currency = currency;
        this.bid = bid;
        this.ask = ask;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public Currency getCurrency() {
        return currency;
    }
}

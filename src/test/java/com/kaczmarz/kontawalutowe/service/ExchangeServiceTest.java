package com.kaczmarz.kontawalutowe.service;

import com.kaczmarz.kontawalutowe.model.Currency;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeServiceTest {
    private final ExchangeService exchangeService = new ExchangeService();

    @Test
    void testIsAmoutAfterExchangeLowerBetweenPLNandUSD() throws IOException {
        BigDecimal startValue = new BigDecimal("100");
        BigDecimal amount = exchangeService.getExchangeAmount(Currency.PLN, Currency.USD, startValue);
        amount = exchangeService.getExchangeAmount(Currency.USD, Currency.PLN, amount);
;
        assertTrue(amount.compareTo(startValue) < 0);
    }

}
package com.kaczmarz.kontawalutowe.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Account {
    private List<SubAccount> subAccounts;


    public Account(List<SubAccount> subAccounts){
        this.subAccounts = subAccounts;
    }

    public void addSubAccount(SubAccount subAccount){
        this.subAccounts.add(subAccount);
    }

    public void addFunds(Currency currency, BigDecimal amount){
        getSubAccountByCurrency(currency)
                .setBalance(getSubAccountBalance(currency)
                        .add(amount));
    }

    public void substractFunds(Currency currency, BigDecimal amount){
        getSubAccountByCurrency(currency)
                .setBalance(getSubAccountBalance(currency)
                        .subtract(amount));;

    }

    public BigDecimal getSubAccountBalance(Currency currency){
        SubAccount temp = getSubAccountByCurrency(currency);

        if (Objects.isNull(temp)) addSubAccount(new SubAccount(currency, new BigDecimal("0")));

        return temp.getBalance();
    }

    public SubAccount getSubAccountByCurrency(Currency currency){
        return subAccounts.stream()
                .filter(sA -> sA.getCurrency().equals(currency))
                .findAny()
                .orElse(null);
    }

    private boolean isSubAccountPresent(Currency currency){
        SubAccount temp = subAccounts.stream()
                .filter(sA -> (sA.getCurrency().equals(currency)))
                .findAny()
                .orElse(null);

        return temp != null;
    }

    public List<SubAccount> getSubAccounts() {
        return subAccounts;
    }
}

package com.kaczmarz.kontawalutowe.model;

public class User {
    private String PESEL;
    private String fullName;
    private Account account;

    public User(String PESEL, String fullName, Account account){
        this.PESEL = PESEL;
        this.fullName = fullName;
        this.account = account;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPESEL() {
        return PESEL;
    }

    public Account getAccount() {
        return account;
    }
}

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

    @Override
    public boolean equals(Object o){
        if (o == this) return true;

        if (!(o instanceof User)) return false;

        User u = (User) o;

        return PESEL.equals(u.PESEL)
                && fullName.equals(u.fullName);
    }
}

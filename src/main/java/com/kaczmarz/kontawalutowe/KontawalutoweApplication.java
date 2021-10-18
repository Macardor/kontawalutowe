package com.kaczmarz.kontawalutowe;

import com.kaczmarz.kontawalutowe.database.Database;
import com.kaczmarz.kontawalutowe.model.Account;
import com.kaczmarz.kontawalutowe.model.Currency;
import com.kaczmarz.kontawalutowe.model.SubAccount;
import com.kaczmarz.kontawalutowe.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class KontawalutoweApplication {

	public static void main(String[] args) {
		List<SubAccount> subAccounts = new ArrayList<>();
		SubAccount plnTest = new SubAccount(Currency.PLN, new BigInteger("100"));
		subAccounts.add(plnTest);
		Account testAccount = new Account(subAccounts);
		User testUser = new User("99120101234", "Jan Kowalski", testAccount);
		Database.users.add(testUser);
		SpringApplication.run(KontawalutoweApplication.class, args);
	}

}

package com.kaczmarz.kontawalutowe.controller;

import com.kaczmarz.kontawalutowe.exception.ExchangeServiceException;
import com.kaczmarz.kontawalutowe.exception.UnderageUserException;
import com.kaczmarz.kontawalutowe.model.Account;
import com.kaczmarz.kontawalutowe.model.Currency;
import com.kaczmarz.kontawalutowe.model.SubAccount;
import com.kaczmarz.kontawalutowe.model.User;
import com.kaczmarz.kontawalutowe.service.ExchangeService;
import com.kaczmarz.kontawalutowe.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/kontawalutowe/user")
public class UserController {

    private final UserService userService;
    private final ExchangeService exchangeService;

    public UserController(UserService userService, ExchangeService exchangeService){
        this.userService = userService;
        this.exchangeService = exchangeService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public User getUser(@RequestParam String PESEL){
        return userService.getUserByPESEL(PESEL);
    }

    @PostMapping(
            value = "/register",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void registerUser(@RequestBody Map<String, String> userData){
        Account account = new Account(new ArrayList<>());
        account.addSubAccount(new SubAccount(Currency.PLN, new BigDecimal(userData.get("balance"))));

        User inputUser = new User(
                userData.get("pesel"),
                userData.get("fullName"),
                account);

        if(userService.isOfAge(inputUser.getPESEL())){
            userService.addUser(inputUser);
        }else throw new UnderageUserException("User has to be age 18 or above to create an account");
    }

    @PostMapping(
            value = "/exchange",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void exchangeFunds(@RequestBody Map<String, String> userData){
        String PESEL = userData.get("pesel");
        Currency from = Currency.valueOf(userData.get("from").toUpperCase());
        Currency to = Currency.valueOf(userData.get("to").toUpperCase());
        BigDecimal amount = new BigDecimal(userData.get("amount"));

        User user = userService.getUserByPESEL(PESEL);

        if (user.getAccount().getSubAccountBalance(from).compareTo(amount) > 0){
            try{
                BigDecimal exchangeAmount = exchangeService.getExchangeAmount(from, to, amount);
                user.getAccount().substractFunds(from, amount);
                user.getAccount().addFunds(to, exchangeAmount);
                userService.updateUser(user);
            }catch (IOException e){
                throw new ExchangeServiceException("Couldn't exchange funds");
            }
        }


    }

}

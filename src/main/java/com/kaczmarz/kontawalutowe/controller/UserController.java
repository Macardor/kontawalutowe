package com.kaczmarz.kontawalutowe.controller;

import com.kaczmarz.kontawalutowe.exception.UnderageUserException;
import com.kaczmarz.kontawalutowe.model.Account;
import com.kaczmarz.kontawalutowe.model.Currency;
import com.kaczmarz.kontawalutowe.model.SubAccount;
import com.kaczmarz.kontawalutowe.model.User;
import com.kaczmarz.kontawalutowe.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Map;

@RestController
@RequestMapping("api/kontawalutowe/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
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
        Account account = new Account();
        account.addSubAccount(new SubAccount(Currency.PLN, new BigInteger(userData.get("balance"))));
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
    public void exchangeMoney(@RequestBody Map<String, String> userData){

    }

}

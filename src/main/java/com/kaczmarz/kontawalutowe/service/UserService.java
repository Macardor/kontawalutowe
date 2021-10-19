package com.kaczmarz.kontawalutowe.service;

import com.kaczmarz.kontawalutowe.database.Database;
import com.kaczmarz.kontawalutowe.exception.UserNotFoundException;
import com.kaczmarz.kontawalutowe.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class UserService {
    private static final int AGE = 18;
    private Database database = new Database();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public UserService(){

    }

    public void addUser(User user){
        database.addUser(user);
    }

    public User getUserByPESEL(String PESEL){
        User user;
        try {
            user = Database.getUserByPESEL(PESEL);
        } catch (NullPointerException e) {
            throw new UserNotFoundException("User not found in Database");
        }
        return user;
    }

    public void updateUser(User user){
        database.updateUser(user);
    }

    public boolean isOfAge(String PESEL){
        LocalDate userDateOfBirth = PESELToAge(PESEL);
        LocalDate present = LocalDate.now();
        long diff = ChronoUnit.YEARS.between(userDateOfBirth, present);
        return diff >= AGE;
    }

    private LocalDate PESELToAge(String PESEL){
        String[] split = PESEL.split("(?<=\\G.{2})");
        String yy;
        String mm = split[1];
        String dd = split[2];

        //PESEL 2000 - 2099
        if(Integer.parseInt(mm) > 12 && Integer.parseInt(mm) <= 32){
            mm = String.valueOf(Integer.parseInt(mm) - 20);
            yy = "20" + split[0];
        }
        //PESEL 2100 - 2199
        else if(Integer.parseInt(mm) > 32 && Integer.parseInt(mm) <= 52){
            mm = String.valueOf(Integer.parseInt(mm) - 40);
            yy = "21" + split[0];
        }
        //PESEL 1900 - 1999
        else{
            yy = "19" + split[0];
        }

        String stringDate = "";

        stringDate = new StringBuilder(stringDate)
                .append(yy)
                .append(mm)
                .append(dd)
                .toString();

        return LocalDate.parse(stringDate, formatter);
    }

}

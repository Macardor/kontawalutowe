package com.kaczmarz.kontawalutowe.database;

import com.kaczmarz.kontawalutowe.model.User;

import java.util.ArrayList;
import java.util.List;

public class Database {
    public static List<User> users = new ArrayList<>();

    public void addUser(User user){
        if(!users.contains(user)) users.add(user);
    }

    public static User getUserByPESEL(String PESEL){
        return users.stream()
                .filter(user -> PESEL.equals(user.getPESEL()))
                .findAny()
                .orElse(null);
    }

    public void updateUser(User user){
        for (User u : users) {
            if (u.getPESEL().equals(user.getPESEL())){
                u = user;
            }
        }
    }
}

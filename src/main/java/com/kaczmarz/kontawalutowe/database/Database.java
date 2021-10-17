package com.kaczmarz.kontawalutowe.database;

import com.kaczmarz.kontawalutowe.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Database {
    private static List<User> users = new ArrayList<>();

    public void addUser(User user){
        if(!users.contains(user)) users.add(user);
    }

    public User getUserByPESEL(String PESEL){
        User u = users.stream()
                .filter(user -> PESEL.equals(user.getPESEL()))
                .findAny()
                .orElse(null);

        return u;
    }

    public void updateUser(User user){
        for (User u : users) {
            if (u.getPESEL().equals(user.getPESEL())){
                u = user;
            }
        }
    }

    public void deleteUserByPesel(String PESEL){
        User u = users.stream()
                .filter(user -> PESEL.equals(user.getPESEL()))
                .findAny()
                .orElse(null);

        if(Objects.nonNull(u)) users.remove(u);
    }
}

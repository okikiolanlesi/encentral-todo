package org.example.services;

import org.apache.commons.collections4.map.HashedMap;
import org.example.entities.User;

public class UserService {
    private HashedMap<String, User> users;

    public UserService(HashedMap<String, User> users){
        this.users = users;
    }

    public String register(String email, String password){

        if(users.containsKey(email)){
            return "User already exists with that email";
        }

        users.put(email, new User(email, password));

        return "User registered successfully";
    }

    public String login(String email, String password){

        if(!users.containsKey(email)){
            return "User does not exist";
        }
        User user = users.get(email);

        if(!user.getPassword().equals(password)){
            return "wrong password";
        }
        user.setIsLoggedIn(true);

        return "User logged in successfully successfully";
    }

    public String updatePassword(String email, String newPassword){

        if(!users.containsKey(email)){
            return "User does not exist";
        }

        User user = users.get(email);

        user.setPassword(newPassword);

        return "User password updated successfully successfully";
    }
}

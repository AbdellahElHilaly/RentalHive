package com.youcode.rentalhive.helper;

import com.youcode.rentalhive.dao.model.User;
import lombok.AllArgsConstructor;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@AllArgsConstructor
public class Authentication {




    public Optional<User> authenticate(User user, String password) {
        if (user.getPassword().equals(password)) {
            user.setIsAuthenticated(true);
            return Optional.of(user);
        }
        return Optional.empty();
    }



}

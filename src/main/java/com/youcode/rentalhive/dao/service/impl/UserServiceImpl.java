package com.youcode.rentalhive.dao.service.impl;

import com.youcode.rentalhive.dao.model.User;
import com.youcode.rentalhive.dao.repository.UserRepository;
import com.youcode.rentalhive.dao.service.UserService;
import com.youcode.rentalhive.helper.Authentication;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Authentication authentication= new Authentication();


    @Override
    public List<User> selectAll() {
        return (userRepository.findAll());
    }

    @Override
    public Optional<User> selectById(Long id) {
        return Optional.of(findByIdOrThrow(id));
    }

    @Override
    public Optional<User> insert(User user) {
        return Optional.of(userRepository.save(user));
    }

    @Override
    public Optional<User> update(User user, Long id) {
        user.setId(findByIdOrThrow(id).getId());
        return Optional.of(userRepository.save(user));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.delete(findByIdOrThrow(id));
    }

    @Override
    public void login(String email, String password) {
        User user = findByEmailOrThrow(email);

        if(user.getIsAuthenticated()) {
            throw new IllegalArgumentException("User is already authenticated");
        }

        else if (authentication.authenticate(user, password).isPresent()) {
            user.setIsAuthenticated(true);
            userRepository.save(user);
        }
        else {
            throw new IllegalArgumentException("Invalid credentials");
        }

    }

    @Override
    public void logout(String email) {
        User user = findByEmailOrThrow(email);
        if (user.getIsAuthenticated()) {
            user.setIsAuthenticated(false);
            userRepository.save(user);
        }
        else {
            throw new IllegalArgumentException("User is not authenticated");
        }
    }


    @Override
    public User findByIdOrThrow(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Invalid user Id : " + id);
        });
    }

    @Override
    public User findByEmailOrThrow(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> {
            return new IllegalArgumentException("Invalid user email : " + email);
        });
    }


}


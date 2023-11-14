package com.youcode.rentalhive.dao.service;

import com.youcode.rentalhive.dao.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    public List<User> selectAll();
    public Optional<User> selectById(Long id);
    public Optional<User> insert(User user);
    public Optional<User> update(User user, Long id);
    public void deleteById(Long id);
    void login(String email, String password);
    void logout(String email);

    public User findByIdOrThrow(Long id);
    public User findByEmailOrThrow(String email);

}


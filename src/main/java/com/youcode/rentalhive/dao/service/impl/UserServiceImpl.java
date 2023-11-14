package com.youcode.rentalhive.dao.service.impl;

import com.youcode.rentalhive.dao.model.User;
import com.youcode.rentalhive.dao.repository.UserRepository;
import com.youcode.rentalhive.dao.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


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
    public Optional<User> update(User user) {
        findByIdOrThrow(user.getId());
        return Optional.of(userRepository.save(findByIdOrThrow(user.getId())));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.delete(findByIdOrThrow(id));

    }

    @Override
    public User findByIdOrThrow(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> {
                    new IllegalArgumentException("Invalid user Id:" + id);
                    return null;
                }
        );
    }
}


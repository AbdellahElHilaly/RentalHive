package com.youcode.rentalhive.dao.service.impl;

import com.youcode.rentalhive.dao.model.User;
import com.youcode.rentalhive.dao.repository.UserRepository;
import com.youcode.rentalhive.dao.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public List<User> selectAll() {
        return userRepository.findAll();
    }
}

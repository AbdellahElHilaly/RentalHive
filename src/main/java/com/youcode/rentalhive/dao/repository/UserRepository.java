package com.youcode.rentalhive.dao.repository;

import com.youcode.rentalhive.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Optional<User> findByEmail(String email);
}

package com.youcode.rentalhive.dao.service.save;

import com.youcode.rentalhive.dao.model.User;
import com.youcode.rentalhive.dao.repository.UserRepository;
import com.youcode.rentalhive.dao.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class UserServiceSaveTest {

    @Autowired
    private UserService userService;


    @Test
    void testInsertNewEmail() {
        User user = User.builder()
                .name("John Doe")
                .email("john.doe." + UUID.randomUUID() + "@gmail.com")
                .password("password123")
                .build();

        Optional<User> insertedUser = userService.insert(user);
        Assertions.assertTrue(insertedUser.isPresent(), "User should be inserted");
        Assertions.assertNotNull(insertedUser.get().getId(), "User ID should not be null after insert");
    }

    @Test
    void testInsertExistingEmail() {
        User user = User.builder()
                .name("John Doe")
                .email("abdellah@gmail.com")
                .password("password123")
                .build();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> userService.insert(user));
    }

    @Test
    void testInsertNullEmail() {
        User user = User.builder()
                .name("John Doe")
                .email(null)
                .password("password123")
                .build();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> userService.insert(user));
    }

    @Test
    void testInsertNullUser() {
        Assertions.assertThrows(NullPointerException.class, () -> userService.insert(null));
    }



}



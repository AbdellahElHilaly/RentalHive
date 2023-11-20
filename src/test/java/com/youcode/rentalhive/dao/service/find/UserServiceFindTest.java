package com.youcode.rentalhive.dao.service.find;

import com.youcode.rentalhive.dao.model.User; // Assuming User is your entity class
import com.youcode.rentalhive.dao.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceFindTest {

    @Autowired
    private UserService userService;

    @Test
    void testFindAll() {
        List<User> userList = userService.selectAll();
        Assertions.assertNotNull(userList, "User list should not be null");
        Assertions.assertFalse(userList.isEmpty(), "User list should not be empty");
    }

    @Test
    void testFindByLongId() {
        User user = userService.findByIdOrThrow(1L);
        Assertions.assertNotNull(user, "User should not be null");
    }

    @Test
    void testFindByNullId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.findByIdOrThrow(null));
    }

    @Test
    void testFindByExistingEmail() {
        User user = userService.findByEmailOrThrow("abdellah@gmail.com");
        Assertions.assertNotNull(user, "User should not be null");

    }

    @Test
    void testFindByNonExistingEmail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.findByEmailOrThrow("##########"));
    }

    @Test
    void testFindByNullEmail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.findByEmailOrThrow(null));
    }

}

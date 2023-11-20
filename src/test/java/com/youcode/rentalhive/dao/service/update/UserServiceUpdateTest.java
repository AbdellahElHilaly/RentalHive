package com.youcode.rentalhive.dao.service.update;

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

@SpringBootTest
public class UserServiceUpdateTest {

    @Autowired
    private UserService userService;

    @SpyBean
    private UserRepository userRepositorySpy;

    @Test
    void testUpdateExistingEmail() {
        User existingUser = userService.selectAll().get(0);

        User updatedUser = User.builder()
                .id(existingUser.getId())
                .name("Updated Name")
                .email("abdellah@gmail.com") // Using an existing email
                .password("updatedPassword123")
                .build();

        Mockito.doThrow(DataIntegrityViolationException.class)
                .when(userRepositorySpy).save(Mockito.any(User.class));

        Assertions.assertThrows(DataIntegrityViolationException.class,
                () -> userService.update(updatedUser, existingUser.getId()));

        Mockito.verify(userRepositorySpy, Mockito.times(1)).save(Mockito.eq(updatedUser));
    }

    @Test
    void testUpdateNullUser() {
        User existingUser = userService.selectAll().get(0);

        Assertions.assertThrows(NullPointerException.class,
                () -> userService.update(null, existingUser.getId()));

        Mockito.verify(userRepositorySpy, Mockito.never()).save(Mockito.any(User.class));
    }

}

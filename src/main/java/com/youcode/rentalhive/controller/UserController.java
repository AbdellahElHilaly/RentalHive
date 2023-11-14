package com.youcode.rentalhive.controller;

import com.youcode.rentalhive.dao.model.User;
import com.youcode.rentalhive.dao.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("api/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.selectAll());
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(userService.findByIdOrThrow(Long.valueOf(id)));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().header("Message", "Invalid user Id format").build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().header("Message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().header("Message", e.getMessage()).build();
        }
    }

    @PostMapping("/api/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.insert(user).orElse(null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().header("Message", e.getMessage()).build();
        }
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.update(user, Long.valueOf(id)).orElse(null));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().header("Message", "Invalid user Id format").build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().header("Message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().header("Message", e.getMessage()).build();
        }
    }


    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        try {
            userService.deleteById(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().header("Message", "Invalid user Id format").build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().header("Message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().header("Message", e.getMessage()).build();
        }
    }


}

package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.User;
import com.BLUEGREEN.WebWatchMovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserAPIController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User registrationRequest) {
        User registeredUser = userService.registerUser(registrationRequest, registrationRequest.getRoleIds());

        return ResponseEntity.ok(registeredUser);
    }

    @PutMapping("/{userId}/roles")
    public ResponseEntity<User> editUserRole(@PathVariable int userId, @RequestBody User roleList) {
        User updatedUser = userService.editUserRole(userId, roleList.getRoleIds());
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User loginUser) {
        String nameLogin = loginUser.getNameLogin();
        String password = loginUser.getPassword();

        User user = userService.loginUser(nameLogin, password);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(401).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/hide")
    public ResponseEntity<Void> hideUser(@PathVariable int id, @RequestBody User check) {
        boolean result = userService.hideUser(id, check.getIsHidden());
        if (result) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/loadUserByUsername")
    public ResponseEntity<UserDetails> loadUserByUsername(@RequestBody String username) {
        try {
            UserDetails userDetails = userService.loadUserByUsername(username);
            return ResponseEntity.ok(userDetails);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}

package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.User;
import com.BLUEGREEN.WebWatchMovie.model.UserRoles;
import com.BLUEGREEN.WebWatchMovie.model.UserRolesId;
import com.BLUEGREEN.WebWatchMovie.service.UserService;
import org.hibernate.internal.util.collections.ArrayHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

//    @PostMapping("/register")
//    public ResponseEntity<User> registerUser(@RequestBody User user) {
//        User registeredUser = userService.registerUser(user);
//        return ResponseEntity.ok(registeredUser);
//    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user, @RequestParam int[] roles) {
        User registeredUser = userService.registerUser(user, roles);
        return ResponseEntity.ok(registeredUser);
    }

//    @PostMapping("/register")
//    public ResponseEntity<User> registerUser(@RequestBody User registerRequest) {
//        User user = new User();
//        user.setNameLogin(registerRequest.getNameLogin());
//        user.setPassword(registerRequest.getPassword());
//        user.setName(registerRequest.getName());
//        user.setEmail(registerRequest.getEmail());
//        user.setIsHidden(false);
////        User registeredUser = userService.registerUser(user, registerRequest.getRoleId());
////        return ResponseEntity.ok(registeredUser);
//        return ResponseEntity.ok(user);
//    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestParam String nameLogin, @RequestParam String password) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/hide")
    public ResponseEntity<Void> hideUser(@PathVariable int id) {
        if (userService.hideUser(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

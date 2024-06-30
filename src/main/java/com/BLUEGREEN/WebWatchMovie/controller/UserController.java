package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.User;
import com.BLUEGREEN.WebWatchMovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestParam String nameLogin, @RequestParam String password) {
        User user = userService.loginUser(nameLogin, password);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(401).build();
    }

    @GetMapping("/anime-details")
    public String animeDetails() {
        return "user/anime-details";
    }

    @GetMapping("/anime-watching")
    public String animeWatching() {
        return "user/anime-watching";
    }

    @GetMapping("/blog")
    public String blog() {
        return "user/blog";
    }

    @GetMapping("/blog-details")
    public String blogDetails() {
        return "user/blog-details";
    }

    @GetMapping("/categories")
    public String categories() {
        return "user/categories";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "user/signup";
    }
}

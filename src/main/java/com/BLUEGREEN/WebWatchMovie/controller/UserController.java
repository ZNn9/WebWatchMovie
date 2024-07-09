package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.User;
import com.BLUEGREEN.WebWatchMovie.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "/user/login";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, @RequestParam("confirmPassword") String confirmPassword, Model model) {
        try {
            // Validate and register user
            if (!user.getPassword().equals(confirmPassword)) {
                throw new RuntimeException("Passwords do not match!");
            }
            // Set giá trị mặc định cho roleIds nếu cần
            int[] defaultRoleIds = {1}; // Example default roleIds
            userService.registerUser(user, defaultRoleIds);
            return "redirect:/"; // Redirect to homepage or login page
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error"; // Handle registration error
        }
    }
}

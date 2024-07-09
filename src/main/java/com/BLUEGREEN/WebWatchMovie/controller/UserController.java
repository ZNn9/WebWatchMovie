package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.PasswordResetToken;
import com.BLUEGREEN.WebWatchMovie.model.User;
import com.BLUEGREEN.WebWatchMovie.repository.TokenRepository;
import com.BLUEGREEN.WebWatchMovie.repository.UserRepository;
import com.BLUEGREEN.WebWatchMovie.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/register")
    public String register(@NotNull Model model) {
        model.addAttribute("user", new User());
        return "user/signup";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user,
                           @NotNull BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            return "user/signup";
        }

        // Assign default roles to the user (e.g., ROLE_USER)
        int[] defaultRoles = {1}; // Assuming 1 is the ID for the default role
        userService.registerUser(user, defaultRoles);

        return "redirect:/user/login";
    }


    @GetMapping("/forgotPassword")
    public String forgotPassword() {
        return "user/forgot-password";
    }

    @PostMapping("/forgotPassword")
    public String forgotPassordProcess(@ModelAttribute User users) {
        String output = "";
        User user = userRepository.findByEmail(users.getEmail());
        if (user != null) {
            output = userService.sendEmail(user);
        }
        if (output.equals("success")) {
            return "redirect:/user/forgotPassword?success";
        }
        return "redirect:/user/login?error";
    }

    @GetMapping("/resetPassword/{token}")
    public String resetPasswordForm(@PathVariable String token, Model model) {
        PasswordResetToken reset = tokenRepository.findByToken(token);
        if (reset != null && userService.hasExipred(reset.getExpiryDateTime())) {
            model.addAttribute("email", reset.getUser().getEmail());
            return "user/reset-password";
        }
        return "redirect:/user/forgotPassword?error";
    }

    @PostMapping("/resetPassword")
    public String passwordResetProcess(@ModelAttribute User userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail());
        if(user != null) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userRepository.save(user);
        }
        return "redirect:/user/login";
    }
}

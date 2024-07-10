package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.User;
import com.BLUEGREEN.WebWatchMovie.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping("/login")
//    public String showLoginForm(){
//        return "/user/login";
//    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "user/login";
    }

//    @PostMapping("/login")
//    public String loginUser(@RequestParam("nameLogin") String nameLogin,
//                            @RequestParam("password") String password,
//                            Model model,
//                            RedirectAttributes redirectAttributes) {
//
//        User user = userService.loginUser(nameLogin, password);
//        if (user != null) {
//            // Đăng nhập thành công, chuyển hướng đến trang chính
//            return "redirect:/";
//        } else {
//            // Đăng nhập không thành công, hiển thị thông báo lỗi
//            model.addAttribute("error", "Invalid username or password");
//            return "user/login";
//        }
//    }

    @GetMapping("/register")
    public String showRegisterForm(@NotNull Model model) {
        model.addAttribute("user", new User());
        return "user/register"; // Trả về tên file HTML
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult bindingResult,
                               @ModelAttribute("confirmPassword") String confirmPassword,
                               @ModelAttribute("roleIds") String roleIds,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        // Kiểm tra lỗi của form
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("user", user); // Giữ lại dữ liệu đã nhập
            return "user/register"; // Trả về lại template đăng ký
        }

        // Kiểm tra mật khẩu và confirmPassword
        if (!user.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Password and Confirm Password must match");
            model.addAttribute("user", user); // Giữ lại dữ liệu đã nhập
            return "user/register"; // Trả về lại template đăng ký
        }

        // Xử lý roleIds từ input ẩn
        int[] newRoleIds = Arrays.stream(roleIds.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        user.setRoleIds(newRoleIds);

        // Xử lý logic đăng ký người dùng
        userService.registerUser(user, user.getRoleIds());

        return "redirect:/"; // Chuyển hướng sau khi đăng ký thành công
    }
}

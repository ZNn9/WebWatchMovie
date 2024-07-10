package com.BLUEGREEN.WebWatchMovie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/catalog")
    public String catalog() {
        return "admin/catalog";
    }

    @GetMapping("/add-item")
    public String additem() {
        return "admin/add-item";
    }

    @GetMapping("/comments")
    public String comments() {
        return "admin/comments";
    }

    @GetMapping("/edit-user")
    public String edituser() {
        return "admin/edit-user";
    }


    @GetMapping("/forgot")
    public String forgot() {
        return "admin/forgot";
    }

    @GetMapping("/reviews")
    public String reviews() {
        return "admin/reviews";
    }

    @GetMapping("/users")
    public String users () {
        return "admin/users";
    }

    @GetMapping("/index")
    public String index() {
        return "admin/index";
    }

}

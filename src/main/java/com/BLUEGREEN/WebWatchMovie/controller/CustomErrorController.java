//package com.BLUEGREEN.WebWatchMovie.controller;
//
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import javax.servlet.http.HttpServletRequest;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@Controller
//public class CustomErrorController implements ErrorController {
//
//    private static final Logger logger = LoggerFactory.getLogger(CustomErrorController.class);
//
//    @RequestMapping("/error")
//    public String handleError(HttpServletRequest request, Model model) {
//        Object status = request.getAttribute("javax.servlet.error.status_code");
//        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
//
//        logger.error("Error with status code " + status, throwable);
//
//        model.addAttribute("status", status);
//        model.addAttribute("error", throwable != null ? throwable.getMessage() : "N/A");
//
//        return "error";
//    }
//
//    // Phương thức getErrorPath đã được loại bỏ
//}

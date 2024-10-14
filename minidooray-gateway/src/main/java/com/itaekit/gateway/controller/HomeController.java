package com.itaekit.gateway.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"/", "/index"})
    public String home(HttpSession session, Model model) {
        model.addAttribute("userId", session.getAttribute("userId"));
        return "index";
    }
}

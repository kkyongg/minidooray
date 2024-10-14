package com.itaekit.gateway.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomControllerAdvice {
    @ExceptionHandler({Exception.class})
    public String exceptionHandler(Exception ex, Model model) {
        model.addAttribute("exception", ex.getClass().getSimpleName());
        model.addAttribute("message", ex.getMessage());
        return "error";
    }
}

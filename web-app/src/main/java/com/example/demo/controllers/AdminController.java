package com.example.demo.controllers;

import com.example.demo.models.user.User;
import com.example.demo.repositories.user.UserRepository;
import javassist.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AdminController {

    UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("admins")
    public String index(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            User user = getOptionalUser(userRepository.findByUsername(auth.getName()));
            model.addAttribute("username", user.getUsername());
            model.addAttribute("user", user);
            model.addAttribute("role", user.getRole());
        }
        catch(Exception e) {

            }
        return "views/admin/index";
    }

    @GetMapping("admins/create")
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "views/admin/create";
    }

    @PostMapping("admins/add")
    public String create(@ModelAttribute User user, BindingResult errors, Model model) {
        System.out.println("User: " + user);
        System.out.println("BindingResult: " + errors);
        System.out.println("POST: admins/add");
        return "views/admin/index";
    }

    private User getOptionalUser(Optional<User> optionalUser) throws Exception {
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new Exception();
        }

    }
}

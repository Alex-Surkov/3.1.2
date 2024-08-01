package com.github.AlexSurkov312.controller;

import com.github.AlexSurkov312.model.User;
import com.github.AlexSurkov312.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String printUsers(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping
    public String addUser(@RequestParam("Name") String name, @RequestParam("lastName") String lastName, @RequestParam("age") int age, ModelMap model) {
        userService.saveUser(name, lastName, age);
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") long id, ModelMap model) {
        userService.removeUserById(id);
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") long id, Model user) {
        user.addAttribute(userService.getUser(id));
        return "update";
    }

    @PostMapping("/update")
    public String update(ModelMap model,
                         @RequestParam("id") long id,
                         @RequestParam("name") String newName,
                         @RequestParam("lastName") String newSurname,
                         @RequestParam("age") int newAge) {
        userService.updateUser(id, newName, newAge, newSurname);
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
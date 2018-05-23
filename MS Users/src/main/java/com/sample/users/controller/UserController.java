package com.sample.users.controller;

import com.sample.users.exception.EmailAlreadyUsedException;
import com.sample.users.exception.UserNotFoundException;
import com.sample.users.model.User;
import com.sample.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/auth/login")
    private String login(@RequestBody Map<String, String> json) throws UserNotFoundException {
        String email = json.get("email");
        String password = json.get("password");

        if (email == null || password == null) {
            return null;
        }

        User user = userService.getUserByEmailAndPassword(email, password);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user.getToken();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/auth/signup")
    private String signup(@RequestBody User user) throws EmailAlreadyUsedException {
        return userService.addUser(user);
    }

    @RequestMapping("/users/{userid}")
    private User getUser(@PathVariable String userid) {
        return userService.getUser(userid);
    }

    @RequestMapping("/users/token/{token}")
    private User getUserByToken(@PathVariable String token) {
        return userService.getUserByToken(token);
    }

}

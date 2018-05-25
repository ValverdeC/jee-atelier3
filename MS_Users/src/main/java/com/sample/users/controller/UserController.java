package com.sample.users.controller;

import com.sample.users.exception.EmailAlreadyUsedException;
import com.sample.users.exception.ParameterNotSpecifiedException;
import com.sample.users.exception.UserNotFoundException;
import com.sample.users.model.User;
import com.sample.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/auth/login")
    private String login(@RequestBody Map<String, String> json) throws UserNotFoundException {
        String email = json.get("email");
        String password = json.get("password");

        if (email == null || password == null) {
            return null;
        }

        User user = userService.getUserByEmailAndPassword(email, password);
        return user.getToken();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/auth/signup")
    private String signup(@RequestBody User user) throws EmailAlreadyUsedException, ParameterNotSpecifiedException {
        return userService.addUser(user);
    }

    @RequestMapping("/users/{userid}")
    private User getUser(@PathVariable String userid) throws UserNotFoundException {
        return userService.getUser(userid);
    }

    @RequestMapping("/me")
    private User getUserByToken(@RequestHeader("Authorization") String token) throws UserNotFoundException {
        return userService.getUserByToken(token);
    }

    @RequestMapping("/me/wallet")
    private BigDecimal getWallet(@RequestHeader("Authorization") String token) throws UserNotFoundException {
        User user = userService.getUserByToken(token);
        return user.getWallet();
    }

    @RequestMapping("/me/wallet/add/{amount:.+}")
    private BigDecimal addToWallet(@RequestHeader("Authorization") String token, @PathVariable BigDecimal amount) throws UserNotFoundException {
        return userService.addToWallet(token, amount);
    }

    @RequestMapping("/me/wallet/remove/{amount:.+}")
    private BigDecimal removeFromWallet(@RequestHeader("Authorization") String token, @PathVariable BigDecimal amount) throws UserNotFoundException {
        return userService.removeFromWallet(token, amount);
    }

}

package com.sample.users.service;

import com.sample.users.exception.*;
import com.sample.users.model.User;
import com.sample.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String id) throws UserNotFoundException {
        User user = userRepository.findOne(Integer.valueOf(id));
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    public String addUser(User user) throws EmailAlreadyUsedException, ParameterNotSpecifiedException {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new PasswordNotSpecifiedException();
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new EmailNotSpecifiedException();
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new NameNotSpecifiedException();
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new EmailAlreadyUsedException();
        }
        String key = getSaltString();
        user.setToken(key);
        userRepository.save(user);
        return user.getToken();
    }

    public User getUserByEmailAndPassword(String email, String password) throws UserNotFoundException, ParameterNotSpecifiedException {
        if (password == null || password.isEmpty()) {
            throw new PasswordNotSpecifiedException();
        }
        if (email == null || email.isEmpty()) {
            throw new EmailNotSpecifiedException();
        }

        User user = userRepository.findByEmailAndPassword(email, password);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    public User getUserByToken(String token) throws UserNotFoundException {
        User user = userRepository.findByToken(token);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    public BigDecimal addToWallet(String token, BigDecimal amount) throws UserNotFoundException {
        User user = this.getUserByToken(token);
        user.addToWallet(amount);
        userRepository.save(user);
        return user.getWallet();
    }

    public BigDecimal removeFromWallet(String token, BigDecimal amount) throws UserNotFoundException {
        User user = this.getUserByToken(token);
        user.removeFromWallet(amount);
        userRepository.save(user);
        return user.getWallet();
    }

    private String getSaltString() {
        String allCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        SecureRandom rnd = new SecureRandom();
        while (salt.length() < 32) { // length of the random string.
            int index = rnd.nextInt(allCharacters.length());
            salt.append(allCharacters.charAt(index));
        }
        return salt.toString();

    }

}


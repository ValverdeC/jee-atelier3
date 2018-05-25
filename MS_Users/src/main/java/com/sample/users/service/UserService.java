package com.sample.users.service;

import com.sample.users.exception.EmailAlreadyUsedException;
import com.sample.users.exception.UserNotFoundException;
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

    public String addUser(User user) throws EmailAlreadyUsedException {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new EmailAlreadyUsedException();
        }
        String key = getSaltString();
        user.setToken(key);
        user = userRepository.save(user);
        return user.getToken();
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.delete(Integer.valueOf(id));
    }

    public User getUserByEmailAndPassword(String email, String password) throws UserNotFoundException {
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
        user = userRepository.save(user);
        return user.getWallet();
    }

    public BigDecimal removeFromWallet(String token, BigDecimal amount) throws UserNotFoundException {
        User user = this.getUserByToken(token);
        user.removeFromWallet(amount);
        user = userRepository.save(user);
        return user.getWallet();
    }

    private String getSaltString() {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        SecureRandom rnd = new SecureRandom();
        while (salt.length() < 32) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();

    }

}


package com.sample.users.service;

import com.sample.users.exception.EmailAlreadyUsedException;
import com.sample.users.model.User;
import com.sample.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(String id) {
        return userRepository.findOne(Integer.valueOf(id));
    }

    public String addUser(User user) throws EmailAlreadyUsedException {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new EmailAlreadyUsedException();
        }
        String key = getSaltString();
        user.setToken(key);
        userRepository.save(user);
        return user.getToken();
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.delete(Integer.valueOf(id));
    }

    public User getUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public User getUserByToken(String token) {
        return userRepository.findByToken(token);
    }

    protected String getSaltString() {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        SecureRandom rnd = new SecureRandom();
        while (salt.length() < 32) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}


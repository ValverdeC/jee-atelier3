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

    /**
     * Récupération d'un utilisateur selon son identifiant
     *
     * @param id L'identifiant de l'utilisateur
     * @return L'utilisateur sélectionné
     * @throws UserNotFoundException Si l'utilisateur n'a pas été trouvé
     */
    public User getUser(String id) throws UserNotFoundException {
        User user = userRepository.findOne(Integer.valueOf(id));
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    /**
     * Inscription d'un utilisateur
     * @param user L'utilisateur qui s'inscrit
     * @return Le token de l'utilisateur
     * @throws EmailAlreadyUsedException Si un utilisateur existe déjà avec l'adresse mail renseigné
     * @throws ParameterNotSpecifiedException Si l'adresse mai, le nom ou le mot de passe sont pas fournis
     */
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

    /**
     * Récupération d'un utilisateur
     * @param email L'adresse mail de l'utilisateur.
     * @param password Le mot de l'utilisateur.
     * @return L'utilisateur sélectionné
     * @throws UserNotFoundException Si l'utilisateur n'a pas été trouvé.
     * @throws ParameterNotSpecifiedException Si l'adresse mail ou le mot de passe sont pas fournis.
     */
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

    /**
     * Récupération d'un utilisateur selon son token
     * @param token Le token de l'utilisateur
     * @return L'utilisateur sélectionné
     * @throws UserNotFoundException Si l'utilisateur n'a pas été trouvé
     */
    public User getUserByToken(String token) throws UserNotFoundException {
        User user = userRepository.findByToken(token);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    /**
     * Ajoute de l'argent au porte-monnaie de l'utilisateur
     * @param token Le token de l'utilisateur
     * @param amount Montant à ajouter
     * @return Le nouveau montant du porte-monnaie
     * @throws UserNotFoundException Si l'utilisateur n'a pas été trouvé
     */
    public BigDecimal addToWallet(String token, BigDecimal amount) throws UserNotFoundException {
        User user = this.getUserByToken(token);
        user.addToWallet(amount);
        userRepository.save(user);
        return user.getWallet();
    }

    /**
     * Enlève de l'argent au porte-monnaie de l'utilisateur
     * @param token Le token de l'utilisateur
     * @param amount Montant à enlever
     * @return Le nouveau montant du porte-monnaie
     * @throws UserNotFoundException Si l'utilisateur n'a pas été trouvé
     */
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


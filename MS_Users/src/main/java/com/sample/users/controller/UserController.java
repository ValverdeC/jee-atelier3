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

    /**
     * Connexion d'un utilisateur
     *
     * @param json Un JSON avec l'adresse mail et le mot de passe de l'utilisateur
     * @return Le token de l'utilisateur
     * @throws UserNotFoundException          Si l'utilisateur n'a pas été trouvé
     * @throws ParameterNotSpecifiedException Si l'adresse mail ou le mot de passe sont pas fournis
     */
    @RequestMapping(method = RequestMethod.POST, value = "/auth/login")
    private String login(@RequestBody Map<String, String> json) throws UserNotFoundException, ParameterNotSpecifiedException {
        String email = json.get("email");
        String password = json.get("password");

        User user = userService.getUserByEmailAndPassword(email, password);
        return user.getToken();
    }

    /**
     * Inscription d'un utilisateur
     * @param user L'utilisateur qui s'inscrit
     * @return Le token de l'utilisateur
     * @throws EmailAlreadyUsedException Si un utilisateur existe déjà avec l'adresse mail renseigné
     * @throws ParameterNotSpecifiedException Si l'adresse mai, le nom ou le mot de passe sont pas fournis
     */
    @RequestMapping(method = RequestMethod.POST, value = "/auth/signup")
    private String signup(@RequestBody User user) throws EmailAlreadyUsedException, ParameterNotSpecifiedException {
        return userService.addUser(user);
    }

    /**
     * Récupération d'un utilisateur selon son identifiant
     * @param userid L'identifiant de l'utilisateur
     * @return Les informations de l'utilisateur
     * @throws UserNotFoundException Si l'utilisateur n'a pas été trouvé
     */
    @RequestMapping("/users/{userid}")
    private User getUser(@PathVariable String userid) throws UserNotFoundException {
        return userService.getUser(userid);
    }

    /**
     * Récupération d'un utilisateur selon son token
     * @param token Le token de l'utilisateur
     * @return Les informations de l'utilisateur
     * @throws UserNotFoundException Si l'utilisateur n'a pas été trouvé
     */
    @RequestMapping("/me")
    private User getUserByToken(@RequestHeader("Authorization") String token) throws UserNotFoundException {
        return userService.getUserByToken(token);
    }

    /**
     * Récupération du montant du porte-monnaie de l'utilisateur
     * @param token Le token de l'utilisateur
     * @return Le montant du porte-monnaie
     * @throws UserNotFoundException Si l'utilisateur n'a pas été trouvé
     */
    @RequestMapping("/me/wallet")
    private BigDecimal getWallet(@RequestHeader("Authorization") String token) throws UserNotFoundException {
        User user = userService.getUserByToken(token);
        return user.getWallet();
    }

    /**
     * Ajoute de l'argent au porte-monnaie de l'utilisateur
     * @param token Le token de l'utilisateur
     * @param amount Montant à ajouter
     * @return Le nouveau montant du porte-monnaie
     * @throws UserNotFoundException Si l'utilisateur n'a pas été trouvé
     */
    @RequestMapping("/me/wallet/add/{amount:.+}")
    private BigDecimal addToWallet(@RequestHeader("Authorization") String token, @PathVariable BigDecimal amount) throws UserNotFoundException {
        return userService.addToWallet(token, amount);
    }

    /**
     * Enlève de l'argent au porte-monnaie de l'utilisateur
     * @param token Le token de l'utilisateur
     * @param amount Montant à enlever
     * @return Le nouveau montant du porte-monnaie
     * @throws UserNotFoundException Si l'utilisateur n'a pas été trouvé
     */
    @RequestMapping("/me/wallet/remove/{amount:.+}")
    private BigDecimal removeFromWallet(@RequestHeader("Authorization") String token, @PathVariable BigDecimal amount) throws UserNotFoundException {
        return userService.removeFromWallet(token, amount);
    }

}

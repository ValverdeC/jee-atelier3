package com.sample.users.service;

import com.sample.users.exception.*;
import com.sample.users.model.User;
import com.sample.users.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static final int USER_ID = 42;
    private static final String USER_NAME = "Arthur Dent";
    private static final String USER_EMAIL = "arthur@dent.com";
    private static final String USER_PASSWORD = "arthur.dent";
    private static final BigDecimal USER_WALLET = BigDecimal.valueOf(79);
    private static final String USER_TOKEN = "OSNTfwVVFN1hc1p3d5KnSYg7Hsh1s7E8";
    @Mock
    private
    UserRepository userRepositoryMock;
    @InjectMocks
    private
    UserService userService;

    @Test
    public void testGetUser_succeed() throws UserNotFoundException {
        User user = new User();
        user.setId(USER_ID);
        user.setName(USER_NAME);
        user.setEmail(USER_EMAIL);
        user.setWallet(USER_WALLET);
        when(userRepositoryMock.findOne(USER_ID)).thenReturn(user);
        assertEquals(user, userService.getUser(String.valueOf(USER_ID)));
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetUser_failed_userNotFound() throws UserNotFoundException {
        when(userRepositoryMock.findOne(USER_ID)).thenReturn(null);
        userService.getUser(String.valueOf(USER_ID));
    }

    @Test
    public void testAddUser() throws EmailAlreadyUsedException, ParameterNotSpecifiedException {
        User user = new User();
        user.setEmail(USER_EMAIL);
        user.setName(USER_NAME);
        user.setPassword(USER_PASSWORD);
        userService.addUser(user);
    }

    @Test(expected = EmailNotSpecifiedException.class)
    public void testAddUser_noEmail() throws EmailAlreadyUsedException, ParameterNotSpecifiedException {
        User user = new User();
        user.setName(USER_NAME);
        user.setPassword(USER_PASSWORD);
        userService.addUser(user);
    }

    @Test(expected = EmailNotSpecifiedException.class)
    public void testAddUser_emptyEmail() throws EmailAlreadyUsedException, ParameterNotSpecifiedException {
        User user = new User();
        user.setEmail("");
        user.setName(USER_NAME);
        user.setPassword(USER_PASSWORD);
        userService.addUser(user);
    }

    @Test(expected = PasswordNotSpecifiedException.class)
    public void testAddUser_noPassword() throws EmailAlreadyUsedException, ParameterNotSpecifiedException {
        User user = new User();
        user.setName(USER_NAME);
        user.setEmail(USER_EMAIL);
        userService.addUser(user);
    }

    @Test(expected = PasswordNotSpecifiedException.class)
    public void testAddUser_emptyPassword() throws EmailAlreadyUsedException, ParameterNotSpecifiedException {
        User user = new User();
        user.setPassword("");
        user.setName(USER_NAME);
        user.setEmail(USER_EMAIL);
        userService.addUser(user);
    }

    @Test(expected = NameNotSpecifiedException.class)
    public void testAddUser_noName() throws EmailAlreadyUsedException, ParameterNotSpecifiedException {
        User user = new User();
        user.setPassword(USER_PASSWORD);
        user.setEmail(USER_EMAIL);
        userService.addUser(user);
    }

    @Test(expected = NameNotSpecifiedException.class)
    public void testAddUser_emptyName() throws EmailAlreadyUsedException, ParameterNotSpecifiedException {
        User user = new User();
        user.setName("");
        user.setPassword(USER_PASSWORD);
        user.setEmail(USER_EMAIL);
        userService.addUser(user);
    }

    @Test(expected = EmailAlreadyUsedException.class)
    public void testAddUser_emailAlreadyUsed() throws EmailAlreadyUsedException, ParameterNotSpecifiedException {
        User user = new User();
        user.setName(USER_NAME);
        user.setEmail(USER_EMAIL);
        user.setPassword(USER_PASSWORD);
        when(userRepositoryMock.findByEmail(USER_EMAIL)).thenReturn(user);
        userService.addUser(user);
    }

    @Test
    public void testGetUserByEmailAndPassword() throws UserNotFoundException, ParameterNotSpecifiedException {
        User user = new User();
        user.setName(USER_NAME);
        user.setEmail(USER_EMAIL);
        user.setPassword(USER_PASSWORD);

        when(userRepositoryMock.findByEmailAndPassword(USER_EMAIL, USER_PASSWORD)).thenReturn(user);
        assertEquals(user, userService.getUserByEmailAndPassword(USER_EMAIL, USER_PASSWORD));
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetUserByEmailAndPassword_fail() throws UserNotFoundException, ParameterNotSpecifiedException {
        when(userRepositoryMock.findByEmailAndPassword(USER_EMAIL, USER_PASSWORD)).thenReturn(null);
        userService.getUserByEmailAndPassword(USER_EMAIL, USER_PASSWORD);
    }

    @Test(expected = PasswordNotSpecifiedException.class)
    public void testGetUserByEmailAndPassword_failPassword() throws UserNotFoundException, ParameterNotSpecifiedException {
        userService.getUserByEmailAndPassword(USER_EMAIL, null);
    }

    @Test(expected = PasswordNotSpecifiedException.class)
    public void testGetUserByEmailAndPassword_failPasswordEmpty() throws UserNotFoundException, ParameterNotSpecifiedException {
        userService.getUserByEmailAndPassword(USER_EMAIL, "");
    }

    @Test(expected = EmailNotSpecifiedException.class)
    public void testGetUserByEmailAndPassword_failEmail() throws UserNotFoundException, ParameterNotSpecifiedException {
        userService.getUserByEmailAndPassword(null, USER_PASSWORD);
    }

    @Test(expected = EmailNotSpecifiedException.class)
    public void testGetUserByEmailAndPassword_failEmailEmpty() throws UserNotFoundException, ParameterNotSpecifiedException {
        userService.getUserByEmailAndPassword("", USER_PASSWORD);
    }

    @Test
    public void testGetUserByToken() throws UserNotFoundException {
        User user = new User();
        user.setName(USER_NAME);
        user.setEmail(USER_EMAIL);
        user.setPassword(USER_PASSWORD);
        user.setToken(USER_TOKEN);

        when(userRepositoryMock.findByToken(USER_TOKEN)).thenReturn(user);

        assertEquals(user, userService.getUserByToken(USER_TOKEN));
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetUserByToken_userNotFound() throws UserNotFoundException {
        when(userRepositoryMock.findByToken(USER_TOKEN)).thenReturn(null);
        userService.getUserByToken(USER_TOKEN);
    }

    @Test
    public void testAddToWallet() throws UserNotFoundException {
        User user = new User();
        user.setName(USER_NAME);
        user.setEmail(USER_EMAIL);
        user.setPassword(USER_PASSWORD);
        user.setToken(USER_TOKEN);
        user.setWallet(USER_WALLET);

        when(userRepositoryMock.findByToken(USER_TOKEN)).thenReturn(user);

        assertEquals(BigDecimal.valueOf(119).setScale(2, BigDecimal.ROUND_HALF_UP), userService.addToWallet(USER_TOKEN, BigDecimal.valueOf(40)));
    }

    @Test
    public void testRemoveFromWallet() throws UserNotFoundException {
        User user = new User();
        user.setName(USER_NAME);
        user.setEmail(USER_EMAIL);
        user.setPassword(USER_PASSWORD);
        user.setToken(USER_TOKEN);
        user.setWallet(USER_WALLET);

        when(userRepositoryMock.findByToken(USER_TOKEN)).thenReturn(user);

        assertEquals(BigDecimal.valueOf(39).setScale(2, BigDecimal.ROUND_HALF_UP), userService.removeFromWallet(USER_TOKEN, BigDecimal.valueOf(40)));
    }
}
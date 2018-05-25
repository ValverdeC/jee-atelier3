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

    @Test(expected = EmailNotSpecifiedException.class)
    public void testAddUser_noEmail() throws EmailAlreadyUsedException, ParameterNotSpecifiedException {
        User user = new User();
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

    @Test(expected = NameNotSpecifiedException.class)
    public void testAddUser_noName() throws EmailAlreadyUsedException, ParameterNotSpecifiedException {
        User user = new User();
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
}
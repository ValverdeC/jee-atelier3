package com.sample.users.service;

import com.sample.users.exception.UserNotFoundException;
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
}
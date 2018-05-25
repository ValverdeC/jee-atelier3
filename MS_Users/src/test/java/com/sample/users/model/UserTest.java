package com.sample.users.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class UserTest {

    private static final Integer USER_ID = 42;
    private static final String USER_NAME = "Arthur Dent";
    private static final String USER_EMAIL = "arthur@dent.com";
    private static final String USER_PASSWORD = "arthur.dent";
    private static final BigDecimal USER_WALLET = BigDecimal.valueOf(79);
    private static final String USER_TOKEN = "OSNTfwVVFN1hc1p3d5KnSYg7Hsh1s7E8";

    @Test
    public void addToWallet() {
        User user = new User();
        user.setWallet(USER_WALLET);
        user.addToWallet(BigDecimal.valueOf(40.567));
        assertEquals(BigDecimal.valueOf(119.57), user.getWallet());
    }

    @Test
    public void removeFromWallet() {
        User user = new User();
        user.setWallet(USER_WALLET);
        user.removeFromWallet(BigDecimal.valueOf(40));
        assertEquals(BigDecimal.valueOf(39.00).setScale(2, BigDecimal.ROUND_HALF_UP), user.getWallet());
    }

    @Test
    public void removeFromWallet_walletUnderZero() {
        User user = new User();
        user.setWallet(USER_WALLET);
        user.removeFromWallet(BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(0), user.getWallet());
    }

    @Test
    public void testContructorWithParameters() {
        User user = new User(USER_ID, USER_NAME, USER_EMAIL, USER_PASSWORD);

        assertEquals(USER_ID, user.getId());
        assertEquals(USER_NAME, user.getName());
        assertEquals(USER_EMAIL, user.getEmail());
        assertEquals(USER_PASSWORD, user.getPassword());
    }

    @Test
    public void testSetId() {
        User user = new User();
        user.setId(USER_ID);
        assertEquals(USER_ID, user.getId());
    }

    @Test
    public void testSetName() {
        User user = new User();
        user.setName(USER_NAME);
        assertEquals(USER_NAME, user.getName());
    }

    @Test
    public void testSetEmail() {
        User user = new User();
        user.setEmail(USER_EMAIL);
        assertEquals(USER_EMAIL, user.getEmail());
    }

    @Test
    public void testSetPassword() {
        User user = new User();
        user.setPassword(USER_PASSWORD);
        assertEquals(USER_PASSWORD, user.getPassword());
    }

    @Test
    public void testSetToken() {
        User user = new User();
        user.setToken(USER_TOKEN);
        assertEquals(USER_TOKEN, user.getToken());
    }

    @Test
    public void testSetWallet() {
        User user = new User();
        user.setWallet(USER_WALLET);
        assertEquals(USER_WALLET, user.getWallet());
    }

    @Test
    public void testGetId() {
        User user = new User();
        user.setId(USER_ID);
        assertEquals(USER_ID, user.getId());
    }

    @Test
    public void testGetName() {
        User user = new User();
        user.setName(USER_NAME);
        assertEquals(USER_NAME, user.getName());
    }

    @Test
    public void testGetEmail() {
        User user = new User();
        user.setEmail(USER_EMAIL);
        assertEquals(USER_EMAIL, user.getEmail());
    }

    @Test
    public void testGetPassword() {
        User user = new User();
        user.setPassword(USER_PASSWORD);
        assertEquals(USER_PASSWORD, user.getPassword());
    }

    @Test
    public void testGetToken() {
        User user = new User();
        user.setToken(USER_TOKEN);
        assertEquals(USER_TOKEN, user.getToken());
    }

    @Test
    public void testGetWallet() {
        User user = new User();
        user.setWallet(USER_WALLET);
        assertEquals(USER_WALLET, user.getWallet());
    }
}
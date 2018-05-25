package com.sample.users.model;

import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class UserTest {

    private static User user;

    @BeforeClass
    public static void setUp() {
        user = new User();
        user.setId(42);
        user.setName("Arthur Dent");
        user.setEmail("arthur@dent.com");
        user.setWallet(BigDecimal.valueOf(79));
    }

    @Test
    public void addToWallet() {
        user.setWallet(BigDecimal.valueOf(79));
        user.addToWallet(BigDecimal.valueOf(40.567));
        assertEquals(BigDecimal.valueOf(119.57), user.getWallet());
    }

    @Test
    public void removeFromWallet() {
        user.setWallet(BigDecimal.valueOf(79));
        user.removeFromWallet(BigDecimal.valueOf(40));
        assertEquals(BigDecimal.valueOf(39.00).setScale(2, BigDecimal.ROUND_HALF_UP), user.getWallet());
    }

    @Test
    public void removeFromWallet_walletUnderZero() {
        user.setWallet(BigDecimal.valueOf(79));
        user.removeFromWallet(BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(0), user.getWallet());
    }
}
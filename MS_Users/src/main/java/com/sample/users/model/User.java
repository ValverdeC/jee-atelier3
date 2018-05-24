package com.sample.users.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String token;
    @Column(columnDefinition = "decimal(10, 2) default '0.00'")
    private BigDecimal wallet = BigDecimal.ZERO;

    public User(Integer id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getToken() {
        return token;
    }

    @JsonProperty
    public void setToken(String token) {
        this.token = token;
    }

    @JsonIgnore
    public BigDecimal getWallet() {
        return wallet;
    }

    @JsonProperty
    public void setWallet(BigDecimal wallet) {
        this.wallet = wallet;
    }

    public BigDecimal addToWallet(BigDecimal amount) {
        this.wallet = this.wallet.add(amount);
        return this.wallet;
    }

    public BigDecimal removeFromWallet(BigDecimal amount) {
        this.wallet = this.wallet.subtract(amount);
        if (this.wallet.compareTo(BigDecimal.ZERO) < 0) {
            this.wallet = BigDecimal.ZERO;
        }
        return this.wallet;
    }
}

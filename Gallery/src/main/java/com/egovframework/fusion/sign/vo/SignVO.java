package com.egovframework.fusion.sign.vo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.Date;

public class SignVO implements Serializable {
    private static final long serialVersionUID = 362498820763181265L;
    Integer userId;
    @NotNull
    @Pattern(regexp = "^[a-z]+[a-z0-9]{5,19}$")
    String userUsername;
    @NotNull
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,20}$")
    String userPassword;
    Date userRegistDate;

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUserUsername() {
        return userUsername;
    }
    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public Date getUserRegistDate() {
        return userRegistDate;
    }
    public void setUserRegistDate(Date userRegistDate) {
        this.userRegistDate = userRegistDate;
    }
}



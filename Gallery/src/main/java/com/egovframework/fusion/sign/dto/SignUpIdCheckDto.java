package com.egovframework.fusion.sign.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

public class SignUpIdCheckDto implements Serializable {
    private static final long serialVersionUID = 362498820763181265L;

    @NotNull
    @Pattern(regexp = "^[a-z]+[a-z0-9]{5,19}$")
    private String userUsername;

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }
}

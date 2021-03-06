package com.iri.movietickets.model.dto;

import com.iri.movietickets.model.validator.CustomEmailConstraint;
import com.iri.movietickets.model.validator.PasswordsEqualConstraint;

@PasswordsEqualConstraint
public class UserRequestDto {
    @CustomEmailConstraint
    private String email;
    private String password;
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}

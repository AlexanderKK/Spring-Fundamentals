package org.softuni.mobilele.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserLoginDTO extends UserDTO {

    @NotBlank
    private String password;

    public UserLoginDTO() {}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

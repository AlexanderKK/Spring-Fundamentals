package org.softuni.mobilele.model.dto;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.softuni.mobilele.model.validation.UniqueUserEmail;

@MappedSuperclass
public abstract class UserDTO {

    @Email(message = "Email should be valid!")
    @NotBlank(message = "Email should not be empty!")
    @UniqueUserEmail
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

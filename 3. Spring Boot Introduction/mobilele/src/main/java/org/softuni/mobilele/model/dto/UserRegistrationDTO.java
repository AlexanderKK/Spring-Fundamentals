package org.softuni.mobilele.model.dto;

import jakarta.validation.constraints.NotBlank;
import org.softuni.mobilele.model.validation.FieldMatch;

@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords must match!"
)
public class UserRegistrationDTO extends UserDTO {

    @NotBlank(message = "First name should not be empty!")
    private String firstName;

    @NotBlank(message = "Last name should not be empty!")
    private String lastName;

    @NotBlank(message = "Password should not be empty!")
    private String password;

    @NotBlank(message = "Confirmed password should not be empty!")
    private String confirmPassword;

    public UserRegistrationDTO() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}

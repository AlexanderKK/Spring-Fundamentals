package org.softuni.mobilele.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.softuni.mobilele.model.validation.UniqueUserEmail;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Email(message = "Email should be valid!")
    @NotBlank(message = "Email should not be empty!")
    @UniqueUserEmail
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password should not be empty!")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "First name should not be empty!")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name should not be empty!")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "is_active")
    private Boolean isActive = Boolean.FALSE;

    @ManyToOne
    private UserRoleEntity role;

    @Column(name = "image_url")
    private String imageUrl;

    @NotNull
    @Column(nullable = false)
    private LocalDate created;

    @Column
    private LocalDate modified;

    public UserEntity() {}

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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public UserRoleEntity getRole() {
        return role;
    }

    public void setRole(UserRoleEntity role) {
        this.role = role;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }

    public UserEntity createUser() {
        this.setCreated(LocalDate.now());

        return this;
    }

}

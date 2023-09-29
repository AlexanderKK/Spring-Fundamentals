package org.softuni.mobilele.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.softuni.mobilele.model.entity.enums.UserRoleEnum;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.ORDINAL)
    private UserRoleEnum role;

    public UserRoleEntity() {}

    public UserRoleEntity(UserRoleEnum role) {
        this.role = role;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

}

package org.softuni.mobilele.model.dto;

import jakarta.validation.constraints.NotBlank;

public class BrandJsonDTO {

    @NotBlank
    private String name;

    public BrandJsonDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

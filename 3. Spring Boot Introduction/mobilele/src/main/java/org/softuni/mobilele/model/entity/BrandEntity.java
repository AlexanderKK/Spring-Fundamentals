package org.softuni.mobilele.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "brands")
@NamedEntityGraph(
        name = "brandWithModels",
        attributeNodes = @NamedAttributeNode(value = "models")
)
public class BrandEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private LocalDate created;

    @Column
    private LocalDate modified;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    private Set<ModelEntity> models;

    public BrandEntity() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<ModelEntity> getModels() {
        return models;
    }

    public void setModels(Set<ModelEntity> models) {
        this.models = models;
    }

    public BrandEntity create() {
        this.setCreated(LocalDate.now());

        return this;
    }

}

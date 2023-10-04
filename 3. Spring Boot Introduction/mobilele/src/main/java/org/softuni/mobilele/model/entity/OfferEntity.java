package org.softuni.mobilele.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.JdbcTypeCode;
import org.softuni.mobilele.model.entity.enums.EngineEnum;
import org.softuni.mobilele.model.entity.enums.TransmissionEnum;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity {

    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private EngineEnum engine;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(nullable = false)
    private Integer mileage;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TransmissionEnum transmission;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private LocalDate created;

    @Column
    private LocalDate modified;

    @ManyToOne
    private ModelEntity model;

    @ManyToOne
    private UserEntity seller;

    public OfferEntity() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public void setEngine(EngineEnum engine) {
        this.engine = engine;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    public ModelEntity getModel() {
        return model;
    }

    public void setModel(ModelEntity model) {
        this.model = model;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public void setSeller(UserEntity seller) {
        this.seller = seller;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

}

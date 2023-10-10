package org.softuni.mobilele.model.dto;

import org.softuni.mobilele.model.entity.enums.EngineEnum;
import org.softuni.mobilele.model.entity.enums.TransmissionEnum;

import java.math.BigDecimal;

public record CreateOfferDTO (
    Long modelId,
    BigDecimal price,
    EngineEnum engine,
    TransmissionEnum transmission,
    Integer year,
    Integer mileage,
    String description,
    String imageUrl
) {}

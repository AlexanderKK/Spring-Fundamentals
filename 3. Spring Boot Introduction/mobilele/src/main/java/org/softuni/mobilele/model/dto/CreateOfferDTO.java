package org.softuni.mobilele.model.dto;

import jakarta.validation.constraints.*;
import org.softuni.mobilele.model.entity.enums.EngineEnum;
import org.softuni.mobilele.model.entity.enums.TransmissionEnum;
import org.softuni.mobilele.model.validation.YearNotInTheFuture;

import java.math.BigDecimal;

public record CreateOfferDTO(
    @NotNull @Positive Long modelId,
    @NotNull @Positive BigDecimal price,
    @NotNull EngineEnum engine,
    @NotNull TransmissionEnum transmission,
    @YearNotInTheFuture
    @NotNull(message = "Year must be provided!") @Min(value = 1930, message = "Year must be above 1930!") Integer year,
    @NotNull @Positive Integer mileage,
    @NotEmpty @Size(min = 5, max = 512) String description,
    @NotEmpty String imageUrl) {

    public static CreateOfferDTO empty() {
        return new CreateOfferDTO(null,null,null,null,null,null,null,null);
    }

}

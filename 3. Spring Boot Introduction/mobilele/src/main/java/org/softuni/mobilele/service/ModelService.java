package org.softuni.mobilele.service;

import org.softuni.mobilele.model.dto.BrandModelDTO;

import java.util.List;

public interface ModelService {

    List<BrandModelDTO> getModelsPerBrand();

}

package org.softuni.mobilele.service.impl;

import org.modelmapper.ModelMapper;
import org.softuni.mobilele.model.dto.ModelDTO;
import org.softuni.mobilele.model.entity.ModelEntity;
import org.softuni.mobilele.repository.ModelRepository;
import org.softuni.mobilele.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final ModelMapper mapper;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, ModelMapper mapper) {
        this.modelRepository = modelRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ModelDTO> getModelsPerBrand() {
        List<ModelEntity> models = this.modelRepository.findAll();

        return models.stream()
                .map(model -> {
                    ModelDTO modelDTO = mapper.map(model, ModelDTO.class);

                    modelDTO.setBrand(model.getBrand().getName());

                    return modelDTO;
                })
                .toList();
    }

}

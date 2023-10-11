package org.softuni.mobilele.service.impl;

import org.softuni.mobilele.model.dto.BrandDTO;
import org.softuni.mobilele.model.dto.ModelDTO;
import org.softuni.mobilele.model.entity.ModelEntity;
import org.softuni.mobilele.repository.BrandRepository;
import org.softuni.mobilele.repository.ModelRepository;
import org.softuni.mobilele.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<BrandDTO> getAllBrands() {
        return this.brandRepository.getAllBrands().stream()
                .map(brand -> new BrandDTO(
                        brand.getName(),
                        brand.getModels().stream()
                                .map(model -> new ModelDTO(model.getId(), model.getName()))
                                .sorted(Comparator.comparing(ModelDTO::name))
                                .collect(Collectors.toList())
                ))
                .sorted(Comparator.comparing(BrandDTO::name))
                .collect(Collectors.toList());

//        Map<String, BrandDTO> brands = new TreeMap<>();
//
//        for (ModelEntity model : modelRepository.findAll()) {
//            String brandName = model.getBrand().getName();
//
//            brands.putIfAbsent(
//                    brandName,
//                    new BrandDTO(brandName, new ArrayList<>())
//            );
//
//            brands.get(brandName)
//                    .models()
//                    .add(new ModelDTO(model.getId(), model.getName()));
//
//        }

//        return brands.values().stream().toList();
    }

}

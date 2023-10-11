package org.softuni.mobilele.service.impl;

import org.modelmapper.ModelMapper;
import org.softuni.mobilele.model.dto.CreateOfferDTO;
import org.softuni.mobilele.model.entity.ModelEntity;
import org.softuni.mobilele.model.entity.OfferEntity;
import org.softuni.mobilele.repository.ModelRepository;
import org.softuni.mobilele.repository.OfferRepository;
import org.softuni.mobilele.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper mapper;
    private final ModelRepository modelRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository,
                            ModelMapper mapper,
                            ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.mapper = mapper;
        this.modelRepository = modelRepository;
    }

    @Override
    public UUID createOffer(CreateOfferDTO createOfferDTO) {
        OfferEntity offer = mapper.map(createOfferDTO, OfferEntity.class);
        offer.setUuid(UUID.randomUUID());

        Long modelId = createOfferDTO.modelId();

        ModelEntity model = this.modelRepository
                .findById(modelId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Model with id %d not found!", modelId)));

        offer.setModel(model);
        offer.setCreated(LocalDate.now());

        offerRepository.save(offer);

        return offer.getUuid();
    }

}

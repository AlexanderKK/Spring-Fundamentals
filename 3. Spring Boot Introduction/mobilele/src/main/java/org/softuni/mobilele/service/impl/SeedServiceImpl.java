package org.softuni.mobilele.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.softuni.mobilele.model.dto.BrandJsonDTO;
import org.softuni.mobilele.model.dto.BrandModelDTO;
import org.softuni.mobilele.model.dto.UserJsonDTO;
import org.softuni.mobilele.model.entity.BrandEntity;
import org.softuni.mobilele.model.entity.ModelEntity;
import org.softuni.mobilele.model.entity.UserEntity;
import org.softuni.mobilele.model.entity.UserRoleEntity;
import org.softuni.mobilele.model.entity.enums.CategoryEnum;
import org.softuni.mobilele.model.entity.enums.UserRoleEnum;
import org.softuni.mobilele.repository.BrandRepository;
import org.softuni.mobilele.repository.ModelRepository;
import org.softuni.mobilele.repository.UserRepository;
import org.softuni.mobilele.repository.UserRoleRepository;
import org.softuni.mobilele.service.SeedService;
import org.softuni.mobilele.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.softuni.mobilele.constants.Messages.*;

@Service
public class SeedServiceImpl implements SeedService {

    private final Gson gson;
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ValidationUtil validator;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    @Autowired
    public SeedServiceImpl(Gson gson,
                           ModelMapper mapper,
                           UserRepository userRepository,
                           UserRoleRepository userRoleRepository,
                           ValidationUtil validator,
                           BrandRepository brandRepository,
                           ModelRepository modelRepository) {
        this.gson = gson;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.validator = validator;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public void seedRoles() {
        if(userRoleRepository.count() == 0) {
            userRoleRepository.saveAllAndFlush(
                    List.of(
                            new UserRoleEntity(UserRoleEnum.USER),
                            new UserRoleEntity(UserRoleEnum.ADMIN)
                    ));
        }
    }

    @Override
    public String seedUsers() {
        if(userRepository.count() > 0) {
            return String.format(ENTITIES_SEEDED, "Users");
        }

        BufferedReader bufferedReader = readFileFromResources(USERS_JSON_FILENAME);

        seedRoles();

        UserJsonDTO[] userJsonDTOs = gson.fromJson(bufferedReader, UserJsonDTO[].class);

        StringBuilder sb = new StringBuilder();

        List<UserEntity> users = Arrays.stream(userJsonDTOs)
                .filter(userDTO -> {
                    boolean isUserValid = validator.isValid(userDTO);

                    appendValidationMsg(sb, String.format("User %s %s",
                            userDTO.getFirstName(), userDTO.getLastName()), isUserValid);

                    return isUserValid;
                })
                .map(userDTO -> {
                    UserEntity user = mapper.map(userDTO, UserEntity.class);

                    UserRoleEntity userRole;
                    if(userDTO.getRole() == null) {
                        userRole = this.userRoleRepository.findById(1L).get();
                    } else {
                        userRole = this.userRoleRepository.findById(userDTO.getRole())
                                .orElseThrow(NoSuchElementException::new);
                    }
                    user.setRole(userRole);

                    return user.createUser();
                })
                .toList();

        this.userRepository.saveAllAndFlush(users);

        if(!sb.toString().contains("Invalid")) {
            sb.append(String.format(ENTITIES_SEEDED_SUCCESS, "Users")).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public String seedBrands() {
        if (this.brandRepository.count() > 0) {
            return String.format(ENTITIES_SEEDED, "Brands");
        }

        BufferedReader readBrands = readFileFromResources(BRANDS_JSON_FILENAME);

        BrandJsonDTO[] brandJsonDTOs = this.gson.fromJson(readBrands, BrandJsonDTO[].class);

        StringBuilder sb = new StringBuilder();
        List<BrandEntity> brands = Arrays.stream(brandJsonDTOs)
                .filter(brandDTOs -> {
                    boolean isBrandValid = validator.isValid(brandDTOs);

                    appendValidationMsg(sb, String.format("Brand %s", brandDTOs.getName()), isBrandValid);

                    return isBrandValid;
                })
                .map(brandDTO -> {
                    BrandEntity brand = mapper.map(brandDTO, BrandEntity.class);

                    return brand.create();
                })
                .toList();

        this.brandRepository.saveAllAndFlush(brands);

        if(!sb.toString().contains("Invalid")) {
            sb.append(String.format(ENTITIES_SEEDED_SUCCESS, "Brands")).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public String seedModels() {
        if (this.modelRepository.count() > 0) {
            return String.format(ENTITIES_SEEDED, "Models");
        }

        BufferedReader readModels = readFileFromResources(MODELS_JSON_FILENAME);

        BrandModelDTO[] brandModelDTOS = this.gson.fromJson(readModels, BrandModelDTO[].class);

        StringBuilder sb = new StringBuilder();

        for (BrandModelDTO brandModelDTO : brandModelDTOS) {
            CategoryEnum categoryEnum;
            try {
                categoryEnum = CategoryEnum.valueOf(brandModelDTO.getCategory().toUpperCase());
            } catch (RuntimeException ignored) {
                sb.append(String.format(INVALID_ENTITY, "category")).append(System.lineSeparator());
                continue;
            }

            Optional<BrandEntity> brand = this.getBrandByName(brandModelDTO.getBrand());
            if (brand.isEmpty()) {
                sb.append(String.format(INVALID_ENTITY, "brand")).append(System.lineSeparator());
                continue;
            }

            boolean isModelValid = this.validator.isValid(brandModelDTO);
            if(isModelValid) {
                ModelEntity model = this.mapper.map(brandModelDTO, ModelEntity.class);

                model.setCategory(categoryEnum);

                model.setBrand(brand.get());

                this.modelRepository.saveAndFlush(model.create());
            }

            appendValidationMsg(sb, String.format("%s %s", brandModelDTO.getBrand(), brandModelDTO.getName()), isModelValid);
        }

        if(!sb.toString().contains("Invalid")) {
            sb.append(String.format(ENTITIES_SEEDED_SUCCESS, "Models")).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    private Optional<BrandEntity> getBrandByName(String name) {
        return this.brandRepository.findByName(name);
    }

    private static void appendValidationMsg(StringBuilder sb, String entityName, boolean isEntityValid) {
        if(isEntityValid) {
            sb.append(String.format(ENTITY_ADDED_SUCCESS, entityName));
            sb.append(System.lineSeparator());
        } else {
            sb.append(String.format(INVALID_ENTITY, entityName));
            sb.append(System.lineSeparator());
        }
    }

    private static BufferedReader readFileFromResources(String fileName) {
        return new BufferedReader(
                new InputStreamReader(
                        ClassLoader.getSystemResourceAsStream(fileName)));
    }

}

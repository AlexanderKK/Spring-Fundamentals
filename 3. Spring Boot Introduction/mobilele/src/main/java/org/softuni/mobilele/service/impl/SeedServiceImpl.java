package org.softuni.mobilele.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.softuni.mobilele.model.dto.UserJsonDTO;
import org.softuni.mobilele.model.entity.UserEntity;
import org.softuni.mobilele.model.entity.UserRoleEntity;
import org.softuni.mobilele.model.entity.enums.UserRoleEnum;
import org.softuni.mobilele.repository.BrandRepository;
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

@Service
public class SeedServiceImpl implements SeedService {

    private final Gson gson;
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ValidationUtil validationUtil;
    private final BrandRepository brandRepository;

    @Autowired
    public SeedServiceImpl(Gson gson,
                           ModelMapper mapper,
                           UserRepository userRepository,
                           UserRoleRepository userRoleRepository,
                           ValidationUtil validationUtil,
                           BrandRepository brandRepository) {
        this.gson = gson;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.validationUtil = validationUtil;
        this.brandRepository = brandRepository;
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
            return "Users already seeded";
        }

        BufferedReader bufferedReader =
                new BufferedReader(
                        new InputStreamReader(
                                ClassLoader.getSystemResourceAsStream("files/users.json")));

        seedRoles();

        UserJsonDTO[] userJsonDTOS = gson.fromJson(bufferedReader, UserJsonDTO[].class);

        StringBuilder sb = new StringBuilder();

        List<UserEntity> users = Arrays.stream(userJsonDTOS)
                .filter(userJsonDTO -> {
                    boolean isValid = validationUtil.isValid(userJsonDTO);
                    if(isValid) {
                        sb.append(String.format("User %s %s added successfully",
                                userJsonDTO.getFirstName(),
                                userJsonDTO.getLastName()));
                        sb.append(System.lineSeparator());
                    } else {
                        sb.append(String.format("Invalid User %s %s",
                                userJsonDTO.getFirstName(),
                                userJsonDTO.getLastName()));
                        sb.append(System.lineSeparator());
                    }

                    return isValid;
                })
                .map(userJsonDTO -> {
                    UserEntity user = mapper.map(userJsonDTO, UserEntity.class);

                    UserRoleEntity userRole;
                    if(userJsonDTO.getRole() == null) {
                        userRole = this.userRoleRepository.findById(1L).get();
                    } else {
                        userRole = this.userRoleRepository.findById(userJsonDTO.getRole())
                                .orElseThrow(NoSuchElementException::new);
                    }
                    user.setRole(userRole);

                    return user.createUser();
                })
                .toList();

        this.userRepository.saveAllAndFlush(users);

        if(!sb.toString().contains("Invalid")) {
            sb.append("Users seeded successfully").append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public String seedBrands() {
        if (this.brandRepository.count() > 0) {
            return "Brands already seeded";
        }

        return "Seeding brands...";
    }

}

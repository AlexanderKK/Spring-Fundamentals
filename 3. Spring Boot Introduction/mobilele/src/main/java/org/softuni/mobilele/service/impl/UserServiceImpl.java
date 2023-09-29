package org.softuni.mobilele.service.impl;

import org.modelmapper.ModelMapper;
import org.softuni.mobilele.model.dto.UserDTO;
import org.softuni.mobilele.model.dto.UserLoginDTO;
import org.softuni.mobilele.model.dto.UserRegistrationDTO;
import org.softuni.mobilele.model.entity.UserEntity;
import org.softuni.mobilele.repository.UserRepository;
import org.softuni.mobilele.service.UserService;
import org.softuni.mobilele.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final ValidationUtil validator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper, ValidationUtil validator) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public String register(UserRegistrationDTO userRegistrationDTO) {
        boolean isUserPresent = isPresent(userRegistrationDTO);
        if(isUserPresent) {
            return "User already registered";
        }

        if(!validator.isValid(userRegistrationDTO)) {
            return "Invalid user";
        }

        UserEntity user = mapper.map(userRegistrationDTO, UserEntity.class);

        this.userRepository.save(user.createUser());

        return "User registered successfully";
    }

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        boolean isUserValid = validator.isValid(userLoginDTO);
        boolean isUserLogged = isLogged(userLoginDTO);

        if(isUserValid && isUserLogged) {
            return "Successful login";
        }
        return "User error";
    }

    private Boolean isPresent(UserDTO userDTO) {
        return this.userRepository.findByEmail(userDTO.getEmail()).isPresent();
    }

    private Boolean isLogged(UserLoginDTO userLoginDTO) {
        return this.userRepository
                .findByEmailAndPassword(userLoginDTO.getEmail(), userLoginDTO.getPassword())
                .isPresent();
    }

}

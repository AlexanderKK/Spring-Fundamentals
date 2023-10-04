package org.softuni.mobilele.service.impl;

import org.modelmapper.ModelMapper;
import org.softuni.mobilele.model.dto.UserLoginDTO;
import org.softuni.mobilele.model.dto.UserRegistrationDTO;
import org.softuni.mobilele.model.entity.UserEntity;
import org.softuni.mobilele.repository.UserRepository;
import org.softuni.mobilele.service.UserService;
import org.softuni.mobilele.util.CurrentUser;
import org.softuni.mobilele.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final ValidationUtil validator;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper mapper,
                           ValidationUtil validator,
                           PasswordEncoder passwordEncoder,
                           CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public String register(UserRegistrationDTO userRegistrationDTO) {
        boolean isUserPresent = this.userRepository.findByEmail(userRegistrationDTO.getEmail()).isPresent();
        if(isUserPresent ||
                !userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword()) ||
                !validator.isValid(userRegistrationDTO)) {
            return "Invalid User";
        }

        UserEntity user = mapper.map(userRegistrationDTO, UserEntity.class);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        this.userRepository.save(user.createUser());

        return "User registered successfully";
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        UserEntity userEntity = this.userRepository
                .findByEmail(userLoginDTO.getEmail())
                .orElse(null);

        boolean loginSuccess = false;

        if(userEntity != null) {
            String rawPassword = userLoginDTO.getPassword();
            String encodedPassword = userEntity.getPassword();

            loginSuccess = encodedPassword != null && passwordEncoder.matches(rawPassword, encodedPassword);

            if(loginSuccess) {
                currentUser
                        .setLogged(true)
                        .setFirstName(userEntity.getFirstName())
                        .setLastName(userEntity.getLastName());
            } else {
                currentUser.logout();
            }
        }

        return loginSuccess;
    }

    @Override
    public void logout() {
        this.currentUser.logout();
    }

}

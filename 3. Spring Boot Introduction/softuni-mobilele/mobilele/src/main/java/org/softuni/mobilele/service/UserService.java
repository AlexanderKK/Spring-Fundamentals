package org.softuni.mobilele.service;

import org.softuni.mobilele.model.dto.UserLoginDTO;
import org.softuni.mobilele.model.dto.UserRegistrationDTO;

public interface UserService {

    String register(UserRegistrationDTO userRegistrationDTO);

    String login(UserLoginDTO userLoginDTO);

}
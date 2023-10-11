package org.softuni.mobilele.service;

import org.softuni.mobilele.model.dto.UserLoginDTO;
import org.softuni.mobilele.model.dto.UserRegistrationDTO;

public interface UserService {

    void register(UserRegistrationDTO userRegistrationDTO);

    boolean login(UserLoginDTO userLoginDTO);

    void logout();

}

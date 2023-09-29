package bg.softuni.service;

import bg.softuni.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findYoungestUser();

}

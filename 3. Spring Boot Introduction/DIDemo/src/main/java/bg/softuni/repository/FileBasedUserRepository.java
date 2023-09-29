package bg.softuni.repository;

import bg.softuni.model.User;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

@Repository(value = "fileRepo")
public class FileBasedUserRepository implements UserRepository {

    @Override
    public List<User> findAll() {
        return new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("users.csv"))))
                .lines()
                .map(this::parseUser)
                .toList();
    }

    private User parseUser(String line) {
        String[] tokens = line.split(",");
        return new User(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
    }

}

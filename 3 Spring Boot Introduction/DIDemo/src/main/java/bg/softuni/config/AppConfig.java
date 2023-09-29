package bg.softuni.config;

import bg.softuni.repository.FileBasedUserRepository;
import bg.softuni.repository.InMemoryUserRepository;
import bg.softuni.repository.UserRepository;
import bg.softuni.service.UserService;
import bg.softuni.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
//    <bean
//    id="fileBasedUserRepository"
//    class="bg.softuni.repository.FileBasedUserRepository"/>
//
//    <bean
//    id="userService"
//    autowire="constructor"
//    class="bg.softuni.service.UserServiceImpl"/>

//    @Bean
//    public UserRepository userRepository() {
//        return new FileBasedUserRepository();
//    }

//    @Bean
//    public UserService userService(UserRepository userRepository) {
//        return new UserServiceImpl(userRepository);
//    }

}

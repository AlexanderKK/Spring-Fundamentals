package bg.softuni;

import bg.softuni.config.AppConfig;
import bg.softuni.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        // 1. Spring XML Configuration (deprecated)
//        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // 2. Spring Annotation Configuration
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // 3. Spring Annotation Configuration With Automatic Beans (Stereotype Annotations: @Component, @Service, @Repository)
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("bg.softuni");

        UserService userService = context.getBean(UserService.class);

        System.out.println(userService.findYoungestUser().orElseThrow().description());

        UserService userService1 = context.getBean(UserService.class);
        UserService userService2 = context.getBean(UserService.class);

        System.out.println("Singleton scope " + (userService1 == userService2));
    }

}

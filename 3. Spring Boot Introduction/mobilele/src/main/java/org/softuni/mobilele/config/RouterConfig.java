package org.softuni.mobilele.config;

import org.softuni.mobilele.web.Handlers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routingFunction(Handlers handlers) {
        return route()
            .GET("/test", handlers::test)
            .build();
    }

}

package org.softuni.mobilele.web;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Map;

@Component
public class Handlers {

    public ServerResponse test(ServerRequest serverRequest) {
        return ServerResponse.ok().render(
                "test",
                Map.of("message", "Hello, Alex"));
    }

}

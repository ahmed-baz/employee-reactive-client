package org.demo.app.config;


import org.demo.app.handler.EmployeeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RoutesConfig {

    @Bean
    public RouterFunction<ServerResponse> router(EmployeeHandler handler) {
        return route()
                .GET("api/v1/employee/random/{size}", handler::getRandom)
                .GET("api/v1/employee/{id}", handler::getOne)
                .GET("api/v1/employee", handler::getAll)
                .build();
    }

}

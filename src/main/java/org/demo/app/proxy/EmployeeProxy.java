package org.demo.app.proxy;


import lombok.RequiredArgsConstructor;
import org.demo.app.exception.EmployeeReactiveException;
import org.demo.app.model.Employee;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmployeeProxy {

    private final WebClient webClient;
    private static final String FIND_ONE_PATH = "employee/";
    private static final String FIND_RANDOM_LIST_PATH = "employee/create/";
    private static final String FIND_ALL_PATH = "employee";

    public Flux<Employee> getAllEmployees() {
        return webClient
                .get()
                .uri(FIND_ALL_PATH)
                .exchangeToFlux(response -> response.bodyToFlux(Employee.class))
                //.onErrorResume(e -> Flux.just(getStaticEmployee()));
                .onErrorResume(WebClientRequestException.class, e -> Flux.just(getStaticEmployee()));
    }

    public Flux<Employee> getRandomList(int size) {
        return webClient
                .get()
                .uri(FIND_RANDOM_LIST_PATH + size)
                .exchangeToFlux(response -> response.bodyToFlux(Employee.class))
                //.onErrorResume(e -> Flux.just(getStaticEmployee()));
                .onErrorResume(e -> e instanceof WebClientRequestException, e -> Flux.just(getStaticEmployee()));
    }

    public Mono<Employee> getOne(int id) {
        return webClient
                .get()
                .uri(FIND_ONE_PATH + id)
                .exchangeToMono(response -> response.bodyToMono(Employee.class))
                //.onErrorResume(WebClientRequestException.class::isInstance, e -> Mono.just(getStaticEmployee()))
                .onErrorReturn(getStaticEmployee());

    }

    private Employee getStaticEmployee() {
        var employee = Employee
                .builder()
                .id(UUID.randomUUID().toString())
                .firstName("NONE")
                .lastName("NONE")
                .email("none@demo.org")
                .joinDate(new Date())
                .salary(new BigDecimal(0)).build();
        return employee;
    }
}

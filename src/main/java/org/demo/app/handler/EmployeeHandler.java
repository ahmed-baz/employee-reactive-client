package org.demo.app.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.demo.app.model.Employee;
import org.demo.app.proxy.EmployeeProxy;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
@Log4j2
public class EmployeeHandler {

    private final EmployeeProxy employeeProxy;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(employeeProxy.getAllEmployees(), Employee.class);
    }

    public Mono<ServerResponse> getOne(ServerRequest request) {
        String pathVariable = request.pathVariable("id");
        int id = Integer.parseInt(pathVariable);
        return ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(employeeProxy.getOne(id), Employee.class);
    }

    public Mono<ServerResponse> getRandom(ServerRequest request) {
        String pathVariable = request.pathVariable("size");
        int size = Integer.parseInt(pathVariable);
        return ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(employeeProxy.getRandomList(size), Employee.class);
    }


}

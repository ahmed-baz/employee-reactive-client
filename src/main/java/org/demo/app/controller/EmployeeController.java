package org.demo.app.controller;


import org.demo.app.model.Employee;
import org.demo.app.proxy.EmployeeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeProxy employeeProxy;

    @GetMapping(value = "random/{size}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> getAllEmployees(@PathVariable int size) {
        return employeeProxy.getRandomList(size);
    }

    @GetMapping(value = "{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Employee> getOne(@PathVariable int id) {
        return employeeProxy.getOne(id);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> getAllEmployees() {
        return employeeProxy.getAllEmployees();
    }

}

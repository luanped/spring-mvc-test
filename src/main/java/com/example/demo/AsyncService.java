package com.example.demo;

import java.util.concurrent.CompletableFuture;

import com.example.demo.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AsyncService {

    private static Logger log = LoggerFactory.getLogger(AsyncService.class);

//    @Autowired
//    private RestTemplate restTemplate;

//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

    @Async("asyncExecutor")
    public CompletableFuture<Employee> getEmployeeName() throws InterruptedException
    {
        log.info("getEmployeeName starts");
        RestTemplate restTemplate = new RestTemplate();
        Employee p = restTemplate.getForObject("http://localhost:8080/greeting?name=bob", Employee.class);

        // log.info("employeeNameData, {}", p);
        Thread.sleep(1000L);    //Intentional delay
        log.info("employeeNameData completed");
        // Employee p = new Employee("test", 10);
        return CompletableFuture.completedFuture(p);
    }

}
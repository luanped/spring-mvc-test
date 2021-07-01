package com.example.demo.services;

import java.util.concurrent.CompletableFuture;

import com.example.demo.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AsyncService {

    private static Logger log = LoggerFactory.getLogger(AsyncService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private int someNumber;

    @Async("asyncExecutor")
    public CompletableFuture<User> getUser() throws InterruptedException
    {
        log.info("getUser starts");
        User u = restTemplate.getForObject("https://reqres.in/api/users/2", User.class);

        Thread.sleep(5000L);    //Intentional delay
        log.info("getUser completed");
        return CompletableFuture.completedFuture(u);
    }

    @Async("asyncExecutor")
    public CompletableFuture<Integer> getUserAge() throws InterruptedException
    {
        log.info("getUserAge starts");
        Thread.sleep(3000L);    //Intentional delay
        log.info("getUserAge completed");
        return CompletableFuture.completedFuture(someNumber);
    }
}
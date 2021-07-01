package com.example.demo.controllers;

import com.example.demo.services.AsyncService;
import com.example.demo.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class GreetingController {
    private static Logger log = LoggerFactory.getLogger(GreetingController.class);

    @Autowired
    private AsyncService service;

    @GetMapping("/testAsync")
    public User test() throws InterruptedException, ExecutionException {
        log.info("testAsynch Start");

        CompletableFuture<User> userPromise = service.getUser();
        CompletableFuture<Integer> agePromise = service.getUserAge();

        // Wait until they are all done
        // We only have 1 async service, but there could be more. Similar to JS promise.join
        CompletableFuture.allOf(userPromise, agePromise).join();

        User response = userPromise.get(); // retrieve from the user promise
        response.setAge(agePromise.get());

        // sample response: {"name":"Janet","lastName":"Weaver","email":"janet.weaver@reqres.in","age":99}
        return response;
    }

}
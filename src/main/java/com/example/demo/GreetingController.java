package com.example.demo;

import com.example.demo.model.Employee;
import com.example.demo.model.Person;
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

    @GetMapping("/greeting")
    public Person greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);

        Person p = new Person(name, 10);
        return p;
    }

    @GetMapping("/testAsync")
    public Employee test() throws InterruptedException, ExecutionException {
        log.info("testAsynch Start");

        CompletableFuture<Employee> employeeName = service.getEmployeeName();

        // Wait until they are all done
        // We only have 1 async service, but there could be more. Similar to JS promise.join
        CompletableFuture.allOf(employeeName).join();

        log.info("EmployeeName--> " + employeeName.get());

        return employeeName.get();
    }

}
package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class User {
    private String name;
    private String lastName;
    private String email;

    // the https://reqres.in/ mock api do not have "age" property, we will fill this in manually in the controller
    private int age;

    public User(@JsonProperty("data") Map<String, String> data) {
        this.name = data.get("first_name");
        this.lastName = data.get("last_name");
        this.email = data.get("email");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

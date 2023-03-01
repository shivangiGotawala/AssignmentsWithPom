package com.automation.framework.utils;


import com.github.javafaker.Faker;

public class DataGeneratorByFaker {

    public String getUsername() {
        return new Faker().name().username();
    }

}

package ru.serov.springboot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    @Value("${app.services.greeting.message}")
    private String greeting;

    public String greeting() {
        return greeting;
    }
}

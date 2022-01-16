package ru.serov.springboot.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.serov.springboot.IntegrationTestBase;

import static org.junit.jupiter.api.Assertions.assertEquals;


class GreetingServiceTest extends IntegrationTestBase {
    @Autowired
    private GreetingService greetingService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGreeting() {
        String expectMessage = "HelloTest!";
        assertEquals(expectMessage, greetingService.greeting());
    }
}
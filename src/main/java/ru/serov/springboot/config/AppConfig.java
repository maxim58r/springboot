package ru.serov.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import ru.serov.springboot.conditional.FirstConditional;

import javax.annotation.PostConstruct;

@Slf4j
@Conditional(FirstConditional.class)
@Configuration
public class AppConfig {

    @PostConstruct
    public void init() {
        log.warn("app is loaded!");
    }
}

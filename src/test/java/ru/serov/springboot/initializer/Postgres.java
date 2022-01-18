package ru.serov.springboot.initializer;

import lombok.experimental.UtilityClass;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

@UtilityClass
public class Postgres {

    public static final PostgreSQLContainer<?> CONTAINER =
            new PostgreSQLContainer<>("postgres:13");
//                    .withDatabaseName()
//                    .withPassword()
//                    .withUsername()
//                    .withUrlParam();

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.username=" + CONTAINER.getUsername(),
                    "spring.datasource.password=" + CONTAINER.getPassword(),
                    "spring.datasource.url=" + CONTAINER.getJdbcUrl()
            ).applyTo(applicationContext);

        }
    }
}

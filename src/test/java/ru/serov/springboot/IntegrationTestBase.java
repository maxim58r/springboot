package ru.serov.springboot;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.serov.springboot.initializer.Postgres;

@Sql("/sql/data.sql")
@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(initializers = {Postgres.Initializer.class})
@Transactional
public abstract class IntegrationTestBase {

    @BeforeAll
    static void init() {
        Postgres.CONTAINER.start();
    }
}

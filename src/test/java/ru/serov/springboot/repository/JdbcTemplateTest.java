package ru.serov.springboot.repository;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import ru.serov.springboot.IntegrationTestBase;
import ru.serov.springboot.entity.Company;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JdbcTemplateTest extends IntegrationTestBase {

    private static final String INSERT_SQL = "insert into company (name) values (?); ";
    private static final String DELETE_RETURNING_SQL = "DELETE FROM company WHERE name = ? RETURNING *";
    public static final String MICROSOFT = "Microsoft";

    @Autowired
    private JdbcOperations jdbcOperations;

    @Test
    void testInsert() {
        var result = jdbcOperations.update(INSERT_SQL, "Microsoft");
        assertEquals(1, result);
    }

    @Test
    void testReturning() {
        jdbcOperations.update(INSERT_SQL, "Microsoft");

        var result = jdbcOperations.query(DELETE_RETURNING_SQL, (rs, rowNum) -> Company.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .build(), MICROSOFT);
        assertThat(result, hasSize(1));
    }
}

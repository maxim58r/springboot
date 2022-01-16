package ru.serov.springboot.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.serov.springboot.IntegrationTestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeRepositoryTest extends IntegrationTestBase {
    private static final Long ID = 1l;
    private static final String FIRST_NAME = "mar";
    private static final String FIRST_NAME_FULL = "Maria";

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void findById() {
        var employee = employeeRepository.findById(ID);
        assertTrue(employee.isPresent());
    }

    @Test
    void findByFirstName() {
        var employee = employeeRepository.findByFirstNameContainingIgnoreCase(FIRST_NAME);
        assertTrue(employee.isPresent());
    }

    @Test
    void findByFirstNameContainingIgnoreCaseAndSalary() {
        var employees = employeeRepository.findByFirstNameContainingIgnoreCaseAndSalary(FIRST_NAME, 150_000);
        assertThat(employees, hasSize(1));
    }

    @Test
    void findByFirstNameAndSalary() {
        var employees = employeeRepository.findByFirstNameAndSalary(FIRST_NAME_FULL, 150_000);
        assertThat(employees, hasSize(1));
    }

    @Test
    void findBySalaryGreaterThan() {
        var employees = employeeRepository.findBySalaryGreaterThan(15_000);
        assertThat(employees, hasSize(2));
    }

    @Test
    void findBySalaryGreaterThanNative() {
        var employees = employeeRepository.findBySalaryGreaterThanNative(15_000);
        assertThat(employees, hasSize(2));
        assertEquals("Maxim Serov", employees.get(0).getFullName());
    }
}
package ru.serov.springboot.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import ru.serov.springboot.IntegrationTestBase;
import ru.serov.springboot.dto.EmployeeFilter;
import ru.serov.springboot.util.QPredicates;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.serov.springboot.entity.QEmployee.employee;

class EmployeeRepositoryTest extends IntegrationTestBase {
    private static final Long ID = 1L;
    private static final String FIRST_NAME = "mar";
    private static final String FIRST_NAME_FULL = "Maria";
    public static final String MAXIM_SEROV = "Maxim Serov";

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
        assertEquals(MAXIM_SEROV, employees.get(0).getFio());
    }

    @Test
    void findBySalaryGreaterThanNative() {
        var employees = employeeRepository.findBySalaryGreaterThanNative(15_000);
        assertThat(employees, hasSize(2));
        assertEquals(MAXIM_SEROV, employees.get(0).getFullName());
    }

    @Test
    void findCustomQuery() {
        var filter = EmployeeFilter.builder()
                .firstName("mAx")
                .build();
        var employees = employeeRepository.findByFilter(filter);
        assertThat(employees, hasSize(1));
    }

    @Test
    void testQuerydslPredicates() {
        var predicate = employee.firstName.containsIgnoreCase("arI")
                .and(employee.salary.goe(10_000));
        var employees = employeeRepository.findAll(predicate, Pageable.unpaged());
        assertThat(employees.getContent(), hasSize(1));
    }

    @Test
    void  testQPredicates() {
        var filter = EmployeeFilter.builder()
                .firstName("mAx")
                .salary(1000)
                .build();
        var predicate = QPredicates.builder()
                .add(filter.getFirstName(), employee.firstName::containsIgnoreCase)
                .add(filter.getLastName(), employee.lastName::containsIgnoreCase)
                .add(filter.getSalary(), employee.salary::goe)
                .buildAnd();
        var allEmployees = employeeRepository.findAll(predicate);
        assertTrue(allEmployees.iterator().hasNext());
    }
}
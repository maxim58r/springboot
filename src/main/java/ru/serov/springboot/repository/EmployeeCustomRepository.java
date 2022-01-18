package ru.serov.springboot.repository;

import ru.serov.springboot.dto.EmployeeFilter;
import ru.serov.springboot.entity.Employee;

import java.util.List;

public interface EmployeeCustomRepository {
    List<Employee> findByFilter(EmployeeFilter filter);
}

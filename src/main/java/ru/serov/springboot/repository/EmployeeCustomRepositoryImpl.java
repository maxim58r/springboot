package ru.serov.springboot.repository;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import ru.serov.springboot.dto.EmployeeFilter;
import ru.serov.springboot.entity.Employee;

import javax.persistence.EntityManager;
import java.util.List;

import static ru.serov.springboot.entity.QEmployee.employee;

@RequiredArgsConstructor
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository {
    private final EntityManager entityManager;

    @Override
    public List<Employee> findByFilter(EmployeeFilter filter) {
        return new JPAQuery<Employee>(entityManager)
                .select(employee)
                .from(employee)
                .where(employee.firstName.containsIgnoreCase(filter.getFirstName()))
                .fetch();
    }
}

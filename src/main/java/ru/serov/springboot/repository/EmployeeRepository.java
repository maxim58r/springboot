package ru.serov.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.serov.springboot.entity.Employee;
import ru.serov.springboot.projection.EmployeeNameView;
import ru.serov.springboot.projection.EmployeeNativeView;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByFirstNameContainingIgnoreCase(String firstName);

    List<Employee> findByFirstNameContainingIgnoreCaseAndSalary(String firstName, Integer salary);

    @Query("select e " +
            "from Employee e " +
            "where e.firstName = :name " +
            "and e.salary = :salary")
    List<Employee> findByFirstNameAndSalary(@Param("name") String firstName,
                                            @Param("salary") Integer salary);

    List<EmployeeNameView> findBySalaryGreaterThan(Integer salary);

    @Query(value = "select e.id as id, " +
            "e.first_name || ' ' || e.last_name as fullName  " +
            "from employee e " +
            "where e.salary > :salary", nativeQuery = true)
    List<EmployeeNativeView> findBySalaryGreaterThanNative(@Param("salary") Integer salary);
}

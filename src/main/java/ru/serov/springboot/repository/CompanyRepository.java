package ru.serov.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.serov.springboot.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}

package ru.serov.springboot.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeNameView {

    @Value("#{target.id + ' ' + target.firstName}")
    String getFirstName();

    String getLastName();

    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFio();
}

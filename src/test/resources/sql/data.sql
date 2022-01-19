insert into company (id, name)
values (1, 'Apple'),
       (2, 'Google');

insert into employee (id, first_name, last_name, birth_day, salary, company_id)
values (1, 'Maxim', 'Serov', '1982-12-25', 60000, 2),
       (2, 'Maria', 'Serova', '1981-11-17', 150000, 1);

select setval('company_id_seq', (select max(id) from company));
select setval('employee_id_seq', (select max(id) from employee));
select setval('flight_id_seq', (select max(id) from flight));
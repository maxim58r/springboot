create table if not exists company
(
    id   serial primary key,
    name varchar(128) not null unique
);

create table if not exists employee
(
    id         serial primary key,
    first_name varchar(128) not null unique,
    last_name  varchar(128) not null unique,
    birth_day  DATE,
    salary     integer      not null,
    company_id integer references company (id)
);

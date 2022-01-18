create table if not exists flight
(
    id   serial primary key,
    name varchar(128) not null unique,
    destination varchar(256) not null
);

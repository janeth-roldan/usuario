CREATE DATABASE usuario;
\c usuario;

create table usuario
(
    id         uuid not null
        primary key,
    created    timestamp,
    modified   timestamp,
    version    bigint,
    email      varchar(255),
    is_active  boolean,
    last_login timestamp,
    name       varchar(255),
    password   varchar(255),
    token      varchar(255)
);

alter table usuario
    owner to postgres;

	
	
create table phone
(
    id           uuid not null
        primary key,
    created      timestamp,
    modified     timestamp,
    version      bigint,
    city_code    varchar(255),
    country_code varchar(255),
    number       varchar(255),
    usuario_id   uuid not null
        constraint "FKqg6mgdw7jgi55jvcponsyqgf"
            references usuario
);

alter table phone
    owner to postgres;	
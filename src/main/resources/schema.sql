drop table if exists default_file_extensions cascade;

drop table if exists custom_file_extensions cascade;

create table if not exists  default_file_extensions
(
    id          bigint auto_increment primary key,
    name        varchar(30) not null,
    checked     bit         not null,
    created_at  datetime(6) not null,
    modified_at datetime(6) null
);

create table if not exists  custom_file_extensions
(
    id          bigint auto_increment primary key,
    name        varchar(30) not null,
    created_at  datetime(6) not null,
    modified_at datetime(6) null
);
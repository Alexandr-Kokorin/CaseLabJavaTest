 create table files
 (
     id              bigint generated always as identity,
     file            text                     not null,
     title           text                     not null,
     creation_date   timestamp with time zone not null,
     description     text                     not null,

     primary key (id)
 )

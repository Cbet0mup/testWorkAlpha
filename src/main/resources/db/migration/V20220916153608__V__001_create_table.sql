drop table if exists users;
CREATE TABLE  users (

                       id BIGINT PRIMARY KEY auto_increment not null ,

                       login VARCHAR(25) UNIQUE,

                       password VARCHAR(50),

                       name VARCHAR(25),

                       surname VARCHAR(25),

                       patronymic VARCHAR(25),

                       is_banned BOOLEAN DEFAULT false

);
drop table if exists users;
CREATE TABLE if not exists users (

                        id BIGINT PRIMARY KEY auto_increment not null ,

                        login VARCHAR(25) UNIQUE,

                        password VARCHAR(50),

                        name VARCHAR(25),

                        surname VARCHAR(25),

                        patronymic VARCHAR(25),

                        banned BOOLEAN DEFAULT false

);
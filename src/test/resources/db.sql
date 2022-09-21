CREATE TABLE  users (

                        id BIGINT PRIMARY KEY,

                        login VARCHAR(25) UNIQUE,

                        password VARCHAR(50),

                        name VARCHAR(25),

                        surname VARCHAR(25),

                        patronymic VARCHAR(25),

                        banned BOOLEAN DEFAULT false

);


DROP DATABASE IF EXISTS company;
CREATE DATABASE company;

USE company;

CREATE TABLE company.departments
(
    department_id   INT     PRIMARY KEY     AUTO_INCREMENT,
    department_name     VARCHAR(50)     NOT NULL
);

CREATE TABLE company.employees
(
    employee_id     INT     PRIMARY KEY     AUTO_INCREMENT,
    employee_first_name   VARCHAR(50)     NOT NULL,
    employee_last_name       VARCHAR(50)    NOT NULL,
    employee_hire_date		DATETIME	NOT NULL 	DEFAULT NOW(),
    department_id   INT     NOT NULL,

    CONSTRAINT employees_fk_departments
        FOREIGN KEY (department_id) REFERENCES departments(department_id)
);

INSERT INTO departments VALUES
(DEFAULT, 'IT'),
(DEFAULT, 'Accounting'),
(DEFAULT, 'Management'),
(DEFAULT, 'Janitor');

INSERT INTO employees VALUES
(DEFAULT, 'Bob', 'Dylan', FROM_UNIXTIME(UNIX_TIMESTAMP(now()) - FLOOR(0 + (RAND() * 2592000))), 1),
(DEFAULT, 'Tim', 'Dylan', FROM_UNIXTIME(UNIX_TIMESTAMP(now()) - FLOOR(0 + (RAND() * 2592000))),  1),
(DEFAULT, 'Janice', 'Dylan', FROM_UNIXTIME(UNIX_TIMESTAMP(now()) - FLOOR(0 + (RAND() * 2592000))),  4),
(DEFAULT, 'Immergeil', 'Dylan', FROM_UNIXTIME(UNIX_TIMESTAMP(now()) - FLOOR(0 + (RAND() * 2592000))),  3),
(DEFAULT, 'Dylan', 'TheOne', FROM_UNIXTIME(UNIX_TIMESTAMP(now()) - FLOOR(0 + (RAND() * 2592000))),  2),
(DEFAULT, 'Tom', 'Hanks', FROM_UNIXTIME(UNIX_TIMESTAMP(now()) - FLOOR(0 + (RAND() * 2592000))),  4),
(DEFAULT, 'TJ', 'ThaOne', FROM_UNIXTIME(UNIX_TIMESTAMP(now()) - FLOOR(0 + (RAND() * 2592000))),  2);

DROP USER IF EXISTS admin@localhost;

CREATE USER admin@localhost IDENTIFIED BY "admin";
GRANT SELECT, INSERT, UPDATE, DELETE 
	ON company.*
	TO admin@localhost;
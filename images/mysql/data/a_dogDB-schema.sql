DROP SCHEMA IF EXISTS dogdb;
CREATE SCHEMA dogdb;
USE dogdb;


CREATE TABLE dog (
	dog_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    dog_name VARCHAR(45) NOT NULL,
    dog_age INTEGER, 
    dog_weight DOUBLE,
    dog_quote VARCHAR(200),
    
    primary key(dog_id)
);
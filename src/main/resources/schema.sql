
--
--need to change ' ' to ` `
DROP TABLE IF EXISTS `employees`;

CREATE TABLE `employees` (
`emp_no` INT NOT NULL AUTO_INCREMENT,
 `birth_date` DATETIME NOT NULL,
 `first_name` VARCHAR(100) NOT NULL,
 `last_name` VARCHAR(100) NOT NULL,
 `email` VARCHAR(100) NOT NULL UNIQUE,
 `gender` VARCHAR(100) NOT NULL,
 `hire_date` DATETIME NOT NULL,
 `salary` BIGINT NOT NULL,
 PRIMARY KEY(`emp_no`)
);

DROP TABLE IF EXISTS `titles`;
CREATE TABLE `titles` (
    `title_id` INT NOT NULL AUTO_INCREMENT,
    `title_name` VARCHAR(100) NOT NULL,
    `emp_no_foreign_key` INT,
    PRIMARY KEY(title_id),
    FOREIGN KEY(emp_no_foreign_key) REFERENCES `employees` (`emp_no`)
);
DROP TABLE users IF EXISTS;

CREATE TABLE users (
  id bigint(20) PRIMARY KEY auto_increment,
  address varchar(255),
  email varchar(25),
  firstName varchar(20),
  lastname varchar(20),
  phone bigint(20),
  sex varchar(10)
);
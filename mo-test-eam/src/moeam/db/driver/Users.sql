-- This is dangerous and not compliant with JDBC specs, should be changed in the future
DROP TABLE IF EXISTS users;

CREATE TABLE users 
(
	P_userId int PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
	userName VARCHAR(15),
	password VARCHAR(30)
);
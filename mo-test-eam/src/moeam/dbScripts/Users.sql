-- This is dangerous and not compliant with JDBC specs, should be changed in the future
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
	P_userId int PRIMARY KEY,
	loginName VARCHAR(15),
	password VARCHAR(30),
	userName VARCHAR(15)
);
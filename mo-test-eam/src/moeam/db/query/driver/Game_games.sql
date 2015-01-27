
-- This is dangerous and not compliant with JDBC specs, should be changed in the future
DROP TABLE IF EXISTS games;

CREATE TABLE games
(
	P_gameId int PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
	gameName VARCHAR(15),
	companyName VARCHAR(15),
	downloadLink VARCHAR(50),
	description VARCHAR(300)
);
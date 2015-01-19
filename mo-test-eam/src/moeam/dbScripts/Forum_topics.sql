-- Create games table first since topics has a FK dependency

-- This is dangerous and not compliant with JDBC specs, should be changed in the future
DROP TABLE IF EXISTS topics;

CREATE TABLE topics
(
	P_topicId int PRIMARY KEY,
	F_gameId int, FOREIGN KEY (F_gameId) REFERENCES games(P_gameId),
	topicName VARCHAR(20),
	dateCreated DATE
);

-- Create games table first since topics has a FK dependency

DROP TABLE IF EXISTS topics;
CREATE TABLE topics(P_topicId int PRIMARY KEY,F_gameId int,FOREIGN KEY (F_gameId) REFERENCES games(P_gameId),topicName VARCHAR(20),dateCreated DATE);
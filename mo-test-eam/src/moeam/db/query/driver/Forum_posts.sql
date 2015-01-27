-- Create Topics and Users tables first because of FK dependency

-- This is dangerous and not compliant with JDBC specs, should be changed in the future
DROP TABLE IF EXISTS posts;

CREATE TABLE posts
(
	P_postId int PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
	F_topicId int, FOREIGN KEY (F_topicId) REFERENCES topics(P_topicId),
	F_userId int, FOREIGN KEY (F_userId) REFERENCES users(P_userId),
	contents VARCHAR(500),
	dateCreated DATE
);
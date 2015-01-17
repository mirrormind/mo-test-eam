-- Join with forum topics

DROP TABLE IF EXISTS posts;
CREATE TABLE posts(P_postId int PRIMARY KEY,F_userId int,FOREIGN KEY (F_userId) REFERENCES users(P_userId),contents VARCHAR(500),dateCreated DATE);
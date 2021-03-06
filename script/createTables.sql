
CREATE TABLE im_user(
id INT AUTO_INCREMENT PRIMARY KEY,
account VARCHAR(24) UNIQUE NOT NULL,
pwd CHAR(32) NOT NULL,
sex SMALLINT NOT NULL,
NAME VARCHAR(24) NOT NULL,
headPic VARCHAR(128) NOT NULL,
createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE im_friend(
id INT AUTO_INCREMENT PRIMARY KEY,
fromUserId INT  NOT NULL,
toUserId INT  NOT NULL,
createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE im_friend_req(
id INT AUTO_INCREMENT PRIMARY KEY,
fromUserId INT  NOT NULL,
toUserId INT  NOT NULL,
STATUS INT NOT NULL
);

CREATE TABLE im_group(
id INT AUTO_INCREMENT PRIMARY KEY,
NAME VARCHAR(24) ,
headPic VARCHAR(128),
creatorId INT NOT NULL,
createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE im_group_mem(
id INT AUTO_INCREMENT PRIMARY KEY,
groupId INT NOT NULL,
userId INT NOT NULL,
createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
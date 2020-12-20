DROP TABLE IF EXISTS user;

CREATE TABLE user (
  user_id INT AUTO_INCREMENT  PRIMARY KEY,
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  active VARCHAR(250) DEFAULT NULL
);

INSERT INTO user (username, password, active)
values ('admin',
'admin',
true);

DROP TABLE IF EXISTS role;

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
 `authority` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);


INSERT INTO role(authority)
values ('Administrator');

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `user_fk_idx` (`user_id`),
  KEY `role_fk_idx` (`role_id`),
  CONSTRAINT `role_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);
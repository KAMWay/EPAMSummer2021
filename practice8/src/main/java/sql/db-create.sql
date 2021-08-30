DROP DATABASE practice8;
CREATE DATABASE practice8;

DROP TABLE IF EXISTS `users`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
    `id` INTEGER(11) NOT NULL AUTO_INCREMENT,
    `login` VARCHAR(10) NOT NULL ,
    PRIMARY KEY (`id`),
    UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES (1,'ivanov');

UNLOCK TABLES;

DROP TABLE IF EXISTS `teams`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `teams` (
    `id` INTEGER(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(10) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
LOCK TABLES `teams` WRITE;
INSERT INTO `teams` VALUES (1,'teamA');

UNLOCK TABLES;

DROP TABLE IF EXISTS `users_teams`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `users_teams` (
    `user_id` INTEGER(11) NOT NULL,
    `team_id` INTEGER(11) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
LOCK TABLES `users_teams` WRITE;
INSERT INTO `users_teams` VALUES (1,1);

UNLOCK TABLES;
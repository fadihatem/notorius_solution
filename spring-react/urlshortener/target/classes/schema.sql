CREATE TABLE `url` (
                       `url_id` bigint NOT NULL AUTO_INCREMENT,
                       `shortenedurl` varchar(255) DEFAULT NULL,
                       `url` varchar(255) DEFAULT NULL,
                       PRIMARY KEY (`url_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
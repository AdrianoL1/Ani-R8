CREATE TABLE `user_manga` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `chapters_read` int NOT NULL,
    `personal_rating` int NOT NULL,
    `status` enum('COMPLETED','DROPPED','OH','PTR','READING') NOT NULL,
    `volumes_read` int NOT NULL,
    `manga_id` bigint NOT NULL,
    `user_id` bigint NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKb1v8v0fawp9gkes0g3omi9hme` (`user_id`,`manga_id`),
    KEY `FK8bfdeomlixb6tb91hmhxki0wv` (`manga_id`),
    CONSTRAINT `FK8bfdeomlixb6tb91hmhxki0wv` FOREIGN KEY (`manga_id`) REFERENCES `manga` (`id`),
    CONSTRAINT `FKeayhrpl682blllw0pox6m9eae` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB;
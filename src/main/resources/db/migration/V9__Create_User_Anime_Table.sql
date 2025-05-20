CREATE TABLE `user_anime` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `episodes_watched` int NOT NULL,
    `personal_rating` int NOT NULL,
    `status` enum('COMPLETED','DROPPED','OH','PTW','WATCHING') NOT NULL,
    `anime_id` bigint NOT NULL,
    `user_id` bigint NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKsj370mua9fw29suomsee5xc3` (`user_id`,`anime_id`),
    KEY `FK4lmo9a6am68v3p2k6ibfuhvtf` (`anime_id`),
    CONSTRAINT `FK4lmo9a6am68v3p2k6ibfuhvtf` FOREIGN KEY (`anime_id`) REFERENCES `anime` (`id`),
    CONSTRAINT `FK55v95xmhebbs3vdd5cr2yg3mb` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;
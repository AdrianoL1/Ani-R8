CREATE TABLE `role` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `name` enum('ADMIN','USER') NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;
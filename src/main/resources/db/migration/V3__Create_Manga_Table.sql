CREATE TABLE `manga` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `author` varchar(255) NOT NULL,
    `chapters` varchar(255) NOT NULL,
    `description` text NOT NULL,
    `published_from` varchar(255) NOT NULL,
    `published_to` varchar(255) NOT NULL,
    `status` enum('AIRING','FINISHED','HIATUS','PUBLISHING') NOT NULL,
    `title` varchar(255) NOT NULL,
    `volumes` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKkf81qtu3nktogbnx0gxuagqkr` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=1;
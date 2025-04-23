CREATE TABLE IF NOT EXISTS `anime` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `aired_from` varchar(255) NOT NULL,
    `aired_to` varchar(255) NOT NULL,
    `author` varchar(255) NOT NULL,
    `description` text NOT NULL,
    `status` enum('AIRING','FINISHED','HIATUS','PUBLISHING') NOT NULL,
    `title` varchar(255) NOT NULL,
    `total_episodes` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK54eaowtdrykgn24dd0g93dcy4` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=1;
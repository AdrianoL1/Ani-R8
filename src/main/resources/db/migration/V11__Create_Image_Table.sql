CREATE TABLE IF NOT EXISTS `image` (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `content_type` varchar(255) NOT NULL,
   `file_size` bigint NOT NULL,
   `filename` varchar(255) NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1
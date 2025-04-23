CREATE TABLE `anime_genre` (
   `anime_id` bigint NOT NULL,
   `genre_id` bigint NOT NULL,
   PRIMARY KEY (`anime_id`,`genre_id`),
   KEY `FKfsd5quou9lv0tlt8br825w9yn` (`genre_id`),
   CONSTRAINT `FK72693b55ypxe99ik71d43od0v` FOREIGN KEY (`anime_id`) REFERENCES `anime` (`id`),
   CONSTRAINT `FKfsd5quou9lv0tlt8br825w9yn` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;
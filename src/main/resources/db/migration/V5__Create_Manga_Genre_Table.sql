CREATE TABLE `manga_genre` (
   `manga_id` bigint NOT NULL,
   `genre_id` bigint NOT NULL,
   PRIMARY KEY (`manga_id`,`genre_id`),
   KEY `FK7gxn1ikfo8qol8c7sv7extxy8` (`genre_id`),
   CONSTRAINT `FK7gxn1ikfo8qol8c7sv7extxy8` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`),
   CONSTRAINT `FKrxffop27eowouvvsdmkftsafc` FOREIGN KEY (`manga_id`) REFERENCES `manga` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;
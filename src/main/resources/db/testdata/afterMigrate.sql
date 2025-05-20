SET foreign_key_checks = 0;

DELETE FROM anime;
DELETE FROM manga;
DELETE FROM genre;
DELETE FROM anime_genre;
DELETE FROM manga_genre;
DELETE FROM user;
DELETE FROM role;
DELETE FROM user_role;
DELETE FROM user_anime;

SET foreign_key_checks = 1;

ALTER TABLE anime auto_increment = 1;
ALTER TABLE manga auto_increment = 1;
ALTER TABLE genre auto_increment = 1;
ALTER TABLE anime_genre auto_increment = 1;
ALTER TABLE manga_genre auto_increment = 1;
ALTER TABLE user auto_increment = 1;
ALTER TABLE role auto_increment = 1;
ALTER TABLE user_anime auto_increment = 1;

INSERT INTO `anime` (`aired_from`, `aired_to`, `author`, `description`, `status`, `title`, `total_episodes`) VALUES ('2004','2005','Naoki Urasawa','Monster anime','FINISHED','Monster','74');
INSERT INTO `genre` (`name`) VALUES ('Fantasy'),('Action'),('Romance');
INSERT INTO `manga` (`author`, `chapters`, `description`, `published_from`, `published_to`, `status`, `title`, `volumes`) VALUES ('Tsugumi Ohba','108','manga death note','2003','2006','FINISHED','Death Note','12');
INSERT INTO `anime_genre` (`anime_id`, `genre_id`) VALUES (1,1),(1,2);
INSERT INTO `manga_genre` (`manga_id`, `genre_id`) VALUES (1,1),(1,2);
INSERT INTO `role` (`name`) VALUES ('ADMIN'), ('USER');
INSERT INTO `user` (`email`, `password`, `username`) VALUES ('admin@admin.com', '$2a$12$OPQdHPI7xWobP3AQGDLkiOgVSwaIcnDGAvcj0KHlIcKEvC1gV2Aca', 'sysAdm');
INSERT INTO `user_role` VALUES (1, 1)
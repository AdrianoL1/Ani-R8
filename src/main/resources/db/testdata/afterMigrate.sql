SET foreign_key_checks = 0;

DELETE FROM anime;
DELETE FROM manga;
DELETE FROM genre;
DELETE FROM anime_genre;
DELETE FROM manga_genre;

SET foreign_key_checks = 1;

ALTER TABLE anime auto_increment = 1;
ALTER TABLE manga auto_increment = 1;
ALTER TABLE genre auto_increment = 1;
ALTER TABLE anime_genre auto_increment = 1;
ALTER TABLE manga_genre auto_increment = 1;

INSERT INTO `anime` (`aired_from`, `aired_to`, `author`, `description`, `status`, `title`, `total_episodes`) VALUES ('2004','2005','Naoki Urasawa','Monster anime','FINISHED','Monster','74');
INSERT INTO `genre` (`name`) VALUES ('Fantasy'),('Action'),('Romance');
INSERT INTO `manga` (`author`, `chapters`, `description`, `published_from`, `published_to`, `status`, `title`, `volumes`) VALUES ('Tsugumi Ohba','108','manga death note','2003','2006','FINISHED','Death Note','12');
INSERT INTO `anime_genre` (`anime_id`, `genre_id`) VALUES (1,1),(1,2);
INSERT INTO `manga_genre` (`manga_id`, `genre_id`) VALUES (1,1),(1,2);
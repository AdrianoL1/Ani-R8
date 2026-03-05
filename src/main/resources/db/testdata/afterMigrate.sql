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
DELETE FROM user_manga;

SET foreign_key_checks = 1;

ALTER TABLE anime auto_increment = 1;
ALTER TABLE manga auto_increment = 1;
ALTER TABLE genre auto_increment = 1;
ALTER TABLE anime_genre auto_increment = 1;
ALTER TABLE manga_genre auto_increment = 1;
ALTER TABLE user auto_increment = 1;
ALTER TABLE role auto_increment = 1;
ALTER TABLE user_anime auto_increment = 1;
ALTER TABLE user_manga auto_increment = 1;

INSERT INTO `anime` (`aired_from`, `aired_to`, `author`, `description`, `status`, `title`, `total_episodes`) VALUES ('2004','2005','Naoki Urasawa','Monster anime','FINISHED','Monster','74'),
                                                                                                                    ('2011','2011','Nitroplus','Stein;Gate anime','FINISHED','Steins;Gate','24'),
                                                                                                                    ('2009','2010','Hiromu Arakawa','Fullmetal Alchemist: Brotherhood anime','FINISHED','Fullmetal Alchemist: Brotherhood','64'),
                                                                                                                    ('2014','2014','Adachitoka','Noragami anime','FINISHED','Noragami','12'),
                                                                                                                    ('2014','2015','Hitoshi Iwaaki','Kiseijuu: Sei no Kakuritsu anime','FINISHED','Kiseijuu: Sei no Kakuritsu','24'),
                                                                                                                    ('1998','1998','Yasuyuki Ueda','Serial Experiments Lain anime','FINISHED','Serial Experiments Lain','13'),
                                                                                                                    ('2011','2014','Yoshihiro Togashi','Hunter x Hunter anime','FINISHED','Hunter x Hunter','148');
INSERT INTO `genre` (`name`) VALUES ('Fantasy'),('Action'),('Romance'), ('Sci-Fi'), ('Suspense'), ('Comedy'), ('Drama'), ('Mystery'), ('Supernatural'), ('Adventure');
INSERT INTO `manga` (`author`, `chapters`, `description`, `published_from`, `published_to`, `status`, `title`, `volumes`) VALUES ('Tsugumi Ohba','108','manga death note','2003','2006','FINISHED','Death Note','12'),
                                                                                                                                 ('Naoki Urasawa','249','manga 20th Century Boys','1999','2006','FINISHED','20th Century Boys','22'),
                                                                                                                                 ('Inio Asano','9','manga Hikari no Machi','2004','2005','FINISHED','Hikari no Machi','1'),
                                                                                                                                 ('Muneyuki Kaneshiro','163','manga Jagaaaaaan','2017','2021','FINISHED','Jagaaaaaan','14'),
                                                                                                                                 ('Shinji Kajio','37','manga Sasurai Emanon','2008','2017','FINISHED','Sasurai Emanon','3');
INSERT INTO `anime_genre` (`anime_id`, `genre_id`) VALUES (1,1),
                                                          (1,2),
                                                          (2,4),
                                                          (2,5),
                                                          (2,7),
                                                          (3,2),
                                                          (3,10),
                                                          (3,7),
                                                          (4,2),
                                                          (4,9),
                                                          (5,2),
                                                          (5,4),
                                                          (5,5),
                                                          (6,8),
                                                          (6,5),
                                                          (6,9),
                                                          (7,2),
                                                          (7,10);
INSERT INTO `manga_genre` (`manga_id`, `genre_id`) VALUES (1,1),
                                                          (1,2),
                                                          (2,4),
                                                          (2,7),
                                                          (2,8),
                                                          (3,7),
                                                          (4,7),
                                                          (4,8),
                                                          (4,9),
                                                          (5,10);
INSERT INTO `role` (`name`) VALUES ('ADMIN'), ('USER');
INSERT INTO `user` (`email`, `password`, `username`) VALUES ('admin@admin.com', '$2a$12$OPQdHPI7xWobP3AQGDLkiOgVSwaIcnDGAvcj0KHlIcKEvC1gV2Aca', 'sysAdm');
INSERT INTO `user_role` VALUES (1, 1)
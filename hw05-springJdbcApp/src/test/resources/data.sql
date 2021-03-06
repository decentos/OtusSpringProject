INSERT INTO AUTHORS(FIRST_NAME, LAST_NAME) VALUES ('Lewis', 'Carrol');
INSERT INTO AUTHORS(FIRST_NAME, LAST_NAME) VALUES ('Charlotte', 'Bronte');
INSERT INTO AUTHORS(FIRST_NAME, LAST_NAME) VALUES ('Miguel', 'de Cervantes');
INSERT INTO AUTHORS(FIRST_NAME, LAST_NAME) VALUES ('Herbert', 'Wells');
INSERT INTO AUTHORS(FIRST_NAME, LAST_NAME) VALUES ('Leo', 'Tolstoy');
INSERT INTO AUTHORS(FIRST_NAME, LAST_NAME) VALUES ('Jane', 'Austen');
INSERT INTO AUTHORS(FIRST_NAME, LAST_NAME) VALUES ('Gabriel', 'García Márquez');
INSERT INTO AUTHORS(FIRST_NAME, LAST_NAME) VALUES ('Leonardo', 'Fibonacci');

INSERT INTO GENRES(GENRE) VALUES ('Literary realism');
INSERT INTO GENRES(GENRE) VALUES ('Fantasy');
INSERT INTO GENRES(GENRE) VALUES ('Autobiography');
INSERT INTO GENRES(GENRE) VALUES ('Novel');
INSERT INTO GENRES(genre) VALUES ('Science-fiction');
INSERT INTO GENRES(GENRE) VALUES ('Romance');
INSERT INTO GENRES(GENRE) VALUES ('Mathematics');

INSERT INTO BOOKS(TITLE, AUTHOR_ID, GENRE_ID) VALUES ('Alice in Wonderland', 1, 2);
INSERT INTO BOOKS(TITLE, AUTHOR_ID, GENRE_ID) VALUES ('Jane Eyre', 2, 3);
INSERT INTO BOOKS(TITLE, AUTHOR_ID, GENRE_ID) VALUES ('Don Quixote', 3, 4);
INSERT INTO BOOKS(TITLE, AUTHOR_ID, GENRE_ID) VALUES ('The Time Machine', 4, 5);
INSERT INTO BOOKS(TITLE, AUTHOR_ID, GENRE_ID) VALUES ('Anna Karenina', 5, 1);
INSERT INTO BOOKS(TITLE, AUTHOR_ID, GENRE_ID) VALUES ('Pride and Prejudice', 6, 6);
INSERT INTO BOOKS(TITLE, AUTHOR_ID, GENRE_ID) VALUES ('Childhood', 5, 3);
INSERT INTO BOOKS(TITLE, AUTHOR_ID, GENRE_ID) VALUES ('Boyhood', 5, 3);
INSERT INTO BOOKS(TITLE, AUTHOR_ID, GENRE_ID) VALUES ('Love in the Time of Cholera', 7, 4);
INSERT INTO BOOKS(TITLE, AUTHOR_ID, GENRE_ID) VALUES ('The Book of Calculation', 8, 7);
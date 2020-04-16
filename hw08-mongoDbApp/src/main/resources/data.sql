insert into AUTHORS(FIRST_NAME, LAST_NAME) values ('Lewis', 'Carrol');
insert into AUTHORS(FIRST_NAME, LAST_NAME) values ('Charlotte', 'Bronte');
insert into AUTHORS(FIRST_NAME, LAST_NAME) values ('Miguel', 'de Cervantes');
insert into AUTHORS(FIRST_NAME, LAST_NAME) values ('Herbert', 'Wells');
insert into AUTHORS(FIRST_NAME, LAST_NAME) values ('Leo', 'Tolstoy');
insert into AUTHORS(FIRST_NAME, LAST_NAME) values ('Jane', 'Austen');
insert into AUTHORS(FIRST_NAME, LAST_NAME) values ('Gabriel', 'García Márquez');
insert into AUTHORS(FIRST_NAME, LAST_NAME) values ('Leonardo', 'Fibonacci');

insert into GENRES(GENRE) values ('Literary realism');
insert into GENRES(GENRE) values ('Fantasy');
insert into GENRES(GENRE) values ('Autobiography');
insert into GENRES(GENRE) values ('Novel');
insert into GENRES(genre) values ('Science-fiction');
insert into GENRES(GENRE) values ('Romance');
insert into GENRES(GENRE) values ('Mathematics');

insert into BOOKS(TITLE, AUTHOR_ID, GENRE_ID) values ('Alice in Wonderland', 1, 2);
insert into BOOKS(TITLE, AUTHOR_ID, GENRE_ID) values ('Jane Eyre', 2, 3);
insert into BOOKS(TITLE, AUTHOR_ID, GENRE_ID) values ('Don Quixote', 3, 4);
insert into BOOKS(TITLE, AUTHOR_ID, GENRE_ID) values ('The Time Machine', 4, 5);
insert into BOOKS(TITLE, AUTHOR_ID, GENRE_ID) values ('Anna Karenina', 5, 1);
insert into BOOKS(TITLE, AUTHOR_ID, GENRE_ID) values ('Pride and Prejudice', 6, 6);
insert into BOOKS(TITLE, AUTHOR_ID, GENRE_ID) values ('Childhood', 5, 3);
insert into BOOKS(TITLE, AUTHOR_ID, GENRE_ID) values ('Boyhood', 5, 3);
insert into BOOKS(TITLE, AUTHOR_ID, GENRE_ID) values ('Love in the Time of Cholera', 7, 4);
insert into BOOKS(TITLE, AUTHOR_ID, GENRE_ID) values ('The Book of Calculation', 8, 7);

insert into COMMENTS(COMMENTARY, BOOK_ID) values ('Excellent', 1);
insert into COMMENTS(COMMENTARY, BOOK_ID) values ('Nice', 2);
insert into COMMENTS(COMMENTARY, BOOK_ID) values ('Awesome book', 2);
insert into COMMENTS(COMMENTARY, BOOK_ID) values ('Pretty good', 3);
insert into COMMENTS(COMMENTARY, BOOK_ID) values ('Very good', 5);
insert into COMMENTS(COMMENTARY, BOOK_ID) values ('Bad book', 9);
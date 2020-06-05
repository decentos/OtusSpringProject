drop table IF EXISTS AUTHORS;
drop table IF EXISTS BOOKS;
drop table IF EXISTS GENRES;
drop table IF EXISTS COMMENTS;

create TABLE AUTHORS(
                     ID BIGINT PRIMARY KEY AUTO_INCREMENT,
                     FIRST_NAME VARCHAR(50) NOT NULL,
                     LAST_NAME VARCHAR(50) NOT NULL
                    );

create TABLE GENRES(
                    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
                    GENRE VARCHAR(255) NOT NULL
                   );

create TABLE BOOKS(
                   ID BIGINT PRIMARY KEY AUTO_INCREMENT,
                   TITLE VARCHAR(255),
                   AUTHOR_ID BIGINT,
                   GENRE_ID BIGINT,
                   FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHORS (ID) ON delete CASCADE,
                   FOREIGN KEY (GENRE_ID) REFERENCES GENRES (ID) ON delete CASCADE
                  );

create TABLE COMMENTS(
                      ID BIGINT PRIMARY KEY AUTO_INCREMENT,
                      COMMENTARY VARCHAR(255),
                      BOOK_ID BIGINT,
                      FOREIGN KEY (BOOK_ID) REFERENCES BOOKS (ID) ON delete CASCADE
                     );

create TABLE USERS(
                     ID BIGINT PRIMARY KEY AUTO_INCREMENT,
                     NAME VARCHAR(50) NOT NULL,
                     PASSWORD VARCHAR(255) NOT NULL
                    );
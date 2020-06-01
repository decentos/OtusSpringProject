drop table IF EXISTS AUTHORS;
drop table IF EXISTS BOOKS;
drop table IF EXISTS GENRES;
drop table IF EXISTS COMMENTS;
drop table IF EXISTS auth_user_group;
drop table IF EXISTS users;

create TABLE AUTHORS
(
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    FIRST_NAME VARCHAR(50) NOT NULL,
    LAST_NAME VARCHAR(50) NOT NULL
);

create TABLE GENRES
(
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    GENRE VARCHAR(255) NOT NULL
);

create TABLE BOOKS
(
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    TITLE VARCHAR(255),
    AUTHOR_ID BIGINT,
    GENRE_ID BIGINT,
    FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHORS (ID) ON delete CASCADE,
    FOREIGN KEY (GENRE_ID) REFERENCES GENRES (ID) ON delete CASCADE
);

create TABLE COMMENTS
(
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    COMMENTARY VARCHAR(255),
    BOOK_ID BIGINT,
    FOREIGN KEY (BOOK_ID) REFERENCES BOOKS (ID) ON delete CASCADE
);

create TABLE users
(
    id                BIGSERIAL PRIMARY KEY,
    username          VARCHAR(128) NOT NULL UNIQUE,
    password          VARCHAR(256) NOT NULL,
    is_acc_non_exp    BOOLEAN,
    is_acc_non_locked BOOLEAN,
    is_cred_non_exp   BOOLEAN,
    is_enabled        BOOLEAN
);

create TABLE auth_user_group
(
    auth_user_group_id BIGSERIAL,
    username           VARCHAR(128) NOT NULL,
    auth_group         VARCHAR(256) NOT NULL,
    PRIMARY KEY (auth_user_group_id)
);
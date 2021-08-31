CREATE TABLE USERS
(
    id       bigint auto_increment primary key,
    username varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null
);

CREATE TABLE ENTRY
(
    id        bigint auto_increment primary key,
    title     varchar(255) not null,
    text      varchar(255) not null/*,
    author_id bigint,
    foreign key (author_id) references users(id)*/
);
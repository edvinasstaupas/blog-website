create table users
(
    id           bigint not null
        constraint users_pkey
            primary key,
    email        varchar(255) unique,
    password     varchar(255),
    username     varchar(255),
    user_type_id bigint
        constraint user_user_type_fkey
            references user_type
);

create table entry
(
    id        bigint not null
        constraint entry_pkey
            primary key,
    text      text,
    title     varchar(255),
    author_id bigint
        constraint user_entry_author_fkey
            references users
);

create table user_type
(
    id   bigint not null
        constraint user_type_pkey
            primary key,
    name varchar(255)
);
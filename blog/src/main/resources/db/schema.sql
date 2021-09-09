create table user_type
(
    id   bigint not null
        constraint user_type_pkey
            primary key,
    name varchar(255)
);

create table users
(
    id           bigint not null
        constraint users_pkey
            primary key,
    email        varchar(255)
        constraint unique_user_email
            unique,
    password     varchar(255),
    username     varchar(255)
        constraint unique_user_username
            unique,
    user_type_id bigint
        constraint user_user_type_fkey
            references user_type
);

create table post
(
    id        bigint not null
        constraint post_pkey
            primary key,
    text      text,
    title     varchar(255),
    author_id bigint
        constraint user_post_author_fkey
            references users
);
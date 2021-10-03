create table roles
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
        constraint uk_6dotkott2kjsp8vw4d0m25fb7
            unique,
    password     varchar(255),
    username     varchar(255)
        constraint uk_r43af9ap4edm43mmtq01oddj6
            unique
);

create table users_roles
(
    user_id  bigint not null
        constraint users_roles_user
            references users,
    roles_id bigint not null
        constraint users_roles_roles
            references roles,
    primary key (user_id, roles_id)
);

create table post
(
    id        bigint       not null
        constraint post_pkey
            primary key,
    post_date timestamp,
    text      text         not null,
    title     varchar(255) not null,
    author_id bigint
        constraint user_post_author_fkey
            references users
);

create table comment
(
    id        bigint not null
        constraint comment_pkey
            primary key,
    post_date timestamp,
    text      text   not null,
    author_id bigint
        constraint user_comment_author_fkey
            references users,
    post_id   bigint
        constraint post_comment_fkey
            references post
);

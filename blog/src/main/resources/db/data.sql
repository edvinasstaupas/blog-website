INSERT INTO USER_TYPE (id, name)
values (1, 'member'),
       (2, 'admin');
INSERT INTO USERS (id, username, email, password, user_type_id)
values (1, 'a', 'edvinasstaup@gmail.com', 'pass', 2),
       (2, 'b', 'b', 'a', 1),
       (3, 'c', 'c', 'a', 1),
       (4, 'd', 'd', 'a', 1),
       (5, 'e', 'e', 'a', 1);
INSERT INTO POST (id, text, title, author_id, post_date)
values (1, 'a', 'a', 1, NOW()),
       (2, 'b', 'b', 1, NOW()),
       (3, 'c', 'c', 1, NOW()),
       (4, 'd', 'd', 1, NOW()),
       (5, 'e', 'e', 1, NOW());
INSERT INTO COMMENT (id, text, post_id, author_id, post_date)
values (1, 'a', 1, 1, NOW()),
       (2, 'b', 1, 2, NOW()),
       (3, 'c', 1, 3, NOW()),
       (4, 'd', 1, 4, NOW()),
       (5, 'e', 1, 5, NOW());

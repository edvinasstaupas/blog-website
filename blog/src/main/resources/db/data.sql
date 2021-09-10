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
values (1, 'a', 'a', 1),
       (2, 'b', 'b', 1),
       (3, 'c', 'c', 1),
       (4, 'd', 'd', 1),
       (5, 'e', 'e', 1);

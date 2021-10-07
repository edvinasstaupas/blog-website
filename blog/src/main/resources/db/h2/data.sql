INSERT INTO ROLES (id, name)
values (1, 'MEMBER'),
       (2, 'ADMIN');
INSERT INTO USERS (id, username, email, password)
values (1, 'a', 'edvinasstaup@gmail.com', '{bcrypt}$2a$12$w8AIULx6.K.2E8jpEteDI.W3RjuLV.oCsGqd/Aox.fb4VhHvtl7ie'),
       (2, 'b', 'b', '{bcrypt}$2a$12$DT1lREfpU8AE.sNB5OAibuVp..XsuebRTEToLEKc2D8fkiUivrV4C'),
       (3, 'c', 'c', '{bcrypt}$2a$12$DT1lREfpU8AE.sNB5OAibuVp..XsuebRTEToLEKc2D8fkiUivrV4C'),
       (4, 'd', 'd', '{bcrypt}$2a$12$DT1lREfpU8AE.sNB5OAibuVp..XsuebRTEToLEKc2D8fkiUivrV4C'),
       (5, 'e', 'e', '{bcrypt}$2a$12$DT1lREfpU8AE.sNB5OAibuVp..XsuebRTEToLEKc2D8fkiUivrV4C');
INSERT INTO USERS_ROLES (user_id, roles_id)
values (1, 1),
       (1, 2),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1);
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

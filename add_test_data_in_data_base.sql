INSERT INTO GOOD_LINE_CLI_SCHEME.USERS (USER_NAME, USER_LOGIN, USER_PASS_HASH, USER_SALT) VALUES ('JonDoe', 'jdoe', 'd824656e5bb0902512e1e8a7f1a099cb', '+NSw]7');
INSERT INTO GOOD_LINE_CLI_SCHEME.USERS(USER_NAME, USER_LOGIN, USER_PASS_HASH, USER_SALT) VALUES ('JaneRow', 'jrow', 'ca776c3650876297843e05870e45a037', '7xpK2L$');

INSERT INTO GOOD_LINE_CLI_SCHEME.USER_RESOURCES (USER_ID, USER_RESOURCE_PATH, USER_RESOURCE_ROLE) VALUES (1, 'a', 'READ');
INSERT INTO GOOD_LINE_CLI_SCHEME.USER_RESOURCES (USER_ID, USER_RESOURCE_PATH, USER_RESOURCE_ROLE) VALUES (1, 'a.b', 'WRITE');
INSERT INTO GOOD_LINE_CLI_SCHEME.USER_RESOURCES (USER_ID, USER_RESOURCE_PATH, USER_RESOURCE_ROLE) VALUES (2, 'a.b.c', 'EXECUTE');
INSERT INTO GOOD_LINE_CLI_SCHEME.USER_RESOURCES (USER_ID, USER_RESOURCE_PATH, USER_RESOURCE_ROLE) VALUES (1, 'a.bc', 'EXECUTE');
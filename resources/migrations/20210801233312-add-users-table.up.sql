CREATE TABLE users
(id         VARCHAR PRIMARY KEY,
 first_name VARCHAR,
 last_name  VARCHAR,
 email      VARCHAR,
 admin      BOOLEAN,
 last_login TIMESTAMP,
 is_active  BOOLEAN,
 pass       VARCHAR);

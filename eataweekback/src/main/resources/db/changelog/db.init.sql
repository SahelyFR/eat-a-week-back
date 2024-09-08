DROP INDEX IF EXISTS ix_auth_username;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS authorities CASCADE;

CREATE SEQUENCE users_seq;
CREATE SEQUENCE authorities_seq;

CREATE TABLE users (
  id integer NOT NULL DEFAULT nextval('users_seq'),
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  enabled BOOLEAN NOT NULL DEFAULT TRUE,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_DATE,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_DATE
);

ALTER SEQUENCE users_seq OWNED BY users.id;
  
CREATE TABLE authorities (
  id integer NOT NULL DEFAULT nextval('authorities_seq'),
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (username) REFERENCES users(username)
);

ALTER SEQUENCE authorities_seq OWNED BY authorities.id;

CREATE UNIQUE INDEX ix_auth_username
  on authorities (username,authority);
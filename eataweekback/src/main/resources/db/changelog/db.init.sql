CREATE SEQUENCE IF NOT EXISTS users_seq;
CREATE SEQUENCE IF NOT EXISTS roles_seq;
CREATE SEQUENCE IF NOT EXISTS recipes_seq;

CREATE TABLE IF NOT EXISTS users (
  id integer NOT NULL DEFAULT nextval('users_seq'),
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  enabled BOOLEAN NOT NULL DEFAULT TRUE,
  role_id integer NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_DATE,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_DATE
);

ALTER SEQUENCE users_seq OWNED BY users.id;
  
CREATE TABLE IF NOT EXISTS recipes (
  id integer NOT NULL DEFAULT nextval('recipes_seq'),
  name VARCHAR(70) NOT NULL,
  link text,
  image text,
  starting_month integer DEFAULT 1,
  ending_month integer DEFAULT 12,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_DATE,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_DATE
);

ALTER SEQUENCE recipes_seq OWNED BY recipes.id;

CREATE TABLE IF NOT EXISTS roles (
  id integer NOT NULL DEFAULT nextval('roles_seq'),
  name VARCHAR(50) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_DATE,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_DATE
);

ALTER SEQUENCE roles_seq OWNED BY roles.id;

CREATE UNIQUE INDEX IF NOT EXISTS ix_auth_username
  on users (id,role_id);

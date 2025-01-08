CREATE SEQUENCE IF NOT EXISTS menus_seq;

CREATE TABLE IF NOT EXISTS menus (
  id integer NOT NULL DEFAULT nextval('menus_seq'),
  week_menu integer NOT NULL,
  enabled BOOLEAN NOT NULL DEFAULT TRUE,
  creator_id integer NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_DATE,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_DATE
);

ALTER SEQUENCE menus_seq OWNED BY menus.id;

CREATE UNIQUE INDEX IF NOT EXISTS ix_menu_creator on menus (id,creator_id);
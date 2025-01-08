CREATE SEQUENCE IF NOT EXISTS ingredients_seq;

CREATE TABLE IF NOT EXISTS measures (
  name VARCHAR(50) NOT NULL,
  short_name VARCHAR(5) NOT NULL UNIQUE,
  enabled BOOLEAN NOT NULL DEFAULT TRUE,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_DATE,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_DATE
);


CREATE TABLE IF NOT EXISTS ingredients (
  id integer NOT NULL DEFAULT nextval('ingredients_seq'),
  name VARCHAR(100) NOT NULL UNIQUE,
  enabled BOOLEAN NOT NULL DEFAULT TRUE,
  measure_id integer NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_DATE,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_DATE
);

ALTER SEQUENCE ingredients_seq OWNED BY ingredients.id;

ALTER TABLE recipes ADD COLUMN IF NOT EXISTS steps JSON;

CREATE TABLE IF NOT EXISTS recipe_ingredients (
  recipe_id integer NOT NULL,
  ingredient_id integer NOT NULL
)
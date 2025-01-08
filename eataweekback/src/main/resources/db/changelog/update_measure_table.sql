ALTER TABLE ingredients DROP COLUMN IF EXISTS linked_measure;
ALTER TABLE ingredients DROP COLUMN IF EXISTS measure;
ALTER TABLE ingredients DROP COLUMN IF EXISTS measure_id;
ALTER TABLE ingredients ADD COLUMN IF NOT EXISTS measure_short_name TEXT;

ALTER TABLE measures DROP COLUMN IF EXISTS ingredients;

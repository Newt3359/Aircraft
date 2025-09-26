CREATE SEQUENCE IF NOT EXISTS pilot_id_seq;
ALTER TABLE pilot
    ALTER COLUMN id SET NOT NULL;
ALTER TABLE pilot
    ALTER COLUMN id SET DEFAULT nextval('pilot_id_seq');

ALTER SEQUENCE pilot_id_seq OWNED BY pilot.id;
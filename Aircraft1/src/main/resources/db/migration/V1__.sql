CREATE TABLE aircraft
(
    id       BIGINT NOT NULL,
    airframe VARCHAR(255),
    pilot    VARCHAR(255),
    CONSTRAINT pk_aircraft PRIMARY KEY (id)
);
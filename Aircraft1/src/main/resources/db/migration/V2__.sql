CREATE TABLE pilot
(
    id         BIGINT  NOT NULL,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    age        INTEGER NOT NULL,
    CONSTRAINT pk_pilot PRIMARY KEY (id)
);
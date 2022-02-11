CREATE TABLE locations
(
    id                      uuid NOT NULL PRIMARY KEY,
    location_id             BIGINT NOT NULL,
    external_location_id    VARCHAR(256) NOT NULL,
    partner                 VARCHAR(256) NOT NULL,
    created_at              TIMESTAMP NOT NULL,
    updated_at              TIMESTAMP NOT NULL,
    deleted_at              TIMESTAMP DEFAULT NULL
);

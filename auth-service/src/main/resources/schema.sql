CREATE SCHEMA IF NOT EXISTS auth;

CREATE TABLE IF NOT EXISTS auth.oauth2_registered_client
(
    id                            varchar(100)                            NOT NULL
        PRIMARY KEY,
    client_id                     varchar(100)                            NOT NULL,
    client_id_issued_at           timestamp     DEFAULT CURRENT_TIMESTAMP NOT NULL,
    client_secret                 varchar(200)  DEFAULT NULL::character varying,
    client_secret_expires_at      timestamp,
    client_name                   varchar(200)                            NOT NULL,
    client_authentication_methods varchar(1000)                           NOT NULL,
    authorization_grant_types     varchar(1000)                           NOT NULL,
    redirect_uris                 varchar(1000) DEFAULT NULL::character varying,
    scopes                        varchar(1000)                           NOT NULL,
    client_settings               varchar(2000)                           NOT NULL,
    token_settings                varchar(2000)                           NOT NULL
);

ALTER TABLE IF EXISTS auth.oauth2_registered_client
    OWNER TO postgres;

CREATE TABLE IF NOT EXISTS auth.users
(
    username varchar(50)          NOT NULL
        PRIMARY KEY,
    password varchar(100)         NOT NULL,
    enabled  boolean DEFAULT true NOT NULL
);

ALTER TABLE IF EXISTS auth.users
    OWNER TO postgres;

CREATE TABLE IF NOT EXISTS auth.authorities
(
    username  varchar(50) NOT NULL
        REFERENCES auth.users,
    authority varchar(50) NOT NULL
);

ALTER TABLE IF EXISTS auth.authorities
    OWNER TO postgres;
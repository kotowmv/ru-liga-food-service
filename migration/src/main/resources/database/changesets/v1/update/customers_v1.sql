-- CREATES --

CREATE SEQUENCE customers_id_seq;

CREATE TABLE IF NOT EXISTS customers
(
    id integer NOT NULL DEFAULT nextval('customers_id_seq'),
    phone varchar NOT NULL,
    email varchar,
    adress varchar NOT NULL,
    CONSTRAINT customers_pk PRIMARY KEY (id)
);

COMMENT ON TABLE customers IS 'Клиенты';
COMMENT ON COLUMN customers.id IS 'Идентификатор';
COMMENT ON COLUMN customers.phone IS 'Номер телефона';
COMMENT ON COLUMN customers.email IS 'Электронная почта';
COMMENT ON COLUMN customers.adress IS 'Адрес';
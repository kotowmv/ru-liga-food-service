-- CREATES --

CREATE SEQUENCE couriers_id_seq
INCREMENT 1
START WITH 1;

CREATE TABLE IF NOT EXISTS couriers
(
    id integer NOT NULL DEFAULT nextval('couriers_id_seq'),
    phone varchar NOT NULL,
    status varchar NOT NULL,
    coordinates varchar NOT NULL,
    CONSTRAINT couriers_pk PRIMARY KEY (id)
);

COMMENT ON TABLE couriers IS 'Курьеры';
COMMENT ON COLUMN couriers.id IS 'Идентификатор';
COMMENT ON COLUMN couriers.phone IS 'Номер телефона';
COMMENT ON COLUMN couriers.status IS 'Статус';
COMMENT ON COLUMN couriers.coordinates IS 'Координаты';
-- CREATES --

CREATE SEQUENCE restaurants_id_seq;

CREATE TABLE IF NOT EXISTS restaurants
(
    id integer NOT NULL DEFAULT nextval('restaurants_id_seq'),
    address varchar NOT NULL,
    status varchar NOT NULL,
    CONSTRAINT restaurants_pk PRIMARY KEY (id)
);

COMMENT ON TABLE restaurants IS 'Рестораны';
COMMENT ON COLUMN restaurants.id IS 'Идентификатор';
COMMENT ON COLUMN restaurants.address IS 'Адрес ресторана';
COMMENT ON COLUMN restaurants.status IS 'Статус (открыт/закрыт)';
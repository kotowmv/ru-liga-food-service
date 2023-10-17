-- CREATES --

CREATE SEQUENCE restaurants_id_seq;

CREATE TABLE IF NOT EXISTS restaurants
(
    id integer NOT NULL DEFAULT nextval('restaurants_id_seq'),
    email varchar NOT NULL,
    status varchar NOT NULL,
    CONSTRAINT restaurants_pk PRIMARY KEY (id)
);

COMMENT ON TABLE restaurants IS 'Заказы';
COMMENT ON COLUMN restaurants.id IS 'Идентификатор';
COMMENT ON COLUMN restaurants.email IS 'Электронная почта';
COMMENT ON COLUMN restaurants.status IS 'Статус (открыт/закрыт)';
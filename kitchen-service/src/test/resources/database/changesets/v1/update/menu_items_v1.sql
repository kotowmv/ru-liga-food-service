-- CREATES --

CREATE SEQUENCE menu_items_id_seq;

CREATE TABLE IF NOT EXISTS menu_items
(
    id integer NOT NULL DEFAULT nextval('menu_items_id_seq'),
    restaurant_id integer NOT NULL,
    name varchar NOT NULL,
    price double precision NOT NULL,
    image varchar,
    description varchar,
    CONSTRAINT menu_items_pk PRIMARY KEY (id),
    CONSTRAINT menu_items_restaurants_fk FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

COMMENT ON TABLE menu_items IS 'Позиции меню';
COMMENT ON COLUMN menu_items.id IS 'Идентификатор';
COMMENT ON COLUMN menu_items.restaurant_id IS 'Идентификатор ресторана';
COMMENT ON COLUMN menu_items.name IS 'Наименование позиции';
COMMENT ON COLUMN menu_items.price IS 'Стоимость';
COMMENT ON COLUMN menu_items.image IS 'Ссылка на изображение';
COMMENT ON COLUMN menu_items.description IS 'Описание';
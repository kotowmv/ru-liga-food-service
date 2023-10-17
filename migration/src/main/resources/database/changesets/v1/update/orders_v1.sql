-- CREATES --

CREATE SEQUENCE orders_id_seq;

CREATE TABLE IF NOT EXISTS orders
(
    id integer NOT NULL DEFAULT nextval('orders_id_seq'),
    customer_id integer NOT NULL,
    restaurant_id integer NOT NULL,
    status varchar NOT NULL,
    courier_id integer,
    time timestamp NOT NULL,
    CONSTRAINT orders_pk PRIMARY KEY (id),
    CONSTRAINT orders_customers_fk FOREIGN KEY (customer_id) REFERENCES customers(id),
    CONSTRAINT orders_restaurants_fk FOREIGN KEY (restaurant_id) REFERENCES restaurants(id),
    CONSTRAINT orders_couriers_fk FOREIGN KEY (courier_id) REFERENCES couriers(id)
);

COMMENT ON TABLE orders IS 'Заказы';
COMMENT ON COLUMN orders.id IS 'Идентификатор';
COMMENT ON COLUMN orders.customer_id IS 'Идентификатор клиента';
COMMENT ON COLUMN orders.restaurant_id IS 'Идентификатор ресторана';
COMMENT ON COLUMN orders.status IS 'Статус заказа';
COMMENT ON COLUMN orders.courier_id IS 'Идентификатор курьера';
COMMENT ON COLUMN orders.time IS 'Дата и время заказа';
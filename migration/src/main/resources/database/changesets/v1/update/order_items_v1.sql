-- CREATES --

CREATE SEQUENCE order_items_id_seq;

CREATE TABLE IF NOT EXISTS order_items
(
    id integer NOT NULL DEFAULT nextval('order_items_id_seq'),
    order_id integer NOT NULL,
    menu_item_id integer NOT NULL,
    price money NOT NULL,
    quantity integer NOT NULL,
    CONSTRAINT order_items_pk PRIMARY KEY (id),
    CONSTRAINT order_items_orders_fk FOREIGN KEY (order_id) REFERENCES orders(id),
    CONSTRAINT order_items_menu_fk FOREIGN KEY (menu_item_id) REFERENCES menu_items(id)
);

COMMENT ON TABLE order_items IS 'Позиции заказа';
COMMENT ON COLUMN order_items.id IS 'Идентификатор';
COMMENT ON COLUMN order_items.order_id IS 'Идентификатор заказа';
COMMENT ON COLUMN order_items.menu_item_id IS 'Идентификатор позиции меню';
COMMENT ON COLUMN order_items.price IS 'Стоимость';
COMMENT ON COLUMN order_items.quantity IS 'Количество';
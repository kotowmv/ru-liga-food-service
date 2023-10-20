-- ADDS --
INSERT INTO couriers(phone, status, coordinates) VALUES ('+79101234567', 'NOT_WORKING','10.000, 20.000');
INSERT INTO couriers(phone, status, coordinates) VALUES ('+77471234567', 'DELIVERING','5.000, 25.000');
INSERT INTO couriers(phone, status, coordinates) VALUES ('+79521234567', 'PENDING','7.500, 5.500');

INSERT INTO customers(phone, email, address) VALUES ('+79109876543', 'test1@mail.com','Adress_1');
INSERT INTO customers(phone, email, address) VALUES ('+77479876543', 'test2@mail.com','Adress_2');
INSERT INTO customers(phone, email, address) VALUES ('+79529876543', 'test3@mail.com','Adress_3');

INSERT INTO restaurants (address, status) VALUES ('first restaurant, 57', 'CLOSE');
INSERT INTO restaurants (address, status) VALUES ('second restaurant, 86', 'OPEN');

INSERT INTO orders (customer_id, restaurant_id, status, courier_id, timestamp) VALUES (1,1,'CUSTOMER_PAID',1,'2023-01-08 18:05:06');
INSERT INTO orders (customer_id, restaurant_id, status, courier_id, timestamp) VALUES (3,2,'KITCHEN_DENIED',2,'2023-02-08 20:15:02');
INSERT INTO orders (customer_id, restaurant_id, status, courier_id, timestamp) VALUES (2,2,'DELIVERY_COMPLETE',3,'2023-02-08 21:01:54');

INSERT INTO menu_items(restaurant_id, name, price, image, description) VALUES (1,'meat',200,'http://food-service.ru/img/meat.jpg', 'some description');
INSERT INTO menu_items(restaurant_id, name, price, image, description) VALUES (2,'vegetables',100,'http://food-service.ru/img/vegetables.jpg', 'some description');
INSERT INTO menu_items(restaurant_id, name, price, image, description) VALUES (2,'soup',150,'http://food-service.ru/img/soup.jpg', 'some description');

INSERT INTO order_items(order_id, menu_item_id, price, quantity) VALUES (1, 1, 200, 1);
INSERT INTO order_items(order_id, menu_item_id, price, quantity) VALUES (2, 2, 100, 5);
INSERT INTO order_items(order_id, menu_item_id, price, quantity) VALUES (3, 3, 150, 2);



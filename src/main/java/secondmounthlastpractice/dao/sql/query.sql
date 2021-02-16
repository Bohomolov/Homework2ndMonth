--добавление элементов в таблици
insert into market.products(name, description)
VALUES ('hummer', 'tools'),
       ('potato', 'food'),
       ('pen', 'chancellery'),
       ('table', 'furniture');

INSERT INTO market.customer(name)
VALUES ('Oleg'),
       ('Igor'),
       ('Irina'),
       ('Diana');

INSERT INTO market.order(customer_id)
VALUES (1),
       (2),
       (3),
       (4);

INSERT INTO market.order_product
VALUES (1, 2),
       (1, 1),
       (2, 2),
       (1, 1),
       (3, 3),
       (4, 4),
       (2, 1),
       (4, 1),
       (2, 4);


--запросы к таблицам
update market.products
set description = 'xz'
where id = (select c.id from market.customer c where c.name = 'Igor')

SELECT c.name as customer_name, p.name
from market.customer c
         inner join market."order" o on o.customer_id = c.id
         inner join market.order_product op on op.order_id = o.id
inner join market.products p on op.product_id = p.id
where p.name = 'hummer'

SELECT p.name as product_name, c.name as customer_name
from market.products p
         inner join market.order_product op on p.id = op.product_id
         inner join market."order" o on o.id = op.order_id
inner join market.customer c on o.customer_id = c.id
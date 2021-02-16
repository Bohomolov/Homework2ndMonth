--создание таблиц
create table market.customer
(
    id   serial not null,
    name varchar,
    primary key (id)
);
create table market."order"
(
    id          serial not null,
    customer_id int references market.customer (id),
    primary key (id)
);

create table market.products
(
    id          serial not null,
    name        varchar,
    description varchar,
    primary key (id)
);
create table market.order_product
(
    product_id int references market.products (id),
    order_id   int references market."order" (id)
);
create table market.customer_list
(
    id          serial not null,
    customer_id int references market.customer (id),
    primary key (id)
)
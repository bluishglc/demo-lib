DROP DATABASE IF EXISTS sharding_account;

CREATE DATABASE sharding_account;
USE sharding_account;

DROP TABLE IF EXISTS account;

create table account (
    id bigint(20) not null,
	name varchar(255) not null,
    constraint pk_account primary key (id)
);


DROP DATABASE IF EXISTS sharding_order_0;

CREATE DATABASE sharding_order_0;
USE sharding_order_0;

DROP TABLE IF EXISTS orders;

create table orders (
    id bigint(20) not null,
	name varchar(255) not null,
    constraint pk_orders primary key (id)
);


DROP DATABASE IF EXISTS sharding_order_1;

CREATE DATABASE sharding_order_1;
USE sharding_order_1;

DROP TABLE IF EXISTS orders;

create table orders (
    id bigint(20) not null,
	name varchar(255) not null,
    constraint pk_orders primary key (id)
);


DROP DATABASE IF EXISTS sharding_order_2;

CREATE DATABASE sharding_order_2;
USE sharding_order_2;

DROP TABLE IF EXISTS orders;

create table orders (
    id bigint(20) not null,
	name varchar(255) not null,
    constraint pk_orders primary key (id)
);


DROP DATABASE IF EXISTS sharding_order_3;

CREATE DATABASE sharding_order_3;
USE sharding_order_3;

DROP TABLE IF EXISTS orders;

create table orders (
    id bigint(20) not null,
	name varchar(255) not null,
    constraint pk_orders primary key (id)
);


DROP DATABASE IF EXISTS sharding_order_4;

CREATE DATABASE sharding_order_4;
USE sharding_order_4;

DROP TABLE IF EXISTS orders;

create table orders (
    id bigint(20) not null,
	name varchar(255) not null,
    constraint pk_orders primary key (id)
);

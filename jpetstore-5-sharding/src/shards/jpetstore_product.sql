DROP TABLE IF EXISTS INVENTORY;
DROP TABLE IF EXISTS ITEM;
DROP TABLE IF EXISTS PRODUCT;
DROP TABLE IF EXISTS CATEGORY;
DROP TABLE IF EXISTS SUPPLIER;

create table SUPPLIER (
    suppid int not null,
    name varchar(80) null,
    status varchar(2) not null,
    addr1 varchar(80) null,
    addr2 varchar(80) null,
    city varchar(80) null,
    state varchar(80) null,
    zip varchar(5) null,
    phone varchar(80) null,
    constraint pk_SUPPLIER primary key (suppid)
);

create table CATEGORY (
	catid int not null,
	name varchar(80) null,
	descn varchar(255) null,
	constraint pk_CATEGORY primary key (catid)
);

create table PRODUCT (
    productid int not null,
    category int not null,
    name varchar(80) null,
    descn varchar(255) null,
    constraint pk_PRODUCT primary key (productid),
        constraint fk_PRODUCT foreign key (category)
        references CATEGORY (catid)
);

create index productCat on PRODUCT (category);
create index productName on PRODUCT (name);

create table ITEM (
    itemid int not null,
    productid int not null,
    listprice decimal(10,2) null,
    unitcost decimal(10,2) null,
    supplier int null,
    status varchar(2) null,
    attr1 varchar(80) null,
    attr2 varchar(80) null,
    attr3 varchar(80) null,
    attr4 varchar(80) null,
    attr5 varchar(80) null,
    constraint pk_ITEM primary key (itemid),
        constraint fk_ITEM_1 foreign key (productid)
        references PRODUCT (productid),
        constraint fk_ITEM_2 foreign key (supplier)
        references SUPPLIER (suppid)
);

create index itemProd on ITEM (productid);

create table INVENTORY (
    itemid int not null,
    qty int not null,
    constraint pk_INVENTORY primary key (itemid)
);
DROP TABLE IF EXISTS ACCOUNT;
DROP TABLE IF EXISTS PROFILE;
DROP TABLE IF EXISTS BANNERDATA;

create table ACCOUNT (
	id int not null,
    username varchar(80) not null,
    password varchar(80)  not null,
    email varchar(80) not null,
    firstname varchar(80) not null,
    lastname varchar(80) not null,
    status varchar(2)  null,
    addr1 varchar(80) not null,
    addr2 varchar(40) null,
    city varchar(80) not  null,
    state varchar(80) not null,
    zip varchar(20) not null,
    country varchar(20) not null,
    phone varchar(80) not null,
    constraint pk_ACCOUNT primary key (id)
);

create table PROFILE (
    userid int not null,
    langpref varchar(80) not null,
    favcategory int,
    mylistopt bool,
    banneropt bool,
    constraint pk_PROFILE primary key (userid)
);

create table BANNERDATA (
    favcategory int not null,
    bannername varchar(255)  null,
    constraint pk_BANNERDATA primary key (favcategory)
);


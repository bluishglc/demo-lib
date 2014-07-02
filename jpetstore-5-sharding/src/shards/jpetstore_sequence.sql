DROP TABLE IF EXISTS SEQUENCE;

CREATE TABLE SEQUENCE
(
    name               varchar(30)  not null,
    nextid             int          not null,
    constraint pk_SEQUENCE primary key (name)
);
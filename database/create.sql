create table if not exists carts
(
    id       int auto_increment
        primary key,
    item_id  int null,
    quantity int not null,
    user_id  int null
);

create table if not exists category
(
    id          int auto_increment
        primary key,
    name        varchar(50)  not null,
    description varchar(200) not null,
    created_at  varchar(255) null,
    updated_at  varchar(255) null,
    constraint category_pk
        unique (name)
);

create table if not exists items
(
    delivery_price  int          null,
    discount_per    int          null,
    id              int auto_increment
        primary key,
    price           int          not null,
    stock           int          null,
    created_at      datetime(6)  null,
    updated_at      datetime(6)  null,
    child_category  varchar(25)  not null,
    parent_category varchar(25)  not null,
    selection       varchar(25)  not null,
    name            varchar(50)  not null,
    img_path        varchar(100) null,
    description     varchar(200) not null,
    img             longblob     null
);

create table if not exists orders
(
    id           int auto_increment
        primary key,
    user_id      int           null,
    created_at   datetime(6)   null,
    updated_at   datetime(6)   null,
    payment      varchar(10)   not null,
    card_number  varchar(16)   null,
    name         varchar(50)   not null,
    phone_number varchar(50)   not null,
    request      varchar(100)  not null,
    address1     varchar(500)  not null,
    address2     varchar(500)  not null,
    post_code    varchar(500)  not null,
    items        varchar(5000) not null
);

create table if not exists users
(
    id               int auto_increment
        primary key,
    created_at       datetime(6)  null,
    updated_at       datetime(6)  null,
    name             varchar(20)  not null,
    role             varchar(20)  null,
    email            varchar(50)  not null,
    login_id         varchar(50)  not null,
    phone_number     varchar(50)  not null,
    password         varchar(100) not null,
    delivery_address varchar(500) null
);


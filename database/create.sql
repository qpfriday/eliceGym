-- auto-generated definition
create table users
(
    id               int auto_increment
        primary key,
    name             varchar(20)  not null,
    email            varchar(50)  not null,
    password         varchar(100) not null,
    login_id         varchar(50)  not null,
    delivery_address varchar(500) null,
    role             varchar(10)  null,
    created_at       varchar(50)  null,
    updated_at       varchar(50)  null,
    constraint members_email_uindex
        unique (email)
);
-- auto-generated definition
create table carts
(
    id      int auto_increment
        primary key,
    user_id int not null,
    item_id int not null
);

-- auto-generated definition
create table items
(
    id              int auto_increment
        primary key,
    name            varchar(50)  not null,
    img_path        varchar(100) null,
    price           int          not null,
    discount_per    int          null,
    parent_category varchar(25)  not null,
    chile_category  varchar(25)  not null,
    selection       varchar(25)  null,
    description     varchar(200) not null,
    stock           int          not null,
    delivery_price  int          null,
    created_at      varchar(50)  null,
    updated_at      varchar(50)  null
);

-- auto-generated definition
create table orders
(
    id           int auto_increment
        primary key,
    user_id      int          not null,
    name         varchar(50)  not null,
    phone_number varchar(50)  not null,
    address      varchar(500) not null,
    request      varchar(100) not null,
    payment      varchar(10)  not null,
    card_number  varchar(16)  null,
    items        varchar(500) not null,
    created_at   varchar(50)  null,
    updated_at   varchar(50)  null
);



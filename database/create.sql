create table category
(
    id              int auto_increment
        primary key,
    name            varchar(50)  not null,
    description     varchar(200) not null,
    created_at      varchar(255) null,
    updated_at      varchar(255) null,
    constraint category_pk
        unique (name)
);

create table users
(
    id               int auto_increment
        primary key,
    name             varchar(20)  not null,
    email            varchar(50)  not null,
    password         varchar(100) not null,
    login_id         varchar(50)  not null,
    delivery_address varchar(500) null,
    role             varchar(20)  null,
    phone_number     varchar(50)  not null,
    created_at       varchar(50)  null,
    updated_at       varchar(50)  null,
    constraint members_email_uindex
        unique (email)
);

create table carts
(
    id      int auto_increment
        primary key,
    user_id int not null,
    item_id int not null
);

create table items
(
    id             int auto_increment
        primary key,
    name           varchar(50)  not null,
    img_path       varchar(100) null,
    price          int          not null,
    discount_per   int          null,
    selection      varchar(25)  null,
    description    varchar(200) not null,
    stock          int          not null,
    delivery_price int          null,
    created_at     datetime(6)  null,
    updated_at     datetime(6)  null,
    img            longblob     null,
    category_id    int          null,
    constraint fk_category
        foreign key (category_id) references category (id)
);

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

create table if not exists Ingredient(
    id varchar(4) not null,
    name varchar (25) not null,
    type varchar (10) not null
);

create table if not exists Taco(
    id identity,
    name varchar (50) not null,
    createdAt timestamp not null
);

create table if not exists Taco_Ingredients(
    taco bigint not null,
    ingredient varchar (4)
);

alter table Taco_Ingredients add foreign key (taco) references Taco(id);

alter table Taco_Ingredients add foreign key (ingredient) references Ingredient (id);

create table if not exists Taco_Order(
    id identity,
    deliveryName varchar (50) not null,
    deliveryStreet varchar (50) not null,
    deliveryCity varchar (50) not null,
    deliveryState varchar (2) not null,
    deliveryZip varchar (10) not null,
    ccNumber varchar (16) not null,
    ccExpiration varchar (5) not null,
    ccCVV varchar(3) not null,
    placedAt timestamp not null
);

create table if not exists Taco_Order_Tacos(
    tacoOrder bigint not null,
    taco bigint not null
);

alter table Taco_Order_Tacos add foreign key (tacoOrder) references Taco_Order (id);

alter table Taco_Order_Tacos add foreign key (taco) references Taco (id);

create table if not exists Users (
    username varchar (15) primary key,
    password varchar (50) not null,
    enabled smallint (1)
);

create table if not exists Authorities (
    id identity,
    username varchar (15) not null,
    authority varchar (20) not null
);

alter table Authorities add foreign key (username) references Users (username);

create table if not exists Groups (
    id identity,
    name varchar (20) not null
);

create table if not exists Group_Members(
    group_id bigint not null,
    username varchar (15) not null
);

alter table Group_Members add foreign key (group_id) references Groups (id);
alter table Group_Members add foreign key (username) references Users (username);

create table if not exists Group_Authorities(
    group_id bigint not null,
    name varchar (20) not null
);

alter table Group_Authorities add foreign key (group_id) references Groups (id);
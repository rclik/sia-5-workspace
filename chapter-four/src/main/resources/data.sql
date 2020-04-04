delete from Ingredient;
insert into Ingredient (id, name, type)
values ('FLTO', 'Flour Tortilla', 'WRAP');
insert into Ingredient (id, name, type)
values ('COTO', 'Corn Tortilla', 'WRAP');
insert into Ingredient (id, name, type)
values ('GRBF', 'Ground Beef', 'PROTEIN');
insert into Ingredient (id, name, type)
values ('CARN', 'Carnitas', 'PROTEIN');
insert into Ingredient (id, name, type)
values ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into Ingredient (id, name, type)
values ('LETC', 'Lettuce', 'VEGGIES');
insert into Ingredient (id, name, type)
values ('CHED', 'Cheddar', 'CHEESE');
insert into Ingredient (id, name, type)
values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into Ingredient (id, name, type)
values ('SLSA', 'Salsa', 'SAUCE');
insert into Ingredient (id, name, type)
values ('SRCR', 'Sour Cream', 'SAUCE');

delete from Users;
insert into Users (username, password, enabled) values ('admin', 'admin', 1);

delete from Authorities;
insert into Authorities (id, username, authority) values (1, 'admin', 'ADMIN_AUTHORITY');

delete from Groups;
insert into Groups (id, name) values ( 1, 'ADMIN_GROUP' );

delete from Group_Members;
insert into Group_Members (group_id, username) values ( 1, 'admin' );

delete from Group_Authorities;
insert into Group_Authorities (group_id, name) values (1, 'ADMIN_AUTHORITY');
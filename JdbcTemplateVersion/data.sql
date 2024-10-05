delete from Ingredient_Ref;
delete from Shawarma;
delete from Shawarma_Order;
delete from Ingredient;

insert into Ingredient(id, name, type)
    values ('ORLA', 'Origin Lavash', 'WRAP');
insert into Ingredient(id, name, type)
    values ('CHLA', 'Cheese Lavash', 'WRAP');
insert into Ingredient(id, name, type)
    values ('COLA', 'Corn Lavash', 'WRAP');
insert into Ingredient(id, name, type)
    values ('GRBF', 'Ground Beef', 'PROTEIN');
insert into Ingredient(id, name, type)
    values ('CHFI', 'Chicken Fillet', 'PROTEIN');
insert into Ingredient(id, name, type)
    values ('PORK', 'Pork', 'PROTEIN');
insert into Ingredient(id, name, type)
    values ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into Ingredient(id, name, type)
    values ('WHCA', 'White Cabbage', 'VEGGIES');
insert into Ingredient(id, name, type)
    values ('POTA', 'Potato', 'VEGGIES');
insert into Ingredient(id, name, type)
    values ('PICK', 'Pickle', 'VEGGIES');
insert into Ingredient(id, name, type)
    values ('FRCU', 'Fresh Cucumber', 'VEGGIES');
insert into Ingredient(id, name, type)
    values ('MAYO', 'Mayo', 'SAUCE');
insert into Ingredient(id, name, type)
    values ('KETC', 'Ketchup', 'SAUCE');




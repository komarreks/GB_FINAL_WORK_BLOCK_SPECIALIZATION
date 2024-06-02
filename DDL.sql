create database HumanFriends;

use HumanFriends;

create table animals(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name_class varchar(50) not null
);

insert into animals (name_class)
values
('pets'),
('pack animals');

create table dogs(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name varchar(50) not null,
    birth_date date,
    commands text,
    color varchar(50) not null,
    breed varchar(50) not null,
	home_maintenance_only boolean,
    decorative boolean,
    animal_class int,
    foreign key (animal_class) references animals(id) on update cascade on delete cascade
);

create table cats(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name varchar(50) not null,
    birth_date date,
    commands text,
    color varchar(50) not null,
    breed varchar(50) not null,
	home_maintenance_only boolean,
    standart_size boolean,
    animal_class int,
    foreign key (animal_class) references animals(id) on update cascade on delete cascade
);

create table hamsters(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name varchar(50) not null,
    birth_date date,
    commands text,
    color varchar(50) not null,
    breed varchar(50) not null,
	home_maintenance_only boolean,
    animal_class int,
    foreign key (animal_class) references animals(id) on update cascade on delete cascade
);

insert into dogs(name, birth_date, commands, color, breed, home_maintenance_only, decorative, animal_class)
values
('Шарик', '2005-02-13', 'сидеть лежать стоять', 'черный', 'сенбернар', false, false, 1),
('Тузик', '2010-05-06', 'сидеть лежать стоять голос', 'коричневый', 'лабрадор', false, false, 1),
('Пузя', '2020-06-02', '', 'бежевый', 'мопс', true, true, 1);

insert into cats(name, birth_date, commands, color, breed, home_maintenance_only, standart_size, animal_class)
values
('Маркиз', '2007-03-14', '', 'пепельный', 'мэйнкун', true, false, 1),
('Филипс', '2009-06-17', '', 'черный рыжый', 'бенгал', true, true, 1),
('Ася', '2013-08-22', 'мур', 'белый', 'сибирская', true, true, 1);

insert into hamsters(name, birth_date, commands, color, breed, home_maintenance_only, animal_class)
values
('Флоп', '2020-03-14', '', 'белый', 'сирийский', true, 1),
('Пупс', '2021-06-17', '', 'рыжый', 'сирийский', true, 1),
('Федор', '2024-02-22', '', 'белый', 'джунгарик', true, 1);

create table horses(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name varchar(50) not null,
    birth_date date,
    commands text,
    color varchar(50) not null,
    breed varchar(50) not null,
	maximumWeight int,
    purpose varchar(50),
    pony boolean,
    animal_class int,
    foreign key (animal_class) references animals(id) on update cascade on delete cascade
);

create table camels(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name varchar(50) not null,
    birth_date date,
    commands text,
    color varchar(50) not null,
    breed varchar(50) not null,
	maximumWeight int,
    purpose varchar(50),
    humps int,
    animal_class int,
    foreign key (animal_class) references animals(id) on update cascade on delete cascade
);

create table donkeys(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name varchar(50) not null,
    birth_date date,
    commands text,
    color varchar(50) not null,
    breed varchar(50) not null,
	maximumWeight int,
    purpose varchar(50),
    animal_class int,
    foreign key (animal_class) references animals(id) on update cascade on delete cascade
);

insert into horses(name, birth_date, commands, color, breed, maximumWeight, purpose, pony, animal_class)
values
('Быстрый', '2021-04-15', 'го тпру галс', 'вороной', 'черный арабский', 90, 'Скачки Племенное разведение', false, 2),
('Пробка', '2022-04-15', 'го тпру', 'белая в яблоках', 'сербская пони', 25, 'Развлечение детей в парке', true, 2),
('Каштанка', '2022-06-22', 'го тпру', 'рыжая', 'русская', 50, 'Перевозка телег', false, 2);

insert into camels(name, birth_date, commands, color, breed, maximumWeight, purpose, humps, animal_class)
values
('Араб', '2020-04-15', 'вниз вверх пошел стоп', 'желтый', 'дромадер', 120, 'Перевозка грузов', 1, 2),
('Крокан', '2022-06-01', 'вниз вверх пошел стоп', 'желтый', 'бактриан', 120, 'Перевозка грузов, равзлечение туристов', 2, 2),
('Грог', '2021-08-05', 'вниз вверх пошел стоп', 'желтый', 'дромадер', 120, 'равзлечение туристов', 2, 2);

insert into donkeys(name, birth_date, commands, color, breed, maximumWeight, purpose, animal_class)
values
('Пикша', '2020-04-15', 'пошел стоп', 'коричневый', 'пуату', 120, 'Перевозка грузов', 2),
('Белыш', '2022-06-01', 'пошел стоп', 'темно коричневый', 'каталонская порода', 120, 'Перевозка грузов, равзлечение туристов', 2),
('Махмуд', '2021-08-05', 'пошел стоп', 'серый', 'Миниатюрная средиземноморская порода', 120, 'равзлечение туристов', 2);

delete from camels where animal_class=2;

create table three_year_animals(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name varchar(50) not null,
    original_table varchar(50) not null,
    birth_date date,
    age_in_month int,
    animal_class int,
    foreign key (animal_class) references animals(id) on update cascade on delete cascade
);

insert into three_year_animals(name, original_table, birth_date, age_in_month, animal_class)
select
	name,
    'dogs' as original_table,
    birth_date,
    TIMESTAMPDIFF(month, birth_date, NOW()) as age_in_month,
    animal_class
from
	dogs
where TIMESTAMPDIFF(month, birth_date, NOW())<36

union

select
	name,
    'cats' as original_table,
    birth_date,
    TIMESTAMPDIFF(month, birth_date, NOW()) as age_in_month,
    animal_class
from
	cats
where TIMESTAMPDIFF(month, birth_date, NOW())<36

union

select
	name,
    'hamsters' as original_table,
    birth_date,
    TIMESTAMPDIFF(month, birth_date, NOW()) as age_in_month,
    animal_class
from
	hamsters
where TIMESTAMPDIFF(month, birth_date, NOW())<36

union

select
	name,
    'horses' as original_table,
    birth_date,
    TIMESTAMPDIFF(month, birth_date, NOW()) as age_in_month,
    animal_class
from
	horses
where TIMESTAMPDIFF(month, birth_date, NOW())<36

union

select
	name,
    'camels' as original_table,
    birth_date,
    TIMESTAMPDIFF(month, birth_date, NOW()) as age_in_month,
    animal_class
from
	camels
where TIMESTAMPDIFF(month, birth_date, NOW())<36

union

select
	name,
    'donkeys' as original_table,
    birth_date,
    TIMESTAMPDIFF(month, birth_date, NOW()) as age_in_month,
    animal_class
from
	donkeys
where TIMESTAMPDIFF(month, birth_date, NOW())<36;

create table pets(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name varchar(50) not null,
    type varchar(50) not null,
    birth_date date,
    commands text,
    animal_class int,
    foreign key (animal_class) references animals(id) on update cascade on delete cascade
);

create table pack_animals(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name varchar(50) not null,
    type varchar(50) not null,
    birth_date date,
    commands text,
    animal_class int,
    foreign key (animal_class) references animals(id) on update cascade on delete cascade
);

insert into pets(name, type, birth_date, commands, animal_class)
select
	name,
    'dogs' as type,
    birth_date,
    commands,
    animal_class
from
	dogs
union
select
	name,
    'cats' as type,
    birth_date,
    commands,
    animal_class
from
	cats
union
select
	name,
    'hamsters' as type,
    birth_date,
    commands,
    animal_class
from
	hamsters;

insert into pack_animals(name, type, birth_date, commands, animal_class)
select
	name,
    'horses' as type,
    birth_date,
    commands,
    animal_class
from
	horses
union
select
	name,
    'camels' as type,
    birth_date,
    commands,
    animal_class
from
	camels
union
select
	name,
    'donkeys' as type,
    birth_date,
    commands,
    animal_class
from
	donkeys
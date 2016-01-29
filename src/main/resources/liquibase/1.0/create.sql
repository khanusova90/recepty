--liquibase formatted sql

--changeset hanuska1:create-1
create table RECIPE(
	ID_RECIPE bigint(19) not null auto_increment,
	RATING float(4,4),
	RATE_COUNT int ,
	PREPARATION longtext not null,
	primary key(ID_RECIPE)
);

create table RECIPE_MEAL(
	ID_RECIPE_MEAL bigint(19) not null auto_increment,
	ID_RECIPE bigint(19) not null,
	MEAL varchar(255) not null,
	primary key (ID_RECIPE_MEAL),
	constraint fk_recipe_meals foreign key fk_recipe_meals(ID_RECIPE) references RECIPE(ID_RECIPE)
);

create table INGREDIENT(
	ID_INGREDIENT bigint(19) not null auto_increment,
	INGREDIENT_NAME varchar(255) not null,
	primary key (ID_INGREDIENT)
);

create table RECIPE_INGREDIENT(
	ID_RECIPE_INGREDIENT bigint(19) not null auto_increment,
	ID_RECIPE bigint(19),
	ID_INGREDIENT bigint(19),
	AMOUNT decimal(5,2),
	UNIT varchar(5),
	primary key(ID_RECIPE_INGREDIENT),
	constraint fk_recipe foreign key fk_recipe(ID_RECIPE) references RECIPE(ID_RECIPE),
	constraint fk_ingredient foreign key fk_ingredient(ID_INGREDIENT) references INGREDIENT(ID_INGREDIENT)
);

--changeset hanuska1:create-2
drop table if exists app_user;
drop table if exists user_role;

create table APP_USER(
	ID_APP_USER bigint(19) not null auto_increment,
	USERNAME varchar(255) not null,
	PASSWORD varchar(255) not null,
	primary key (ID_APP_USER)
);

create table USER_ROLE(
	ID_USER_ROLE bigint(19) not null auto_increment,
	ROLE varchar(255) not null,
	ID_APP_USER bigint(19) not null,
	primary key(ID_USER_ROLE),
	constraint fk_user_roles foreign key fk_user_roles(ID_APP_USER) references APP_USER(ID_APP_USER)
);

--changeset hanuska1:create-3
alter table RECIPE add ID_APP_USER bigint(19) not null;
alter table RECIPE add constraint fk_recipe_user foreign key fk_recipe_user(ID_APP_USER) references APP_USER(ID_APP_USER);
alter table APP_USER add EMAIL varchar(255);
alter table APP_USER add RATING int;

--changeset hanuska1:create-4
create table FAVORITE_RECIPES(
	ID_FAVORITE_RECIPE bigint(19) not null auto_increment,
	ID_APP_USER bigint(19) not null,
	ID_RECIPE bigint(19) not null,
	primary key(ID_FAVORITE_RECIPE),
	constraint fk_favorite_user foreign key fk_favorite_user(ID_APP_USER) references APP_USER(ID_APP_USER),
	constraint fk_favorite_recipe foreign key fk_favorite_recipe(ID_RECIPE) references RECIPE(ID_RECIPE)
);

--changeset hanuska1:create-5
alter table recipe add NAME varchar(255) not null;

--changeset hanuska1:create-6
alter table recipe add DESCRIPTION varchar(255);

--changeset hanuska1:create-7
create table PHOTO(
	ID_PHOTO bigint(19) not null auto_increment,
	PHOTO blob not null,
	ID_RECIPE bigint(19) not null,
	primary key(ID_PHOTO),
	constraint fk_photo_recipe foreign key fk_photo_recipe(ID_RECIPE) references RECIPE(ID_RECIPE)
);

--changeset hanuska1:create-8
alter table photo add ACTIVE bit not null,
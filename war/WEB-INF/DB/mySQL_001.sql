 create database cloudservice;
 
 create table registration(
	id varchar(20) not null,
	cloudvalue int not null,
	name varchar(20) not null,
	lastname varchar(20) not null,
	email varchar(25) not null,
	password varchar(20) not null,
	street varchar(20) not null,
	house varchar(20) not null,
	post varchar(20) not null,
	city varchar(20) not null,
	comment varchar(100)
);
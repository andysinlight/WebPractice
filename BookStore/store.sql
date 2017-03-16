create database bookstore;
use bookstore;
create table category(
	id varchar(100) not null primary key,
	name varchar(100) not null,
	des varchar(255)
);
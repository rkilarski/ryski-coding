-- create and select the database
DROP DATABASE IF EXISTS chickenrice;
CREATE DATABASE chickenrice;
USE chickenrice;  -- MySQL command



create table Person
	{
	person_id int(38) unsigned primary key auto_increment,
	first_name varchar(25) not null,
	middle_name varchar(25) null,
	last_name varchar(25) not null,
	email_address varchar(255) not null,
	address_line_1 varchar(255),
	address_line_2 varchar(255),
	city varchar(25),
	state varchar(2),
	zip varchar(10),
	telephone_1 varchar(10),
	is_staff varchar(2) 
	} engine=INNODB
	
	
	-- create the users and grant priveleges to those users
	GRANT SELECT, INSERT, DELETE, UPDATE
	ON chickenrice.*
	TO webuser@localhost
	IDENTIFIED BY 'doyoufeellikechickentonight';

	GRANT SELECT
	ON products
	TO mgs_tester@localhost
	IDENTIFIED BY 'pa55word';
	
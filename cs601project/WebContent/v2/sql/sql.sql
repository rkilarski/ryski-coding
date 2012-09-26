create table Person
	{
	person_id int(38) unsigned primary key auto_increment,
	first_name varchar(25) not null,
	gender char(1) not null,
	middle_name varchar(25) null,
	last_name varchar(25) not null
	} engine=INNODB
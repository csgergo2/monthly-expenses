CREATE TABLE PRIO_GROUP (
	id int(100) AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) not null unique,
    prio int(5) not null,
    color VARCHAR(7) not null,
    text_color VARCHAR(7) not null
);
ALTER TABLE PRIO_GROUP ADD COLUMN text_color varchar(7) not null;
ALTER TABLE PRIO_GROUP ADD UNIQUE (name);
drop table prio_group;

INSERT INTO PRIO_GROUP (name, prio, color, text_color) values ("Kötelező", 1, "#fa0000", "#000000");
INSERT INTO PRIO_GROUP (name, prio, color, text_color) values ("Havi költség", 2, "#e0a902", "#000000");
INSERT INTO PRIO_GROUP (name, prio, color, text_color) values ("Becsúszott", 3, "#00e0b0", "#000000");
INSERT INTO PRIO_GROUP (name, prio, color, text_color) values ("Bevétel", 100, "#00fa36", "#000000");

select * from prio_group;
delete from prio_group;

CREATE TABLE TAG (
	id int(100) AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100),
    prio int(5) not null,
    prio_group int(100),
    constraint tag_on_prio_group foreign key (prio_group) references PRIO_GROUP(id)
);
delete from TAG;
drop table tag;

INSERT INTO TAG (name, prio, prio_group) values ("KP Felvétel", 1, 2);
INSERT INTO TAG (name, prio, prio_group) values ("Rezsi", 2, 1);
INSERT INTO TAG (name, prio, prio_group) values ("Auchan", 1, 2);
INSERT INTO TAG (name, prio, prio_group) values ("Benzin", 2, 2);
INSERT INTO TAG (name, prio, prio_group) values ("BKV", 3, 2);
INSERT INTO TAG (name, prio, prio_group) values ("KV", 1, 3);
INSERT INTO TAG (name, prio, prio_group) values ("Fizetés", 1, 4);
INSERT INTO TAG (name, prio, prio_group) values ("Lottó", 2, 4);
update tag set prio_group = 3;

delete from tag where name like "test";
select * from TAG;
commit;

CREATE TABLE ITEM (
	id int(100) AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) not null,
    tag int(100) not null,
    amount int(10) not null,
    is_income boolean default false,
    is_end_month boolean default false,
    date date not null,
    year int(4) not null,
    month VARCHAR(50) not null,
    constraint item_on_tag foreign key (tag) references Tag(id)
);
delete from ITEM;
drop table ITEM;
SET SQL_SAFE_UPDATES = 0;
SET SQL_SAFE_UPDATES = 1;
commit;

INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Auchan", 3, 10, '2019-3-01', 2019, 'FEBRUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Auchan", 3, 10, '2020-3-01', 2020, 'FEBRUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Spar", 3, 15, '2020-3-01', 2020, 'FEBRUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Tesco", 3, 11, '2020-3-02', 2020, 'FEBRUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Benzin", 4, 20, '2020-3-03', 2020, 'FEBRUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Auchan", 3, 15, '2020-3-04', 2020, 'MARCH');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Spar", 3, 15, '2020-3-04', 2020, 'MARCH');
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income, is_end_month) values ("Fizetés", 7, 200, '2020-3-04', 2020, 'MARCH', true, true);
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("KP Felvétel", 1, 100, '2020-3-04', 2020, 'MARCH');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Auchan", 3, 12, '2020-3-04', 2020, 'MARCH');

select * from item;

select year from item group by year;
select i.year from item i group by i.year;

select t.id, t.name, t.prio, t.prio_group, t.prio_group_id from item i join tag t on i.tag = t.id group by t.name order by count(t.id) desc;


select a.item_name from (
select i.name item_name from item i
left join tag t on i.tag = t.id where i.name = null
union all
select t.name tag_name from item i
right join tag t on i.tag = t.id) a
group by (a.item_name)
order by count(a.item_name) desc;

select * from item i where 
	(null IS NULL OR (null IS NOT NULL && i.year = null)) AND
    ("5" IS NULL OR ("5" IS NOT NULL && i.name LIKE "5%"));



CREATE TABLE MONTH_COMMENT (
	id int(100) AUTO_INCREMENT PRIMARY KEY,
    month VARCHAR(50) not null,
    comment VARCHAR(5000) not null
);
select * from month_comment;
delete from month_comment;
drop table month_comment;

CREATE TABLE TAG_COMMENT (
	id int(100) AUTO_INCREMENT PRIMARY KEY,
	comment VARCHAR(5000) not null,
    tag int(100) not null
);
select * from TAG_COMMENT;
delete from TAG_COMMENT;
drop table tag_comment;


CREATE TABLE CUSTOM_COUNTER (
	id int(100) auto_increment primary key,
    name varchar(100) not null,
    data TEXT
);

select * from custom_counter;
delete from custom_counter;
drop table custom_counter;

CREATE TABLE CUSTOM_COUNTER_ITEM (
	custom_counter_id int(100) not null,
    item_id int(100) not null,
    primary key (custom_counter_id, item_id),
    foreign key (custom_counter_id) references CUSTOM_COUNTER(id),
    foreign key (item_id) references item(id)
)ENGINE=INNODB;
drop table CUSTOM_COUNTER_ITEM;
select * from custom_counter_item;
select * from item;
select * from custom_counter;
insert into custom_counter_item (custom_counter_id, item_id) values (1, 20);
insert into custom_counter_item (custom_counter_id, item_id) values (1, 21);
insert into custom_counter_item (custom_counter_id, item_id) values (1, 25);
insert into custom_counter_item (custom_counter_id, item_id) values (2, 22);
insert into custom_counter_item (custom_counter_id, item_id) values (2, 24);

select i.* from custom_counter_item cci left join item i on i.id = cci.item_id where cci.custom_counter_id = 2;

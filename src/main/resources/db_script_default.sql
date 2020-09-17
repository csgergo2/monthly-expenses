CREATE TABLE TAG (
	name VARCHAR(100) PRIMARY KEY,
    prio int(5)
);
delete from TAG;

INSERT INTO TAG (name, prio) values ("KP Felvétel", 1);
INSERT INTO TAG (name, prio) values ("Rezsi", 2);
INSERT INTO TAG (name, prio) values ("Auchan", 1);
INSERT INTO TAG (name, prio) values ("Benzin", 2);
INSERT INTO TAG (name, prio) values ("BKV", 3);
INSERT INTO TAG (name, prio) values ("KV", 1);
INSERT INTO TAG (name, prio) values ("Fizetés", 1);

select * from TAG;


CREATE TABLE ITEM (
	id int(100) AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100),
    tag VARCHAR(100),
    amount int(10),
    is_income boolean default false,
    is_new_month boolean default false,
    date date,
    year int(4),
    month int(2),
    constraint item_on_tag foreign key (tag) references Tag(name)
);
delete from ITEM;
drop table ITEM;
commit;

INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Auchan", "Auchan", 10, '2020-3-01', 2020, 2);
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Spar", "Auchan", 15, '2020-3-01', 2020, 2);
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Tesco", "Auchan", 11, '2020-3-02', 2020, 2);
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Benzin", "Benzin", 20, '2020-3-03', 2020, 2);
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Auchan", "Auchan", 15, '2020-3-04', 2020, 3);
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Spar", "Auchan", 15, '2020-3-04', 2020, 3);
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income, is_new_month) values ("Fizetés", "Fizetés", 200, '2020-3-04', 2020, 3, true, true);
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("KP Felvétel", "KP Felvétel", 100, '2020-3-04', 2020, 3);
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Auchan", "Auchan", 12, '2020-3-04', 2020, 3);

select * from item;
CREATE TABLE TAG (
	name VARCHAR(100) PRIMARY KEY,
    prio int(5)
);

INSERT INTO TAG (name, prio) values ("KP Felvétel", 1);
INSERT INTO TAG (name, prio) values ("Rezsi", 2);
INSERT INTO TAG (name, prio) values ("Auchan", 1);
INSERT INTO TAG (name, prio) values ("Benzin", 2);
INSERT INTO TAG (name, prio) values ("BKV", 3);
INSERT INTO TAG (name, prio) values ("KV", 1);

select * from TAG;


CREATE TABLE ITEM (
	name VARCHAR(100) PRIMARY KEY,
    tag VARCHAR(100),
    amount int(10),
    is_income boolean default false,
    is_end_month boolean default false,
    date date,
    constraint item_on_tag foreign key (tag) references Tag(name)
);

INSERT INTO ITEM (name, tag, amount, is_income, is_end_month, date) values ("Auchan", "Auchan");
INSERT INTO ITEM (name, tag, ) values ("Spar", "Auchan");
INSERT INTO ITEM (name, tag) values ("Benzin", "Benzin");
INSERT INTO ITEM (name, tag) values ("KP Felvétel", "KP Felvétel");

select * from item;

drop table item;
CREATE TABLE PRIO_GROUP ( UPDATE!!!!
	id int(100) AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) not null unique,
    prio int(5) not null,
    color VARCHAR(7) not null,
    text_color VARCHAR(7) not null
);
CREATE TABLE TAG (
	id int(100) AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100),
    prio int(5) not null,
    prio_group int(100),
    constraint tag_on_prio_group foreign key (prio_group) references PRIO_GROUP(id)
);
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
CREATE TABLE MONTH_COMMENT (
	id int(100) AUTO_INCREMENT PRIMARY KEY,
    month VARCHAR(50) not null,
    comment VARCHAR(5000) not null
);CREATE TABLE TAG_COMMENT (
	id int(100) AUTO_INCREMENT PRIMARY KEY,
	comment VARCHAR(5000) not null,
    tag int(100) not null
);
CREATE TABLE CUSTOM_COUNTER (
	id int(100) auto_increment primary key,
    name varchar(100) not null,
    data TEXT
);
CREATE TABLE CUSTOM_COUNTER_ITEM (
	custom_counter_id int(100) not null,
    item_id int(100) not null,
    primary key (custom_counter_id, item_id),
    foreign key (custom_counter_id) references CUSTOM_COUNTER(id),
    foreign key (item_id) references item(id)
)ENGINE=INNODB;
INSERT INTO TAG (name, prio, prio_group) values ("KP Felvétel", 1, 1);
INSERT INTO TAG (name, prio, prio_group) values ("Rezsi", 2, 1);
INSERT INTO TAG (name, prio, prio_group) values ("Auchan", 1, 2);
INSERT INTO TAG (name, prio, prio_group) values ("Benzin", 2, 2);
INSERT INTO TAG (name, prio, prio_group) values ("BKV", 3, 2);
INSERT INTO TAG (name, prio, prio_group) values ("KV", 1, 3);
INSERT INTO TAG (name, prio, prio_group) values ("Fizetés", 1, 4);
INSERT INTO TAG (name, prio, prio_group) values ("Lottó", 2, 4);

DELETE FROM ITEM;
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Tesco", 3, 10, '2018-12-15', 2018, 'DECEMBER');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Spar", 3, 10, '2018-12-24', 2018, 'DECEMBER');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Benzin", 4, 20, '2018-12-30', 2018, 'DECEMBER');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Auchan", 3, 10, '2019-1-01', 2018, 'DECEMBER');
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income, is_end_month) values ("Fizetés", 7, 200, '2019-1-07', 2018, 'DECEMBER', true, true);

INSERT INTO ITEM (name, tag, amount, date, year, month) values ("KP Felvétel", 1, 100, '2019-1-08', 2019, 'JANUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Tesco", 3, 10, '2019-1-09', 2019, 'JANUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Spar", 3, 10, '2019-1-15', 2019, 'JANUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income) values ("5-ös lottó", 8, 20, '2019-1-18', 2019, 'JANUARY', true);
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Benzin", 4, 20, '2019-1-27', 2019, 'JANUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Auchan", 3, 10, '2019-2-02', 2019, 'JANUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income, is_end_month) values ("Fizetés", 7, 200, '2019-2-06', 2019, 'JANUARY', true, true);

INSERT INTO ITEM (name, tag, amount, date, year, month) values ("KP Felvétel", 1, 100, '2019-2-08', 2019, 'FEBRUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Rezsi", 2, 10, '2019-2-09', 2019, 'FEBRUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Spar", 3, 10, '2019-2-15', 2019, 'FEBRUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income) values ("5-ös lottó", 8, 20, '2019-2-18', 2019, 'FEBRUARY', true);
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Benzin", 4, 20, '2019-2-27', 2019, 'FEBRUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Auchan", 3, 10, '2019-3-02', 2019, 'FEBRUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income, is_end_month) values ("Fizetés", 7, 200, '2019-3-06', 2019, 'FEBRUARY', true, true);

INSERT INTO ITEM (name, tag, amount, date, year, month) values ("KP Felvétel", 1, 100, '2019-3-08', 2019, 'MARCH');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Tesco", 3, 10, '2019-3-09', 2019, 'MARCH');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Spar", 3, 10, '2019-3-15', 2019, 'MARCH');
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income) values ("5-ös lottó", 8, 20, '2019-3-18', 2019, 'MARCH', true);
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Benzin", 4, 20, '2019-3-27', 2019, 'MARCH');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Auchan", 3, 10, '2019-3-02', 2019, 'MARCH');
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income, is_end_month) values ("Fizetés", 7, 200, '2019-12-07', 2019, 'MARCH', true, true);




INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Rezsi", 2, 10, '2019-12-15', 2019, 'DECEMBER');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Spar", 3, 10, '2019-12-24', 2019, 'DECEMBER');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Benzin", 4, 20, '2019-12-30', 2019, 'DECEMBER');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Auchan", 3, 10, '2020-1-01', 2019, 'DECEMBER');
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income, is_end_month) values ("Fizetés", 7, 200, '2020-1-07', 2020, 'DECEMBER', true, true);

INSERT INTO ITEM (name, tag, amount, date, year, month) values ("KP Felvétel", 1, 100, '2020-1-08', 2020, 'JANUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Rezsi", 2, 10, '2020-1-09', 2020, 'JANUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Spar", 3, 10, '2020-1-15', 2020, 'JANUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income) values ("5-ös lottó", 8, 20, '2020-1-18', 2020, 'JANUARY', true);
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Benzin", 4, 20, '2020-1-27', 2020, 'JANUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Auchan", 3, 10, '2020-2-02', 2020, 'JANUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income, is_end_month) values ("Fizetés", 7, 200, '2020-2-06', 2020, 'JANUARY', true, true);

INSERT INTO ITEM (name, tag, amount, date, year, month) values ("KP Felvétel", 1, 100, '2020-2-08', 2020, 'FEBRUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Rezsi", 2, 10, '2020-2-09', 2020, 'FEBRUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Spar", 3, 10, '2020-2-15', 2020, 'FEBRUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income) values ("5-ös lottó", 8, 20, '2020-2-18', 2020, 'FEBRUARY', true);
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Benzin", 4, 20, '2020-2-27', 2020, 'FEBRUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Auchan", 3, 10, '2020-3-02', 2020, 'FEBRUARY');
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income, is_end_month) values ("Fizetés", 7, 200, '2020-3-06', 2020, 'FEBRUARY', true, true);

INSERT INTO ITEM (name, tag, amount, date, year, month) values ("KP Felvétel", 1, 100, '2020-3-08', 2020, 'MARCH');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Rezsi", 2, 10, '2020-3-09', 2020, 'MARCH');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Spar", 3, 10, '2020-3-15', 2020, 'MARCH');
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income) values ("5-ös lottó", 8, 20, '2020-3-18', 2020, 'MARCH', true);
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Benzin", 4, 20, '2020-3-27', 2020, 'MARCH');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Auchan", 3, 10, '2020-4-02', 2020, 'MARCH');
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income, is_end_month) values ("Fizetés", 7, 200, '2020-6-30', 2020, 'MARCH', true, true);



INSERT INTO ITEM (name, tag, amount, date, year, month) values ("KP Felvétel", 1, 100, '2020-7-08', 2020, 'JULE');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Rezsi", 2, 10, '2020-7-09', 2020, 'JULE');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Spar", 3, 10, '2020-7-15', 2020, 'JULE');
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income) values ("5-ös lottó", 8, 20, '2020-7-18', 2020, 'JULE', true);
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Benzin", 4, 20, '2020-7-27', 2020, 'JULE');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Auchan", 3, 10, '2020-7-31', 2020, 'JULE');
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income, is_end_month) values ("Fizetés", 7, 200, '2020-7-31', 2020, 'JULE', true, true);

INSERT INTO ITEM (name, tag, amount, date, year, month) values ("KP Felvétel", 1, 100, '2020-7-31', 2020, 'AUGUST');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Rezsi", 2, 10, '2020-7-31', 2020, 'AUGUST');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Spar", 3, 10, '2020-8-15', 2020, 'AUGUST');
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income) values ("5-ös lottó", 8, 20, '2020-8-18', 2020, 'AUGUST', true);
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Benzin", 4, 20, '2020-8-27', 2020, 'AUGUST');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Auchan", 3, 10, '2020-8-31', 2020, 'AUGUST');
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income, is_end_month) values ("Fizetés", 7, 200, '2020-8-31', 2020, 'AUGUST', true, true);

INSERT INTO ITEM (name, tag, amount, date, year, month) values ("KP Felvétel", 1, 100, '2020-8-31', 2020, 'SEPTEMBER');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Rezsi", 2, 10, '2020-9-09', 2020, 'SEPTEMBER');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Tesco", 3, 10, '2020-9-10', 2020, 'SEPTEMBER');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Tesco", 3, 10, '2020-9-11', 2020, 'SEPTEMBER');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Tesco", 3, 10, '2020-9-11', 2020, 'SEPTEMBER');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Tesco", 3, 10, '2020-9-12', 2020, 'SEPTEMBER');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Spar", 3, 10, '2020-9-15', 2020, 'SEPTEMBER');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Spar", 3, 10, '2020-9-16', 2020, 'SEPTEMBER');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Spar", 3, 10, '2020-9-17', 2020, 'SEPTEMBER');
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income) values ("5-ös lottó", 8, 20, '2020-9-18', 2020, 'SEPTEMBER', true);
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Benzin", 4, 20, '2020-9-27', 2020, 'SEPTEMBER');
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income, is_end_month) values ("Fizetés", 7, 200, '2020-9-30', 2020, 'SEPTEMBER', true, true);

INSERT INTO ITEM (name, tag, amount, date, year, month) values ("KP Felvétel", 1, 100, '2020-9-30', 2020, 'OCTOBER');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Rezsi", 2, 10, '2020-10-09', 2020, 'OCTOBER');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Tesco", 3, 10, '2020-10-10', 2020, 'OCTOBER');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Tesco", 3, 10, '2020-10-11', 2020, 'OCTOBER');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Tesco", 3, 10, '2020-10-11', 2020, 'OCTOBER');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Tesco", 3, 10, '2020-10-12', 2020, 'OCTOBER');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Spar", 3, 10, '2020-10-15', 2020, 'OCTOBER');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Spar", 3, 10, '2020-10-16', 2020, 'OCTOBER');
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Spar", 3, 10, '2020-10-17', 2020, 'OCTOBER');
INSERT INTO ITEM (name, tag, amount, date, year, month, is_income) values ("5-ös lottó", 8, 20, '2020-10-18', 2020, 'OCTOBER', true);
INSERT INTO ITEM (name, tag, amount, date, year, month) values ("Benzin", 4, 20, '2020-10-27', 2020, 'OCTOBER');
COMMIT;
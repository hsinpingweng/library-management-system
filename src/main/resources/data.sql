insert into category (name) values('Romance');
insert into category (name) values('Fiction');
insert into publisher (name) values('Publisher A');
insert into publisher (name) values('Publisher B');
insert into publisher (name) values('Publisher C');
insert into author (name, description) values('Tom', 'best writer');
insert into author (name, description) values('Tat', 'Taiwanese writer');
insert into book (name, description, isbn, author_id, category_id, publisher_id) values('Book A', 'Description A', '0000000000000', 1, 1, 1);
insert into book (name, description, isbn, author_id, category_id, publisher_id) values('Book B', 'Description B', '1111111111111', 2, 2, 2);
INSERT INTO users
(id, login, first_name, last_name, patronymic, gender, dt_deleted, phone, email)
VALUES(13, 'peter','Петр', 'Петров', 'Петрович', 'MALE', null, '79561234388', 'peter@mail.ru');

INSERT INTO users
(id, login, first_name, last_name, patronymic, gender, dt_deleted, phone, email)
VALUES(14, 'foma','Фома', 'Фомин', 'Фомич', 'MALE', null, '79191234377', 'foma@mail.ru');

INSERT INTO user_contact_info (id, user_id, type, value)
VALUES(100, 13, 'telegram', '@peter');

INSERT INTO user_contact_info (id, user_id, type, value)
VALUES(200, 14, 'telegram', '@foma');

insert into subscription (creator_user_id, "id", subscriber_user_id)
values (13, 100, 14);

insert into subscription (creator_user_id, "id", subscriber_user_id)
values (14, 101, 13);

insert into news (id, creator_user_id, subject, dt_create, text)
values('0de9930e-63b4-49e3-bb03-21f83ab4b56f', 13, 'test subject', '2024-01-25 00:00:00', 'my news is good');

insert into news (id, creator_user_id, subject, dt_create, text)
values('b3e2ac6d-1225-46b8-84d1-02301b4d131f', 14, 'test subject', '2024-01-24 00:00:00', 'test news');
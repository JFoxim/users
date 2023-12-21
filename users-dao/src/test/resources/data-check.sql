INSERT INTO user_contact
(id, email, messenger, phone, address)
VALUES('04ff9c70-7cc4-4709-9803-8d6051482b22', 'peter@mail.ru', '@PeterMessager', '79231234567', null);

INSERT INTO user_contact
(id, email, messenger, phone, address)
VALUES('fa657154-624b-45a9-b4e2-d7e76a6dbd11', 'foma@mail.ru', '@FomaMessager', '79561234388', null);

INSERT INTO users
(id, login, first_name, last_name, patronymic, gender, contact_id, dt_deleted)
VALUES(13, 'peter','Петр', 'Петров', 'Петрович', 'М', '04ff9c70-7cc4-4709-9803-8d6051482b22', null);

INSERT INTO users
(id, login, first_name, last_name, patronymic, gender, contact_id, dt_deleted)
VALUES(14, 'foma','Фома', 'Фомин', 'Фомич', 'М', 'fa657154-624b-45a9-b4e2-d7e76a6dbd11', null);

insert into subscription (creator_user_id, "id", subscriber_user_id)
values (13, 100, 14);
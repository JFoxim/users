INSERT INTO user_contact
(id, email, messenger, phone, address)
VALUES('04ff9c70-7cc4-4709-9803-8d6051482b22', 'peter@mail.ru', '@testMessager', '79231234567', null);


INSERT INTO users
(id, login, first_name, last_name, patronymic, gender, contact_id, dt_deleted)
VALUES(13, 'peter','Петр', 'Петров', 'Петрович', 'М', '04ff9c70-7cc4-4709-9803-8d6051482b22', null);
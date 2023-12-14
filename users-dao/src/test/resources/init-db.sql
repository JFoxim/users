CREATE SCHEMA IF NOT EXISTS users;

INSERT INTO user_contact
(id, email, messenger, phone, address)
VALUES('c087ccac-3cfa-4e83-9d66-fde949a48f39', 'ivan@mail.ru', '@testMessager', '79231234567', null);


INSERT INTO users
(id, login, first_name, last_name, patronymic, gender, contact_id, dt_deleted)
VALUES(10, 'ivan','Иван', 'Иванов', 'Иванович', 'М', 'c087ccac-3cfa-4e83-9d66-fde949a48f39', null);

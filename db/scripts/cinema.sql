create table movies (
id serial primary key,
name varchar(200)
);

insert into movies(name) values('10:00 Веном 2');
insert into movies(name) values('12:00 Не время умирать');
insert into movies(name) values('14:00 МУЛЬТ в кино');
insert into movies(name) values('17:00 Веном 2');
insert into movies(name) values('20:00 Не время умирать');
insert into movies(name) values('22:00 Лощина мертвецов');

create TABLE IF NOT EXISTS users (
id SERIAL PRIMARY KEY,
name varchar(200),
email varchar(200) unique,
password varchar(200),
phone varchar(200)
);

CREATE TABLE ticket (
id SERIAL PRIMARY KEY,
row INT NOT NULL,
cell INT NOT NULL,
users_id INT NOT NULL REFERENCES users(id),
movies_id INT NOT NULL REFERENCES movies(id),
CONSTRAINT uniqs UNIQUE (movies_id, row, cell)
);
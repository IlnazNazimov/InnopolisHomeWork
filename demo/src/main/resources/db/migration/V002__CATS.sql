CREATE TABLE cats (
    id SERIAL primary key,
    name varchar(20) unique,
    color varchar (10),
    paws_count BIGINT default 4
);

INSERT into cats (name, color, paws_count)
VALUES ('Barsik', 'RED', 3),
('Vasya', 'BLACK', 4),
('Puska', 'BLUE', 5);
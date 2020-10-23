CREATE TABLE users (
    id SERIAL primary key,
    name varchar(20) unique,
    password VARCHAR(255)
);

INSERT INTO users (name, password)
VALUES ('user', '$2y$05$TlsdUPcjojVA0AUe9JlSTOaEWxFss6zzmJLcjfrQoQikS0yR6h.gq')
--Login:user
--password:user
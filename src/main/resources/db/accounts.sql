<---Создание таблицы--->
CREATE TABLE accounts(
    id INT NOT NULL PRIMARY KEY,
    amount INT NOT NULL
);
<---Заполнение таблицы--->
INSERT INTO accounts(id, amount)
    VALUES (1, 0), (2, 0), (3, 0), (4, 0), (5, 0), (6, 0), (7, 0), (8, 0);
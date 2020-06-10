----====================================================
----INSERTS TO DATA AT TABLES DEFAULT--
----====================================================
BEGIN TRANSACTION;
INSERT INTO customer (id, name, email) VALUES
(1, 'Mariazinha', 'mariazinha@email.com'),
(2, 'Joãozinho', 'joaozinho@email.com');
COMMIT;
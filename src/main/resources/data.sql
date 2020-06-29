------====================================================
------INSERTS TO DATA AT TABLES DEFAULT--
------====================================================
BEGIN TRANSACTION;
INSERT INTO SALARY (ID, INIT_SALARY, FINISH_SALARY, PERCENT)
VALUES
(1, 1000, 2000, 5),
(2, 2001, 3000, 10),
(3, 3001, 4000, 15),
(4, 4001, 5000, 20),
(5, 5001, 6000, 25),
(6, 6001, 7000, 30),
(7, 7001, 8000, 35),
(8, 8001, 9000, 40);
COMMIT;

BEGIN TRANSACTION;
INSERT INTO AGE (ID, AGE, PERCENT)
VALUES
(1, 80, 20),
(2, 50, 70),
(3, 30, 90),
(4, 20, 100);
COMMIT;
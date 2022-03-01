INSERT INTO USER (Name, DOB) VALUES 
("Alice Allison", "1988-09-27 12:00:00"),
("Bailey Brown", "1991-10-13 12:00:00"),
("Charles Charleson", "1975-03-21 12:00:00"),
("Dane Danowsky", "1982-04-08 12:00:00");

INSERT INTO GOAL (UserId, Totalamount, Currentamount, Description) VALUES 
(1, 3000, 500, "Vacation"),
(2, 5000, 5000, "Emergency"),
(4, 50000, 100, "Vehicle"),
(1, 1000000, 45000, "Retirement");

INSERT INTO CATEGORY (UserId, Title, Description) VALUES 
(1, "Fuel", "Gasoline, diesel, charging"),
(1, "Entertainment", "Movies, activities, sports"),
(1, "Food", "Restataunts, groceries, delivery"),
(2, "Other", "Purchases, investments, transfers"),
(3, "Food", "Restataunts, groceries, delivery"),
(4, "Other", "Purchases, investments, transfers"),
(2, "Food", "Restataunts, groceries, delivery"),
(4, "Other", "Purchases, investments, transfers"),
(1, "Housing", "Rent, utilities"),
(3, "Housing", "Rent, utilities"),
(4, "Housing", "Rent, utilities");

INSERT INTO EXPENSE (UserId, Title, Description, Amount, Category) VALUES 
(1, "Shell", "Gasoline", 45, 1),
(1, "Shell", "Gasoline", 15, 1),
(2, "Shell", "Gasoline", 34, 1),
(2, "Theater", "Tickets", 27, 2),
(3, "Dance Class", "Monthy Tuition", 90, 2),
(3, "Football Game", "Tickets", 100, 2),
(4, "Grocery Store", "Groceries", 150, 3),
(4, "Grocery Store", "Groceries", 75, 7),
(1, "Grocery Store", "Groceries", 37, 5),
(2, "Sunglass Hut", "Sunglasses", 200, 4),
(3, "Savings", "Savings", 400, 6),
(4, "Fidelity", "Stock Purchase", 750, 8),
(1, "Rent", "Monthly Rent", 1200, 9),
(3, "Gas", "Utilites", 80, 10),
(4, "Rent", "Monthly Rent", 1350, 11);

INSERT INTO Bill (BillId, Recurringtime, Duedate) VALUES 
(13, "Month", "2022-01-01 12:00:00"),
(14, "Month", "2022-01-10 12:00:00"),
(15, "Month", "2022-01-20 12:00:00");

INSERT INTO Transaction (TransactionId, Date) VALUES
(1, "2022-01-01 12:01:00"),
(2, "2022-01-01 11:05:00"),
(3, "2022-01-02 10:09:00"),
(4, "2022-01-02 09:10:00"),
(5, "2022-01-03 08:20:00"),
(6, "2022-01-03 07:25:00"),
(7, "2022-01-04 06:30:00"),
(8, "2022-01-04 05:35:00"),
(9, "2022-01-05 04:45:00"),
(10, "2022-01-05 03:47:00"),
(11, "2022-01-06 02:50:00"),
(12, "2022-01-06 01:55:00");
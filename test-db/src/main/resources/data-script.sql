INSERT INTO company (company_id, name, employees)
VALUES (1, 'Apple', '20000000'),
       (2, 'Xiaomi', '300000'),
       (3, 'Samsung', '4000000'),
       (4, 'Asus', '6000000'),
       (5, 'Zte', '500000');

INSERT INTO phone (phone_id, name, price, company_id)
VALUES (1, 'Iphone 5',  200, 1),
       (2, 'Iphone se', 500, 1),
       (3, 'Redmi',     200, 2),
       (4, 'Galaxy',    800, 3),
       (5, 'Mi mix',    450, 2),
       (6, 'Zenfone',   650, 4),
       (7, 'Blade',     150, 5),
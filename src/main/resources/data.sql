-- /**
--  * Author:  Baljeet
--  * Created: Jan 27, 2025
--  */

insert into CITY values(1, 1223, 'New York', 'New York');
insert into CITY values(2, 1290, 'Los Angeles', 'California');
insert into CITY values(3, 607, 'Chicago', 'Illinois');
insert into CITY values(4, 1740, 'Houston', 'Texas');
insert into CITY values(5, 1344, 'Pheonix', 'Arizona');
insert into CITY values(6, 369, 'Philadelphia', 'Pennsylvania');

-- ny chicago
insert into LINK values(1, 30, 1,  TRUE,  3,1);
-- ny philly
insert into LINK values(2, 30, 1, FALSE,  6, 1);

-- la pheonix
insert into LINK values(3, 30, 1, TRUE, 
(select id from City where id = 5), 
(select id from City where id = 2));
-- la houston
insert into LINK values(4, 30, 1, FALSE, 
(select id from City where id = 4),
(select id from City where id = 2));

-- chicago huston
insert into LINK values(5, 30, 1, TRUE,
(select id from City where id = 4),
(select id from City where id = 3));
-- chicago pheonix
insert into LINK values(6, 30, 1, FALSE,
(select id from City where id = 5),
(select id from City where id = 3));
-- chicago ny
insert into LINK values(7, 30, 1, TRUE,
(select id from City where id = 1),
(select id from City where id = 3));

-- houston la
insert into LINK values(8, 30, 1, FALSE,
(select id from City where id = 2),
(select id from City where id = 4));
-- houston chicago
insert into LINK values(9, 30, 1, TRUE,
(select id from City where id = 3),
(select id from City where id = 4));
-- houston philly
insert into LINK values(10, 30, 1, FALSE,
(select id from City where id = 6),
(select id from City where id = 4));

-- pheonix la
insert into LINK values(11, 30, 1, TRUE,
(select id from City where id = 2),
(select id from City where id = 5));
-- pheonix chicago
insert into LINK values(12, 30, 1, FALSE,
(select id from City where id = 3),
(select id from City where id = 5));

-- philly houston
insert into LINK values(13, 30, 1, TRUE,
(select id from City where id = 4),
(select id from City where id = 6));
-- philly ny
insert into LINK values(14, 30, 1, FALSE,
(select id from City where id = 1),
(select id from City where id = 6));
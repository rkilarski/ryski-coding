--
-- Database: `chickenrice`
--

-- --------------------------------------------------------
-- create and select the database
USE chickenrice;

INSERT INTO `food` (`id`, `name`, `description`, `isVegetarian`, `imageName`) VALUES
(1, 'empanadas', 'two perfect pastries stuffed with chicken and cheese', 'N', 'bg-04.jpg'),
(2, 'ravioli', 'steamed or fried chicken and ricotta ravioli served in a light broth', 'N', null),
(3, 'espicy curry coconut soup', 'a lovely spicy and thick soup', 'N', null),
(4, 'risotto', 'creamy classic dish with bits of chicken, topped with parmesan and truffles', 'N', 'bg-03.jpg'),
(5, 'paella', 'the spanish rice dish with a lovely long-grain rice from galicia and the best saffron, cooked with onions and spices in a delicious broth', 'N', null),
(6, 'tallarin verde', 'a milanese-style chicken topped with noodles tossed in a spinach sauce', 'N', 'bg-08.jpg'),
(7, 'rotisseried', 'breast and leg rubbed with our special spices and served with a choice of sides', 'N', 'bg-01.jpg'),
(8, 'fried', 'on-the-bone chicken breast, leg, and wing, battered and fried', 'N', null),
(9, 'milanese', 'a flattened chicken breast, dipped in our spices and breadcrumbs, and then fried', 'N', null),
(10, 'chicoletipan', 'open-faced sandwich served on our freshly baked bread with provolone cheese and vegetables of the day', 'N', null),
(11, 'udon noodle', 'pan-fried noodles with bits of chicken and grilled vegetables of the month', 'N', null),
(12, 'brown', 'whole-grain rice cooked in a smooth chicken broth for additional flavor', 'N', null),
(13, 'medium grain', 'normally for risotto, a medium-grain white rice is a wonderful creamy alternative to the long-grain standard', 'N', null),
(14, 'basmati', 'an aromatic long-grain', 'N', null),
(15, 'filet mignion', 'just salt and pepper is all that''s needed to highlight this delicious cut of beef', 'N', null),
(16, 'sully''s salmon', 'not to be missed, this british invasion dish will bring up all sorts of happy memories', 'N', null),
(17, 'roast vegetables', 'a seasonal set of vegetables, tossed with our special spices, and grilled', 'N', null),
(18, 'mashed', 'creamy mashed potatoes, finished with butter, sour cream, and topped with shredded cheese', 'N', null),
(19, 'biscuit', 'a perfectly baked and buttery buiscuit', 'N', null),
(20, 'polenta', 'corn meal cooked over a long flame and fried to flaky goodness', 'N', null),
(21, 'babka', 'a slice of a traditional polish chocolate cake topped with a butter and cocoa liqueur', 'N', null),
(22, 'flan', 'delicious vanilla custard topped with caramel and a dollop of home-made dulce de leche', 'N', 'bg-07.jpg'),
(23, 'chocotorta', 'thin chocolate wafers layered with a mixture of dulce-de-leche and sour cream, topped with a chocolate sauce', 'N', null);


INSERT INTO `menutype` (`id`, `name`) VALUES
(1, 'appetizer'),
(2, 'chickenrice'),
(3, 'chicken'),
(4, 'rice'),
(5, 'notchicken'),
(6, 'side'),
(7, 'dessert');

INSERT INTO `eventType` (`id`, `eventType`) VALUES
(1, 'birthday'),
(2, 'wedding'),
(3, 'bar/bat mitzvah'),
(4, 'office party'),
(5, 'reunion'),
(6, 'other');

INSERT INTO `orderstatus` (`id`, `orderStatus`) VALUES
(1, 'new'),
(2, 'in progress'),
(3, 'ready for pickup/delivery'),
(4, 'out for delivery'),
(5, 'closed');

INSERT INTO `reservationstatus` (`id`, `reservationStatus`) VALUES
(1, 'new'),
(2, 'cancelled'),
(3, 'seated');

INSERT INTO `ordertype` (`id`, `orderType`) VALUES
(1, 'takeout'),
(2, 'delivery'),
(3, 'dine in');

INSERT INTO `menu` (`id`, `menuType`, `foodItem`, `price`, `cost`, `specialDay`) VALUES
(1, 1, 1, 7, 5, ''),
(2, 1, 2, 8, 5, ''),
(3, 1, 3, 6, 5, 'M'),
(4, 2, 4, 9, 5, ''),
(5, 2, 5, 4, 5, ''),
(6, 2, 6, 6, 5, 'T'),
(7, 3, 7, 5, 5, ''),
(8, 3, 8, 7, 5, ''),
(9, 3, 9, 10, 5, ''),
(10, 3, 10, 12, 5, 'W'),
(11, 3, 11, 11, 5, 'R'),
(12, 4, 12, 15, 5, ''),
(13, 4, 13, 15, 5, ''),
(14, 4, 14, 17, 5, ''),
(15, 5, 15, 14, 5, ''),
(16, 5, 16, 16, 5, ''),
(17, 6, 17, 10, 5, ''),
(18, 6, 18, 12, 5, ''),
(19, 6, 19, 14, 5, ''),
(20, 6, 20, 10, 5, ''),
(21, 7, 21, 6, 5, ''),
(22, 7, 22, 5, 5, ''),
(23, 7, 23, 7, 5,'F');

INSERT INTO `diningTable` (`id`, `name`, `seatCount`) VALUES
(1, '1', 2),
(2, '2', 2),
(3, '3', 2),
(4, '4', 2),
(5, '5', 4),
(6, '6', 4),
(7, '7', 4),
(8, '8', 4),
(9, '9', 6),
(10, '10', 6);

INSERT INTO `states` (`id`, `st`, `stateName`) VALUES
(1,'al','alabama'),
(2,'ak','alaska'),
(3,'az','arizona'),
(4,'ar','arkansas'),
(5,'ca','california'),
(6,'co','colorado'),
(7,'ct','connecticut'),
(8,'de','delaware'),
(9,'dc','district of columbia'),
(10,'fl','florida'),
(11,'ga','georgia'),
(12,'hi','hawaii'),
(13,'id','idaho'),
(14,'il','illinois'),
(15,'in','indiana'),
(16,'ia','iowa'),
(17,'ks','kansas'),
(18,'ky','kentucky'),
(19,'la','louisiana'),
(20,'me','maine'),
(21,'md','maryland'),
(22,'ma','massachusetts'),
(23,'mi','michigan'),
(24,'mn','minnesota'),
(25,'ms','mississippi'),
(26,'mo','missouri'),
(27,'mt','montana'),
(28,'ne','nebraska'),
(29,'nv','nevada'),
(30,'nh','new hampshire'),
(31,'nj','new jersey'),
(32,'nm','new mexico'),
(33,'ny','new york'),
(34,'nc','north carolina'),
(35,'nd','north dakota'),
(36,'oh','ohio'),
(37,'ok','oklahoma'),
(38,'or','oregon'),
(39,'pa','pennsylvania'),
(40,'ri','rhode island'),
(41,'sc','south carolina'),
(42,'sd','south dakota'),
(43,'tn','tennessee'),
(44,'tx','texas'),
(45,'ut','utah'),
(46,'vt','vermont'),
(47,'va','virginia'),
(48,'wa','washington'),
(49,'wv','west virginia'),
(50,'wi','wisconsin'),
(51,'wy','wyoming');

INSERT INTO `person` (`id`, `firstName`, `middleName`, `lastName`, `email`, `password`, `addressLine1`, `addressLine2`, `city`, `st`, `zip`, `telephone`, `isStaff`, `blacklistFlag`, `blacklistReason`, `sendEmail`) VALUES
(1, 'manager', NULL, 'manager', aes_encrypt('manager@chickenrice.com','chickenrice'), aes_encrypt('password','chickenrice'), '895 Commonwealth Ave', NULL, 'Boston', 'MA', '02445', '6173535000', 'Y', NULL, NULL, 'N'),
(2, 'Ryszard', NULL, 'Kilarski', aes_encrypt('rkilarski@gmail.com','chickenrice'), aes_encrypt('password','chickenrice'), '99 Pond Ave Apt 701', NULL, 'Brookline', 'MA', '02445', '6179534258', 'Y', NULL, NULL, 'N'),
(3, 'Bad', 'C', 'Customer', aes_encrypt('badcustomer@gmail.com','chickenrice'), aes_encrypt('password','chickenrice'), 'No address', NULL, 'Boston', 'MA', '02445', '6179534258', 'Y', 'Y', 'This is a bad client, never pays.', 'N');
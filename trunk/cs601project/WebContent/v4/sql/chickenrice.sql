--
-- Database: `chickenrice`
--

-- --------------------------------------------------------
-- create and select the database
DROP DATABASE IF EXISTS chickenrice;
CREATE DATABASE chickenrice;
USE chickenrice;
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- Create services user.
GRANT SELECT, INSERT, UPDATE, DELETE
ON *
TO chickenrice@localhost
IDENTIFIED BY 'password';

CREATE TABLE IF NOT EXISTS `event` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `eventDateTime` datetime NOT NULL,
  `person` int(10) NOT NULL,
  `description` varchar(255) NOT NULL,
  `eventType` int(10) NOT NULL,
  `reservationStatus` int(10) NOT NULL,
  `hours` int(2) NOT NULL,
  `personCount` int(2) NOT NULL,
  `price` int(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `food` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `description` varchar(255) NOT NULL,
  `isVegetarian` varchar(1) NOT NULL DEFAULT 'N',
  `imageName` varchar(255) NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `keyword` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(25) NOT NULL,
  `weight` int(2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `keyword` (`keyword`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `diningTable` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `seatCount` varchar(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `menuType` int(10) NOT NULL,
  `foodItem` int(10) NOT NULL,
  `price` int(5) NOT NULL,
  `cost` int(5) NOT NULL,
  `specialDay` varchar(1) NULL,
  PRIMARY KEY (`id`),
  KEY `menuType` (`menuType`),
  KEY `foodItem` (`foodItem`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `menutype` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

CREATE TABLE IF NOT EXISTS `eventType` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `eventType` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

CREATE TABLE IF NOT EXISTS `person` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `middleName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NULL,
  `addressLine1` varchar(255) DEFAULT NULL,
  `addressLine2` varchar(255) DEFAULT NULL,
  `city` varchar(25) DEFAULT NULL,
  `st` varchar(2) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  `isStaff` varchar(1) NULL DEFAULT 'N',
  `blacklistFlag` varchar(1) DEFAULT NULL,
  `blacklistReason` varchar(255) DEFAULT NULL,
  `sendEmail` varchar(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

CREATE TABLE IF NOT EXISTS `emailAddress` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

CREATE TABLE IF NOT EXISTS `reservation` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `person` int(10) NOT NULL,
  `tableSize` int(2) NOT NULL,
  `reservationDateTime` datetime NOT NULL,
  `reservationStatus` int(10) NOT NULL,
  `diningTable` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `person` (`person`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `reservationStatus` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `reservationStatus` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `orderStatus` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `orderStatus` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `orderType` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `orderType` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `customerOrder`(
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `customerAddress` int(10) NOT NULL, 
  `orderStatus` int(10) NOT NULL,
  `orderType` int(10) NOT NULL,
  `paidFlag` varchar(1) DEFAULT 'N',
  `datetimeOrdered` datetime NOT NULL,
  `event` int(10) NULL,
   PRIMARY KEY (`id`),
   KEY `datetimeOrdered` (`datetimeOrdered`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `customerOrderDetail`(
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `orderNumber` int(10) NOT NULL,
  `menuItem` int(10) NOT NULL,
  `customerRequest` varchar(255) NULL,
   PRIMARY KEY (`id`),
   KEY `orderNumber` (`orderNumber`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `customerOrderAddress` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) NOT NULL,
  `middleName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `addressLine1` varchar(255) NOT NULL,
  `addressLine2` varchar(255) DEFAULT NULL,
  `city` varchar(25) NOT NULL,
  `st` varchar(2) NOT NULL,
  `zip` varchar(10) NOT NULL,
  `billingAddressLine1` varchar(255) NOT NULL,
  `billingAddressLine2` varchar(255) DEFAULT NULL,
  `billingCity` varchar(25) NOT NULL,
  `billingSt` varchar(2) NOT NULL,
  `billingZip` varchar(10) NOT NULL,
  `telephone` varchar(15) NOT NULL,
  `ccnumber4` varchar(255) NOT NULL,
  `ccexpmonth` varchar(255) NOT NULL,
  `ccexpyear` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `states` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `st` varchar(2) NOT NULL,
  `stateName` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

ALTER TABLE `event`
  ADD CONSTRAINT `eventPersonFK` FOREIGN KEY (`person`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `eventTypeFK` FOREIGN KEY (`eventType`) REFERENCES `eventType` (`id`),
  ADD CONSTRAINT `eventReservationStatusFK` FOREIGN KEY (`reservationStatus`) REFERENCES `reservationStatus` (`id`);

ALTER TABLE `menu`
  ADD CONSTRAINT `menuFoodItemFK` FOREIGN KEY (`foodItem`) REFERENCES `food` (`id`),
  ADD CONSTRAINT `menuMenuTypeFK` FOREIGN KEY (`menuType`) REFERENCES `menutype` (`id`);

ALTER TABLE `reservation`
  ADD CONSTRAINT `reservationPersonFK` FOREIGN KEY (`person`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `reservationStatusFK` FOREIGN KEY (`reservationStatus`) REFERENCES `reservationStatus` (`id`),
  ADD CONSTRAINT `reservationDiningTableFK` FOREIGN KEY (`diningTable`) REFERENCES `diningTable` (`id`);

ALTER TABLE `customerOrder`
  ADD CONSTRAINT `orderStatusFK` FOREIGN KEY (`orderStatus`) REFERENCES `orderStatus` (`id`),
  ADD CONSTRAINT `orderTypeFK` FOREIGN KEY (`orderType`) REFERENCES `orderType` (`id`),
  ADD CONSTRAINT `customerAddressFK` FOREIGN KEY (`customerAddress`) REFERENCES `customerOrderAddress` (`id`),
  ADD CONSTRAINT `orderEventFK` FOREIGN KEY (`event`) REFERENCES `event` (`id`);
  
 ALTER TABLE `customerOrderDetail`
  ADD CONSTRAINT `menuItemFK` FOREIGN KEY (`menuItem`) REFERENCES `menu` (`id`),
  ADD CONSTRAINT `orderNumberFK` FOREIGN KEY (`orderNumber`) REFERENCES `customerOrder` (`id`);


  
  
-- DATA SECTION --
-- create and select the database
USE chickenrice;

INSERT INTO `food` (`id`, `name`, `description`, `isVegetarian`, `imageName`) VALUES
(1, 'empanadas', 'two perfect pastries stuffed with chicken and cheese', 'n', 'bg-04.jpg'),
(2, 'ravioli', 'steamed or fried chicken and ricotta ravioli served in a light broth', 'n', 'bg-15.jpg'),
(3, 'espicy curry coconut soup', 'a lovely spicy and thick soup', 'n', 'bg-16.jpg'),
(4, 'risotto', 'creamy classic dish with bits of chicken, topped with parmesan and truffles', 'n', 'bg-03.jpg'),
(5, 'paella', 'the spanish rice dish with a lovely long-grain rice from galicia and the best saffron, cooked with onions and spices in a delicious broth', 'n', 'bg-03.jpg'),
(6, 'tallarin verde', 'a milanese-style chicken topped with noodles tossed in a spinach sauce', 'n', 'bg-08.jpg'),
(7, 'rotisseried', 'breast and leg rubbed with our special spices and served with a choice of sides', 'n', 'bg-01.jpg'),
(8, 'fried', 'on-the-bone chicken breast, leg, and wing, battered and fried', 'n', 'bg-17.jpg'),
(9, 'milanese', 'a flattened chicken breast, dipped in our spices and breadcrumbs, and then fried', 'n', 'bg-18.jpg'),
(10, 'chicoletipan', 'open-faced sandwich served on our freshly baked bread with provolone cheese and vegetables of the day', 'n', 'npa.jpg'),
(11, 'udon noodle', 'pan-fried noodles with bits of chicken and grilled vegetables of the month', 'n', 'bg-14.jpg'),
(12, 'brown', 'whole-grain rice cooked in a smooth chicken broth for additional flavor', 'n', 'npa.jpg'),
(13, 'medium grain', 'normally for risotto, a medium-grain white rice is a wonderful creamy alternative to the long-grain standard', 'n', 'npa.jpg'),
(14, 'basmati', 'an aromatic long-grain', 'n', 'npa.jpg'),
(15, 'filet mignion', 'just salt and pepper is all that''s needed to highlight this deliciously hunky cut of beef', 'n', 'npa.jpg'),
(16, 'sully''s salmon', 'not to be missed, this british invasion dish will bring up all sorts of happy memories', 'n', 'npa.jpg'),
(17, 'roast vegetables', 'a seasonal set of vegetables, tossed with our special spices, and grilled', 'n', 'npa.jpg'),
(18, 'mashed', 'creamy mashed potatoes, finished with butter, sour cream, and topped with shredded cheese', 'n', 'npa.jpg'),
(19, 'biscuit', 'a perfectly baked and buttery buiscuit', 'n', 'npa.jpg'),
(20, 'polenta', 'corn meal cooked over a long flame and fried to flaky goodness', 'n', 'npa.jpg'),
(21, 'babka', 'a slice of a traditional polish chocolate cake topped with a butter and cocoa liqueur', 'n', 'bg-12.jpg'),
(22, 'flan', 'delicious vanilla custard topped with caramel and a dollop of home-made dulce de leche', 'n', 'bg-07.jpg'),
(23, 'chocotorta', 'thin chocolate wafers layered with a mixture of dulce-de-leche and sour cream, topped with a chocolate sauce', 'n', 'npa.jpg'),
(24, 'event', 'event', 'n', 'npa.jpg');

INSERT INTO `keyword` (`id`, `keyword`, `weight`) VALUES
(1, 'chicken', 0),
(2, 'tonight', 0),
(3, 'yum', 0),
(4, 'rice', 0),
(5, 'paella', 0),
(6, 'empanadas', 0),
(7, 'rotisserie', 0),
(8, 'grill', 0),
(9, 'basmati', 0),
(10, 'ravioli', 0),
(11, 'milanese', 0),
(12, 'creamy', 0),
(13, 'soup', 0),
(14, 'udon noodle', 0),
(15, 'babka', 0),
(16, 'chocolate', 0),
(17, 'flan', 0),
(18, 'chocotorta', 0);

INSERT INTO `menutype` (`id`, `name`) VALUES
(1, 'appetizer'),
(2, 'chickenrice'),
(3, 'chicken'),
(4, 'rice'),
(5, 'notchicken'),
(6, 'side'),
(7, 'dessert'),
(8, 'event');

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
(3, 'seated'),
(4, 'done');

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
(23, 7, 23, 7, 5,'F'),
(24, 8, 24, 25, 15,'');

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
(1, 'manager', NULL, 'manager', aes_encrypt('manager@chickenrice.com','chickenrice'), aes_encrypt('password','chickenrice'), aes_encrypt('895 Commonwealth Ave','chickenrice'), NULL, aes_encrypt('Boston','chickenrice'), aes_encrypt('ma','chickenrice'), aes_encrypt('02445','chickenrice'), '6173535000', 'Y', NULL, NULL, 'N'),
(2, 'Ryszard', NULL, 'Kilarski', aes_encrypt('rkilarski@gmail.com','chickenrice'), aes_encrypt('password','chickenrice'), aes_encrypt('99 Pond Ave Apt 701','chickenrice'), NULL, aes_encrypt('Brookline','chickenrice'), aes_encrypt('ma','chickenrice'), aes_encrypt('02445','chickenrice'), '6179534258', 'Y', NULL, NULL, 'N'),
(3, 'Bad', 'C', 'Customer', aes_encrypt('badcustomer@gmail.com','chickenrice'), aes_encrypt('password','chickenrice'), aes_encrypt('No address','chickenrice'), NULL, 'Boston', 'ma', '02445', '6179534258', 'Y', 'Y', 'This is a bad client, never pays.', 'N');
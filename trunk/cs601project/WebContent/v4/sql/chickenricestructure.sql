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
  `reservationDate` varchar(10) NOT NULL,
  `person` int(10) NOT NULL,
  `name` varchar(25) NOT NULL,
  `description` varchar(255) NOT NULL,
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
  `name` varchar(25) NOT NULL,
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
  KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

CREATE TABLE IF NOT EXISTS `emailAddress` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

CREATE TABLE IF NOT EXISTS `reservation` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `person` int(10) NOT NULL,
  `tableSize` int(2) NOT NULL,
  `diningTable` int(2) NOT NULL,
  `reservationDate` varchar(10) NOT NULL,
  `reservationTime` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `person` (`person`)
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
   PRIMARY KEY (`id`)
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
  `firstName` varchar(255) DEFAULT NULL,
  `middleName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `addressLine1` varchar(255) DEFAULT NULL,
  `addressLine2` varchar(255) DEFAULT NULL,
  `city` varchar(25) DEFAULT NULL,
  `st` varchar(2) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

ALTER TABLE `event`
  ADD CONSTRAINT `eventPersonFK` FOREIGN KEY (`person`) REFERENCES `person` (`id`);

ALTER TABLE `menu`
  ADD CONSTRAINT `menuFoodItemFK` FOREIGN KEY (`foodItem`) REFERENCES `food` (`id`),
  ADD CONSTRAINT `menuMenuTypeFK` FOREIGN KEY (`menuType`) REFERENCES `menutype` (`id`);

ALTER TABLE `reservation`
  ADD CONSTRAINT `reservationPersonFK` FOREIGN KEY (`person`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `reservationDiningTableFK` FOREIGN KEY (`diningTable`) REFERENCES `diningTable` (`id`);

ALTER TABLE `customerOrder`
  ADD CONSTRAINT `orderStatusFK` FOREIGN KEY (`orderStatus`) REFERENCES `orderStatus` (`id`),
  ADD CONSTRAINT `orderTypeFK` FOREIGN KEY (`orderType`) REFERENCES `orderType` (`id`),
  ADD CONSTRAINT `customerAddressFK` FOREIGN KEY (`customerAddress`) REFERENCES `customerOrderAddress` (`id`);
  
 ALTER TABLE `customerOrderDetail`
  ADD CONSTRAINT `menuItemFK` FOREIGN KEY (`menuItem`) REFERENCES `menu` (`id`),
  ADD CONSTRAINT `orderNumberFK` FOREIGN KEY (`orderNumber`) REFERENCES `customerOrder` (`id`);


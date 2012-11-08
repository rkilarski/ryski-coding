--
-- Database: `chickenrice`
--

-- --------------------------------------------------------
-- create and select the database
DROP DATABASE IF EXISTS chickenrice;
CREATE DATABASE chickenrice;
USE chickenrice;
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

CREATE TABLE IF NOT EXISTS `events` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `dateStart` varchar(10) NOT NULL,
  `dateEnd` varchar(10) NOT NULL,
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

CREATE TABLE IF NOT EXISTS `emailaddress` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

CREATE TABLE IF NOT EXISTS `reservations` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `person` int(10) NOT NULL,
  `tableSize` int(2) NOT NULL,
  `diningTable` int(2) NOT NULL,
  `reservationDate` varchar(10) NOT NULL,
  `reservationTime` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `person` (`person`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `takeoutorder`(
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `orderNumber` int(10) NOT NULL,
  `person` int(10) NOT NULL, 
  `menuItem` int(10) NOT NULL,
   PRIMARY KEY (`id`),
   KEY `orderNumber` (`orderNumber`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

ALTER TABLE `menu`
  ADD CONSTRAINT `menuFoodItemFK` FOREIGN KEY (`foodItem`) REFERENCES `food` (`id`),
  ADD CONSTRAINT `menuMenuTypeFK` FOREIGN KEY (`menuType`) REFERENCES `menutype` (`id`);

ALTER TABLE `reservations`
  ADD CONSTRAINT `reservationsPersonFK` FOREIGN KEY (`person`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `reservationsDiningTableFK` FOREIGN KEY (`diningTable`) REFERENCES `diningTable` (`id`);

ALTER TABLE `takeoutorder`
  ADD CONSTRAINT `takoutorderPersonFK` FOREIGN KEY (`person`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `menuItemFK` FOREIGN KEY (`menuItem`) REFERENCES `menu` (`id`);

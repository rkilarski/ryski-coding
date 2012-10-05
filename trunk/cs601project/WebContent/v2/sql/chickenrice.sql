--
-- Database: `chickenrice`
--

-- --------------------------------------------------------
-- create and select the database
DROP DATABASE IF EXISTS chickenrice;
CREATE DATABASE chickenrice;
USE chickenrice;
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;


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
  `isSpecial` varchar(1) NOT NULL DEFAULT 'N',
  `isVegetarian` varchar(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `menuType` int(10) NOT NULL,
  `foodItem` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `menuType` (`menuType`),
  KEY `foodItem` (`foodItem`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `menutype` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `person` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `middleName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `emailAddress` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `addressLine1` varchar(255) DEFAULT NULL,
  `addressLine2` varchar(255) DEFAULT NULL,
  `city` varchar(25) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `telephone` varchar(10) DEFAULT NULL,
  `isStaff` varchar(1) NOT NULL DEFAULT 'N',
  `blacklistFlag` varchar(1) DEFAULT NULL,
  `blacklistReason` varchar(255) DEFAULT NULL,
  `sendEmail` varchar(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`id`),
  KEY `emailAddress` (`emailAddress`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

INSERT INTO `person` (`id`, `firstName`, `middleName`, `lastName`, `emailAddress`, `password`, `addressLine1`, `addressLine2`, `city`, `state`, `zip`, `telephone`, `isStaff`, `blacklistFlag`, `blacklistReason`, `sendEmail`) VALUES
(1, 'manager', NULL, 'manager', 'manager@chickenrice.com', 'password', '895 Commonwealth Ave', NULL, 'Boston', 'MA', '02445', '6173535000', 'Y', NULL, NULL, 'N');

CREATE TABLE IF NOT EXISTS `reservations` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `person` int(10) NOT NULL,
  `tableSize` int(2) NOT NULL,
  `date` varchar(10) NOT NULL,
  `time` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `person` (`person`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `specials` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `menuType` int(10) NOT NULL,
  `foodItem` int(10) NOT NULL,
  `specialDay` varchar(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `menuType` (`menuType`),
  KEY `foodItem` (`foodItem`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;



ALTER TABLE `menu`
  ADD CONSTRAINT `menu_ibfk_2` FOREIGN KEY (`foodItem`) REFERENCES `food` (`id`),
  ADD CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`menuType`) REFERENCES `menutype` (`id`);

ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`person`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `specials`
  ADD CONSTRAINT `specials_ibfk_2` FOREIGN KEY (`foodItem`) REFERENCES `food` (`id`),
  ADD CONSTRAINT `specials_ibfk_1` FOREIGN KEY (`menuType`) REFERENCES `menutype` (`id`);

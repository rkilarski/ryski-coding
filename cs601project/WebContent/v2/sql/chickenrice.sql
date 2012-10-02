--
-- Database: `chickenrice`
--

-- --------------------------------------------------------
-- create and select the database
DROP DATABASE IF EXISTS chickenrice;
CREATE DATABASE chickenrice;
USE chickenrice;



-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 02, 2012 at 09:02 PM
-- Server version: 5.5.25a
-- PHP Version: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `chickenrice`
--

-- --------------------------------------------------------

--
-- Table structure for table `food`
--

CREATE TABLE IF NOT EXISTS `food` (
  `foodId` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `description` varchar(255) NOT NULL,
  `isSpecial` varchar(1) NOT NULL DEFAULT 'N',
  `isVegetarian` varchar(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`foodId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE IF NOT EXISTS `menu` (
  `menu_id` int(10) NOT NULL AUTO_INCREMENT,
  `menuType` int(10) NOT NULL,
  `foodItem` int(10) NOT NULL,
  PRIMARY KEY (`menu_id`),
  KEY `menuType` (`menuType`),
  KEY `foodItem` (`foodItem`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `menutype`
--

CREATE TABLE IF NOT EXISTS `menutype` (
  `menutypeId` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  PRIMARY KEY (`menutypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE IF NOT EXISTS `person` (
  `personId` int(10) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) NOT NULL,
  `middleName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `emailAddress` varchar(255) NOT NULL,
  `addressLine1` varchar(255) NOT NULL,
  `addressLine2` varchar(255) NOT NULL,
  `city` varchar(25) NOT NULL,
  `state` varchar(2) NOT NULL,
  `zip` varchar(10) NOT NULL,
  `telephone` varchar(10) NOT NULL,
  `isStaff` varchar(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`personId`),
  KEY `emailAddress` (`emailAddress`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `menu`
--
ALTER TABLE `menu`
  ADD CONSTRAINT `menu_ibfk_2` FOREIGN KEY (`foodItem`) REFERENCES `food` (`foodId`),
  ADD CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`menuType`) REFERENCES `menutype` (`menutypeId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;



	-- create the users and grant priveleges to those users
	GRANT SELECT, INSERT, DELETE, UPDATE
	ON chickenrice.*
	TO manager@chickenrice.com
	IDENTIFIED BY `doyoufeellikechickentonight`;
	
	-- create the users and grant priveleges to those users
	GRANT SELECT, INSERT, DELETE, UPDATE
	ON chickenrice.*
	TO webuser@localhost
	IDENTIFIED BY `doyoufeellikechickentonight`;

	GRANT SELECT
	ON products
	TO mgs_tester@localhost
	IDENTIFIED BY `pa55word`;
	
--Create manager
INSERT INTO Person (first_name, last_name, email_address, address_line_1, address_line_2, city, state, zip, telephone_1, is_staff)
VALUES ('manager', 'manager', 'manager@chickenrice.com','985 Commonwealth Ave','','Boston','MA','02215','6173535000','Y');
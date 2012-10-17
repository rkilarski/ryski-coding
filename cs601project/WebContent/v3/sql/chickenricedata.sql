--
-- Database: `chickenrice`
--

-- --------------------------------------------------------
-- create and select the database
USE chickenrice;

INSERT INTO `food` (`id`, `name`, `description`, `isSpecial`, `isVegetarian`) VALUES
(1, 'empanadas', 'two perfect pastries stuffed with chicken and cheese', 'N', 'N'),
(2, 'ravioli', 'steamed or fried chicken and ricotta ravioli served in a light broth', 'N', 'N'),
(3, 'espicy curry coconut soup', 'a lovely spicy and thick soup', 'N', 'N'),
(4, 'risotto', 'creamy classic dish with bits of chicken, topped with parmesan and truffles', 'N', 'N'),
(5, 'paella', 'the spanish rice dish with a lovely long-grain rice from galicia and the best saffron, cooked with onions and spices in a delicious broth', 'N', 'N'),
(6, 'tallarin verde', 'a milanese-style chicken topped with noodles tossed in a spinach sauce', 'N', 'N'),
(7, 'rotisseried', 'breast and leg rubbed with our special spices and served with a choice of sides', 'N', 'N'),
(8, 'fried', 'on-the-bone chicken breast, leg, and wing, battered and fried', 'N', 'N'),
(9, 'milanese', 'a flattened chicken breast, dipped in our spices and breadcrumbs, and then fried', 'N', 'N'),
(10, 'chicoletipan', 'open-faced sandwich served on our freshly baked bread with provolone cheese and vegetables of the day', 'N', 'N'),
(11, 'udon noodle', 'pan-fried noodles with bits of chicken and grilled vegetables of the month', 'N', 'N'),
(12, 'brown', 'whole-grain rice cooked in a smooth chicken broth for additional flavor', 'N', 'N'),
(13, 'medium grain', 'normally for risotto, a medium-grain white rice is a wonderful creamy alternative to the long-grain standard', 'N', 'N'),
(14, 'basmati', 'an aromatic long-grain', 'N', 'N'),
(15, 'filet mignion', 'just salt and pepper is all that''s needed to highlight this delicious cut of beef', 'N', 'N'),
(16, 'sully''s salmon', 'not to be missed, this british invasion dish will bring up all sorts of happy memories', 'N', 'N'),
(17, 'roast vegetables', 'a seasonal set of vegetables, tossed with our special spices, and grilled', 'N', 'N'),
(18, 'mashed', 'creamy mashed potatoes, finished with butter, sour cream, and topped with shredded cheese', 'N', 'N'),
(19, 'biscuit', 'a perfectly baked and buttery buiscuit', 'N', 'N'),
(20, 'polenta', 'corn meal cooked over a long flame and fried to flaky goodness', 'N', 'N'),
(21, 'babka', 'a slice of a traditional polish chocolate cake topped with a butter and cocoa liqueur', 'N', 'N'),
(22, 'flan', 'delicious vanilla custard topped with caramel and a dollop of home-made dulce de leche', 'N', 'N'),
(23, 'chocotorta', 'thin chocolate wafers layered with a mixture of dulce-de-leche and sour cream, topped with a chocolate sauce', 'N', 'N');


INSERT INTO `menutype` (`id`, `name`) VALUES
(1, 'appetizer'),
(2, 'chickenrice'),
(3, 'chicken'),
(4, 'rice'),
(5, 'notchicken'),
(6, 'side'),
(7, 'dessert');

INSERT INTO `menu` (`id`, `menuType`, `foodItem`, `price`, `cost`) VALUES
(1, 1, 1, 10, 5),
(2, 1, 2, 10, 5),
(3, 1, 3, 10, 5),
(4, 2, 4, 10, 5),
(5, 2, 5, 10, 5),
(6, 2, 6, 10, 5),
(7, 3, 7, 10, 5),
(8, 3, 8, 10, 5),
(9, 3, 9, 10, 5),
(10, 3, 10, 10, 5),
(11, 3, 11, 10, 5),
(12, 4, 12, 10, 5),
(13, 4, 13, 10, 5),
(14, 4, 14, 10, 5),
(15, 5, 15, 10, 5),
(16, 5, 16, 10, 5),
(17, 6, 17, 10, 5),
(18, 6, 18, 10, 5),
(19, 6, 19, 10, 5),
(20, 6, 20, 10, 5),
(21, 7, 21, 10, 5),
(22, 7, 22, 10, 5),
(23, 7, 23, 10, 5);

INSERT INTO `specials` (`id`, `menuType`, `foodItem`, `price`, `cost`, `specialDay`) VALUES
(1, 1, 3, 10, 5,'M'), 
(2, 2, 6, 10, 5,'T'),
(3, 3, 10, 10, 5,'W'),
(4, 3, 11, 10, 5,'R'),
(5, 7, 23, 10, 5,'F');

INSERT INTO `person` (`id`, `firstName`, `middleName`, `lastName`, `email`, `password`, `addressLine1`, `addressLine2`, `city`, `st`, `zip`, `telephone`, `isStaff`, `blacklistFlag`, `blacklistReason`, `sendEmail`) VALUES
(1, 'manager', NULL, 'manager', aes_encrypt('manager@chickenrice.com','chickenrice'), aes_encrypt('password','chickenrice'), '895 Commonwealth Ave', NULL, 'Boston', 'MA', '02445', '6173535000', 'Y', NULL, NULL, 'N');
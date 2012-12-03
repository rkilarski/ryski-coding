Author: Ryszard Kilarski
BU ID: U81-39-8560

MET CS 601 Final Project


Requirements:
	PHP server with PDO extensions.
	MySQL
	Latest versions of IE/Chrome/Safari/Firefox
	
Installation
	-Import the /sql/chickenrice.sql file on the MySql server to import the database.
	-This will also install user manager@chickenrice.com with password 'password'.
	
Notable Items
	-Ajax is used in the "add to mailing list" functionality on the home page and the "add to cart" functionality on the Menu/Specials pages.
	-Note the Twitter integration on the upper right.
	-No Facebook integration: I didn't feel comfortable creating a fake restaurant page on Facebook because it might look too real.
	-Events page checks for conflicts when scheduling new event.
	-Home page word cloud is database-driven and randomized to produce different results each refresh.
	-Background images are randomized every page refresh.
	-You cannot place a regular order on a Sunday; events and reservations for another day are fine.
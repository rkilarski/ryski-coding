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
	
Notable Items, if you're curious.
	-The date the order is taken is saved in the US/Eastern timezone.  
	-The store checks for valid store hours (10:30 am thru 9:30 pm every day except Sunday), 
	 but does NOT disable anything, because you may be testing outside of those hours.
	
	-Home page word cloud is database-driven and randomized to produce different results each refresh.
	-Background images are randomized every page refresh.

	-Every time you log in, a cookie is set with your email address, and then defaulted on the login form.
	-Note the Twitter integration on the upper right on every page.
	-No Facebook integration: I didn't feel comfortable creating a fake restaurant page on Facebook because it might look too real.
	
	-Ajax is used in all the "update" buttons on all staff forms; login, registration, adding to mailing list, adding items to cart, et. al.
	-The only places Ajax is not used are either navigation, the checkout process, and the "Search" buttons on the query forms.
	
	-Events page checks for conflicts when scheduling new event.

Bugs FIXED from Presentation
	-Crash upon Add to Cart from detail page.
	-Automatically run search after login, from saved-off link.
	-Update reservation status.
	-Fixed image sizes to be smaller.
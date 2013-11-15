/*
 * author: Ryszard Kilarski
 * email: emrys@bu.edu
 * BUI ID: U81-39-8560
 */

/**
 * Cart controller that initializes the cart to certain values.
 */
function CartController($scope) {
	$scope.count = 0;
	$scope.totals = 0;

	$scope.initialize = function() {
		// Perform a load from local storage, if any.
		$scope.books = dao.loadFromLocal();
		if ($scope.books == null) {
			if (!window.localStorage) {
				alert('Local storage is not supported on this browser. Nothing will be saved permanently.');
			}
			$scope.books = dao.loadFromDefault();
		}
	};

	/**
	 * Handler to remove a book line.
	 */
	$scope.removeBook = function(index) {
		$scope.books.splice(index, 1);
	};

	/**
	 * Update the book count.
	 */
	$scope.updateBookCount = function() {
		$scope.count = $scope.books.length;
	};

	/**
	 * Update the total $ for all books.
	 */
	$scope.updateTotals = function() {
		var totals = 0;
		angular.forEach($scope.books, function(book) {
			totals = totals + (book.price * book.qty);
		});
		$scope.totals = totals;
		return;
	};

	/**
	 * Watch to update counts and totals when the book array changes.
	 */
	$scope.$watch('books', function() {
		$scope.updateBookCount();
		$scope.updateTotals();
	}, true);

	/**
	 * Add new line into book model.
	 */
	$scope.newBook = function() {
		$scope.books.push(dao.buildNewBook());
	};

	/**
	 * Save cart to local storage.
	 */
	$scope.saveCart = function() {
		dao.saveToLocal($scope.books);
	};
}

/**
 * Object to handle all data requests.
 */
dao = {
	/**
	 * Load the last saved state of the shopping cart from local storage.
	 */
	loadFromLocal : function() {
		if (window.localStorage) {
			var data = window.localStorage.getItem('shoppingcart');
			if (data != null) {
				var cart = JSON.parse(data);
				return cart;
			}
		}
		return null;
	},

	/**
	 * Save the current state of the shopping cart to local storage.
	 */
	saveToLocal : function(cart) {
		if (window.localStorage) {
			var data = JSON.stringify(cart);
			window.localStorage.setItem('shoppingcart', data);
		}
	},

	/**
	 * Load the default list of books.
	 */
	loadFromDefault : function() {
		var defaults = [ {
			title : 'Absolute Java',
			qty : 1,
			price : 114.95
		}, {
			title : 'Pro HTML5',
			qty : 1,
			price : 27.95
		}, {
			title : 'Head First HTML5',
			qty : 1,
			price : 27.89
		} ];
		return defaults;
	},

	buildNewBook : function() {
		var newBook = {
			title : 'New Book',
			qty : 1,
			price : 10.99
		};
		return newBook;
	}
};
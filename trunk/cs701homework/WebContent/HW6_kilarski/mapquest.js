/*
 * author: Ryszard Kilarski
 * email: emrys@bu.edu
 * BUI ID: U81-39-8560
 */

angular.module('angularMapquest', []).controller(
		'mapquestController',
		function($scope, $http, $timeout) {
			var timeout = null;

			/**
			 * Set up form.
			 */
			$scope.initialize = function() {
				$scope.mapsearch = {};
				$scope.directions = new Array;
				if (navigator.geolocation) {
					$scope.mapsearch.from = '<geolocating, please wait...>';
					$scope.mapsearch.to = 'New York, NY';
					$scope.getLocation();
				} else {
					$scope.mapsearch.from = 'Boston, MA';
					$scope.mapsearch.to = 'New York, NY';
				}
			};

			/**
			 * Add a watch on the 'from' value. But ignore it while we're geolocating.
			 */
			$scope.$watch('mapsearch.from', function(newVal) {
				if ($scope.mapsearch.from == '<geolocating, please wait...>') {
					return;
				}
				if (newVal) {
					if (timeout) {
						$scope.directions = new Array;
						$timeout.cancel(timeout);
					}
					timeout = $timeout(function() {
						$scope.onChange();
					}, 600);
				}
				;
			});

			/**
			 * Add a watch on the 'to' value. But ignore it while we're geolocating.
			 */
			$scope.$watch('mapsearch.to', function(newVal) {
				if ($scope.mapsearch.from == '<geolocating, please wait...>') {
					return;
				}
				if (newVal) {
					if (timeout) {
						$scope.directions = new Array;
						$timeout.cancel(timeout);
					}
					timeout = $timeout(function() {
						$scope.onChange();
					}, 600);
				}
				;
			});

			/**
			 * When the user clicks on the button, redraw the map.
			 */
			$scope.onChange = function() {
				var from = $scope.mapsearch.from;
				var to = $scope.mapsearch.to;
				if ((from == "") || (to == "")) {
					return;
				}
				$scope.getDirections(from, to, function($scope) {
					// We could have set it directly in getDirections(), but it's more flexible to
					// provide this callback function to return the data array.
					return function(directions) {
						$scope.directions = directions;
					};
				}($scope));
			};

			/**
			 * Get current location.
			 */
			$scope.getLocation = function() {
				navigator.geolocation.getCurrentPosition(function(position) {
					var latitude = position.coords.latitude;
					var longitude = position.coords.longitude;
					$scope.mapsearch.from = latitude + ',' + longitude;
					$scope.onChange();
				}, function(error) {
					$scope.mapsearch.from = 'Boston, MA';
					$scope.onChange();
					console.log('Error with accessing navigation information');
				}, {
					enableHighAccuracy : true,
					timeout : 5000,
					maximumAge : 0
				});
			};

			/**
			 * Perform the call to MapQuest and populate the information on the form. Provide a
			 * callback to set the data somewhere.
			 */
			$scope.getDirections = function(from, to, callback) {
				var apikey = 'mjtd%7Clu61200ynl%2Cas%3Do5-50ylq';
				var url = 'http://www.mapquestapi.com/directions/v1/route?key=' + apikey + '&from='
						+ encodeURIComponent(from) + '&to=' + encodeURIComponent(to);
				$http.get("cgi-bin/getData.py", {
					params : {
						url : url
					}
				}).success(
						function(data, status, headers, config) {
							try {
								console.log(data);
								var directions = new Array;
								var counter = 0;
								directions.tripDistance = data.route.distance;
								directions.tripTime = data.route.formattedTime;
								var maneuvers = data.route.legs[0].maneuvers;

								// Loop through each maneuver.
								angular.forEach(maneuvers, function(maneuver) {
									var direction = {
										image : maneuver.iconUrl,
										step : counter++,
										link : maneuver.mapUrl,
										text : maneuver.narrative,
										distance : maneuver.distance
									};
									directions.push(direction);
								});
								// Call the callbackmethod to return the data.
								callback(directions);
							} catch (error) {
								alert('Unable to access MapQuest. Please try again later. Error: '
										+ error.message);
							}
						}).error(function(data, status, headers, config) {
					alert('Unable to access MapQuest. Please try again later.');
				});
			};
		});
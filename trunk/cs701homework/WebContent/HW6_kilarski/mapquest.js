/*
 * author: Ryszard Kilarski
 * email: emrys@bu.edu
 * BUI ID: U81-39-8560
 */

angular.module('angularMapquest', []).controller(
		'mapquestController',
		function($scope, $http) {
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
			 * When the user changes either the from or to value, redraw the
			 * map.
			 */
			$scope.onClick = function() {
				var from = $scope.mapsearch.from;
				var to = $scope.mapsearch.to;
				if ((from == "") || (to == "")) {
					return;
				}
				$scope.getDirections(from, to);
			};
			/**
			 * Object to interact with navigation.
			 */
			$scope.getLocation = function() {
				navigator.geolocation.getCurrentPosition(function(position) {
					var latitude = position.coords.latitude;
					var longitude = position.coords.longitude;
					$scope.mapsearch.from = latitude + ',' + longitude;
					$scope.onClick();
				}, function(error) {
					$scope.mapsearch.from = 'Boston, MA';
					$scope.onClick();
					console.log('Error with accessing navigation information');
				}, {
					enableHighAccuracy : true,
					timeout : 5000,
					maximumAge : 0
				});
			};

			/**
			 * Perform the call to MapQuest and populate the information on the
			 * form.
			 */
			$scope.getDirections = function(from, to) {
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
								$scope.directions = directions;
							} catch (error) {
								alert('Unable to access MapQuest. Please try again later. Error: '
										+ error.message);
							}
						}).error(function(data, status, headers, config) {
					alert('Unable to access MapQuest. Please try again later.');
				});
			};
		});
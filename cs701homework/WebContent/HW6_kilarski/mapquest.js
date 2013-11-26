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
				// dom.hideDirections();
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
			 * When the user changes either the from or to value, redraw the map.
			 */
			$scope.onClick = function() {
				var from = $scope.mapsearch.from;
				var to = $scope.mapsearch.to;
				if ((from == "") || (to == "")) {
					// dom.hideDirections();
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
			 * Perform the call to MapQuest and populate the information on the form.
			 */
			$scope.getDirections = function(from, to) {
				// dom.resetDirections();
				// dom.insertWaitListItem();
				// dom.showDirections();
				// var url = mapquest.buildURL(from, to);
				var apikey = 'mjtd%7Clu61200ynl%2Cas%3Do5-50ylq';
				var url = 'http://www.mapquestapi.com/directions/v1/route?key=' + apikey + '&from='
						+ encodeURIComponent(from) + '&to=' + encodeURIComponent(to);
				$http({
					method : 'JSONP',
					url : url
				}).success(function(data) {
					console.log(data);
					var directions = [];
					var counter = 0;
					$scope.directions.tripDistance = data.route.distance;
					$scope.directions.tripTime = data.route.formattedTime;
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
				});
			};
		});

/**
 * dom object to control all DOM interactions.
 */
var dom = {
	hideDirections : function() {
		dom.resetDirections();
		$("#resultsarea").hide();
	},
	showDirections : function() {
		$("#resultsarea").show();
	},
	resetDirections : function() {
		$('#directions').empty();
	}
};

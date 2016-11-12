var app = angular.module('wafepaApp', 
	['ngRoute']);
app.config(function($routeProvider) {
	$routeProvider.when('/home',{
		templateUrl:'/static/app/html/partials/home.html'
	});
});
app.controller('myCtrl', 
	function ($scope) {
		$scope.osoba = {};
		$scope.osoba.ime = 'Pera';
		$scope.predstaviSe = function(){
			alert($scope.osoba.ime+' '+
				$scope.osoba.prezime);
		}
	}
);

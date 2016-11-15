var app = angular.module('wafepaApp', 
	['ngRoute']);
app.config(function($routeProvider) {
	$routeProvider.when('/home',{
		templateUrl:'/static/app/html/partials/home.html'
	});
	$routeProvider.when('/domaci',{
		templateUrl:'/static/app/html/partials/domaci.html'
	});
	$routeProvider.otherwise({
            redirectTo: '/domaci'
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
app.controller('zadatak1',function ($scope) {
	$scope.tekst='';
	$scope.ponisti = function(){
		$scope.tekst='';
	}
});

app.controller('zadatak2', function($scope){
	$scope.prebaci = function(){
		$scope.polje1 = $scope.polje2;
	}
});
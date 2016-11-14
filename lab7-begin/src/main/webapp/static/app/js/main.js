var wafepaApp = angular.module('wafepaApp', ['ngRoute']);

wafepaApp.controller('MyController', function($scope) {
	
	$scope.tekst = 'Cao ja sam tekst';
	
	$scope.broj = 5;
	
	$scope.objekat = { polje: 'vrednost' };
	
	$scope.lista = [3, 4, 'haha', {}, []];
	
	$scope.funkcija = function(p) {
		$scope.lista[4] = $scope.lista[0]*$scope.lista[0];
	};

});

wafepaApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : '/static/app/html/partial/home.html'
        })
        .when('/activities', {
            templateUrl : '/static/app/html/partial/activities.html'
        })
        .otherwise({
            redirectTo: '/'
        });
}]);
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
        .when('/editActivity/:id', {
            templateUrl : '/static/app/html/partial/editActivity.html',
            controller: 'editActivityCtrl'
        })
        .when('/activities', {
            templateUrl : '/static/app/html/partial/activities.html',
            controller: 'activitiesCtrl'
        })
        .otherwise({
            redirectTo: '/',
        });
}]);



wafepaApp.controller('activitiesCtrl', function($scope,$http,$location){
	var ucitajAktivnosti = function () {
		$http.get('api/activities')
			.success(function (data, status) {
				$scope.activities = data;
				$scope.act = {};
			})
			.error(function (data, status) {
				console.log('ERROR!');
			});
	}
	ucitajAktivnosti();
	$scope.deleteActivity = function (id) {
		$http.delete('api/activities/'+id)
		.success(function() {
			ucitajAktivnosti();
		});
	}
	// $scope.act={};
	$scope.saveActivity = function () {
		console.log(JSON.stringify($scope.act));
		if(!$scope.act.id){
			$http.post('api/activities',$scope.act)
			.success(function () {
				ucitajAktivnosti();
			});
		}
		else{
			$http.put('api/activities/'+$scope.act.id,$scope.act)
			.success(function () {
				ucitajAktivnosti();
			});			
		}
	}
	$scope.editActivity = function(activity){
		$scope.act = activity;
	}
	$scope.editNewPage = function(activity){
		$location.path('editActivity/'+activity.id);
	}
});
wafepaApp.controller('editActivityCtrl',function ($scope,$http,$routeParams,$location) {
	var ucitajAktivnost = function () {
		$http.get('api/activities/'+$routeParams.id)
		.success(function (data, status) {
			$scope.activity = data;
		})
	}
	ucitajAktivnost();
	$scope.save = function () {
		$http.put('api/activities/'+$routeParams.id,$scope.activity)
		.success(function () {
			$location.path('activities');
		});
	}
})
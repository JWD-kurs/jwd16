var wafepaApp = angular.module('wafepaApp', ['ngRoute','ui.bootstrap']);

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
        .when('/books', {
            templateUrl : '/static/app/html/partial/books.html',
            controller: 'booksCtrl'
        })
        .otherwise({
            redirectTo: '/',
        });
}]);

wafepaApp.controller('booksCtrl', function ($http,$scope) {
	var getBooks = function (){
		var parameters = {}
		if($scope.search&&$scope.search.name){
			parameters.name=$scope.search.name;
		}
		$http.get('api/books',{params:parameters})
		.success(function (data) {
			$scope.books = data;
		})
		.error(function (data,status) {
			console.log('error',status);
		});
	}
	var getAuthors = function (){
		$http.get('api/authors')
		.success(function (data) {
			$scope.authors = data;
		})
		.error(function (data,status) {
			console.log('error',status);
		});
	}
	getBooks();
	getAuthors();
	$scope.formatAuthors = function(authors){
		retVal='';
		for (var i = 0; i < authors.length; i++) {
			retVal+=authors[i].firstName+' '+authors[i].lastName+','
		}
		return retVal;
	}
	$scope.saveBook = function () {
		var auths = []
		for (var i = 0; i < $scope.authors.length; i++) {
			if($scope.newBook.authors[i]){
				auths.push({'id':$scope.newBook.authors[i]});
			}
		};
		var bookForSending = {};
		bookForSending.title = $scope.newBook.title;
		bookForSending.authors = auths;
		$http.post('api/books',bookForSending)
		.success(function () {
			getBooks()
		});
	}
	$scope.findBooks = function(){
		getBooks();
	}
});

wafepaApp.controller('activitiesCtrl', function($scope,$http,$location,$uibModal){
	$scope.currentPage=0;
	$scope.changePage = function (i) {
		$scope.currentPage += i;
		console.log($scope.currentPage);
		ucitajAktivnosti();
	}
	var ucitajAktivnosti = function () {
		var parameters = {};
		if ($scope.filter && $scope.filter.name) {
			parameters.name = $scope.filter.name;
		};
		parameters.page = $scope.currentPage;
		$http.get('api/activities',{params:parameters})
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
				$scope.alerts.push({type:'success',msg:'Aktivnost '+$scope.act.name+' je uspesno dodata'})
				$scope.filter = {};
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
	$scope.alerts = [
    	// { type: 'danger', msg: 'Oh snap! Change a few things up and try submitting again.' },
    	// { type: 'success', msg: 'Well done! You successfully read this important alert message.' }
  	];

	$scope.addAlert = function() {
		$scope.alerts.push({msg: 'Another alert!'});
	};

	$scope.closeAlert = function(index) {
		$scope.alerts.splice(index, 1);
	};

	$scope.open = function (size) {
	    var modalInstance = $uibModal.open({
	      templateUrl: '/static/app/html/partial/modal.html',
	      controller: 'ModalInstanceCtrl',
	      size: size
	    });
	}

	$scope.search = function(){
		console.log($scope.filter);
		ucitajAktivnosti();
	}
	
});

wafepaApp.controller('ModalInstanceCtrl', function ($uibModalInstance, $scope) {
  $scope.ok = function () {
    $uibModalInstance.close('ok');
  };

  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };
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
// routes
(function () {
	'use strict';
	angular.module('MyRoutes', [])
        .config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
			$locationProvider.hashPrefix('');
            $routeProvider
                .when('/', {
                    templateUrl: 'views/home/home.html',
					controller: 'homeCtrl',
					title:'Inicio'
                })
                /* Default */
                .otherwise({
                    redirectTo: '/'
                });
            }]);
})();

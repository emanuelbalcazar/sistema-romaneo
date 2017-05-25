// routes
(function () {
	'use strict';
	angular.module('MyRoutes', ['ngRoute'])
        .config(['$routeProvider', function ($routeProvider) {

            $routeProvider
                .when('/', {
                    templateUrl: 'views/home/home.html',
					title:'Inicio'
                })
				.when('/resume', {
                    templateUrl: 'views/logger/resume.html',
					controller: 'loggerCtrl',
					title:'Resumen de Logs'
                })
                /* Default */
                .otherwise({
                    redirectTo: '/'
                });
            }]);
})();

// routes
(function () {
	'use strict';
	angular.module('MyRoutes', ['ngRoute'])
        .config(['$routeProvider', function ($routeProvider) {

            $routeProvider
                .when('/', {
                    templateUrl: 'views/navigation/home.html',
					title:'Inicio'
                })
								.when('/sender', {
				                    templateUrl: 'views/messageSender.html',
									controller: 'messageSenderCtrl',
									title:'Enviar Mensajes'
				                })
                /* Default */
                .otherwise({
                    redirectTo: '/'
                });
            }]);
})();

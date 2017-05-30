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
				.when('/resume', {
                    templateUrl: 'views/resume/resume.html',
					controller: 'loggerCtrl',
					title:'Resumen de Logs'
                })
				.when('/message', {
					templateUrl: 'views/message/list.html',
					controller: 'messageCtrl',
					title:'Mensajes'
				})
				.when('/mobile/:id', {
					templateUrl: 'views/mobile/view.html',
					controller: 'mobileViewCtrl',
					title:'Mensajes del dispositivo'
				})
                /* Default */
                .otherwise({
                    redirectTo: '/'
                });
            }]);
})();

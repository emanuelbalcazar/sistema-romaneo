// declare a module
(function () {
	'use strict';
	angular.module('myApp', [
		'ngRoute',
		'MyRoutes',
		'controllerModule',
		'serviceModule'
		]).run(['$rootScope',
		function($rootScope){
			$rootScope.title = "Introducción a la programación web con AngularJS"
		}
	]).config(['$locationProvider', function($locationProvider) {
        $locationProvider.hashPrefix('');
    }]);
})();

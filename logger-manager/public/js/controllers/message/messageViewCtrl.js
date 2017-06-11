(function () {
  'use strict';
    angular.module('controllerModule')
    .controller('messageViewCtrl', ['$scope', '$rootScope', '$location', '$interval', '$routeParams', 'loggerSrv', messageViewCtrl]);

    // Controlador de la pantalla de un mensaje en particular permitiendo seguir su traza.
    function messageViewCtrl($scope, $rootScope, $location, $interval, $routeParams, loggerSrv) {
        if (!$routeParams.id) {
            alert('No se encontro un id valido para el mensaje');
            location.path('/');
        }

        $scope.id = $routeParams.id;
        $scope.type = $routeParams.type;

    } // end messageCtrl
})();

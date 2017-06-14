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

        $scope.messageId = $routeParams.id;
        $scope.messageType = $routeParams.type;
        $scope.allCandidates = [];

        // Busca por el imei del dispositivo todos los mensajes asociados.
        function findMessage() {
            loggerSrv.findMessage($scope.messageId, $scope.messageType).then(function(records) {
                $scope.allCandidates = records;

                $scope.allCandidates.sort(function(a, b) {
                   return a.id - b.id;
                });

                paginate();
            });
        }

        findMessage();

        // Pagination
       function paginate() {
           $scope.totalItems = $scope.allCandidates.length;
           $scope.currentPage = 1;
           $scope.itemsPerPage = 6;

           $scope.$watch("currentPage", function() {
               setPagingData($scope.currentPage);
           });

           function setPagingData(page) {

               var pagedData = $scope.allCandidates.slice(
                   (page - 1) * $scope.itemsPerPage,
                   page * $scope.itemsPerPage
               );

               $scope.aCandidates = pagedData;
           }
       }

    } // end messageCtrl
})();

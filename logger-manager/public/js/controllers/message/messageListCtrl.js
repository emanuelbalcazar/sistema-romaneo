(function () {
  'use strict';
    angular.module('controllerModule')
    .controller('messageListCtrl', ['$scope', '$rootScope', '$location', '$interval', 'loggerSrv', messageListCtrl]);

    // Controlador de la pantalla del listado de mensajes.
    function messageListCtrl($scope, $rootScope, $location, $interval, loggerSrv) {

        $scope.allCandidates = [];

        var findAllInterval = $interval(findAll, 2000);

        // Retorna todos los mensajes registrados en la base de datos.
        function findAll() {
            loggerSrv.findAllMessages().then(function(messages) {
                $scope.allCandidates = messages;

                $scope.allCandidates.sort(function(a, b) {
                   return a.id - b.id;
                });

                paginate();
            });
        }

        // Redirecciona a la vista de ver un mensaje en particular.
        $scope.viewMessage = function(message) {
            $location.path('message/' + message.messageId + '/' + message.messageType);
        }

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

       // Si el usuario abandona la pagina, detiene la ejecucion del $interval
       $scope.$watch(function() {
           return $location.path();
       }, function(newPath, oldPath) {
           if (newPath != oldPath) {
               cancelIntervals();
           }
       });

       // cancela la ejecucion de la funcion $interval
       function cancelIntervals() {
           $interval.cancel(findAllInterval);
       }

       $scope.formatedDate = function(d) {
           return loggerSrv.getFormattedDate(d);
       }

    } // end messageListCtrl
})();
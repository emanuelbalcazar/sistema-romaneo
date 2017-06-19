(function () {
  'use strict';
    angular.module('controllerModule')
    .controller('messageListCtrl', ['$scope', '$rootScope', '$location', '$window', 'loggerSrv', messageListCtrl]);

    // Controlador de la pantalla del listado de mensajes.
    function messageListCtrl($scope, $rootScope, $location, $window, loggerSrv) {

        $scope.allCandidates = [];

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

        findAll();

        // Redirecciona a la vista de ver un mensaje en particular.
        $scope.viewMessage = function(message) {
            $location.path('message/' + message.messageId + '/' + message.messageType);
        }

        // Refresca la pantalla con nuevos datos.
        $scope.refresh = function() {
            findAll();
        }

        // Redirecciona a la ultima pagina visitada.
        $scope.back = function() {
            $window.history.back();
        }

        // Pagination
        function paginate() {
           $scope.totalItems = $scope.allCandidates.length;
           $scope.currentPage = 1;
           $scope.itemsPerPage = 5;

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

       $scope.formatedDate = function(d) {
           return loggerSrv.getFormattedDate(d);
       }

    } // end messageListCtrl
})();

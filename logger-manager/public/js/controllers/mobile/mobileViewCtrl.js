(function () {
  'use strict';
    angular.module('controllerModule')
    .controller('mobileViewCtrl', ['$scope', '$location', '$window', '$routeParams', 'loggerSrv', mobileViewCtrl]);

    // Permite ver un dispositivo y todos sus mensajes enviados/recibidos.
    function mobileViewCtrl($scope, $location, $window, $routeParams, loggerSrv) {

        if (!$routeParams.id) {
            alert('No se encontro un imei valido para el dispositivo');
            location.path('/');
        }

        $scope.imei = $routeParams.id;
        $scope.allCandidates = [];
        $scope.filterToApply = "";

        // Busca por el imei del dispositivo todos los mensajes asociados.
        function findMessagesByMobile() {
            loggerSrv.findMessagesByMobile($scope.imei).then(function(messages) {
                $scope.allCandidates = messages;

                $scope.allCandidates.sort(function(a, b) {
                   return a.id - b.id;
                });

                if ($scope.filterToApply != "") {
                    $scope.filter($scope.filterToApply);
                    return;
                }
                paginate();
            });
        }

        findMessagesByMobile();

        // Refresca la pantalla con nuevos datos.
        $scope.refresh = function() {
            findMessagesByMobile();
        }

        // Redirecciona a la vista de ver un mensaje en particular.
        $scope.viewMessage = function(message) {
            $location.path('message/' + message.messageId + '/' + message.messageType);
        }

        // Redirecciona a la ultima pagina visitada.
        $scope.back = function() {
            $window.history.back();
        }

        $scope.filter = function(filter) {
            $scope.filterToApply = filter;
            $scope.refresh();
            $scope.allCandidates = loggerSrv.filter($scope.allCandidates, filter);
            paginate();
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

    } // end mobileViewCtrl
})();

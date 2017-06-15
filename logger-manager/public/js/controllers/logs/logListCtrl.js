(function () {
  'use strict';
    angular.module('controllerModule')
    .controller('logListCtrl', ['$scope', '$rootScope', '$location', 'loggerSrv', '$uibModal', logListCtrl]);

    // Controlador de la pantalla de logs.
    function logListCtrl($scope, $rootScope, $location, loggerSrv, $modal) {
        $scope.allCandidates = [];

        // Retorna todos los logs registrados en la base de datos.
        function findAll() {
            loggerSrv.findAllLogs().then(function(logs) {
                $scope.allCandidates = logs;

                $scope.allCandidates.sort(function(a, b) {
                   return a.id - b.id;
                });

                paginate();
            });
        }

        findAll();

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

        $scope.formatedDate = function(d) {
           return loggerSrv.getFormattedDate(d);
        }

   } // end logCtrl
})();

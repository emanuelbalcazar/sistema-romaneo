(function () {
  'use strict';
    angular.module('controllerModule')
    .controller('logListCtrl', ['$scope', '$rootScope', '$location', '$interval', 'loggerSrv', logListCtrl]);

    // Controlador de la pantalla de logs.
    function logListCtrl($scope, $rootScope, $location, $interval, loggerSrv) {
        $scope.allCandidates = [];

        var findAllInterval = $interval(findAll, 2000);

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

   } // end logCtrl
})();

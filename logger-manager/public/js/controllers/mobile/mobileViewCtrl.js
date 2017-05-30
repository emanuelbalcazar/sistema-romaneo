(function () {
  'use strict';
    angular.module('controllerModule')
    .controller('mobileViewCtrl', ['$scope', '$location', '$interval', '$routeParams', 'loggerSrv', mobileViewCtrl]);

    // Permite ver un dispositivo y todos sus mensajes enviados/recibidos.
    function mobileViewCtrl($scope, $location, $interval, $routeParams, loggerSrv) {

        if (!$routeParams.id) {
            alert('No se encontro un imei valido para el dispositivo');
            location.path('/');
        }

        $scope.imei = $routeParams.id;
        $scope.allCandidates = [];

        function findMessagesByMobile() {
            loggerSrv.findMessagesByMobile($scope.imei).then(function(messages) {
                $scope.allCandidates = messages;

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
           $scope.itemsPerPage = 8;

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

        findMessagesByMobile();


    } // end mobileCtrl
})();

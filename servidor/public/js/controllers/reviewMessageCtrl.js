(function () {
  'use strict';
    angular.module('controllerModule')
    .controller('reviewMessageCtrl', ['$scope', '$rootScope', '$location','messageSrv', reviewMessageCtrl]);

    // Controlador de la pantalla de mensajes.
    function reviewMessageCtrl($scope, $rootScope, $location, messageSrv) {

        $scope.allCandidates = [];

        messageSrv.findAll().then(function(response){
          $scope.allCandidates = response;
            paginate();
        });

        $scope.resend = function(message) {
            messageSrv.resend(message).then(
                function(response) {
                    $scope.allCandidates = response;
                    paginate();
                });
        }

        $scope.reject = function(message) {
            messageSrv.reject(message).then(
                function(response) {
                    $scope.allCandidates = response;
                    paginate();
                });
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

    } // end messageSenderCtrl
})();

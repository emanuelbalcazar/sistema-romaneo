(function () {
  'use strict';
    angular.module('controllerModule')
    .controller('loggerCtrl', ['$scope', '$rootScope', '$location', '$interval', 'loggerSrv', loggerCtrl]);

    // Controlador del logger.
    function loggerCtrl($scope, $rootScope, $location, $interval, loggerSrv) {

        var countInterval = $interval(countAllLogs, 2000);
        var resumeInterval = $interval(getResume, 3000);

        $scope.running = true;
        $scope.quantity = 'Conectando...';
        $scope.allCandidates = [];
        $scope.data = [];

        // Obtiene la cantidad de logs registrados en la DB.
        function countAllLogs() {
            loggerSrv.countAll().then(function(all) {
                if (all.status) {
                    $scope.quantity = 'Sin conexion';
                    return;
                }
                $scope.quantity = all;
            });
        }

        // Obtiene los logs agrupados por cantidad y arma un resumen de mensajes.
        function getResume() {
            loggerSrv.getResume().then(function(resume) {
                if (resume.status) return; // si hubo un error salgo de la funcion.

                for (var i = 0; i < resume.length; i++) {
                    if (!$scope.data[resume[i].imei]) {
                        $scope.data[resume[i].imei] = {imei: resume[i].imei}; // si no existe el objeto con dicho imei, lo crea.
                    }

                    // if ($scope.data[resume[i].status])
                        $scope.data[resume[i].imei][resume[i].status] = resume[i].logs;
                }

                $scope.allCandidates = $scope.data;
                paginate();
            });
        };

        // Redirecciona a la pantall de visualizacion de mensajes de un dispositivo particular.
        $scope.viewMobile = function(mobile) {
            $location.path('mobile/' + mobile.imei);
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
            $scope.running = false;
            $interval.cancel(countInterval);
            $interval.cancel(resumeInterval);
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

    } // end loggerCtrl
})();

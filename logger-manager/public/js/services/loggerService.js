(function () {
  'use strict';
  angular.module('serviceModule').factory('loggerSrv', ['$http', loggerSrv]);

    // Logger Service
    function loggerSrv($http) {

        var restApi = '/api';

        // Funciones que provee el servicio.
        var service = {
            countAll: countAll,
            getResume: getResume,
            findMessagesByMobile: findMessagesByMobile
        };

        return service;

        // Retorna la cantidad de Logs registrados.
        function countAll() {
            return $http.get(restApi + '/logs/countAll').then(function success(response) {
                return response.data;
            },
            function error(error) {
                return error;
            });
        }

        function getResume() {
            return $http.get(restApi + '/resume').then(function success(response) {
                return response.data;
            },
            function error(error) {
                return error;
            });
        }

        function findMessagesByMobile(imei) {
            return $http.get(restApi + '/mobile/' + imei).then(function success(response) {
                return response.data;
            },
            function error(error) {
                return error;
            });
        }

    } // end loggerSrv
})();

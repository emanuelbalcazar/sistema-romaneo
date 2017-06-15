(function () {
  'use strict';
  angular.module('serviceModule').factory('messageSrv', ['$http', messageSrv]);

    // Logger Service
    function messageSrv($http) {

        var restApi = '/api';

        // Funciones que provee el servicio.
        var service = {
            senderMsg: senderMsg
        };

        return service;

        // Retorna la cantidad de Logs registrados.
        function senderMsg(msg) {
            return $http.post(restApi + '/new', {data: msg}).then(function success(response) {
                return response.data;
            },
            function error(error) {
                return error;
            });
        }


    } // end loggerSrv
})();
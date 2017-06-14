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
            findMessagesByMobile: findMessagesByMobile,
            findMessage : findMessage,
            findAllMessages: findAllMessages
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

        // Retorna un resumen de cantidad de logs registrados en la base de datos.
        function getResume() {
            return $http.get(restApi + '/resume').then(function success(response) {
                return response.data;
            },
            function error(error) {
                return error;
            });
        }

        // Retorna todos los mensajes generados por un dispositivo movil.
        function findMessagesByMobile(imei) {
            return $http.get(restApi + '/mobile/' + imei).then(function success(response) {
                return response.data;
            },
            function error(error) {
                return error;
            });
        }

        // Retorna todos los registros de un mensaje buscado por id y tipo.
        function findMessage(id, type) {
            return $http.get(restApi + '/message/' + id + '/' + type).then(function success(response) {
                return response.data;
            },
            function error(error) {
                return error;
            });
        }

        // Retorna todos los mensajes generados.
        function findAllMessages() {
            return $http.get(restApi + '/messages').then(function success(response) {
                return response.data;
            },
            function error(error) {
                return error;
            });
        }

    } // end loggerSrv
})();

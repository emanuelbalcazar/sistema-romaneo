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
            findAllMessages: findAllMessages,
            findAllLogs: findAllLogs,
            getFormattedDate: getFormattedDate,
            filter: filter
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

        // Retorna todos los registros de logs persistidos en la base de datos.
        function findAllLogs() {
            return $http.get(restApi + '/logs').then(function success(response) {
                return response.data;
            },
            function error(error) {
                return error;
            });
        }

        // Formatea una fecha recibida como String a un formato: dd/mm/yyyy HH:mm:ss
        function getFormattedDate(d) {
            var date = new Date(d);

            var year = date.getFullYear();

            var month = (1 + date.getMonth()).toString();
            month = month.length > 1 ? month : '0' + month;

            var day = date.getDate().toString();
            day = day.length > 1 ? day : '0' + day;

            var minutes = date.getMinutes().toString();
            minutes = minutes.length > 1 ? minutes : '0' + minutes;

            var seconds = date.getSeconds().toString();
            seconds = seconds.length > 1 ? seconds : '0' + seconds;

            var milliseconds = date.getMilliseconds().toString();
            milliseconds = milliseconds.length > 1 ? milliseconds : '0' + milliseconds;

            return day + '/' + month + '/' + year + ' - ' + date.getHours() + ':'
                    + minutes + ':' + seconds + ':' + milliseconds;
        }

        // Filtra un arreglo de elementos.
        function filter(logs, filter) {
            if (filter == "") {
                return logs;
            }

            var result = logs.filter(function(log) {
                return log.level == filter;
            });

            return result;
        }

    } // end loggerSrv
})();

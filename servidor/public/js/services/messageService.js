(function () {
  'use strict';
  angular.module('serviceModule').factory('messageSrv', ['$http', messageSrv]);

    // Logger Service
    function messageSrv($http) {

        var restApi = '/api';

        // Funciones que provee el servicio.
        var service = {
            senderMsg: senderMsg,
            findAll:findAll,
            reject: reject,
            resend: resend
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

        //Obtiene todos los mensajes de la cola de Error.
        function findAll(){
          return $http.get(restApi + '/findAll').then(function success(response) {
              return response.data;
          },
          function error(error) {
              return error;
          });
        }

        function reject(msg) {
            return $http.post(restApi + '/reject', {data: msg}).then(function success(response) {
                return response.data;
            },
            function error(error) {
                return error;
            });
        }

        function resend(msg) {
            return $http.post(restApi + '/resend', {data: msg}).then(function success(response) {
                return response.data;
            },
            function error(error) {
                return error;
            });
        }


    } // end loggerSrv
})();

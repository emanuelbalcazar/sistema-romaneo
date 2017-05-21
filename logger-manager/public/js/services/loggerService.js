(function () {
  'use strict';
  angular.module('serviceModule').factory('loggerSrv', ['$http', loggerSrv]);

    function loggerSrv($http) {

        var service = {
            findAll: findAll
        }

        return service;

        function findAll() {
            return $http.get('http://localhost:3000/api/findAll').then(function success(response){
                return response.data;
            },
            function error(error) {
                return error;
            });
        }
    }
})();

(function () {
  'use strict';
  angular.module('controllerModule').controller('homeCtrl', ['$scope', 'loggerSrv', homeCtrl]);

    function homeCtrl($scope, loggerSrv) {

        $scope.findAll = function() {
            loggerSrv.findAll().then(function(response) {
                alert(JSON.stringify(response));
            });
        }

    }
})();

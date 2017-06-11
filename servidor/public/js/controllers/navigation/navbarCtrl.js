(function () {
  'use strict';
  angular.module('controllerModule').controller('navbarCtrl', ['$scope', '$rootScope', '$location', navbarCtrl]);

    // Controlador del navbar.
    function navbarCtrl($scope, $rootScope, $location) {

        $scope.goToHome = function() {
            $location.path('/');
        };

        $scope.goToMessageSender = function() {
            $location.path('sender');
        };
    }
})();

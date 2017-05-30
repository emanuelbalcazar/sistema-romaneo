(function () {
  'use strict';
  angular.module('controllerModule').controller('navbarCtrl', ['$scope', '$rootScope', '$location', navbarCtrl]);

    // Controlador del navbar.
    function navbarCtrl($scope, $rootScope, $location) {

        $scope.goToHome = function() {
            $location.path('/');
        };

        $scope.goToResume = function() {
            $location.path('resume');
        };

        $scope.goToMessage = function() {
            $location.path('message');
        };
    }
})();

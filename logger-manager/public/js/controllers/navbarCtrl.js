(function () {
  'use strict';
  angular.module('controllerModule').controller('navbarCtrl', ['$scope', '$location', navbarCtrl]);

    // Controlador del navbar.
    function navbarCtrl($scope, $location) {

        $scope.goToResume = function() {
            $location.path('resume');
        };
    }
})();

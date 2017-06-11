(function () {
  'use strict';
    angular.module('controllerModule')
    .controller('messageSenderCtrl', ['$scope', '$rootScope', '$location','messageSrv', messageSenderCtrl]);

    // Controlador de la pantalla de mensajes.
    function messageSenderCtrl($scope, $rootScope, $location,messageSrv) {

      $scope.message = "";
      $scope.showSuccess= false;
      $scope.showDanger= false;
      $scope.sendMessage =sendMessage;
      $scope.resetLayer= resetLayer;

      /**
      *Funcion encargada de enviar un Mesaje desde el servidor al Cliente
      *
      */
      function sendMessage(){

        if($scope.message==""){
          $scope.showDanger=true;
          $scope.messageDanger="Ingrese el mensaje para el Cliente"
          return;
        }

        var formatMessage = {
          id:1,
          imei: 1,
          priority: 4,
          type: 'TEXTO',
          subType:'',
          operation:'',
          timestamp: new Date(),
          text: $scope.message

        }
        messageSrv.senderMsg(formatMessage).then(function(response){
          console.log(response);
          $scope.showSuccess=true;
          $scope.messageSuccess='Mensaje enviado con Exito';
        });
      }

      /**
      *
      *
      */
      function resetLayer(){

        $scope.message="";
        $scope.showSuccess= false;
        $scope.showDanger= false;

      }

    } // end messageSenderCtrl
})();

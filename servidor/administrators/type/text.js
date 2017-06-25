// text.js - modulo encargado de recibir un mensaje de texto comun ya validado y
// generar la operacion correspondiente indicada en el mensaje, eso implica generar el log
// y la sentencia de base de datos correspondiente. (crear).
var rabbitmq = require('../../rabbitmq/rabbitmq-api');
var logger = require('../../logger/logger');
var maxPriority = require('../../config_files/message-config.json').maxPriority;

var id = 0;

exports.getType = function() {
    return 'texto';
}

function saveMessage(message){

  // var fs = require('fs');
  //
  // fs.writeFile('/home/luna/texto_comun.txt', message.text,function(error){
  //   if (error)
  //     console.log(error);
  //   else
  //     console.log('El archivo fue creado, se guardo el mensaje');
  //   });
}

exports.receivedMessage = function(message) {
    // saveMessage(message);
    var sleep = getRandomNumber(5000, 10000);
    setTimeout(function() {
        console.log('Procesando mensajes de texto... tiempo de espera ' + sleep);
    }, sleep);

    logger.logInfo(message, 'servidor', 'CONFIRMADO', 'se recibio el mensaje de TEXTO ', message.text);
    sendConfirmMessage(message);
}

// Genera un mensaje de CONFIRMACION y lo envia a rabbitmq.
function sendConfirmMessage(message) {

    var confirmMessage = {
        id: ++id,
        priority : maxPriority,
        type: 'CONFIRMADO',
        messageId: message.id,
        messageType: message.type,
        messageSubType: message.subType || '',
        imei: message.imei,
        description: ''
    };

    logger.logTrace(message, 'servidor', 'ENVIADO', 'enviado el mensaje de CONFIRMACION de TEXTO');
    rabbitmq.publishMessage(confirmMessage);
}

// Retorna un numero aleatorio entre un rango de numeros pasados como parametros.
function getRandomNumber(low, high) {
    return Math.random() * (high - low) + low;
}

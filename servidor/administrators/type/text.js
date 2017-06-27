// text.js - modulo encargado de recibir un mensaje de texto comun ya validado y
// generar la operacion correspondiente indicada en el mensaje, eso implica generar el log
// y la sentencia de base de datos correspondiente. (crear).
var rabbitmq = require('../../rabbitmq/rabbitmq-api');
var logger = require('../../logger/logger');
var maxPriority = require('../../config_files/message-config.json').maxPriority;
var id = 0;

var async = require('async');
var tasks = [];
var probability = 0;
var sleep = 0;

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
    sleep = getRandomNumber(3000, 10000);

    async.parallel([verify(message, sleep)], function(err, result) {
        //console.log('Async parallel with array', result);
    });
}


var verify = function(message, sleep) {
    return function(done) {
        // task 1 completes in 100ms
        setTimeout(function() {
            sendConfirmMessage(message);
            done(null, message);
        }, sleep);
    }
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

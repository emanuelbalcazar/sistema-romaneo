// romaneo.js - modulo encargado de recibir un mensaje de tipo romaneo ya validado y
// generar la operacion correspondiente indicada en el mensaje, eso implica generar el log
// y la sentencia de base de datos correspondiente. (crear, actualizar).
var rabbitmq = require('../../rabbitmq/rabbitmq-api');
var logger = require('../../logger/logger');
var maxPriority = require('../../config_files/message-config.json').maxPriority;
var probError = require('../../config_files/message-config.json').probError;

var async = require('async');
var tasks = [];

var id = 0; // TODO - cambiar por un id autogenerado unico.
var probability = 0;
var sleep = 0;

// Retorna el tipo de administrador.
exports.getType = function() {
    return 'romaneo';
};

// Recibe el mensaje delegado por el administrador base.
exports.receivedMessage = function(message) {

    probability = getRandomNumber(0, 100);
    sleep = getRandomNumber(5000, 1000);

    async.parallel([verify(message, sleep)], function(err, result) {
        //console.log('Async parallel with array', result);
    });

}

var verify = function(message, sleep) {
    return function(done) {
        // task 1 completes in 100ms
        setTimeout(function() {
            // verifica si el mensaje posee algun error (ficticio) y genera el mensaje de error correspondiente.
            if ((probability < probError) || (message.subType == "ERROR")) {
                sendErrorMessage(message);
            } else {
                //logger.logTrace(message, 'servidor', 'CONFIRMADO', 'se genero la sentencia INSERT en la tabla ROMANEO ' + message.subType);
                sendConfirmMessage(message);
            }
            console.log('Done function ', message);
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

    //logger.logTrace(message, 'servidor', 'ENVIADO', 'enviado el mensaje de CONFIRMACION de ROMANEO ' + confirmMessage.messageSubType);
    rabbitmq.publishMessage(confirmMessage);
}

// Genera un mensaje de ERROR y lo envia a rabbitmq.
function sendErrorMessage(message) {

    var errorMessage = {
        id: ++id,
        priority : maxPriority,
        type: 'ERROR',
        messageId: message.id,
        messageType: message.type,
        messageSubType: message.subType || '',
        imei: message.imei,
        description: 'no se pudo generar la sentencia debido a un error'
    };

    logger.logError(errorMessage, 'servidor', 'ERROR', 'no se pudo generar la sentencia debido a un error de transacción');
    rabbitmq.publishMessage(errorMessage);
    rabbitmq.publishMessageError(errorMessage);
}

// Retorna un numero aleatorio entre un rango de numeros pasados como parametros.
function getRandomNumber(low, high) {
    return Math.random() * (high - low) + low;
}

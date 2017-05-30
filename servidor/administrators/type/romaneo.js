// romaneo.js - modulo encargado de recibir un mensaje de tipo romaneo ya validado y
// generar la operacion correspondiente indicada en el mensaje, eso implica generar el log
// y la sentencia de base de datos correspondiente. (crear, actualizar).
var rabbitmq = require('../../rabbitmq/rabbitmq-api');
var logger = require('../../logger/logger');
var maxPriority = require('../../config_files/message-config.json').maxPriority;
var probError = require('../../config_files/message-config.json').probError;

var id = 0; // TODO - cambiar por un id autogenerado unico.

// Retorna el tipo de administrador.
exports.getType = function() {
    return 'romaneo';
};

// Recibe el mensaje delegado por el administrador base.
exports.receivedMessage = function(message) {

    var probability = getRandomNumber(0, 100);

    if ((probability < probError) || (message.subType == "ERROR")) {
        sendErrorMessage(message);
    } else {
        logger.logInfo(message, 'cliente', 'CONFIRMADO', 'se genero la sentencia INSERT en la tabla ROMANEO ' + messageSubType);
        sendConfirmMessage(message);
    }
};

// Genera un mensaje de CONFIRMACION y lo envia a rabbitmq.
function sendConfirmMessage(message) {

    var confirmMessage = {
        id: ++id,
        priority : maxPriority,
        type: 'CONFIRM',
        messageId: message.id,
        messageType: message.type,
        messageSubType: message.subType || '',
        imei: message.imei,
        description: ''
    };

    logger.logInfo(message, 'servidor', 'ENVIADO', 'enviado el mensaje de CONFIRMACION de ROMANEO ' + confirmMessage.subType);
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

    logger.logError(errorMessage, 'cliente', 'ERROR', 'no se pudo generar la sentencia debido a un error');
    rabbitmq.publishMessage(errorMessage);
}

// Retorna un numero aleatorio entre un rango de numeros pasados como parametros.
function getRandomNumber(low, high) {
    return Math.random() * (high - low) + low;
}

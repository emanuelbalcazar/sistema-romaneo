// romaneo.js - modulo encargado de recibir un mensaje de tipo romaneo ya validado y
// generar la operacion correspondiente indicada en el mensaje, eso implica generar el log
// y la sentencia de base de datos correspondiente. (crear, actualizar).
var rabbitmq = require('../../rabbitmq/rabbitmq-api');
var logger = require('../../logger/logger');
var maxPriority = require('../../config_files/message-config.json').maxPriority;

var id = 0; // TODO - cambiar por un id autogenerado unico.

// Retorna el tipo de administrador.
exports.getType = function() {
    return 'romaneo';
}

// Recibe el mensaje delegado por el administrador base.
exports.receivedMessage = function(message) {

    logger.logInfo(message, 'servidor', 'CONFIRMADO', 'se genero la sentencia INSERT en la tabla ROMANEO');
    sendConfirmMessage(message);
}

// Genera un mensaje de CONFIRMACION y lo envia a rabbitmq.
function sendConfirmMessage(message) {

    var confirmMessage = {
        id: ++id,
        priority : maxPriority,
        type: 'confirm',
        id_msg: message.id,
        type_msg: message.type,
        imei: message.imei,
        description: '',
        timestamp: new Date()
    }

    logger.logInfo(message, 'servidor', 'ENVIADO', 'enviado el mensaje de CONFIRMACION de ROMANEO');
    rabbitmq.publishMessage(confirmMessage);
}

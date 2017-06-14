// geolocation.js - modulo encargado de recibir un mensaje de tipo geolocalizacion ya validado y
// generar la operacion correspondiente indicada en el mensaje, eso implica generar el log
// y la sentencia de base de datos correspondiente. (crear).
var rabbitmq = require('../../rabbitmq/rabbitmq-api');
var logger = require('../../logger/logger');
var maxPriority = require('../../config_files/message-config.json').maxPriority;


exports.getType = function() {
    return 'geolocalizacion';
}

exports.receivedMessage = function(message) {
    logger.logTrace(message, 'servidor', 'CONFIRMADO', 'se genero la sentencia INSERT en la tabla GEOLOCALIZACION');
}

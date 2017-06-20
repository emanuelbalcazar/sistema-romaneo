// parser.js - modulo encargado de parsear los mensajes recibidos y verificar su formato.
// en caso de que el mensaje posea un formato incorrecto se debe generar un mensaje de error
// y publicarlo en la cola de RabbitMQ para ser consumido por el cliente correspondiente.
var Ajv = require('ajv');
var ajv = new Ajv({allErrors: true}); // options can be passed, e.g. {allErrors: true}
var validator = require('./validator');
var baseSchema = require('./schemas/baseMessage.json');
var rabbitmq = require('../rabbitmq/rabbitmq-api');
var logger = require('../logger/logger');

var maxPriority = require('../config_files/message-config.json').maxPriority;
var id = 0;

// Valida el formato del mensaje recibido.
// TODO - posiblemente, utilizemos el async para procesar multiples mensajes
exports.setMessage = function(message) {
    var validBaseSchema = ajv.validate(baseSchema, message);

    if (validBaseSchema) {
        validateTypeMessage(message);
    } else {
        generateErrorMessage(ajv.errorsText(), message);

    }
};

// Valida el formato del mensaje acorde al tipo de mensaje.
function validateTypeMessage(message) {
    var schema = require('./schemas/' + message.type.toLowerCase() + '.json');
    var valid = ajv.validate(schema, message);

    if (valid) {
        validator.validateMessage(message); // el mensaje es correcto en formato y se envia al validador.
    } else {
        generateErrorMessage(ajv.errorsText(), message);
    }
}

// Genera un mensaje de error de formato invalido y lo envia a encolar
// en RabbitMQ con maxima prioridad.
function generateErrorMessage(error, message) {

    var errorMessage = {
        id: ++id,
        priority : maxPriority,
        type: 'error',
        id_msg: message.id,
        type_msg: message.type,
        imei: message.imei,
        description: error,
        timestamp: new Date()
    };

    logger.logError(message, 'servidor', 'formato de mensaje invalido: ' + error);
    rabbitmq.publishMessage(errorMessage);
    rabbitmq.publishMessageError(errorMessage);
}

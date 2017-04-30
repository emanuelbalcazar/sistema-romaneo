// parser.js - modulo encargado de parsear los mensajes recibidos y verificar su formato.
// en caso de que el mensaje posea un formato incorrecto se debe generar un mensaje de error
// y publicarlo en la cola de RabbitMQ para ser consumido por el cliente correspondiente.
validator = require('./validator');

exports.setMessage = function(message) {
    // TODO - posiblemente, utilizemos el async para procesar multiples mensajes
    validator.validateMessage(message);
}

// validator.js - modulo encargado de validar el contenido del mensaje.
// en caso de que el mensaje posea datos invalidos o mal formados se debe generar un mensaje de error
// y publicarlo en la cola de RabbitMQ para ser consumido por el cliente correspondiente.
var administrator = require('../administrators/administrator');

exports.validateMessage = function(message) {
    // TODO - posiblemente, utilizemos el async para procesar multiples mensajes
    administrator.setMessage(message);
}

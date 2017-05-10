// administrator.js - modulo encargado de recibir un mensaje ya validado y delegarlo al
// administrador correspondiente segun el tipo de mensaje. Cada tipo de mensaje posee su
// propio administrador debido a que no todos se procesan de igual manera.

var factory = require('./administratorFactory');

exports.setMessage = function(message) {

    var administrator = factory.getAdministrator(message.type.toLowerCase());
    if (administrator) {
        administrator.receivedMessage(message);
    } else {
        console.error('No existe una implementacion para el tipo de mensaje');
    }
}

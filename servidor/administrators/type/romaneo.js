// romaneo.js - modulo encargado de recibir un mensaje de tipo romaneo ya validado y
// generar la operacion correspondiente indicada en el mensaje, eso implica generar el log
// y la sentencia de base de datos correspondiente. (crear, actualizar).
var log = require('../../utils/logger').getLogger('romaneo');

exports.getType = function() {
    return 'romaneo';
}

exports.receivedMessage = function(message) {
    
    log.info('Se genero la sentencia insert del mensaje ROMANEO');
    console.log('\nSe genero la sentencia insert del mensaje ROMANEO: ', message);
}

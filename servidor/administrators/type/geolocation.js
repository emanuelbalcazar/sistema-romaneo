// geolocation.js - modulo encargado de recibir un mensaje de tipo geolocalizacion ya validado y
// generar la operacion correspondiente indicada en el mensaje, eso implica generar el log
// y la sentencia de base de datos correspondiente. (crear).
var log = require('../../utils/logger').getLogger('geolocalizacion');

exports.getType = function() {
    return 'geolocalizacion';
}

exports.receivedMessage = function(message) {

    log.info('Se genero la sentencia insert del mensaje geolocalizacion');
    console.log('\nSe genero la sentencia insert del mensaje GEOLOCALIZACIÓN: ', message);
}

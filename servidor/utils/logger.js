// Modulo que retorna un Logger configurado para escribir en la consola
// y en un archivo.
var log4js = require('log4js');

log4js.configure({
    appenders: [
        { type: 'console' },
        { type: 'file', filename: 'logs/geolocalizacion.log', category: 'geolocalizacion' },
        { type: 'file', filename: 'logs/romaneo.log', category: 'romaneo' },
        { type: 'file', filename: 'logs/texto.log', category: 'texto' }
    ]
});

exports.getLogger = function(type) {
    return log4js.getLogger(type.toLowerCase());
}

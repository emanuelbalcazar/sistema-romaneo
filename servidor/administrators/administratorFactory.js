// administratorFactory.js - modulo encargado de retornar la implementacion de un
// administrador de mensaje acorde al tipo de mensaje recibido. Si dicha implementacion
// no existe entonces retorna null.

exports.getAdministrator = function(type) {
    var administrator;

    switch (type) {
        case 'romaneo':
            administrator = require('./type/romaneo');
            break;
        case 'geolocalizacion':
            administrator = require('./type/geolocation');
            break;
        case 'texto':
            administrator = require('./type/text');
            break;
        default:
            administrator = null;
    }

    return administrator;
}

/**
* Modelo del objeto Log persistido en la Base de Datos.
**/
var Type = require('sequelize');
var sequelize = require('../database/connection').sequelize;

// Defino el esquema del Log para realizar el mapeo a la tabla correspondiente.
var Log = sequelize.define('logs', {
    id: {type: Type.INTEGER, primaryKey: true},
    id_msg: {type: Type.INTEGER},
    imei: {type: Type.INTEGER},
    source: {type: Type.STRING},
    level: {type: Type.STRING},
    type_msg: {type: Type.STRING},
    description: {type: Type.STRING},
    timestamp: {type: Type.DATE}
});

module.exports.Log = Log;

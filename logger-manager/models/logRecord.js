/**
* Modelo del objeto Log persistido en la Base de Datos.
**/
var Type = require('sequelize');
var sequelize = require('../database/connection').sequelize;

// Defino el esquema del Log para realizar el mapeo a la tabla correspondiente.
var Log = sequelize.define('logs', {
    id: {type: Type.INTEGER, primaryKey: true, allowNull: false, autoIncrement: true},
    messageId: {type: Type.INTEGER},
    imei: {type: Type.INTEGER},
    source: {type: Type.STRING},
    status: {type: Type.STRING},
    level: {type: Type.STRING},
    messageType: {type: Type.STRING},
    description: {type: Type.STRING},
    timestamp: {type: Type.DATE}
});

module.exports.Log = Log;

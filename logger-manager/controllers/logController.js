var logRecord = require('../models/logRecord').Log;
var sequelize = require('../database/connection').sequelize;


// Retorna la cantidad de registros de logs.
exports.countAll = function(callback) {
    logRecord.count().then(function(all) {
        callback(false, all);
    });
}

//Agrupa por cada dispositivo, la cantidad de mensajes de cada tipo
exports.findResumen = function(callback) {
    logRecord.findAll({
        attributes: ['imei', 'status', [sequelize.fn('COUNT', sequelize.col('*')), 'logs']],
        group: ['imei','status'],
        where: {source: 'cliente'}
    }).then(function(all) {
        callback(false, all);
    });
}

// Retorna todos los mensajes generdos por un dispositivo movil.
exports.findMessagesByMobile = function(imei, callback) {
    logRecord.findAll({
        where: {imei: imei}
    }).then(function(all) {
        callback(false, all);
    });
}

// Retorna todos los registros de un mensaje buscado por su id y tipo.
exports.findMessage = function(id, type, callback) {
    logRecord.findAll({
        where: {messageId: id, messageType: type}
    }).then(function(all) {
        callback(false, all);
    });
}

// Retorna todos los logs de mensajes registrados.
exports.findAllMessages = function(callback) {
    logRecord.findAll({
        where: {level: 'info'}
    }).then(function(all) {
        callback(false, all);
    });
}

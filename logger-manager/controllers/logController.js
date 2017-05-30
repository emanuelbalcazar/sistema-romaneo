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

exports.findMessagesByMobile = function(imei, callback) {
    logRecord.findAll({
        where: {imei: imei}
    }).then(function(all) {
        callback(false, all);
    });
}

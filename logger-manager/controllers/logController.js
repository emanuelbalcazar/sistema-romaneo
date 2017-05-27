var logRecord = require('../models/logRecord').Log;
var sequelize = require('../database/connection').sequelize;


module.exports.findAll = function(callback) {

    logRecord.findAll().then(function(all) {
        callback(false, all);
    });
}
//Agrupa por cada dispositivo, la cantidad de mensajes de cada tipo
module.exports.findResumen = function(callback) {

  logRecord.findAll({
    attributes: ['imei', 'status', [sequelize.fn('COUNT', sequelize.col('*')), 'logs']],
    group: ['imei','status']
  }).then(function(all) {
    callback(false, all);
  });
}

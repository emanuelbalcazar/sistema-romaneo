var logRecord = require('../models/logRecord').Log;

module.exports.findAll = function(callback) {

    logRecord.findAll().then(function(all) {
        callback(false, all);
    });
}

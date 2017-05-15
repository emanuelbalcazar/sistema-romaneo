var rabbitmq = require('../rabbitmq/rabbitmq-api');

/**
* Funcion que permite generar un mensaje de log y persistirlo en rabbitmq.
* @param message mensaje que dispara el log.
* @param source fuente de donde se genera el log (cliente o servidor)
* @param description descripcion del log generado.
**/
function Logger() {

    function logInfo(message, source, description) {
        createLog(message, 'info', source, description);
    }

    function logError(message, source, description) {
        createLog(message, 'error', source, description);
    }

    function logDebug(message, source, description) {
        createLog(message, 'debug', source, description);
    }

    // funcion privada para crear el log acorde al nivel indicado.
    function createLog(message, level, source, description) {

        var logRecord = {
            id_msg: message.id,
            imei: message.imei,
            source: source || 'servidor',
            target: ' ',
            level: level,
            type_msg: message.type,
            description: description || '',
            timestamp: new Date()
        }

        pushLog(logRecord);
    }

    // Publica el log en rabbitmq en la cola de Logger.
    function pushLog(logRecord) {
        rabbitmq.publishLoggerMessage(logRecord);
    }

    // Funciones disponibles para el objeto new Logger().
    return {
        logInfo: logInfo,
        logError: logError,
        logDebug: logDebug
    }
}

module.exports = new Logger();

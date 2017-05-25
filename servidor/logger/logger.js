var rabbitmq = require('../rabbitmq/rabbitmq-api');

/**
* Funcion que permite generar un mensaje de log y persistirlo en rabbitmq.
* @param message mensaje que dispara el log.
* @param source fuente de donde se genera el log (cliente o servidor)
* @param description descripcion del log generado.
**/
function Logger() {

    function logInfo(message, source, status, description) {
        createLog(message, 'info', source, status, description);
    }

    function logError(message, source, status, description) {
        createLog(message, 'error', source, status, description);
    }

    function logDebug(message, source, status, description) {
        createLog(message, 'debug', source, status, description);
    }

    // funcion privada para crear el log acorde al nivel indicado.
    function createLog(message, level, source, status, description) {

        var logRecord = {
            messageId: message.id,
            imei: message.imei,
            source: source || 'servidor',
            status: status,
            level: level,
            messageType: message.type,
            description: description || '',
            timestamp: new Date()
        };

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
    };
}

module.exports = new Logger();

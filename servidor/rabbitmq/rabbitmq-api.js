// rabbitmq-api.js - intermediario entre la aplicacion servidor y RabbitMQ.
var amqp = require('amqplib/callback_api');
var serverUrl = require('../config_files/rabbit-config.json').serverUrl;
var clientUrl = require('../config_files/rabbit-config.json').clientUrl;
var loggerUrl = require('../config_files/rabbit-config.json').loggerUrl;

var serverQueue = require('../config_files/rabbit-config.json').serverQueue;
var clientQueue = require('../config_files/rabbit-config.json').clientQueue;
var loggerQueue = require('../config_files/rabbit-config.json').loggerQueue;

var parser = require('../parser/parser');
var logger = require('../logger/logger');
var rabbitmq = require('../rabbitmq/rabbitmq-api');

var maxPriority = require('../config_files/message-config.json').maxPriority;
var id = 0;


// Retorna el driver utilizado para realizar la conexion a RabbitMQ.
exports.getDriverConnector = function() {
    return amqp;
};

// Retorna la url donde se encuentra corriendo RabbitMQ.
exports.getServerUrl = function() {
    return serverUrl;
};

// Retorna el nombre de la cola de la cual consume el servidor.
exports.getServerQueueName = function() {
    return serverQueue;
};

// Inicia el consumo de mensajes desde la cola del servidor en RabbitMQ.
exports.startConsumer = function() {

    amqp.connect(serverUrl, function(err, conn) {   // inicio la conexion a rabbitmq
        if (err) throw err;

        conn.createChannel(function(err, ch) {  // creo un canal de comunicacion
            ch.assertQueue(serverQueue, {durable: true});
            console.log(" [*] Servidor esperando mensajes en", serverQueue);

            ch.consume(serverQueue, function(msg) { // consumo un mensaje
                var message = JSON.parse(msg.content);
                console.log(" [x] Recibido %s ", JSON.stringify(message));
                parser.setMessage(message);
                publishReceivedAck(message);
            }, {noAck: true});
        });
    });
};

// Genera un mensaje de RECIBIDO y lo envia a encolar a rabbitmq.
function publishReceivedAck(message) {

    var received = {
        id: ++id,
        priority : maxPriority,
        type: 'RECIBIDO',
        messageId: message.id,
        messageType: message.type,
        messageSubType: message.subType || '',
        imei: message.imei,
        description: 'mensaje recibido por el servidor',
    };

    logger.logInfo(message, 'servidor', 'RECIBIDO', 'mensaje recibido');
    rabbitmq.publishMessage(received);
}

// Publica un mensaje en la cola del cliente de parte del servidor.
exports.publishMessage = function(message) {

    var ex = 'messages';
    var key = message.imei.toString();

    amqp.connect(clientUrl, function(err, conn) {   // inicio la conexion a rabbitmq
        if (err) throw err;

        conn.createChannel(function(err, ch) {  // creo un canal de comunicacion
            ch.assertExchange(ex, 'direct', {durable: false});
            ch.publish(ex, key, new Buffer(JSON.stringify(message)));
            console.log("\n [x] Publicado %s con clave %s", JSON.stringify(message), key);
        });
    });
};

// Publica un mensaje de log en la cola del logger.
exports.publishLoggerMessage = function(message) {
    amqp.connect(loggerUrl, function(err, conn) {   // inicio la conexion a rabbitmq
        if (err) throw err;

        conn.createChannel(function(err, ch) {  // creo un canal de comunicacion
            ch.assertQueue(loggerQueue, {durable: true});
            ch.sendToQueue(loggerQueue, new Buffer(JSON.stringify(message)));   // publico el mensaje
            console.log("\n [x] Publicado Logger %s en %s", JSON.stringify(message), loggerQueue);
        });
    });
};

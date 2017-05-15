// rabbitmq-api.js - intermediario entre la aplicacion servidor y RabbitMQ.
var amqp = require('amqplib/callback_api');
var serverUrl = require('../config_files/rabbit-config.json').serverUrl;
var clientUrl = require('../config_files/rabbit-config.json').clientUrl;
var loggerUrl = require('../config_files/rabbit-config.json').loggerUrl;

var serverQueue = require('../config_files/rabbit-config.json').serverQueue;
var clientQueue = require('../config_files/rabbit-config.json').clientQueue;
var loggerQueue = require('../config_files/rabbit-config.json').loggerQueue;

var parser = require('../parser/parser');


// Retorna el driver utilizado para realizar la conexion a RabbitMQ.
exports.getDriverConnector = function() {
    return amqp;
}

// Retorna la url donde se encuentra corriendo RabbitMQ.
exports.getServerUrl = function() {
    return serverUrl;
}

// Retorna el nombre de la cola de la cual consume el servidor.
exports.getServerQueueName = function() {
    return serverQueue;
}

// Inicia el consumo de mensajes desde la cola del servidor en RabbitMQ.
exports.startConsumer = function() {

    amqp.connect(serverUrl, function(err, conn) {   // inicio la conexion a rabbitmq
        if (err) throw err;

        conn.createChannel(function(err, ch) {  // creo un canal de comunicacion
            ch.assertQueue(serverQueue, {durable: true});
            console.log(" [*] Waiting for messages in %s. To exit press CTRL + C ", serverQueue);

            ch.consume(serverQueue, function(msg) { // consumo un mensaje
                var message = JSON.parse(msg.content);
                console.log(" [x] Recibido %s ", JSON.stringify(message));
                parser.setMessage(message);

            }, {noAck: true});
        });
    });
}

// Publica un mensaje en la cola del cliente de parte del servidor.
exports.publishMessage = function(message) {

    amqp.connect(clientUrl, function(err, conn) {   // inicio la conexion a rabbitmq
        if (err) throw err;

        conn.createChannel(function(err, ch) {  // creo un canal de comunicacion
            ch.assertQueue(clientQueue, {durable: true});
            ch.sendToQueue(clientQueue, new Buffer(JSON.stringify(message)));   // publico el mensaje
            console.log(" [x] Publicado %s en %s", JSON.stringify(message), clientQueue);
        });
    });
}

// Publica un mensaje de log en la cola del logger.
exports.publishLoggerMessage = function(message) {
    amqp.connect(loggerUrl, function(err, conn) {   // inicio la conexion a rabbitmq
        if (err) throw err;

        conn.createChannel(function(err, ch) {  // creo un canal de comunicacion
            ch.assertQueue(loggerQueue, {durable: true});
            ch.sendToQueue(loggerQueue, new Buffer(JSON.stringify(message)));   // publico el mensaje
            console.log(" [x] Publicado Logger %s en %s", JSON.stringify(message), loggerQueue);
        });
    });
}

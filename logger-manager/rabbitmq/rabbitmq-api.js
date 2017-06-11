// rabbitmq-api.js - intermediario entre la aplicacion servidor y RabbitMQ.
var amqp = require('amqplib/callback_api');
var loggerUrl = require('../config_files/rabbit-config.json').loggerUrl;
var loggerQueue = require('../config_files/rabbit-config.json').loggerQueue;
var logRecord = require('../models/logRecord').Log;

// Inicia el consumo de mensajes desde la cola del logger-manager en RabbitMQ.
exports.startConsumer = function() {

    amqp.connect(loggerUrl, function(err, conn) {   // inicio la conexion a rabbitmq
        if (err) throw err;

        conn.createChannel(function(err, ch) {  // creo un canal de comunicacion
            ch.assertQueue(loggerQueue, {durable: true});
            console.log(" [*] Logger Manager esperando mensajes en %s ", loggerQueue);

            ch.consume(loggerQueue, function(msg) { // consumo un mensaje
                var message = JSON.parse(msg.content);
                console.log(" [x] Recibido %s ", JSON.stringify(message));
                persistMessage(message);

            }, {noAck: true}); // al consumir el mensaje lo remueve de rabbitmq.
        });
    });
}

// Persiste el mensaje de tipo Log en la base de datos.
function persistMessage(message) {

    logRecord.create(message).then(function(data) {
        //console.log('Insertado ', data);
    });
}

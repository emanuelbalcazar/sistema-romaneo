// send.js - modulo de prueba para enviar a la cola del servidor un mensaje personalizado.
// se puede utilizar este modulo como testeo del procesamiento de un mensaje desde que llega
// a la cola de rabbitmq hasta que es utilizado por los administradores de mensajes para generar
// las sentencias de base de datos correspondiente sin necesidad de tener un cliente ejecutandose.

var amqp = require('amqplib/callback_api');
var serverUrl = require('../config_files/rabbit-config.json').serverUrl;
var serverQueue = require('../config_files/rabbit-config.json').serverQueue;

amqp.connect(serverUrl, function(err, conn) {

    if (err) throw err;

    conn.createChannel(function(err, ch) {
        var message = {};
        message.text = process.argv.slice(2).join(' ') || 'Hello World!';
        message.type = 'geolocalizacion'

        ch.assertQueue(serverQueue, {durable: true});
        ch.sendToQueue(serverQueue, new Buffer(JSON.stringify(message)));

        console.log(" [x] Enviado " + JSON.stringify(message));
    });
});

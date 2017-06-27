// send.js - modulo de prueba para enviar a la cola del servidor un mensaje personalizado.
// se puede utilizar este modulo como testeo del procesamiento de un mensaje desde que llega
// a la cola de rabbitmq hasta que es utilizado por los administradores de mensajes para generar
// las sentencias de base de datos correspondiente sin necesidad de tener un cliente ejecutandose.

var amqp = require('amqplib/callback_api');
var serverUrl = require('../config_files/rabbit-config.json').serverUrl;
var serverQueue = require('../config_files/rabbit-config.json').serverQueue;
var errorsQueue = require('../config_files/rabbit-config.json').errorsQueue;

amqp.connect(serverUrl, function(err, conn) {

    if (err) throw err;

    var message = {
        id: 1,
        type: 'ERROR',
        messageId: 3,
        messageType: 'ROMANEO',
        messageSubType: 'FARDO',
        imei: 0,
        description: 'no se pudo generar la sentencia debido a un error de transaccion'
    }

    conn.createChannel(function(err, ch) {  // creo un canal de comunicacion
        ch.assertQueue(errorsQueue, {durable: true});
        ch.sendToQueue(errorsQueue, new Buffer(JSON.stringify(message)));   // publico el mensaje
        console.log('Mensaje de error enviado ', message);
    });

});

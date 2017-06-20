// router.js - en este modulo se declaran las rutas manejadas por el servidor.
var express = require('express');
var url = require('url');
var router = express.Router();
var rabbit = require('../rabbitmq/rabbitmq-api');

// ruta principal.
router.get('/', function(request, response) {
    response.send('Aplicacion inicializada!');
});

// ruta principal.
router.post('/api/new', function(request, response) {
    console.log(request.body.data);
    rabbit.publishMessage(request.body.data);
    rabbit.publishMessageSent(request.body.data);
    response.sendStatus(200);
});

module.exports = router;

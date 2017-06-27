// router.js - en este modulo se declaran las rutas manejadas por el servidor.
var express = require('express');
var url = require('url');
var router = express.Router();
var rabbit = require('../rabbitmq/rabbitmq-api');

// ruta principal.
router.get('/', function(request, response) {
    response.send('Aplicacion inicializada!');
});

router.post('/api/new', function(request, response) {
    console.log(request.body.data);
    rabbit.publishMessage(request.body.data);
    rabbit.publishMessageSent(request.body.data);
    response.sendStatus(200);
});

router.get('/api/findAll', function(request, response) {
  var errorM = rabbit.getAllErrorMessages();

    response.json(errorM);
});

router.post('/api/reject', function(request, response) {
    var errors = rabbit.reject(request.body.data);
    response.json(errors);
});

router.post('/api/resend', function(request, response) {
    var data = rabbit.resend(request.body.data);
    response.json(data);
});

module.exports = router;

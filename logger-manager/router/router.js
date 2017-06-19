// router.js - en este modulo se declaran las rutas manejadas por el logger-manager.
var express = require('express');
var router = express.Router();
var logController = require('../controllers/logController');

// Ruta principal de la aplicacion.
router.get('/', function(req, res) {
    res.send('aplicacion inicializada');
});

// Retorna la cantidad de registros de logs.
router.get('/api/logs/countAll', function(req, res) {
    logController.countAll(function(error, result) {
        if (error) res.send(error);
        res.jsonp(result);
    });
});

// Retorna un resumen agrupado de logs.
router.get('/api/resume', function(req, res) {
    logController.findResumen(function(error, result) {
        if (error) res.send(error);
        res.send(result);
    });
});

// Retorna los mensajes logeados de un dispositivo en particular.
router.get('/api/mobile/:imei', function(req, res) {
    logController.findMessagesByMobile(req.params.imei, function(error, result) {
        if (error) res.send(error);
        res.send(result);
    });
});

// Retorna los registros de un mensaje buscado por su ID y TIPO.
router.get('/api/message/:id/:type', function(req, res) {
    logController.findMessage(req.params.id, req.params.type, function(error, result) {
        if (error) res.send(error);
        res.send(result);
    });
});

// Retorna todos los mensajes registrados.
router.get('/api/messages', function(req, res) {
    logController.findAllMessages(function(error, result) {
        if (error) res.send(error);
        res.send(result);
    });
});

// Retorna todos los logs registrados.
router.get('/api/logs', function(req, res) {
    logController.findAllLogs(function(error, result) {
        if (error) res.send(error);
        res.send(result);
    });
});

module.exports = router;

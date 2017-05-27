// router.js - en este modulo se declaran las rutas manejadas por el logger-manager.
var express = require('express');
var url = require('url');
var router = express.Router();
var logController = require('../controllers/logController');

router.get('/api/findAll', function(req, res) {
    console.log('entro a find');

    logController.findAll(function(error, result) {
        res.send(result);
    });
});

router.get('/api/findResumen', function(req, res) {
    console.log('entro a find');

    logController.findResumen(function(error, result) {
        res.send(result);
    });
});

router.get('/*', function(req, res) {
    res.render('index.html');
});

module.exports = router;

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


module.exports = router;

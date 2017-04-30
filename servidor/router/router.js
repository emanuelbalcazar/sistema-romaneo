// router.js - en este modulo se declaran las rutas manejadas por el servidor.
var express = require('express');
var url = require('url');
var router = express.Router();

// ruta principal.
router.get('/', function(request, response) {
    response.send('Aplicacion inicializada!');
});

module.exports = router;

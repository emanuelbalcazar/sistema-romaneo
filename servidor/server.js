// server.js - modulo principal del servidor.
var router = require('./router/router');
var rabbitmq = require('./rabbitmq/rabbitmq-api');
var appPort = require('./config_files/server-config.json').appPort;
var appHost = require('./config_files/server-config.json').appHost;
var express = require('express');
var app = express();

app.use(router);
app.set('host', appHost)
app.set('port', appPort);

app.listen(app.get('port'), function() {
    console.log('Servidor iniciado en %s:%s', app.get('host'), app.get('port'));
    rabbitmq.startConsumer();
});

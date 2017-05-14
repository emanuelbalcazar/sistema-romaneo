/**
* Modulo principal del logger-manager
**/
var router = require('./router/router');
var appPort = require('./config_files/logger-manager.json').appPort;
var appHost = require('./config_files/logger-manager.json').appHost;
var express = require('express');
var app = express();

var log = require('./models/logRecord').Log;

app.use(router);
app.set('host', appHost);
app.set('port', appPort);

app.listen(app.get('port'), function() {
    console.log('Logger manager iniciado en %s:%s', app.get('host'), app.get('port'));
});

// text.js - modulo encargado de recibir un mensaje de texto comun ya validado y
// generar la operacion correspondiente indicada en el mensaje, eso implica generar el log
// y la sentencia de base de datos correspondiente. (crear).
var log = require('../../utils/logger').getLogger('texto');

exports.getType = function() {
    return 'texto';
}

function saveMessage(message){

  // var fs = require('fs');
  //
  // fs.writeFile('/home/luna/texto_comun.txt', message.text,function(error){
  //   if (error)
  //     console.log(error);
  //   else
  //     console.log('El archivo fue creado, se guardo el mensaje');
  //   });
}

exports.receivedMessage = function(message) {
    // saveMessage(message);
    log.info('Se genero la sentencia insert del mensaje TEXTO');
    console.log('\nSe genero la sentencia insert del mensaje TEXTO: ', message);
}

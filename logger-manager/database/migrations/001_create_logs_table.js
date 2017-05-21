/**
* Crea la tabla Logs en la base de datos.
**/
var create_logs_table = new Migration({

	up: function() {
		this.create_table('logs', function(table) {
			table.integer('id', 'INTEGER', {not_null: true}); // id log
			table.integer('messageId');  // id message
			table.integer('imei');  // imei device
			table.string('source');  // client or servers
			table.string('level');  // error, warn, info, debug or trace
			table.string('status');  // generado, enviado, recibido, confirmado
			table.string('messageType');  // error, ack, romaneo, geolocalizacion, etc.
			table.string('description');
			table.datetime('timestamp');
		});
	},

	down: function() {
		this.drop_table('logs');
	}
});

/**
* Inicia la conexion a la Base de Datos y exporta el conector utilizado.
**/
var Sequelize = require('sequelize');

/**
* Sequelize es un ORM que permite trabajar las tablas de base de datos como objetos.
* Se puede configurar Sequelize para que se conecte a otras bases de datos como MySql, por ej.
**/
var sequelize = new Sequelize('database', '', '', {
    dialect: 'sqlite',
    storage: __dirname + '/file/logger.sqlite',
    define: {
        timestamps: false
    }
});

module.exports.sequelize = sequelize;

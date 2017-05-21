# Logger Manager

Sin descripcion.

___


## Preparación (única vez)

Tener instalado RabbitMQ y corriendo en el puerto 5672.

Instalar Node.js:
* `sudo apt-get install -g npm`
* `sudo apt-get install node`

Instalar Bower:
* `sudo npm install -g bower`


## Despliegue

1. Clonar el repositorio: `https://github.com/emanuelbalcazar/sistema-romaneo`
2. Cambiar directorio: `cd sistema-romaneo/`
3. En `server` y `logger-manager` ejecutar: `npm install`
4. En `logger-manager` ejecutar: `bower install`
5. Migrar la base de datos en `logger-manager` con: `npm run migrate`
6. Para visualizar la BD usar algun visualizador de base de datos SQLITE.
7. El archivo de Base de Datos esta en:  `logger-manager/database/file/logger.sqlite`.
8. Verificar configuracion de ejecucion y conexion a RabbitMQ en el `server` y `logger-manager` `/config_files`
9. Ejecutar el `cliente` desarrollado en java, desde un IDE o con un .jar.
___

# Logger Manager

Aplicación utilizada para visualizar los registros de logs generados en el cliente
y servidor cuyos mensajes se depositan en una cola de RabbitMQ.

___


## Preparación (única vez)

Requisitos previos:

- Tener instalado RabbitMQ y corriendo en el puerto 5672 (es el puerto por defecto).
- Generar un Virtualhost llamado `logger`, esta configuración puede cambiarse
en el archivo ubicado en `logger-manager/config_files/rabbit-config.json`

Instalar Node.js:
* `sudo apt-get install -g npm`
* `sudo apt-get install node`

Instalar Bower:
* `sudo npm install -g bower`


## Despliegue

1. Clonar el repositorio: `https://github.com/emanuelbalcazar/sistema-romaneo`.
2. Cambiar directorio: `cd sistema-romaneo/logger-manager`.
3. Ejecutar: `npm install`.
4. Ejecutar: `bower install`.
5. Migrar la base de datos en con el comando: `npm run migrate`.
6. Para visualizar la Base de Datos usar algún visualizador de base de datos SQLITE (opcional).
7. El archivo de Base de Datos esta en:  `logger-manager/database/file/logger.sqlite`.
8. Verificar configuración de ejecución y conexion a RabbitMQ en `logger-manager/config_files/*`.
9. Ejecutar la aplicación usando: `node server.js` o `npm start`.
10. Visualizar la aplicación en el puerto `8000`, según esta definido en `logger-manager/config_files/logger-manager.json`
___

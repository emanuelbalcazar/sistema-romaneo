# Servidor

___


## Preparación (única vez)

Requisitos previos:

- Tener instalado RabbitMQ y corriendo en el puerto 5672 (es el puerto por defecto).
- Generar un Virtualhost llamado `server`, esta configuración puede cambiarse
en el archivo ubicado en `servidor/config_files/rabbit-config.json`

Instalar Node.js:
* `sudo apt-get install -g npm`
* `sudo apt-get install node`

Instalar Bower:
* `sudo npm install -g bower`


## Despliegue

1. Clonar el repositorio: `https://github.com/emanuelbalcazar/sistema-romaneo`.
2. Cambiar directorio: `cd sistema-romaneo/servidor`.
3. Ejecutar: `npm install`.
4. Ejecutar: `bower install`.
5. Verificar configuración de ejecución y conexion a RabbitMQ en `servidor/config_files/*`.
6. Ejecutar la aplicación usando: `node server.js` o `npm start`.
7. Visualizar la aplicación en el puerto `8080`, según esta definido en `servidor/config_files/server-config.json`
___

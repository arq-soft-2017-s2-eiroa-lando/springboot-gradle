# Backend encuestas UNQ

## Desarrollo

Ejecutar main con los siguientes argumentos en la JVM:

-Dspring.profiles.active=dev 

## Test automatico

En la carpeta "automated_test" se encuentra un script bash para ejecutar los siguientes casos de uso:
- Autenticacion
- Creacion de encuesta
- Obtencion de hashes para contestar la encuesta
- Completar una encuesta como alumno
- Obtener las estadisticas de la encuesta

## Produccion

Ejecutar con -Dspring.profiles.active=prod


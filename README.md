# Reto Pokémon Individual

## Pre-requisitos

* Tener Java 11 instalado
* Comprobar los permisos del fichero gradlew (este fichero se encuentra en la raíz del proyecto, y es lo que se emplea para lanzar la aplicación).
* Es muy probable que la terminal muestre que no tenemos permisos para ejecutarlo, por lo que habrá que cambiarlos mediante `chmod u+x gradlew`
* Tener algún programa como Postman (la parte de la API no es necesario probarla con Postman, pero este tipo de programas hace que dicha labor sea más sencilla por la interfaz visual de este tipo de aplicaciones como Postman)

## Puesta en marcha

CONFIGURAR RABBIT
1) Ejecutar: `docker pull rabbitmq:3-management`
2) Ejecutar: `docker run -p 15672:15672 -p 5672:5672 -d --name some-rabbit rabbitmq:3-management`

CONFIGURAR PROYECTO
1) Para asegurar que partimos de un proyecto fresco, ejecutar `./gradlew clean`
2) Para construir la aplicación, ejecutar `./gradlew build`
3) Para lanzar la aplicación, ejecutar `java -jar build/mdas-api-g5-1.0-SNAPSHOT-runner.jar`

## Parar la aplicación

Se recomienda parar la aplicación pulsando la combinación de teclas CTRL+C en la consola donde se lanzó el fichero JAR.

## Probar la aplicación (desde programas tipo Postman)

1) Para consultar la información del pokemon será necesario crear una nueva petición de tipo GET a http://localhost:8081/pokemon/get/3
2) Para crear un usuario nuevo será necesario crear una petición de tipo GET a http://localhost:8081/user/addUser?name=marc&userId=1 
3) Para crear otro usuario nuevo será necesario crear una petición de tipo GET a http://localhost:8081/user/addUser?name=marc&userId=2
4) Para poner como favorito un pokemon al primer usuario será necesario crear una petición de tipo GET a http://localhost:8081/user/addFavouritePokemon?id=3 y en headers poner key:id y value:1
5) Para poner como favorito un pokemon al segundo usuario será necesario crear una petición de tipo GET a http://localhost:8081/user/addFavouritePokemon?id=3 y en headers poner key:id y value:2
6) Para consultar la información del pokemon será necesario crear una nueva petición de tipo GET a http://localhost:8081/pokemon/get/3

De esta forma podremos ver como la primera petición nos devuelve el pokemon con contador a 0 y al final con el contador a 2

## Ejecutar los test

A pesar de que los test unitarios se ejecutan en el momento de ejecutar el comando `./gradlew build`, también se pueden 
ejecutar mediante el comando `./gradlew cleanTest test`.
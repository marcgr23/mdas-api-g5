## Hace todos los puntos pedidos (40%)

#### El nombre del repositorio es el correcto (mdas-web-${nombre}_${apellido})

No, he tenido que renombrar el proyecto para poder hacerme un clone :-(

#### Permite obtener los detalles de un pokemon vía endpoint (datos + número de veces que se ha marcado como favorito)

OK

#### Actualiza el contador de favoritos vía eventos

A medias, ya que no utilizas eventos de dominio sino que envías el id del pokemon que se ha marcado como favorito.

#### ¿Se controlan las excepciones de dominio? Y si se lanza una excepción desde el dominio, ¿se traduce en infraestructura a un código HTTP?

OK

#### Tests unitarios

OK

#### Tests de aceptación

No hay tests de aceptación de la parte solicitada, el flujo de cuando se añade un pokemon como favorito que mediante un
subscriber se actualice en nuestra BBDD.

#### Tests de integración

OK

**Puntuación: 30/40**

## Se aplican conceptos explicados (50%)

#### Separación correcta de capas (application, domain, infrastructure + BC/module/layer)

- Los eventos de dominio con su propio nombre indican lo que acaba de suceder en el sistema.
  Ejemplo: `FavouritePokemonAdded` o `FavouritePokemonAddedEvent`.

- La responsabilidad de crear el evento y añadirlo la tiene el agregado, no el caso de uso. El caso de uso tiene la
  responsabilidad de publicar el evento.

- La clase `RabbitEventReceiver` llama al caso de uso `SetFavouritePokemonUseCase` pasándole el VO `PokemonId` cuando no
  es necesario, con enviar el String o el Integer directamente valdría. Como cuando desde el controlador enviamos al
  caso de uso los datos que hemos recibido, que son primitivos.

- Estás implementando una especie de caché delegando esta responsabilidad en la capa de dominio. La idea es buena, pero
  esta responsabilidad de comprobar si existe en nuestra memoria el pokemon debería estar en infraestructura.

#### Aggregates + VOs

OK

#### No se trabajan con tipos primitivos en dominio

OK

#### Hay servicios de dominio

OK

#### Hay use cases en aplicación reutilizables

OK

#### Se aplica el patrón repositorio

OK

#### Se usan subscribers

OK. Aunque la solución actual no es muy escalable, ya que está orientada a que haya solo un evento en todo el sistema,
cuando una arquitectura basada en eventos, escucha y lanza muchos eventos. Siguiendo el ejemplo comentado en clase, la
anotación `RabbitListener` hubiera sido todo más legible y sencillo de implementar ;-)

#### Se lanzan eventos de dominio

A medias, ya que lo que se lanzan no son eventos de dominio pertenecientes al agregado, sino mensajes sin significado en
sí.

#### Se utilizan object mothers

- Están creados en el package main cuando es algo de uso exclusivo para tests.
- No se utilizan en todos los tests, únicamente se utilizan en los tests de integración del bounded context `users`.

**Puntuación: 43/50**

## Facilidad setup + README (10%)

#### El README contiene al menos los apartados "cómo ejecutar la aplicación", "cómo usar la aplicación"

OK

#### Es sencillo seguir el apartado "cómo ejecutar la aplicación"

OK

**Puntuación: 10/10**

## Extras

- Añadida una cache que permite no andar pidiendo los datos de un pokemon a la poke-api continuamente +5

**Puntuación: +5**

## Observaciones

- Una creación de un recurso, como es la creación del usuario, es un POST no un GET. Al igual que las modificaciones,
  deberían ser PUT, PATCH o POST, nunca GET.

- Tienes muchos imports en las clases sin usar

**PUNTUACIÓN FINAL: 87/100**

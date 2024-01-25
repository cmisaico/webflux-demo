
# Sermaluc Service

Un servicio la creacion de usuarios

## Tecnologias

**Java:** Springboot, JPA, Webflux, Lombok, Swagger, Jwt

**DB:** H2

**Devops:** Docker


## Installation

> **_1. Primera Opcion_**

- Tener instalado Docker

- Clona este repositorio: `git clone https://github.com/cmisaico/sermaluc-demo`

- Situarse dentro de la carpeta raiz del proyecto clonado y ejecutar  lo siguiente  `docker build -t sermaluc-demo .` para construir la imagen.

- Validar que ha sido creado con   ` docker images`

- Luego ejecutar con el siguiente comando `docker run -p 8008:8008 sermaluc-demo`

> **_2. Segunda Opcion_**

- Tener instalado Java 17

- Tener instalado Gradle 8.4+

- Clona este repositorio: `git clone https://github.com/cmisaico/sermaluc-demo`

- Situarse dentro de la carpeta raiz del proyecto clonado y ejecutar  lo siguiente  `gradle build` para construir el jar.

- Luego ejecutar con el siguiente comando `java -jar build/libs/sermaluc-demo-0.0.1-SNAPSHOT.jar`

## Pruebas

> **_1. Primera Opcion_**

- Consumir el servicio por curl

```bash
curl --location 'http://localhost:8008/api/user/create' \
--header 'Content-Type: application/json' \
--data-raw '{
  "name": "Juan Pablo",
  "email": "juanpablo@gmail.com",
  "password": "L123456hutjgj3",
  "phones": [
    {
      "number": "956123435",
      "citycode": "LI",
      "contrycode": "PE"
    }
  ]
}'
```


> **_2. Segunda Opcion_**

- Tener instalado Postman

- Importar los collections _**Sermaluc.postman_collection.json**_ que se encuentra en la carpeta de resources del proyecto clonado y ejecutar las prueba.





## API de Referencia - Swagger

Entrar el enlance donde se visualizara la api en swagger.
```http
http://localhost:8008/swagger-ui/webjars/swagger-ui/index.html
```

## Diagrama de Solucion




![Descripción de la imagen](/src/main/resources/images/sermaluc-service-solution.png "Diagrama de Solucion")

- En el siguiente enlace se encuentra el diagrama de solucion de la aplicacion [Diagrama de Solucion](https://drive.google.com/file/d/1LVpS_XGuvJDCLw0sGrcgeF9VjIL1WlMU/view?usp=sharing)

[Visita mi Sitio Web](https://www.ejemplo.com)


## Authors

- Christian Misaico

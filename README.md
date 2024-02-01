# Test

Esta es la solución diseñada para satisfacer los requisitos establecidos en la prueba.

## Requerimientos

Asegúrate de tener los siguientes requisitos antes de instalar y utilizar la solución:

- Sistema operativo Linux (puede ser Ubuntu 22.04 u otra versión compatible)
- [Amazon Corretto OpenJDK 17](https://aws.amazon.com/es/corretto/) (u otra versión compatible)
- [Gradle 8](https://gradle.org/)
- [Java IDE](https://www.jetbrains.com/idea/) (por ejemplo, IntelliJ IDEA) o compatible
- [Postman](https://www.postman.com/) (por ejemplo, IntelliJ IDEA) o compatible

## Uso

Para utilizar la solución, sigue estos pasos:

1. **Descarga el repositorio usuario:** [Enlace al Repositorio Usuario](https://github.com/janeth-roldan/usuario).
2. Abre el proyecto en tu Java IDE (por ejemplo, IntelliJ IDEA).
3. Al correr el proyecto se va a generar automátocamente la base de datos en memoria H2
5. Para probar los enspoints se lo puede realizar con la url http://localhost:8080/swagger-ui/index.html#/users

### Utilizando el IDE

1. Ejecuta la aplicación.

### para generar y correr el jar 

1. Dentro del directorio `repositorio-usuario`, ejecuta la tarea Gradle `bootJar`.
```bash
   $ chmod +x gradlew
   $ ./gradlew bootJar
```
2. Para correr el jar generado 
```bash
   $ java -jar cuenta-0.0.1-SNAPSHOT.jar
```


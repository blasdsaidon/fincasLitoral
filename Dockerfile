# Usa una imagen base con Java 17
FROM openjdk:17

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR de la aplicación en la imagen
COPY target/fincasdellitoral-0.0.1-SNAPSHOT.jar fincasdellitoral-0.0.1-SNAPSHOT.jar

# Expone el puerto en el que la aplicación se ejecuta (ajusta si es necesario)
EXPOSE 8080

# Comando para ejecutar la aplicación al iniciar el contenedor
CMD ["java", "-jar", "fincasdellitoral-0.0.1-SNAPSHOT.jar"]

#Usar la imagen de ubuntu
FROM ubuntu:24.04
RUN apt-get update
RUN apt-get install openjdk-21-jdk -y
RUN apt-get install maven -y
#Configuramos el directiorio de trabajo
WORKDIR /app
#Copiamos el fichero jar
COPY . .
#Compilamos el codigo
RUN mvn clean package -Dmaven.test.skip=true
#Exponemos el puerto de TomCat
EXPOSE 8081
#Comando para ejecutar la aplicación
CMD ["java", "-jar", "target/garajes-y-empleados-0.0.1-SNAPSHOT.jar"]
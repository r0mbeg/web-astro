FROM openjdk:17

WORKDIR /app

COPY target/WebAstro-*.jar /web-astro.jar

CMD ["java", "-jar", "/web-astro.jar"]
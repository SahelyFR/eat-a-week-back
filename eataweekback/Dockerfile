FROM openjdk
WORKDIR /eataweek-back

COPY target/eataweekback-1.0-SNAPSHOT.jar /eataweek-back/app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]
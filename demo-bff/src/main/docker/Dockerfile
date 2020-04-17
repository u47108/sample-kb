FROM java:8-alpine
# Working directories for Tomcat by default
VOLUME /tmp
EXPOSE 8080
ADD demo-bff-1.0.0-SNAPSHOT.jar demo-bff.jar
RUN sh -c 'touch /demo-bff.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /demo-bff.jar" ]
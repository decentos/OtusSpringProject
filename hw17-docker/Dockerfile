FROM maven:3.6.3-jdk-11

ENV PROJECT_DIR=/opt/project
RUN mkdir - p $PROJECT_DIR
WORKDIR $PROJECT_DIR

COPY ./pom.xml $PROJECT_DIR/pom.xml

RUN mvn -B -s /usr/share/maven/ref/settings-docker.xml dependency:resolve-plugins dependency:resolve clean

COPY . $PROJECT_DIR

RUN mvn -B -s /usr/share/maven/ref/settings-docker.xml clean package

FROM openjdk:11

ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR

COPY --from=0 $PROJECT_DIR/target/DockerApp.jar $PROJECT_DIR

EXPOSE 8080
CMD ["java","-jar","/opt/project/DockerApp.jar"]


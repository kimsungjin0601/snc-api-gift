FROM gradle:9.2.1-jdk25-alpine AS gradle_build_image
WORKDIR /home/gradle/src/

COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src

#COPY . .
RUN gradle clean bootJar
#RUN export PROFILE=${PROFILE} && gradle clean bootJar

FROM openjdk:25-ea-21-jdk-slim
COPY --from=gradle_build_image /home/gradle/src/build/libs/*.jar app.jar
#COPY app/*.jar app.jar

ARG PROFILE=prod
ENV JAVA_OPTS="-Dspring.profiles.active=${PROFILE}"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

EXPOSE 7000

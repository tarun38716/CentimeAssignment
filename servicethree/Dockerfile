FROM davidcaste/alpine-java-unlimited-jce:jdk8
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY ./build/libs/* /usr/app/
RUN mv /usr/app/servicethree-0.0.1-SNAPSHOT.jar /usr/app/app.jar
CMD ["java","-jar","app.jar"]
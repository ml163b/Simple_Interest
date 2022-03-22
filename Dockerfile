FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER ml163b@gmail.com
ADD target/simple_interest-0.0.1-SNAPSHOT.jar  app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar", "-web -webAllowOthers -tcp -tcpAllowOthers -browser"]

FROM openjdk:11
EXPOSE 8081
ADD target/ssjenkins-0.0.1-SNAPSHOT.jar ssjenkins-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/ssjenkins-0.0.1-SNAPSHOT.jar"]

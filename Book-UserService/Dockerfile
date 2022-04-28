FROM java:8-jre
WORKDIR /usr/src
ADD ./target/Book-UserService-0.0.1-SNAPSHOT.jar /usr/src/Book-UserService-0.0.1-SNAPSHOT.jar
#EXPOSE 8002
ENTRYPOINT ["java","-jar","Book-UserService-0.0.1-SNAPSHOT.jar"]

FROM java:8
EXPOSE 8080

ADD target/aws-elasticbeanstalk-example-0.0.1-SNAPSHOT aws-elasticbeanstalk-example-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","aws-elasticbeanstalk-example-0.0.1-SNAPSHOT.jar"]
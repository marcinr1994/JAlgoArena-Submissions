
FROM openjdk:8


ADD build/libs/jalgoarena-submissions-*.jar /opt/jalgoarena-submissions/jalgoarena-submissions.jar

ENV EUREKA_URL=http://eureka:5000/eureka
EXPOSE 5003

ENTRYPOINT java -Dserver.port=5003 -jar /opt/jalgoarena-submissions/jalgoarena-submissions.jar
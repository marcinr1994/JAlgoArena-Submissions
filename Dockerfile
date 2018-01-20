
FROM openjdk:8

WORKDIR /opt/jalgoarena-submissions
ADD build/libs/jalgoarena-submissions-*.jar /opt/jalgoarena-submissions

ENV EUREKA_URL=http://eureka:5000/eureka
EXPOSE 5003

CMD java -Dserver.port=5003 -jar /opt/jalgoarena-submissions/jalgoarena-submissions-*.jar
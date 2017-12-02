FROM mminderbinder/java-jdk:oracle-java8

VOLUME /tmp
RUN mkdir /root/.aws/
COPY credentials /root/.aws/credentials
ADD carerooms-api-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT sh -c 'java -Dspring.profiles.active=${PROFILE} -jar app.jar 2>&1'
EXPOSE 3001
#EXPOSE 80
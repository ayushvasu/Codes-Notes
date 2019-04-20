FROM openjdk:latest
RUN mkdir -p /opt/app
WORKDIR /opt/app
COPY . /opt/app
RUN chmod +x /opt/app/app-assembly.jar
RUN chmod +x /opt/app/run_jar.sh
#CMD java -jar /opt/app/app-assembly.jar
ENTRYPOINT ["/opt/app/run_jar.sh"]

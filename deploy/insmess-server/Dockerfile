FROM openjdk:11 
MAINTAINER  xujq
ADD entrypoint.sh /home/entrypoint.sh
ADD application.properties /home/application.properties
ADD .env /home/.env
ADD openvidu-server-2.25.0.jar /home/openvidu-server-2.25.0.jar
RUN chmod +x /home/entrypoint.sh
WORKDIR /home
ENTRYPOINT ["./entrypoint.sh"]
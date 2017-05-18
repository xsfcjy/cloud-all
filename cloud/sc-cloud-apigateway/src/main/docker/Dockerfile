#FROM xsfcjy/java:jdk8
FROM tifayuki/java:8
VOLUME /tmp
RUN mkdir /app
ADD sc-cloud-apigateway-0.0.1-RELEASE.jar /app/app.jar
ADD runboot.sh /app/
RUN bash -c 'touch /app/app.jar'
WORKDIR /app
RUN chmod a+x runboot.sh
EXPOSE 8181
CMD /app/runboot.sh
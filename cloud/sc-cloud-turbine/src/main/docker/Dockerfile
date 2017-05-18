#FROM xsfcjy/java:jdk8
FROM tifayuki/java:8
VOLUME /tmp
RUN mkdir /app
ADD sc-cloud-turbine-0.0.1-RELEASE.jar /app/app.jar
ADD runboot.sh /app/
RUN bash -c 'touch /app/app.jar'
WORKDIR /app
RUN chmod a+x runboot.sh
EXPOSE 2181
EXPOSE 2182
EXPOSE 2183
CMD /app/runboot.sh
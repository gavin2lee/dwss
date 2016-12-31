FROM ubuntu:14.04
MAINTAINER gavin <gavin2lee@163.com>
ENV REFRESHED_AT 2016-12-31

COPY sources.list /etc/apt/sources.list
RUN apt-get -yqq update
RUN apt-get -yqq install wget

VOLUME [ "/var/lib/tomcat7/webapps/" ]
WORKDIR /var/lib/tomcat7/webapps/

ENTRYPOINT [ "wget" ]
CMD [ "-?" ]

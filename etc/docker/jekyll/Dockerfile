FROM ubuntu:14.04
MAINTAINER gavin <gavin2lee@163.com>
ENV REFRESHED_AT 2016-12-30

COPY sources.list /etc/apt/sources.list

RUN apt-get -yqq update
RUN apt-get -yqq install ruby2.0 ruby2.0-dev make nodejs
RUN gem install --no-rdoc --no-ri jekyll -v 2.5.3


VOLUME /data
VOLUME /var/www/html

WORKDIR /data

ENTRYPOINT [ "jekyll", "build", "--destination=/var/www/html" ]

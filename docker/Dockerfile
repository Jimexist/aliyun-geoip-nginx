FROM ubuntu:latest

ENV NGINX_CLOJURE_VERSION=0.5.2

WORKDIR /opt/aliyun-geoip-nginx

RUN apt-get -y update && apt-get install -y \
  wget \
  openjdk-8-jre \
  && \
  wget https://sourceforge.net/projects/nginx-clojure/files/nginx-clojure-${NGINX_CLOJURE_VERSION}.tar.gz -O /tmp/nginx-clojure-${NGINX_CLOJURE_VERSION}.tar.gz && \
  tar xvzf /tmp/nginx-clojure-${NGINX_CLOJURE_VERSION}.tar.gz nginx-clojure-${NGINX_CLOJURE_VERSION}/nginx-linux-x64 && \
  rm /tmp/nginx-clojure-${NGINX_CLOJURE_VERSION}.tar.gz && \
  mv nginx-clojure-${NGINX_CLOJURE_VERSION}/nginx-linux-x64 ./nginx && \
  rmdir nginx-clojure-${NGINX_CLOJURE_VERSION} && \
  mkdir -p conf logs temp

ADD aliyun-geoip-nginx-all.jar ./aliyun-geoip-nginx.jar

COPY nginx.conf ./conf/nginx.conf

ENV PATH="$PATH:/opt/aliyun-geoip-nginx"

EXPOSE 80

STOPSIGNAL SIGTERM

CMD ["./nginx", "-g", "daemon off;"]

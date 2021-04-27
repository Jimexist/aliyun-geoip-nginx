FROM ubuntu:latest

ENV NGINX_CLOJURE_VERSION=0.5.2

WORKDIR /opt/aliyun-geoip-nginx

RUN apt-get -y update && apt-get install -y \
  wget \
  openjdk-8-jre \
  && \
  wget https://sourceforge.net/projects/nginx-clojure/files/nginx-clojure-${NGINX_CLOJURE_VERSION}.tar.gz -O /tmp/nginx-clojure-${NGINX_CLOJURE_VERSION}.tar.gz && \
  tar xvzf /tmp/nginx-clojure-${NGINX_CLOJURE_VERSION}.tar.gz \
  nginx-clojure-${NGINX_CLOJURE_VERSION}/nginx-linux-x64 \
  nginx-clojure-${NGINX_CLOJURE_VERSION}/jars/nginx-clojure-${NGINX_CLOJURE_VERSION}.jar && \
  rm /tmp/nginx-clojure-${NGINX_CLOJURE_VERSION}.tar.gz && \
  mv nginx-clojure-${NGINX_CLOJURE_VERSION}/nginx-linux-x64 ./nginx && \
  mv nginx-clojure-${NGINX_CLOJURE_VERSION}/jars/nginx-clojure-${NGINX_CLOJURE_VERSION}.jar ./nginx-clojure.jar && \
  rm -r nginx-clojure-${NGINX_CLOJURE_VERSION} && \
  mkdir -p conf logs temp

ADD aliyun-geoip-nginx-all.jar ./aliyun-geoip-nginx.jar

COPY nginx.conf ./conf/nginx.conf

RUN java -version

ENV PATH="$PATH:/opt/aliyun-geoip-nginx"

EXPOSE 8081

STOPSIGNAL SIGTERM

CMD ["./nginx", "-g", "daemon off;"]

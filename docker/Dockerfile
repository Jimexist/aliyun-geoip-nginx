FROM nginx:alpine AS builder

# nginx:alpine contains NGINX_VERSION environment variable, like so:
# ENV NGINX_VERSION 1.19.0

# Our NGINX_CLOJURE_VERSION version
ENV NGINX_CLOJURE_VERSION 0.5.2

# For latest build deps, see https://github.com/nginxinc/docker-nginx/blob/master/mainline/alpine/Dockerfile
RUN apk add --no-cache \
  gcc \
  libc-dev \
  make \
  openssl-dev \
  pcre-dev \
  zlib-dev \
  linux-headers \
  curl \
  gnupg \
  libxslt-dev \
  gd-dev \
  geoip-dev \
  openjdk11 \
  bash

ADD "http://nginx.org/download/nginx-${NGINX_VERSION}.tar.gz" nginx.tar.gz

ADD "https://github.com/nginx-clojure/nginx-clojure/archive/refs/tags/v${NGINX_CLOJURE_VERSION}.tar.gz" nginx-clojure.tar.gz

ADD "https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein" /usr/local/bin/lein

ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk

ENV PATH="$JAVA_HOME/bin:${PATH}"

RUN java -version && \
  javac -version && \
  chmod +x /usr/local/bin/lein && \
  lein version

RUN mkdir -p /usr/src && \
  tar -zxC /usr/src -f nginx.tar.gz && \
  tar -zxC /usr/src -f nginx-clojure.tar.gz

RUN CONFARGS=$(nginx -V 2>&1 | sed -n -e 's/^.*arguments: //p') \
  cd /usr/src/nginx-$NGINX_VERSION && \
  ./configure --with-compat $CONFARGS --add-module=../nginx-clojure-${NGINX_CLOJURE_VERSION}/src/c && \
  make && \
  make install

RUN cd /usr/src/nginx-clojure-${NGINX_CLOJURE_VERSION} && \
  lein jar

FROM nginx:alpine
# Extract the dynamic module NCHAN from the builder image
COPY --from=builder /usr/local/nginx/nginx-clojure.tar.gz /usr/local/nginx/modules/ngx_nchan_module.so
RUN rm /etc/nginx/conf.d/default.conf

# COPY nginx.conf /etc/nginx/nginx.conf
# COPY default.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
STOPSIGNAL SIGTERM
CMD ["nginx", "-g", "daemon off;"]
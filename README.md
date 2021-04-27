# aliyun-geoip-nginx

Nginx plugin wrapping Aliyun Geoip.

This is a proof of concept that we can use Java code to rewrite headers and plugin into Nginx.

This is based on [nginx-clojure](https://nginx-clojure.github.io/index.html) and [Aliyun Geoip SDK](https://help.aliyun.com/product/151537.html).

## How to build

```bash
# this builds the shadowJar
./gradlew shadowJar
# this copies the shadowJar to docker folder
cp build/libs/aliyun-geoip-nginx-all.jar docker
cd docker
# build the docker image
docker build -t aliyun-geoip-nginx .
# run the image
docker run --rm -it --name aliyun-geoip-nginx -p "8081:8081" aliyun-geoip-nginx
```

```bash
# in another shell
curl -v localhost:8081
```

and then tail the log from docker

```bash
docker exec aliyun-geoip-nginx cat logs/error.log
```

```
2021-04-27 03:31:24[info][7][nginx-clojure-worker-0]AliyunEnrichGeoIPHeaders hit, host is not set
2021-04-27 03:31:24[info][7][nginx-clojure-worker-0]AliyunEnrichGeoIPHeaders all request request {id : 19425232,  uri: /}
2021/04/27 03:31:24 [info] 7#0: *1 client 172.17.0.1 closed keepalive connection
2021-04-27 03:31:40[info][11][nginx-clojure-worker-0]AliyunEnrichGeoIPHeaders hit, host is not set
2021-04-27 03:31:40[info][11][nginx-clojure-worker-0]AliyunEnrichGeoIPHeaders all request request {id : 19332656,  uri: /}
2021/04/27 03:32:40 [info] 11#0: *2 client 172.17.0.1 closed keepalive connection
```

## TODO:

- [ ] Actually implement the IP lookup logic.
- [ ] enable the coroutine support

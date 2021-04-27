# aliyun-geoip-nginx

Nginx plugin wrapping Aliyun Geoip.

This is a proof of concept that we can use Java code to rewrite headers and plugin into Nginx.

This is based on [nginx-clojure](https://nginx-clojure.github.io/index.html) and [Aliyun Geoip SDK](https://help.aliyun.com/product/151537.html).

## How to build

```bash
# this builds the shadowJar
./gradlew shadowJar
# this copies the shadowJar to docker folderr
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

which gives:

```
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8081 (#0)
> GET / HTTP/1.1
> Host: localhost:8081
> User-Agent: curl/7.64.1
> Accept: */*
>
< HTTP/1.1 200 OK
< Date: Tue, 27 Apr 2021 03:12:16 GMT
< Content-Type: application/octet-stream
< Content-Length: 8
< Connection: keep-alive
< Server: nginx-clojure/0.5.2
< jiayu-test-response: 2021-04-27T03:12:16.924
<
* Connection #0 to host localhost left intact
success!* Closing connection 0
```

Note the `jiayu-test-response` is the line rewritten by the Java class.

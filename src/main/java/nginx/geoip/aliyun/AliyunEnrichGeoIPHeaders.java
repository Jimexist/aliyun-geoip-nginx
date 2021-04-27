package nginx.geoip.aliyun;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import com.google.common.net.HttpHeaders;
import nginx.clojure.NginxClojureRT;
import nginx.clojure.java.Constants;
import nginx.clojure.java.NginxJavaRingHandler;

public class AliyunEnrichGeoIPHeaders implements NginxJavaRingHandler {

  @Override
  public Object[] invoke(Map<String, Object> request) throws IOException {
    String host = (String) request.getOrDefault(HttpHeaders.HOST, "not set");
    NginxClojureRT.log.info(String.format("AliyunEnrichGeoIPHeaders hit, host is %s", host));
    NginxClojureRT.log.info(String.format("AliyunEnrichGeoIPHeaders all request %s", request));
    return Constants.PHASE_DONE;
  }
}

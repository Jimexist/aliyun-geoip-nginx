package nginx.geoip.aliyun;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import nginx.clojure.NginxClojureRT;
import nginx.clojure.java.Constants;
import nginx.clojure.java.NginxJavaHeaderFilter;

public class AliyunEnrichGeoIPHeaders implements NginxJavaHeaderFilter {
  @Override
  public Object[] doFilter(
      int status, Map<String, Object> request, Map<String, Object> responseHeaders)
      throws IOException {
    NginxClojureRT.log.info("AliyunEnrichGeoIPHeaders hit");
    responseHeaders.put("jiayu-test-response", LocalDateTime.now().toString());
    return Constants.PHASE_DONE;
  }
}

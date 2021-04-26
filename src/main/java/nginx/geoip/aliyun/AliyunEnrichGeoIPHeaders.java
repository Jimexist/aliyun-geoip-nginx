package nginx.geoip.aliyun;

import java.io.IOException;
import java.util.Map;
import nginx.clojure.java.Constants;
import nginx.clojure.java.NginxJavaHeaderFilter;

public class AliyunEnrichGeoIPHeaders implements NginxJavaHeaderFilter {
  @Override
  public Object[] doFilter(
      int status, Map<String, Object> request, Map<String, Object> responseHeaders)
      throws IOException {
    return Constants.PHASE_DONE;
  }
}

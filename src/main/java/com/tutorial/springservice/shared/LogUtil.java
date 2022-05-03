package com.tutorial.springservice.shared;

public class LogUtil {
    public static String ENDPOINT_CALLED_WITH_REQUEST_FORMAT = "[ENDPOINT %s CALLED] %s";

    public static String endpointCalled(String url, Object request) throws Exception {
        String requestJson = request == null ? "" : ObjectParser.toJsonString(request);
        return String.format(ENDPOINT_CALLED_WITH_REQUEST_FORMAT, url, requestJson);
    }

    public static String endpointCalled(String url) throws Exception {
        return endpointCalled(url, null);
    }
}

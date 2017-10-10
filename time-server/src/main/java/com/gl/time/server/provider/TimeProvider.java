package com.gl.time.server.provider;

import java.util.Map;

public interface TimeProvider {
    Map<String,Object> queryTime(Map<String,Object> params);
}

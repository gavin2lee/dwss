package com.gl.time.server.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gl.time.server.dto.SimplePrincipal;
import com.gl.time.server.dto.SimpleTime;
import com.gl.time.server.provider.TimeProvider;

public class GenericTimeService implements TimeProvider {
    private static final Logger LOG = LoggerFactory.getLogger(GenericTimeService.class);

    public Map<String,Object> queryTime(Map<String,Object> params) {
        SimplePrincipal principal = new SimplePrincipal();
        principal.setUsername((String)params.get("username"));
        principal.setPassword((String)params.get("password"));
        
        LOG.info("RECV:{}", principal);
        
        SimpleTime resp = assembleSimpleTime(principal);
        
        LOG.info("SEND:{}",resp);
        
        Map<String,Object> respMap = new HashMap<String,Object>();
        respMap.put("name", resp.getName());
        respMap.put("time", resp.getTime());
        respMap.put("timeParts", resp.getTimeParts());
        return respMap;
    }
    
    private SimpleTime assembleSimpleTime(SimplePrincipal principal){
        SimpleTime t = new SimpleTime();
        
        Date now = new Date();
        
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("yyyyMMdd");
        String date = df.format(now);
        df.applyPattern("HHmmssSSS");
        String time = df.format(now);
        String name = "Simple"+System.currentTimeMillis();
        t.setName(name);
        String fullTime = date+time;
        
        t.setTime(fullTime);
        List<String> timeParts = new ArrayList<String>();
        timeParts.add(date);
        timeParts.add(time);
        
        t.setTimeParts(timeParts);
        
        return t;
    }

}

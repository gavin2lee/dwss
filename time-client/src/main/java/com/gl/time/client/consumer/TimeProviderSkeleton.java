package com.gl.time.client.consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.rpc.service.GenericService;
import com.gl.time.client.dto.SimplePrincipal;
import com.gl.time.client.dto.SimpleTime;

public class TimeProviderSkeleton {

    private static final Logger LOG = LoggerFactory.getLogger(TimeProviderSkeleton.class);
    @Autowired
    private GenericService genericService;

    @SuppressWarnings("unchecked")
    public SimpleTime getTime(SimplePrincipal principal) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", principal.getUsername());
        params.put("password", principal.getPassword());
        Map<?,?> resp = (Map<?, ?>) genericService.$invoke("queryTime", new String[] { "java.util.Map" }, new Object[] { params });

        LOG.info(resp.getClass().getName());

        SimpleTime st = new SimpleTime();
        st.setName((String)resp.get("name"));
        st.setTime((String)resp.get("time"));
        st.setTimeParts(((List<String>)resp.get("timeParts")));
        return st;
    }

    public void setGenericService(GenericService genericService) {
        this.genericService = genericService;
    }

}

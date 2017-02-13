package com.gl.dwss.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl.dwss.config.GlobalAppProperties;
import com.gl.dwss.objects.TraceContent;

@RestController
public class TraceController {
	private static final Logger LOG = LoggerFactory.getLogger(TraceController.class);
	private Map<Long, TraceContent> traceContents = new Hashtable<Long, TraceContent>();

	private AtomicLong tcIdGen = new AtomicLong();
	
	@Autowired
	private GlobalAppProperties globalAppProperties;
	
	@Value("${appVersion:v1}")
	private String appVersion;

	@PostMapping(path = "/trace-contents")
	public void create(@RequestBody TraceContent tc) {
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("create:%s", tc));
		}
		Long oid = tcIdGen.incrementAndGet();
		tc.setOid(oid);
		tc.setCreateAt(new Date());

		traceContents.put(oid, tc);
	}

	@GetMapping(path = "/trace-contents")
	public List<TraceContent> list() {
		if(LOG.isDebugEnabled()){
			LOG.debug(String.format("%s %s list", globalAppProperties.getAppName(), appVersion));
		}
		return new ArrayList<TraceContent>(traceContents.values());
	}
	
	@GetMapping(path="/trace-contents/{id}")
	public TraceContent findById(@PathVariable("id") long oid){
		TraceContent tc = traceContents.get(oid);
		return tc;
	}
}

package com.gl.dwss.cf.booking.server.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl.dwss.cf.objects.pojo.SimpleObject;

@RestController
public class SimpleTraceController {
	private static final Logger LOG = LoggerFactory.getLogger(SimpleTraceController.class);

	private Map<Long, SimpleObject> sobjs = new Hashtable<Long, SimpleObject>();

	private AtomicLong sobjIdGen = new AtomicLong();

	@PostMapping(path = "/sobjs", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public void create(@RequestBody SimpleObject sobj) {
		LOG.debug(String.format("post:%s", sobj));
		Long oid = sobjIdGen.incrementAndGet();
		sobj.setOid(oid);
		sobj.setCreateAt(new Date());

		sobjs.put(oid, sobj);
	}
	
	@PutMapping(path = "/sobjs")
	public void update(@RequestBody SimpleObject sobj) {
		LOG.debug(String.format("update:%s", sobj));
		if(sobj.getOid() == null){
			throw new IllegalStateException("oid should provide");
		}
		
		SimpleObject oldObj = sobjs.get(sobj.getOid());
		if(oldObj == null){
			throw new IllegalStateException("no such object found");
		}
		
		if(sobj.getContent() != null ){
			oldObj.setContent(sobj.getContent());
			oldObj.setUpdateAt(new Date());
		}
	}

	@GetMapping(path = "/sobjs/{id}")
	public SimpleObject get(@PathVariable("id") long oid) {
		LOG.debug(String.format("get:%s", oid));
		return sobjs.get(oid);
	}

	@GetMapping(path = "/sobjs")
	public List<SimpleObject> list() {
		LOG.debug(String.format("list"));
		return new ArrayList<SimpleObject>(sobjs.values());
	}

	@DeleteMapping(path = "/sobjs/{id}")
	public void delete(@PathVariable("id") long oid) {
		LOG.debug(String.format("delete:%s", oid));
		sobjs.remove(oid);
	}
}

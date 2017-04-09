package com.gl.dwss.dirsync.server.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileListController {
	private static final Logger LOG = LoggerFactory.getLogger(FileListController.class);

	@GetMapping(path = "/fs/**/{filename}",produces={MediaType.TEXT_HTML_VALUE})
	public ModelAndView listDir(@PathVariable String filename, HttpServletResponse resp) {
		if (LOG.isTraceEnabled()) {
			LOG.trace(String.format("listDir:%s", filename));
		}
		return new ModelAndView("index");
	}
}

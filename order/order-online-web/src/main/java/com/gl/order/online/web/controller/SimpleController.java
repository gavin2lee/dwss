package com.gl.order.online.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SimpleController {

    @RequestMapping(value = "/infos", produces = { MediaType.APPLICATION_FORM_URLENCODED_VALUE }, consumes = {
            MediaType.APPLICATION_FORM_URLENCODED_VALUE }, method = { RequestMethod.GET })
    public String getInfo() {
        return "hello Simple Controller";
    }
}

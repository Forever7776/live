package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kz on 2017-11-06.
 */
@Controller
@RequestMapping(value = "/")
public class IndexController extends BaseController{

    @RequestMapping
    @ResponseBody
    public String index(){
        logger.info("sssss");
        return "hello";
    }

    @RequestMapping("/index1")
    public String front(){
        return "front/index";
    }
}

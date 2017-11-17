package com.kz.controller;

import com.kz.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kz on 2017-11-06.
 */
@Controller
@RequestMapping(value = "/")
public class IndexController extends BaseController {


    @Autowired
    private SysUserService sysUserService;

    @RequestMapping
    @ResponseBody
    public String index() {
        return "hello";
    }

    @RequestMapping("/index")
    public String front() {

        return "front/index";
    }
}

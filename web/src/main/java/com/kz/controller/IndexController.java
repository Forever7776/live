package com.kz.controller;

import com.kz.service.qiniu.QiNiuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kz on 2017-11-06.
 */
@Controller
@RequestMapping(value = "/")
public class IndexController extends BaseController {


    @Autowired
    private QiNiuService qiNiuService;

    @RequestMapping
    @ResponseBody
    public String index() {
        return "hello";
    }

    @RequestMapping("/index")
    public String front(ModelMap modelMap) {

        return "front/index";
    }


}

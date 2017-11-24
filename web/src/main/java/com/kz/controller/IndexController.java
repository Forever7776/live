package com.kz.controller;

import com.constants.SystemConstant;
import com.kz.service.QiNiuFileService;
import com.kz.service.SysUserService;
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
    private SysUserService sysUserService;
    @Autowired
    private QiNiuService qiNiuService;

    @Autowired
    private  QiNiuFileService qiNiuFileServic1e;

    @RequestMapping
    @ResponseBody
    public String index() {
        return "hello";
    }

    @RequestMapping("/index")
    public String front(ModelMap modelMap) {
        sysUserService.getAll();
        return "front/index";
    }
}

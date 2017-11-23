package com.kz.controller;


import com.alibaba.fastjson.JSONObject;
import com.kz.entity.QiNiuFile;
import com.kz.service.qiniu.QiNiuService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import tools.util.ConfigTool;
import tools.util.FileUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping(value = "/up")
public class UploadController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    private static final String fileLink = "_";

    private static final String suffixImage = ConfigTool.getProp("qiniu.suffixImage");

    @RequestMapping
    @ResponseBody
    public String index() {
        return "hello";
    }

    @RequestMapping(value = "/pic")
    @ResponseBody
    public JSONObject pic(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        JSONObject result = new JSONObject();
        if (multipartFile.isEmpty()) {
            result.put("msg", "图片为空");
            result.put("success", false);
            return result;
        }
        String originFileName = multipartFile.getOriginalFilename();
        if (!StringUtils.contains(suffixImage, FileUtil.getFileSuffix(originFileName).toUpperCase())) {
            result.put("msg", "图片类型不对");
            result.put("success", false);
            return result;
        }
        String newFileName = System.currentTimeMillis() + fileLink + originFileName;
        QiNiuFile qiNiuFile = new QiNiuFile();
        QiNiuService qiNiuService = new QiNiuService();
        qiNiuFile.setFileName(originFileName);
        qiNiuFile.setUserId(1);
        qiNiuService.uploadFileByte(multipartFile.getBytes(),newFileName, qiNiuFile);
        return result;
    }
}

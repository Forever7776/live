package com.util;


import com.alibaba.fastjson.JSONObject;
import com.kz.controller.BaseController;
import com.kz.entity.QiNiuFile;
import com.kz.service.qiniu.QiNiuFileService;
import com.kz.service.qiniu.QiNiuService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import tools.util.ConfigTool;
import tools.util.FileUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private QiNiuService qiNiuService;

    private static final String fileLink = "_";

    private static final String suffixImage = ConfigTool.getProp("qiniu.suffixImage");

    @RequestMapping("/pic")
    @ResponseBody
    public JSONObject index(@RequestParam(value = "file", required = false)MultipartFile multipartFile, HttpServletRequest request) {
        JSONObject result = new JSONObject();
        if (multipartFile.isEmpty()) {
            result.put("msg", "图片为空");
            result.put("success", false);
            return result;
        }
        String originFileName = multipartFile.getOriginalFilename();
        if (!StringUtils.contains(suffixImage, FileUtils.getFileSuffix(originFileName))) {
            result.put("msg", "图片类型不对");
            result.put("success", false);
            return result;
        }
        String newFileName = System.currentTimeMillis() + fileLink + originFileName;
        String projectPath = request.getSession().getServletContext().getRealPath("/");
        File file = new File(projectPath + newFileName);
        QiNiuFile qiNiuFile = new QiNiuFile();
        qiNiuService.upload(file, qiNiuFile);
        return result;
    }
}

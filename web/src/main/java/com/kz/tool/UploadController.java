package com.kz.tool;


import com.alibaba.fastjson.JSONObject;
import com.constants.SystemConstant;
import com.kz.controller.BaseController;
import com.kz.entity.QiNiuFile;
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
import tools.util.DateUtils;
import tools.util.FileUtil;

import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping(value = "/up")
public class UploadController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private QiNiuService qiNiuService;


    /**
     * 图片上传，采用webupload单次上传
     *
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "/pic")
    @ResponseBody
    public JSONObject pic(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        long begin = System.currentTimeMillis();
        JSONObject result = new JSONObject();
        if (multipartFile.isEmpty()) {
            result.put("msg", "图片为空");
            result.put("success", false);
            return result;
        }
        String originFileName = multipartFile.getOriginalFilename();
        if (!StringUtils.contains(SystemConstant.QINIU_SUFFIXIMAGE, FileUtil.getFileSuffix(originFileName).toUpperCase())) {
            result.put("msg", "图片类型不对");
            result.put("success", false);
            return result;
        }

        String newFileName = DateUtils.format(new Date(), DateUtils.yyyyMMddHHmmss) + SystemConstant.QINIU_FILELINK + originFileName;
        QiNiuFile qiNiuFile = new QiNiuFile();
        qiNiuFile.setFileName(originFileName);
        qiNiuFile.setUserId(1);
        qiNiuService.uploadFileByte(multipartFile.getBytes(), newFileName, qiNiuFile);
        logger.info("本次上传耗时：{}ms,文件名：{}，时间：{}", (System.currentTimeMillis() - begin), originFileName, DateUtils.format(new Date(), DateUtils.YYYYMMDDHHMMSS));
        return result;
    }
}

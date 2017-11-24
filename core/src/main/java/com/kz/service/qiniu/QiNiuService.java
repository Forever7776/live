package com.kz.service.qiniu;

import com.alibaba.fastjson.JSONObject;
import com.kz.common.enums.QiNiuEnum;
import com.kz.entity.QiNiuFile;
import com.kz.service.QiNiuFileService;
import com.kz.service.SysUserService;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.qiniu.QiNiuApi;
import tools.util.ConfigTool;

import java.io.File;
import java.util.Date;

/**
 * 七牛上传辅助类
 */
@Service
public class QiNiuService {
    private static Logger logger = LoggerFactory.getLogger(QiNiuService.class);
    private static String domain = StringUtils.EMPTY;

    @Autowired
    private  QiNiuFileService qiNiuFileService;

    @Autowired
    private QiNiuFileService qiNiuFileService1;
    @Autowired
    private static SysUserService sysUserService;

    public static String url(String filename) {
        try {
            if (StringUtils.isBlank(domain)) {
                domain = ConfigTool.getProp("qiniu.domain");
            }
            return "http://" + domain + "/" + new URLCodec("UTF-8").encode(filename).replace("+", "%20");
        } catch (Exception e) {
            logger.error("QiNiuService@url error", e);
            return StringUtils.EMPTY;
        }
    }

    public void run(File file, QiNiuFile qiniuFile) {
        Thread upThread = new Thread(new UploadProcess(file, qiniuFile));
        upThread.start();
        logger.info("QiNiu file-Thread[" + upThread.getId() + "] begin on");
    }

    public boolean upload(File file, QiNiuFile qiniuFile) {
        try {
            JSONObject jo = QiNiuApi.upload(file);
            save(jo, qiniuFile);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("uploadProcess@QiNiuUpload save model error", e);
        }

        return false;
    }

    public  boolean uploadFileByte(byte[] fileByte, String fileKey, QiNiuFile qiniuFile) {
        try {
            JSONObject jo = QiNiuApi.uploadFileByte(fileByte, fileKey);
            save(jo, qiniuFile);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("uploadProcess@QiNiuUpload save model error", e);
        }

        return false;
    }

    public void save(JSONObject jo, QiNiuFile qiniuFile) {
        if ("SUCCESS".equals(jo.getString("result_code"))) {
            String hash = jo.getString("hash");
            String key = jo.getString("key");
            qiniuFile.setKey(key);
            qiniuFile.setHash(hash);
            qiniuFile.setStatus(QiNiuEnum.STATUS.SUCCESS.getKey());
            qiniuFile.setCreateTime(new Date());
            logger.info("[upload]上传结果返回-->key:" + key + "|hash:" + hash);
        } else {
            logger.info("[upload]上传失败");
            qiniuFile.setStatus(QiNiuEnum.STATUS.FAIL.getKey());
        }
       /* if(qiNiuFileService!=null){
            qiNiuFileService.save(qiniuFile);
        }*/

    }


    class UploadProcess implements Runnable {
        private File file;
        private QiNiuFile qiniuFile;

        public UploadProcess(File file, QiNiuFile qiniuFile) {
            this.file = file;
            this.qiniuFile = qiniuFile;
        }

        @Override
        public void run() {
            upload(file, qiniuFile);
        }
    }
}

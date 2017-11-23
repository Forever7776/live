package tools.qiniu;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class QiNiuApi {
    public final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static String uploadToken;
    private static UploadManager uploadManager;
    public static QiNiuApi qiNiu;
    public static Auth auth;
    private static String bucket;

    public QiNiuApi(String access_key, String secret_key, String bucket) {
        auth = Auth.create(access_key, secret_key);
        this.bucket = bucket;
        reload();
        uploadManager = new UploadManager(new Configuration());
        qiNiu = this;
    }

    public static void reload() {
        uploadToken = auth.uploadToken(bucket);
    }

    public static JSONObject upload(File file) throws QiniuException {
        Response resp = uploadManager.put(file, file.getName(), uploadToken);
        return getResult(resp);
    }

    public static JSONObject uploadFileByte(byte[] fileByte, String fileKey) throws QiniuException {
        Response resp = uploadManager.put(fileByte, fileKey, uploadToken);
        return getResult(resp);
    }

    public static JSONObject getResult(Response response) throws QiniuException {
        JSONObject jo = new JSONObject();
        if (response.isOK()) {
            String result = response.bodyString();
            jo = JSONObject.parseObject(result);
            jo.put("result_code", "SUCCESS");
        } else {
            jo.put("result_code", "ERROR");
        }
        return jo;
    }
}

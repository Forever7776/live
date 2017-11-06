package controller;

import constants.CoreConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by 0 on 2017-11-06.
 */
public class BaseController {
    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = false)
    protected HttpServletRequest request;

    @Autowired(required = false)
    protected HttpServletResponse response;

    @ModelAttribute
    protected void setRequest(HttpServletRequest request) {
        this.request = request;
    }


    /**
     * 上传为空文件
     *
     * @param multipartFile
     * @return
     */
    protected boolean isEmptyFile(MultipartFile multipartFile) {
        return multipartFile == null || multipartFile.getSize() == 0L;
    }

    /**
     * 文件大小效验
     *
     * @param multipartFile
     * @return
     */
    protected boolean fileSizeValidator(MultipartFile multipartFile) {
        if (null != multipartFile) {
            try {
                return (multipartFile.getBytes().length <= CoreConstants.SIGN_FILE_MAX_SIZE);
            } catch (IOException e) {
                logger.error("文件上传异常", e);
            }
        }
        return true;
    }

    protected OutputStream getExcelOutputStream(String title, HttpServletResponse response) {
        try {
            OutputStream os = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=" + new String(title.getBytes("UTF-8"), "ISO8859-1") + ".xls");// 设定输出文件头
            response.setContentType("application/msexcel");
            return os;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void writeImageOutputStream(String title, byte[] bytes, HttpServletResponse response) {
        try {
            OutputStream os = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=" + new String(title.getBytes("UTF-8"), "ISO8859-1") + ".png");// 设定输出文件头
            response.setContentType("application/echartsPng");
            os.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private <T> T getFromSession(HttpServletRequest request, String key) {
        Object object = request.getSession().getAttribute(key);
        if (object == null) {
            return null;
        }
        return (T) object;
    }

    protected void removeSession(HttpServletRequest request, String key) {
        request.getSession().removeAttribute(key);
    }
}

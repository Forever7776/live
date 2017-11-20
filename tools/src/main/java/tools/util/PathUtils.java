package tools.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by kz on 2017/11/17.
 */
public class PathUtils {

    /**
     *  WEB所在目录
     */
    public static String getPath(Class thisClass) {
        String path = thisClass.getClassLoader().getResource("")
                .getPath().replace("%20", " ");
        if(path.indexOf("WEB-INF") > 0){
            path = StringUtils.substringBeforeLast(path, "/WEB-INF");
        }
        return path;
    }

}

package com.constants;

import tools.util.ConfigTool;

/**
 * Created by kz on 2017-11-17.
 */
public class SystemConstant {

    /**七牛*/
    public static final String QINIU_ACCESS = ConfigTool.getProp("qiniu.access");
    public static final String QINIU_SECRET = ConfigTool.getProp("qiniu.secret");
    public static final String QINIU_BUCKET = ConfigTool.getProp("qiniu.bucket");
    public static final String QINIU_SUFFIXIMAGE = ConfigTool.getProp("qiniu.suffixImage");//接受上传图片的类型
    public static final String QINIU_FILELINK = ConfigTool.getProp("qiniu.filelink");//重命名图片名称连接符号
}

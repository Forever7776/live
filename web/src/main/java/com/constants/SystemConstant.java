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
}

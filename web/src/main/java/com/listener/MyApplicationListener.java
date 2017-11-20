package com.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import tools.qiniu.QiNiuApi;
import tools.util.ConfigTool;


@Configuration
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(MyApplicationListener.class);

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new QiNiuApi(ConfigTool.getProp("qiniu.access"),ConfigTool.getProp("qiniu.secret"),ConfigTool.getProp("qiniu.bucket"));
        logger.info("##########七牛启动成功##########");
    }

}

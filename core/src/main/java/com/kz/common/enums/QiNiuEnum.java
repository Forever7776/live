package com.kz.common.enums;

/**
 * Created by kz on 2017-11-20.
 * 七牛
 */
public class QiNiuEnum {

    public enum STATUS{
        FAIL(-1,"上传失败"),
        SUCCESS(0,"上传成功");

        private Integer key;
        private String desc;

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        STATUS(Integer key, String desc) {
            this.key = key;
            this.desc = desc;
        }
    }
}

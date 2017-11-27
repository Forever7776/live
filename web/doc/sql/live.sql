/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : live

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2017-11-20 12:53:27
*/

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(55) DEFAULT NULL COMMENT '用户',
  `file_name` varchar(100) NOT NULL COMMENT '文件名称',
  `file_size` int(11) NOT NULL COMMENT '大小',
  `file_key` varchar(100) DEFAULT NULL COMMENT '七牛KEY名',
  `file_suffix` varchar(50) DEFAULT NULL COMMENT '后缀',
  `file_type` tinyint(2) NOT NULL COMMENT '1.图片',
  `file_path` varchar(50) DEFAULT NULL COMMENT '路径',
  `media_id` varchar(50) DEFAULT NULL COMMENT '微信素材ID',
  `status` tinyint(2) DEFAULT NULL COMMENT '文件上传成功状态（成功：0 失败 ：-1）',
  `insert_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '文件插入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='文件系统表';





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

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qiniu_file
-- ----------------------------
DROP TABLE IF EXISTS `qiniu_file`;
CREATE TABLE `qiniu_file` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `file_name` varchar(50) NOT NULL COMMENT '文件名称',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `qiniu_file`
ADD COLUMN `key`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL AFTER `file_name`,
ADD COLUMN `hash`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL AFTER `key`;
ALTER TABLE `qiniu_file`
ADD COLUMN `status`  tinyint(1) NULL AFTER `hash`;




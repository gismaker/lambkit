/*
Navicat MySQL Data Transfer

Source Server         : 本机P123456
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2020-05-18 12:18:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for error_log
-- ----------------------------
DROP TABLE IF EXISTS `error_log`;
CREATE TABLE `error_log` (
  `message_id` varchar(255) NOT NULL DEFAULT '' COMMENT '位移标识:服务器ID+UUID',
  `service_id` varchar(255) DEFAULT NULL COMMENT '服务器ID',
  `class_name` varchar(255) DEFAULT NULL COMMENT '报错类名',
  `method_name` varchar(255) DEFAULT NULL COMMENT '方法名',
  `parameter_content` varchar(255) DEFAULT NULL COMMENT '传递参数',
  `errorContent` varchar(255) DEFAULT NULL COMMENT '异常内容',
  `create_time` datetime DEFAULT NULL COMMENT '时间',
  `line_number` int(11) DEFAULT NULL COMMENT '报错行号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

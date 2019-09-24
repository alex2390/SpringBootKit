/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : db_user

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 22/09/2019 10:47:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '伪删除标记字段（0:正常;1伪删除）',
  `name` varchar(12) NOT NULL DEFAULT '' COMMENT '姓名',
  `gender` tinyint(1) unsigned DEFAULT '0' COMMENT '性别（0:未知;1:男:2:女）',
  `phone_number` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

SET FOREIGN_KEY_CHECKS = 1;

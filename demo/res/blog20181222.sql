/*
 Navicat Premium Data Transfer

 Source Server         : 本地库
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 22/12/2018 10:16:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for artical
-- ----------------------------
DROP TABLE IF EXISTS `artical`;
CREATE TABLE `artical`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID号',
  `title` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章标题',
  `label` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章标识',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章内容',
  `attachment_path` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件路径',
  `type_id` int(11) NOT NULL COMMENT '类别ID',
  `author_id` int(11) NOT NULL COMMENT '作者ID',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_time` timestamp(0) NOT NULL COMMENT '创建时间',
  `like_num` int(11) NOT NULL COMMENT '点赞数',
  `hate_num` int(11) NOT NULL COMMENT '反对数',
  `status` tinyint(4) NOT NULL COMMENT '状态：0无效，1有效，2私密',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of artical
-- ----------------------------
INSERT INTO `artical` VALUES (1, 'MySQL问题汇总', '记录在使用MySQL过程中遇到过的一些坑', '<p><b>一.使用时间类型TIMESTAMP的坑：</b></p><p>&nbsp; &nbsp; 在创建表的时候如果使用了TIMESTAMP类型而自己设置默认值的话，MySQL会自动默认设置第一个TIMESTAMP类型为：NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0)，也就是说第一个时间类型会在做insert和update操作的时候，自动添加当前时间。</p><p>&nbsp; &nbsp; 一开始我建表的时候先创建create_time再update_time并且没有做默认值设置，于是这个默认设置就导致了我每次程序执行update语句的时候，create_time也会自动更新为当前时间和update_time一样。</p><p>&nbsp; &nbsp;解决方法很简单：（1）为TIMESTAMP设置默认值：DEFAULT CURRENT_TIMESTAMP</p><p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; （2）依旧不做设置。建表时把update_time字段放在create_time字段前面</p>', NULL, 1, 1, '2018-12-17 22:13:11', '2018-12-16 18:17:48', 0, 0, 1);

-- ----------------------------
-- Table structure for artical_type
-- ----------------------------
DROP TABLE IF EXISTS `artical_type`;
CREATE TABLE `artical_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of artical_type
-- ----------------------------
INSERT INTO `artical_type` VALUES (1, '博客', 1);
INSERT INTO `artical_type` VALUES (2, '随笔', 1);
INSERT INTO `artical_type` VALUES (3, '小说', 1);

-- ----------------------------
-- Table structure for login_info
-- ----------------------------
DROP TABLE IF EXISTS `login_info`;
CREATE TABLE `login_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `chances` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login_info
-- ----------------------------
INSERT INTO `login_info` VALUES (1, 'temp@qq.com', '76EF3222F2FA62C907687856856A3EE97404D867E559B837B4DC6492A27165172AD38BA8B4C93BDEED887E3F11678C15', '2', '2018-12-17 23:08:26', '2018-12-17 23:08:26', 5);
INSERT INTO `login_info` VALUES (2, 'test@corp.21cn.com', '510A265242843D8CE34D4F60EC5F6E23B3A1DD391E3E73452F3CD4818AC6E02F62A6823E28AB9B20CA5B544FE629ACB7', '2', '2018-12-17 23:08:50', '2018-12-17 23:08:50', 5);

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES (1, '123', '1234');

SET FOREIGN_KEY_CHECKS = 1;

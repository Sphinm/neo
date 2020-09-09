/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : neo

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 09/09/2020 14:18:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `parentId` int(20) DEFAULT NULL COMMENT '上级菜单id',
  `url` varchar(255) DEFAULT NULL COMMENT '路由链接',
  `path` varchar(255) DEFAULT NULL COMMENT '路径',
  `name` varchar(255) DEFAULT NULL COMMENT '权限名',
  `status` varchar(20) DEFAULT NULL COMMENT '权限状态',
  `createTime` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `updateTime` varchar(255) DEFAULT NULL COMMENT '更新时间',
  `code` varchar(255) DEFAULT NULL COMMENT '前端状态码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `userId` int(255) NOT NULL COMMENT '用户id',
  `userName` varchar(255) DEFAULT NULL COMMENT '用户姓名',
  `mobile` varchar(255) DEFAULT NULL COMMENT '用户手机号',
  `company` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `taxNumber` int(255) DEFAULT NULL COMMENT '公司税号',
  `companyPhone` varchar(255) DEFAULT NULL COMMENT '公司电话',
  `rate` varchar(255) DEFAULT NULL COMMENT '费率',
  `industry` varchar(255) DEFAULT NULL COMMENT '所属行业',
  `address` varchar(255) DEFAULT NULL COMMENT '公司地址',
  `bank` varchar(255) DEFAULT NULL COMMENT '开户行',
  `bankAccount` varchar(255) DEFAULT NULL COMMENT '银行账号',
  `password` varchar(255) DEFAULT NULL COMMENT '登录密码',
  `role` varchar(255) DEFAULT NULL COMMENT '用户权限',
  `enabled` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '用户是否禁用',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 10001, '张三', '13211112222', 'advance ai', 10, '021-10002222', '2', NULL, '上海市', '上海银行', NULL, '1111', 'ADMIN', 0);
INSERT INTO `user` VALUES (2, 10002, '李四', '13511112222', 'Baidu', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2222', 'ADMIN', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

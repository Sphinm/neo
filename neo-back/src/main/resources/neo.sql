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

 Date: 27/09/2020 23:52:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for neo_user
-- ----------------------------
DROP TABLE IF EXISTS `neo_user`;
CREATE TABLE `neo_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `account` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '123456' COMMENT '密码',
  `username` varchar(50) DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `is_locked` tinyint(1) DEFAULT '0' COMMENT '是否锁定（0:未锁定，1:锁定）',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id，关联neo_role表id',
  `related_id` int(11) DEFAULT NULL COMMENT '关联id（关联公司id）',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) DEFAULT NULL COMMENT '更新者id',
  `update_date` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`),
  KEY `u_idx_tel` (`mobile`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Records of neo_user
-- ----------------------------
BEGIN;
INSERT INTO `neo_user` VALUES (1, NULL, '123', 'min.su', '131', '111@11.cn', 0, 1, NULL, 1, '2020-09-27 00:09:37', 1, '2020-09-27 00:09:45');
COMMIT;

-- ----------------------------
-- Table structure for neo_company
-- ----------------------------
DROP TABLE IF EXISTS `neo_company`;
CREATE TABLE `neo_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `company_name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `company_tax` varchar(255) DEFAULT NULL COMMENT '公司税号',
  `company_location` varchar(255) DEFAULT NULL COMMENT '公司地址',
  `company_bank_name` varchar(255) DEFAULT NULL COMMENT '开户行名称',
  `company_bank_number` varchar(255) DEFAULT NULL COMMENT '银行账户（基本户）',
  `company_industry` varchar(255) DEFAULT NULL COMMENT '所属行业',
  `company_rate` varchar(20) DEFAULT NULL COMMENT '税率',
  `contact_name` varchar(255) DEFAULT NULL COMMENT '联系人姓名',
  `company_fixed_tel` varchar(255) DEFAULT NULL COMMENT '公司固定电话',
  `contact_tel` varchar(255) DEFAULT NULL COMMENT '联系人电话',
  `recipient_name` varchar(255) DEFAULT NULL COMMENT '收件人姓名',
  `recipient_tel` varchar(255) DEFAULT NULL COMMENT '收件人电话',
  `recipient_address` varchar(255) DEFAULT NULL COMMENT '收件人地址',
  `company_status` tinyint(1) DEFAULT NULL COMMENT '公司状态(0:待定，1：待定)',
  `creator_id` int(45) unsigned DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(45) DEFAULT NULL COMMENT '更新人',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `company_type` tinyint(1) DEFAULT NULL COMMENT '公司类型（0：代理商，1：客户公司）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公司客户';

-- ----------------------------
-- Records of neo_company
-- ----------------------------
BEGIN;
INSERT INTO `neo_company` VALUES (1, '马大哈', '10292398394757', '北京望京', '北京银行', '10203223423523423', '计算机科学与技术', '12', '苏敏', '010-123131234', '13211112222', '苏敏', '193828237474', '上海哈哈哈哈', NULL, NULL, '2020-09-27 10:28:17', NULL, '2020-09-27 10:28:17', NULL);
COMMIT;

-- ----------------------------
-- Table structure for neo_company_relation
-- ----------------------------
DROP TABLE IF EXISTS `neo_company_relation`;
CREATE TABLE `neo_company_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `agent_id` int(11) DEFAULT NULL COMMENT '代理商公司id',
  `company_id` int(11) DEFAULT NULL COMMENT '公司id（关联neo_company中的id）',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除（0：未删除，1：已删除）',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `update_id` int(11) DEFAULT NULL COMMENT '更新人id',
  `update_date` datetime DEFAULT NULL COMMENT '更新日期',
  `is_checked` tinyint(1) DEFAULT NULL COMMENT '是否审核',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='代理商公司关系表';

-- ----------------------------
-- Table structure for neo_company_tax
-- ----------------------------
DROP TABLE IF EXISTS `neo_company_tax`;
CREATE TABLE `neo_company_tax` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `number` varchar(100) DEFAULT NULL COMMENT '编号',
  `company_id` int(11) DEFAULT NULL COMMENT '公司id（对应neo_company中的id）',
  `month` datetime DEFAULT NULL COMMENT '月份',
  `tax_receive` varchar(255) DEFAULT NULL COMMENT '完税凭证文件名',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `update_id` int(11) DEFAULT NULL COMMENT '更新人id',
  `update_date` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公司完税凭证表';

-- ----------------------------
-- Table structure for neo_employee
-- ----------------------------
DROP TABLE IF EXISTS `neo_employee`;
CREATE TABLE `neo_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `company_id` int(11) DEFAULT NULL COMMENT '公司id(对应neo_company中的id)',
  `serial_number` varchar(100) DEFAULT NULL COMMENT '编号',
  `name` varchar(100) DEFAULT NULL COMMENT '员工姓名',
  `id_verify` varchar(100) DEFAULT NULL COMMENT '身份证号',
  `tel` varchar(100) DEFAULT NULL COMMENT '手机号',
  `id_check` tinyint(1) DEFAULT NULL COMMENT '实名认证（0：未认证，1：认证成功，2:认证失败）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `update_id` int(11) DEFAULT NULL COMMENT '更新人id',
  `update_date` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工签约表';

-- ----------------------------
-- Table structure for neo_finance
-- ----------------------------
DROP TABLE IF EXISTS `neo_finance`;
CREATE TABLE `neo_finance` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `company_id` int(11) DEFAULT NULL COMMENT '公司id，关联neo_company表中的id',
  `total_recharge` double DEFAULT NULL COMMENT '充值总额',
  `total_issued` double DEFAULT NULL COMMENT '发放总额',
  `balance` double DEFAULT NULL COMMENT '余额',
  `rate` float DEFAULT NULL COMMENT '费率',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `update_id` int(11) DEFAULT NULL COMMENT '更新人id',
  `update_date` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公司财务表';

-- ----------------------------
-- Table structure for neo_invoice
-- ----------------------------
DROP TABLE IF EXISTS `neo_invoice`;
CREATE TABLE `neo_invoice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `order_number` varchar(100) DEFAULT NULL COMMENT '订单号(对应充值记录表neo_recharge_record中的order_number)',
  `invoice_content` varchar(255) DEFAULT NULL COMMENT '开票内容',
  `invoice_type` tinyint(1) DEFAULT NULL COMMENT '开票类型（0：专用，1：普通发票）',
  `invoice_amount` double DEFAULT NULL COMMENT '开票金额',
  `invoice_company` varchar(255) DEFAULT NULL COMMENT '发票抬头',
  `receive_name` varchar(100) DEFAULT NULL COMMENT '收件人姓名',
  `receive_tel` varchar(100) DEFAULT NULL COMMENT '收件人电话',
  `receive_address` varchar(255) DEFAULT NULL COMMENT '收件人地址',
  `status` tinyint(1) DEFAULT NULL COMMENT '审核状态（0：待审核，1：审核通过，待寄出，2：审核未通过，3：已寄出）',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `update_id` int(11) DEFAULT NULL COMMENT '更新人id',
  `update_date` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公司开票记录表';

-- ----------------------------
-- Table structure for neo_issue
-- ----------------------------
DROP TABLE IF EXISTS `neo_issue`;
CREATE TABLE `neo_issue` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `company_id` int(11) DEFAULT NULL COMMENT '公司id',
  `order_number` varchar(100) DEFAULT NULL COMMENT '订单号（对应发放明细表neo_issue_detail的order_number）',
  `task_name` varchar(100) DEFAULT NULL COMMENT '发放任务名称',
  `amount` double DEFAULT NULL COMMENT '发放金额',
  `status` tinyint(1) DEFAULT NULL COMMENT '发放状态（0：已创建，1：成功，2：部分失败，2：全部失败）',
  `rebate` double DEFAULT NULL COMMENT '返佣金额',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `update_id` int(11) DEFAULT NULL COMMENT '更新人id',
  `update_date` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公司发放记录表';

-- ----------------------------
-- Table structure for neo_issue_detail
-- ----------------------------
DROP TABLE IF EXISTS `neo_issue_detail`;
CREATE TABLE `neo_issue_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `order_number` varchar(100) DEFAULT NULL COMMENT '订单号',
  `bank_serial_number` varchar(100) DEFAULT NULL COMMENT '打款流水号',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `id_number` varchar(100) DEFAULT NULL COMMENT '身份证号',
  `tel` varchar(100) DEFAULT NULL COMMENT '手机号',
  `bank_number` varchar(100) DEFAULT NULL COMMENT '银行卡号',
  `amount` double DEFAULT NULL COMMENT '打款金额',
  `status` tinyint(1) DEFAULT NULL COMMENT '打款状态（0：等待发放，1：发放成功，2：余额不足，3：信息错误）',
  `info_verify` varchar(100) DEFAULT NULL COMMENT '验证信息',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `update_id` int(11) DEFAULT NULL COMMENT '更新人id',
  `update_date` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='打款明细表';

-- ----------------------------
-- Table structure for neo_recharge_record
-- ----------------------------
DROP TABLE IF EXISTS `neo_recharge_record`;
CREATE TABLE `neo_recharge_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `order_number` varchar(100) DEFAULT NULL COMMENT '订单号',
  `company_id` int(11) DEFAULT NULL COMMENT '公司id(对应公司表neo_company中的id)',
  `payment_amount` double DEFAULT NULL COMMENT '打款金额',
  `account_amount` double DEFAULT NULL COMMENT '到账金额',
  `payment_voucher` varchar(255) DEFAULT NULL COMMENT '打款凭证（文件名）',
  `invoicing_status` tinyint(1) DEFAULT NULL COMMENT '开票状态（0：未开票，1：已开票）',
  `approval_status` tinyint(1) DEFAULT NULL COMMENT '审核状态(0：待审核，1：审核通过，2：审核未通过)',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `update_id` int(11) DEFAULT NULL COMMENT '更新人id',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公司充值记录表';

-- ----------------------------
-- Table structure for neo_sp
-- ----------------------------
DROP TABLE IF EXISTS `neo_sp`;
CREATE TABLE `neo_sp` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `sp_name` varchar(200) DEFAULT NULL COMMENT '客户姓名',
  `sp_tel` varchar(200) DEFAULT NULL COMMENT '客户手机号',
  `sp_type` tinyint(1) DEFAULT NULL COMMENT '客户类型（0：小规模，1：一般纳税人）',
  `sp_pv` varchar(255) DEFAULT NULL COMMENT '打款凭证（链接)',
  `sp_amount` double DEFAULT NULL COMMENT '打款金额',
  `sp_status` tinyint(1) DEFAULT NULL COMMENT '审核状态（0：审核中，1：审核通过，2：审核未通过）',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) DEFAULT NULL COMMENT '更新人id',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除（0：否，1：是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='个体独资管理';

-- ----------------------------
-- Table structure for neo_withdraw
-- ----------------------------
DROP TABLE IF EXISTS `neo_withdraw`;
CREATE TABLE `neo_withdraw` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `order_number` varchar(100) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) DEFAULT NULL COMMENT '申请体现人id',
  `amount` double DEFAULT NULL COMMENT '提现金额',
  `courier_company` varchar(255) DEFAULT NULL COMMENT '快递公司',
  `tracking_number` int(11) DEFAULT NULL COMMENT '快递单号',
  `status` tinyint(1) DEFAULT NULL COMMENT '审核状态（0：待审核，1：审核通过，2：审核不通过）',
  `review_date` datetime DEFAULT NULL COMMENT '审核时间',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `update_id` int(11) DEFAULT NULL COMMENT '更新人id',
  `update_date` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='提现记录表';

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for neo_functions
-- ----------------------------
DROP TABLE IF EXISTS `neo_functions`;
CREATE TABLE `neo_functions` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `function_name` varchar(50) DEFAULT NULL COMMENT '功能名称（中文）',
  `function_type` varchar(50) DEFAULT NULL COMMENT '功能类别',
  `function_info` varchar(50) DEFAULT NULL COMMENT '功能信息（英文）',
  `is_locked` tinyint(1) DEFAULT NULL COMMENT '是否锁定（0：否，1：是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='API 功能权限表';

-- ----------------------------
-- Table structure for neo_role_function
-- ----------------------------
DROP TABLE IF EXISTS `neo_role_function`;
CREATE TABLE `neo_role_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `role_id` int(11) DEFAULT NULL COMMENT '关联neo_role中的id',
  `function_id` int(11) DEFAULT NULL COMMENT '关联neo_functions中的id',
  `is_locked` tinyint(1) DEFAULT NULL COMMENT '是否锁定（0：否，1：是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限关联表';


-- ----------------------------
-- Table structure for neo_role
-- ----------------------------
DROP TABLE IF EXISTS `neo_role`;
CREATE TABLE `neo_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `role_type` varchar(50) DEFAULT NULL COMMENT '角色类型',
  `is_locked` tinyint(1) DEFAULT NULL COMMENT '是否锁定（0：否，1：是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

-- ----------------------------
-- Records of neo_role
-- ----------------------------
BEGIN;
INSERT INTO `neo_role` VALUES (1, '管理员', 'ADMIN', 0);
INSERT INTO `neo_role` VALUES (2, '代理商', 'MERCHANT', 0);
INSERT INTO `neo_role` VALUES (3, '客户公司', 'COMPANY', 0);
INSERT INTO `neo_role` VALUES (4, '员工', 'EMPLOYEE', 0);
COMMIT;

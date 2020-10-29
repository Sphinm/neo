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

 Date: 30/10/2020 00:01:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
  `company_status` tinyint(1) DEFAULT NULL COMMENT '公司状态(0:待定，1：正常)',
  `creator_id` int(45) unsigned DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(45) DEFAULT NULL COMMENT '更新人',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `company_type` tinyint(1) DEFAULT NULL COMMENT '公司类型（0：代理商，1：客户公司）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公司客户';

-- ----------------------------
-- Records of neo_company
-- ----------------------------
BEGIN;
INSERT INTO `neo_company` VALUES (1, '清华大学出版社', '100002342344554', '北京', '北京银行清河支行', '10203223423523423', '计算机出版社', '10', '苏敏', '010-123131234', '1321111', '苏敏', '15619385234', '北京市海淀区清华大学东门', 1, 1, '2020-10-22 23:44:30', 1, '2020-10-23 12:56:57', -1);
INSERT INTO `neo_company` VALUES (14, '测试公司', '100002342344554', '京津冀一体裤', '京津冀银行', '10203223423523423', '京津冀', '10', '测试用户12', '010-123131234', '11111', '测试收件人', '15619385234', '河北石家庄', 0, 1, '2020-10-24 18:39:38', 8, '2020-10-27 21:42:59', 0);
INSERT INTO `neo_company` VALUES (15, '代理商公司', '100002342344554', '北京望京', '北京银行清河支行', '6231254535234234', '计算机', '11', '代理商对接人', '010-123131234', '13211112222', '代理商收件人', '15619385234', '北京市海淀区清华大学东门', 0, 1, '2020-10-24 18:41:23', 1, '2020-10-24 18:41:23', 0);
INSERT INTO `neo_company` VALUES (16, '世纪东方', '100002342344554', '北京望京', '北京银行清河支行', '10203223423523423', '计算机出版社', '12', '杜敏', '010-123131234', '17343452123', '华东', '193828237474', '北京市海淀区清华大学东门', 0, 1, '2020-10-24 22:44:36', 1, '2020-10-24 22:44:36', 0);
INSERT INTO `neo_company` VALUES (18, '123dsadasd', '123', '1', 'k', 'e', '1', '12', '123123ddd', '123', '123', '1', 'kkk', 'k', 1, 1, '2020-10-25 21:59:18', 12, '2020-10-27 22:26:46', 0);
INSERT INTO `neo_company` VALUES (19, '84', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', 1, 1, '2020-10-25 21:59:37', 13, '2020-10-26 12:15:28', 0);
INSERT INTO `neo_company` VALUES (20, '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', 1, 1, '2020-10-25 21:59:48', 1, '2020-10-25 21:59:48', 0);
INSERT INTO `neo_company` VALUES (21, '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', 1, 1, '2020-10-25 21:59:59', 1, '2020-10-25 21:59:59', 0);
INSERT INTO `neo_company` VALUES (22, '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '66', '6', 1, 1, '2020-10-25 22:00:09', 1, '2020-10-25 22:00:09', 0);
INSERT INTO `neo_company` VALUES (23, '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '77', '7', 1, 1, '2020-10-25 22:00:24', 1, '2020-10-25 22:00:24', 0);
INSERT INTO `neo_company` VALUES (24, '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', 1, 1, '2020-10-25 22:00:39', 1, '2020-10-25 22:00:39', 0);
INSERT INTO `neo_company` VALUES (25, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 1, 1, '2020-10-25 22:00:54', 1, '2020-10-25 22:00:54', 0);
INSERT INTO `neo_company` VALUES (26, 'i', 'i', 'r', 'i', 'i', 'i', 'i', '12', 'i', 'i', 'i', 'i', 'i', 1, 1, '2020-10-25 22:01:30', 1, '2020-10-25 22:01:30', 0);
INSERT INTO `neo_company` VALUES (27, 'l', 'l', 'l', 'l', 'l', 'l', 'l', 'l', 'l', 'l', 'l', 'l', 'l', 1, 1, '2020-10-25 22:20:20', 1, '2020-10-25 22:20:20', 0);
INSERT INTO `neo_company` VALUES (28, '测试测试公司123', 'k', '0', '0', '0', '0', '0', '测试公司1', '0', '1', '0', '0', '0', 1, 1, '2020-10-26 14:40:52', 22, '2020-10-26 18:49:20', 1);
INSERT INTO `neo_company` VALUES (29, '99', '9', '9', '9', '10203223423523423', '9', '9', '哈哈哈公司', '9', '99', '9', '9', '9', 1, 1, '2020-10-26 14:44:21', 1, '2020-10-26 14:44:21', 1);
INSERT INTO `neo_company` VALUES (30, '清华大学出版社', '100002342344554', '清华大学', '北京银行清河支行', '10203223423523423', '计算机出版社', '19', '程杰', '010-123131234', '183999900', '程杰', '183123123', '清湖村', 1, 1, '2020-10-26 18:40:16', 1, '2020-10-26 18:40:16', 1);
INSERT INTO `neo_company` VALUES (31, '网红公司', '100', '1', '1', '俩', '1', '1', '网红本人', '1', '199', '1', '1', '1213', 1, 1, '2020-10-26 18:55:42', 1, '2020-10-26 18:55:42', 1);
INSERT INTO `neo_company` VALUES (32, '山东黄金全额请问', '100002342344554', '山东泰山', '123123', '12312', '计算机', '12', '山东黄金', '12312', '111', '泰山', '193828237474', '安定区无', 0, 10, '2020-10-28 13:16:10', 10, '2020-10-28 13:16:10', 1);
COMMIT;

-- ----------------------------
-- Table structure for neo_company_relation
-- ----------------------------
DROP TABLE IF EXISTS `neo_company_relation`;
CREATE TABLE `neo_company_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `agent_id` int(11) DEFAULT NULL COMMENT '代理商公司id',
  `company_id` int(11) DEFAULT NULL COMMENT '公司id（关联neo_company中的id）',
  `is_checked` tinyint(1) DEFAULT NULL COMMENT '是否审核（0：未审核，1：已审核）',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除（0：未删除，1：已删除）',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `update_id` int(11) DEFAULT NULL COMMENT '更新人id',
  `update_date` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='代理商公司关系表';

-- ----------------------------
-- Records of neo_company_relation
-- ----------------------------
BEGIN;
INSERT INTO `neo_company_relation` VALUES (3, 16, 32, 0, 0, 10, '2020-10-28 13:16:10', 10, '2020-10-28 13:16:10');
COMMIT;

-- ----------------------------
-- Table structure for neo_company_tax
-- ----------------------------
DROP TABLE IF EXISTS `neo_company_tax`;
CREATE TABLE `neo_company_tax` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `number` varchar(100) DEFAULT NULL COMMENT '编号',
  `company_id` int(11) DEFAULT NULL COMMENT '公司id（对应neo_company中的id）',
  `month` datetime DEFAULT NULL COMMENT '月份',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除',
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
  `is_locked` tinyint(1) DEFAULT NULL COMMENT '是否锁定（0:未锁定，1:锁定）',
  `is_signup` tinyint(1) DEFAULT NULL COMMENT '是否签约',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工签约表';

-- ----------------------------
-- Records of neo_employee
-- ----------------------------
BEGIN;
INSERT INTO `neo_employee` VALUES (1, 14, NULL, 'sam', '100001', '21123213', 0, NULL, 1, '2020-10-28 22:09:30', 1, '2020-10-28 22:09:35', 0, 0);
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公司财务表';

-- ----------------------------
-- Records of neo_finance
-- ----------------------------
BEGIN;
INSERT INTO `neo_finance` VALUES (1, 32, 100, 500, 90, 10, 0, 1, '2020-10-28 23:00:20', 1, '2020-10-28 23:00:24');
COMMIT;

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
  `function_api` varchar(255) DEFAULT NULL COMMENT 'api',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';

-- ----------------------------
-- Records of neo_functions
-- ----------------------------
BEGIN;
INSERT INTO `neo_functions` VALUES (1, '用户信息', '用户管理', 'user_view', 0, '/userInfo');
INSERT INTO `neo_functions` VALUES (2, '创建用户', '用户管理', 'user_view', 0, '/create/user');
INSERT INTO `neo_functions` VALUES (3, '更改密码', '用户管理', 'change_password', 0, '/change/password');
INSERT INTO `neo_functions` VALUES (4, '加密密码', '用户管理', 'pass', 0, '/encodePassword');
INSERT INTO `neo_functions` VALUES (5, '退出登录', '用户管理', 'user_logout', 0, '/userLogout');
INSERT INTO `neo_functions` VALUES (6, '代理商信息', '用户管理', 'user_merchant', 0, '/fetch/merchant');
INSERT INTO `neo_functions` VALUES (7, '编辑代理商信息', '用户管理', 'user_merchant', 0, '/update/merchant');
INSERT INTO `neo_functions` VALUES (8, '删除代理商', '用户管理', 'user_merchant', 0, '/delete/merchant');
INSERT INTO `neo_functions` VALUES (9, '数据查询', '用户管理', 'user_dataquery', 0, '/fetch/allData');
INSERT INTO `neo_functions` VALUES (10, '查询公司列表', '公司管理', 'company_list', 0, '/fetch/company');
INSERT INTO `neo_functions` VALUES (11, '查询员工列表', '用户管理', 'user_employee', 0, '/fetch/employee');
INSERT INTO `neo_functions` VALUES (12, '删除员工', '用户管理', 'user_employee', 0, '/delete/employee');
INSERT INTO `neo_functions` VALUES (13, '查询待审核公司', '审核管理', 'review_company', 0, '/review/companyList');
INSERT INTO `neo_functions` VALUES (14, '审核公司', '审核管理', 'review_company', 0, '/review/company');
INSERT INTO `neo_functions` VALUES (15, '审核充值列表', '审核管理', 'review_recharge', 0, '/review/rechargeList');
INSERT INTO `neo_functions` VALUES (16, '审核充值', '审核管理', 'review_recharge', 0, '/review/recharge');
INSERT INTO `neo_functions` VALUES (17, '审核开票列表', '审核管理', 'review_invoice', 0, '/review/invoiceList');
INSERT INTO `neo_functions` VALUES (18, '审核开票', '审核管理', 'review_invoice', 0, '/review/invoice');
INSERT INTO `neo_functions` VALUES (19, '审核发放列表', '审核管理', 'review_provide', 0, '/review/provideList');
INSERT INTO `neo_functions` VALUES (20, '审核发放', '审核管理', 'review_provide', 0, '/review/provide');
INSERT INTO `neo_functions` VALUES (21, '审核提现列表', '审核管理', 'review_withdraw', 0, '/review/withdrawList');
INSERT INTO `neo_functions` VALUES (22, '审核提现', '审核管理', 'review_withdraw', 0, '/review/withdraw');
INSERT INTO `neo_functions` VALUES (23, '审核完税列表', '审核管理', 'review_tax', 0, '/review/taxList');
INSERT INTO `neo_functions` VALUES (24, '审核完税', '审核管理', 'review_tax', 0, '/review/tax');
INSERT INTO `neo_functions` VALUES (25, '上传完税', '审核管理', 'review_tax', 0, '/upload/tax');
COMMIT;

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
  `provide_status` tinyint(1) DEFAULT NULL COMMENT '审核状态（0：未审核， 1： 已审核）',
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
  `account_amount` double DEFAULT NULL COMMENT '充值金额',
  `payment_voucher` varchar(255) DEFAULT NULL COMMENT '打款凭证（文件名）',
  `invoicing_status` tinyint(1) DEFAULT NULL COMMENT '开票状态（0：未开票，1：已开票）',
  `approval_status` tinyint(1) DEFAULT NULL COMMENT '审核状态(0：待审核，1：审核通过，2：审核未通过)',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `update_id` int(11) DEFAULT NULL COMMENT '更新人id',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公司充值记录表';

-- ----------------------------
-- Records of neo_recharge_record
-- ----------------------------
BEGIN;
INSERT INTO `neo_recharge_record` VALUES (1, '1012312314321', 16, 3500, 3600, 'http://www.baidu.com', 0, 0, 1, '2020-10-29 22:16:01', 1, '2020-10-29 22:16:07');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限关联表';

-- ----------------------------
-- Records of neo_role_function
-- ----------------------------
BEGIN;
INSERT INTO `neo_role_function` VALUES (1, 1, 1, 0);
INSERT INTO `neo_role_function` VALUES (2, 2, 1, 0);
INSERT INTO `neo_role_function` VALUES (3, 3, 1, 0);
INSERT INTO `neo_role_function` VALUES (4, 4, 1, 0);
INSERT INTO `neo_role_function` VALUES (5, 1, 2, 0);
INSERT INTO `neo_role_function` VALUES (6, 2, 2, 0);
INSERT INTO `neo_role_function` VALUES (7, 3, 2, 0);
INSERT INTO `neo_role_function` VALUES (8, 1, 3, 0);
INSERT INTO `neo_role_function` VALUES (9, 2, 3, 0);
INSERT INTO `neo_role_function` VALUES (10, 3, 3, 0);
INSERT INTO `neo_role_function` VALUES (11, 4, 3, 0);
INSERT INTO `neo_role_function` VALUES (12, 1, 5, 0);
INSERT INTO `neo_role_function` VALUES (13, 2, 5, 0);
INSERT INTO `neo_role_function` VALUES (14, 3, 5, 0);
INSERT INTO `neo_role_function` VALUES (15, 4, 5, 0);
INSERT INTO `neo_role_function` VALUES (16, 1, 6, 0);
INSERT INTO `neo_role_function` VALUES (17, 1, 7, 0);
INSERT INTO `neo_role_function` VALUES (18, 1, 8, 0);
INSERT INTO `neo_role_function` VALUES (19, 1, 9, 0);
INSERT INTO `neo_role_function` VALUES (20, 1, 10, 0);
INSERT INTO `neo_role_function` VALUES (21, 1, 11, 0);
INSERT INTO `neo_role_function` VALUES (22, 1, 12, 0);
INSERT INTO `neo_role_function` VALUES (23, 1, 13, 0);
INSERT INTO `neo_role_function` VALUES (24, 1, 14, 0);
INSERT INTO `neo_role_function` VALUES (25, 1, 15, 0);
INSERT INTO `neo_role_function` VALUES (26, 1, 16, 0);
INSERT INTO `neo_role_function` VALUES (27, 1, 17, 0);
INSERT INTO `neo_role_function` VALUES (28, 1, 18, 0);
INSERT INTO `neo_role_function` VALUES (29, 1, 19, 0);
INSERT INTO `neo_role_function` VALUES (30, 1, 20, 0);
INSERT INTO `neo_role_function` VALUES (31, 1, 21, 0);
INSERT INTO `neo_role_function` VALUES (32, 1, 22, 0);
INSERT INTO `neo_role_function` VALUES (33, 1, 23, 0);
INSERT INTO `neo_role_function` VALUES (34, 1, 24, 0);
INSERT INTO `neo_role_function` VALUES (35, 1, 25, 0);
COMMIT;

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
-- Table structure for neo_user
-- ----------------------------
DROP TABLE IF EXISTS `neo_user`;
CREATE TABLE `neo_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `account` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '123456' COMMENT '密码',
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Records of neo_user
-- ----------------------------
BEGIN;
INSERT INTO `neo_user` VALUES (1, '1311', '$2a$10$9/N00CFpg8JR8VY6Dg86/O6cJfRYW.ilP5O96Nm6P76GXpo9Js3su', '管理员', '1311', '111@11.cn', -1, 1, 1, 1, '2020-09-27 00:09:37', 1, '2020-09-27 00:09:45');
INSERT INTO `neo_user` VALUES (8, '199', '$2a$10$Wx3XrpYVePR8j/JlP90ZK.FNgBQTT708eZgWViKXx5ZdcwKQWSRd.', 'Test Cash5', '199', '12', 1, 2, 14, 1, '2020-10-24 18:39:38', 1, '2020-10-27 21:42:59');
INSERT INTO `neo_user` VALUES (9, '1001', '$2a$10$9/N00CFpg8JR8VY6Dg86/O6cJfRYW.ilP5O96Nm6P76GXpo9Js3su', '代理商测试用户', '1001', '100', 1, 2, 15, 1, '2020-10-24 18:41:23', 1, '2020-10-24 18:41:23');
INSERT INTO `neo_user` VALUES (10, '18333633735', '$2a$10$W6y268gp17zdEeC8zTs3Lum6mmtfIqV15OnUmfDS0k81QH1O0ozEW', 'redis 代理商', '18333633735', '1500160640@qq.com', 1, 2, 16, 1, '2020-10-24 22:44:36', 1, '2020-10-24 22:44:36');
INSERT INTO `neo_user` VALUES (11, '199', '$2a$10$dUD6uEq0C2LvRjCSOSBTn.Ecx1V/.F9agCS.MH3SsXlOq0/SewwHS', 'Test Cash', '199', '100', 1, 2, -1, 1, '2020-10-25 15:59:08', 1, '2020-10-25 15:59:08');
INSERT INTO `neo_user` VALUES (12, '1', '$2a$10$zeoXfbNPOz5OjbxcRMTxQuvpzja8mrJslh2oFkrPVYWWn21lbM3.C', '17777', '1123', 'min.su@advance.ai', 1, 2, 18, 1, '2020-10-25 21:59:18', 1, '2020-10-27 22:26:47');
INSERT INTO `neo_user` VALUES (13, '8', '$2a$10$0UI3WfawpLaSzAo4WTTggeLplEWBDo2UY41rnoIvtme9ZUiQzqRjy', '2', '8', '8', 1, 2, 19, 1, '2020-10-25 21:59:37', 1, '2020-10-26 12:15:29');
INSERT INTO `neo_user` VALUES (14, '3', '$2a$10$Yh17iwfnOWJUaKH0qPGVDuPwtBXvvr1fmU4vb2Y9Ba/lrKM./yIXK', '3', '3', '3', 1, 2, 20, 1, '2020-10-25 21:59:48', 1, '2020-10-25 21:59:48');
INSERT INTO `neo_user` VALUES (15, '4', '$2a$10$WHWroiYE3J0ul5kXcU8Wj.R9ZcMfzxDPGGKTFsNGmQQLv2v7XdgeS', '44', '4', '4', 1, 2, 21, 1, '2020-10-25 21:59:59', 1, '2020-10-25 21:59:59');
INSERT INTO `neo_user` VALUES (16, '6', '$2a$10$ZRXnI4Fsb9lev2zZovG89u4NgV1vbPsBGZtyFfny6ZVUFsi6u5zcq', '6', '6', '6', 1, 2, 22, 1, '2020-10-25 22:00:09', 1, '2020-10-25 22:00:09');
INSERT INTO `neo_user` VALUES (17, '7', '$2a$10$X8q.U9SnjQMe57YKJC9Y4enLyaMkwAPpQOGLOyGpjTOIPKOG4HU82', '7', '7', '7', 1, 2, 23, 1, '2020-10-25 22:00:24', 1, '2020-10-25 22:00:24');
INSERT INTO `neo_user` VALUES (18, '9', '$2a$10$37/9yg.nekFPiHPUa.dz..KJV8r0dZ6Ap4kOQMc1GPSg/XiLGO5WW', '9', '9', '9', 1, 2, 24, 1, '2020-10-25 22:00:39', 1, '2020-10-25 22:00:39');
INSERT INTO `neo_user` VALUES (19, '0', '$2a$10$mQ1q63ufMyprfwHbBNuEyuJueXvqO6GO9syrxLCuaVXZYVjXB97nG', '0', '0', '0', 0, 2, 25, 1, '2020-10-25 22:00:54', 1, '2020-10-25 22:00:54');
INSERT INTO `neo_user` VALUES (20, '12', '$2a$10$Aq9uzylzHWtfdcuFZPQEiOaTs2EeaXsVr.e5vpZ41zQ4dZexOMbS.', '12', '12', '12', 0, 2, 26, 1, '2020-10-25 22:01:30', 1, '2020-10-25 22:01:30');
INSERT INTO `neo_user` VALUES (21, '13211111000', '$2a$10$KTmAo4YC1sk6qFNXFv5EVeqD.oO1wYPasxleim0EyhaS9sDJwDGia', 'kqw', '13211111000', NULL, 1, 2, 27, 1, '2020-10-25 22:20:20', 1, '2020-10-25 22:20:20');
INSERT INTO `neo_user` VALUES (22, '11123', '$2a$10$sb8.Li4fuG6LrMxkwHZ/eeGa.uVbr3y6WFwJ5QG7pJpumuF8amBLu', '测试公司账户12', '11123', NULL, 1, 3, 28, 1, '2020-10-26 14:40:52', 1, '2020-10-26 18:49:20');
INSERT INTO `neo_user` VALUES (23, '1001', '$2a$10$G994V9ouJehJdnV8ZbJCuezyUARzsZ.oX2R0sG07HCRJ4wVd0.6aq', '哈哈哈', '1001', NULL, 0, 3, 29, 1, '2020-10-26 14:44:21', 1, '2020-10-26 14:44:21');
INSERT INTO `neo_user` VALUES (24, '10086', '$2a$10$0JlXvRpDonrGxASGYDExvezFUwbCgHpLYNe5hCqv88kawzZtzlBXW', '重构公司', '10086', '12312@a.cn', 1, 3, 30, 1, '2020-10-26 18:40:16', 1, '2020-10-26 18:40:16');
INSERT INTO `neo_user` VALUES (25, '10010', '$2a$10$Ub95APmU7wFDAVkbwSWdiu7vILGZ8HhRJL9DtKL0DywoU6m3YBYVS', '网红公司', '10010', NULL, 1, 3, 31, 1, '2020-10-26 18:55:42', 1, '2020-10-26 18:55:42');
INSERT INTO `neo_user` VALUES (29, '11', '$2a$10$U0tC55yT32fakUMS6.Kkd.CUwM6FZsKYJFbyQqPR6HoVPUV7My7Ne', '没解决', '11', NULL, 1, 3, 32, 10, '2020-10-28 13:16:10', 10, '2020-10-28 13:16:10');
COMMIT;

-- ----------------------------
-- Table structure for neo_withdraw
-- ----------------------------
DROP TABLE IF EXISTS `neo_withdraw`;
CREATE TABLE `neo_withdraw` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `order_number` varchar(100) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) DEFAULT NULL COMMENT '申请提现人id',
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

CREATE TABLE `neo_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `account` varchar(50) NULL COMMENT '用户名',
  `password` varchar(50) NULL COMMENT '密码',
  `username` varchar(50) NULL COMMENT '姓名',
  `tel` varchar(50) NULL COMMENT '手机号',
  `email` varchar(50) NULL COMMENT '邮箱',
  `is_locked` tinyint(1) NULL COMMENT '是否锁定（0:未锁定，1:锁定）',
  `role_id` int NULL COMMENT '角色id，关联neo_role表id',
  `related_id` int NULL COMMENT '关联id（关联公司id）',
  `creator_id` int NULL COMMENT '创建人id',
  `creator_date` datetime NULL COMMENT '创建时间',
  `update_id` int NULL COMMENT '更新者id',
  `update_date` datetime NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) COMMENT = '用户表';

CREATE TABLE `neo_company`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `company_name` varchar(255) NULL COMMENT '公司名称',
  `company_tax` varchar(255) NULL COMMENT '公司税号',
  `company_location` varchar(255) NULL COMMENT '公司地址',
  `company_bank_name` varchar(255) NULL COMMENT '开户行名称',
  `company_bank_number` varchar(255) NULL COMMENT '银行账户（基本户）',
  `company_industry` varchar(255) NULL COMMENT '所属行业',
  `company_rate` varchar(20) NULL COMMENT '税率',
  `contact_name` varchar(255) NULL COMMENT '联系人姓名',
  `contact_tel` varchar(255) NULL COMMENT '联系人电话',
  `recipient_name` varchar(255) NULL COMMENT '收件人姓名',
  `recipient_tel` varchar(255) NULL COMMENT '收件人电话',
  `recipient_address` varchar(255) NULL COMMENT '收件人地址',
  `company_status` tinyint(1) NULL COMMENT '公司状态(0:待定，1：待定)',
  `creator_id` int UNSIGNED NULL COMMENT '创建人',
  `creator_date` datetime NULL COMMENT '创建时间',
  `update_id` int NULL COMMENT '更新人',
  `update_date` datetime NULL COMMENT '更新时间',
  `company_type` tinyint(1) NULL COMMENT '公司类型（0：代理商，1：客户公司，2：管理员）',
  PRIMARY KEY (`id`)
) COMMENT = '公司客户';

CREATE TABLE `neo_company_relation`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `agent_id` int NULL COMMENT '代理商公司id',
  `company_id` int NULL COMMENT '公司id（关联neo_company中的id）',
  `is_deleted` tinyint(1) NULL COMMENT '是否删除（0：未删除，1：已删除）',
  `creator_id` int NULL COMMENT '创建人id',
  `creator_date` datetime NULL COMMENT '创建日期',
  `update_id` int NULL COMMENT '更新人id',
  `update_date` datetime NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) COMMENT = '代理商公司关系表';

CREATE TABLE `neo_company_tax`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `number` varchar(100) NULL COMMENT '编号',
  `company_id` int NULL COMMENT '公司id（对应neo_company中的id）',
  `month` datetime NULL COMMENT '月份',
  `tax_receive` varchar(255) NULL COMMENT '完税凭证文件名',
  `remark` varchar(255) NULL COMMENT '备注信息',
  `creator_id` int NULL COMMENT '创建人id',
  `creator_date` datetime NULL COMMENT '创建日期',
  `update_id` int NULL COMMENT '更新人id',
  `update_date` datetime NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) COMMENT = '公司完税凭证表';

CREATE TABLE `neo_employee`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `company_id` int NULL COMMENT '公司id(对应neo_company中的id)',
  `serial_number` varchar(100) NULL COMMENT '编号',
  `name` varchar(100) NULL COMMENT '员工姓名',
  `id_verify` varchar(100) NULL COMMENT '身份证号',
  `tel` varchar(100) NULL COMMENT '手机号',
  `id_check` tinyint(1) NULL COMMENT '实名认证（0：未认证，1：认证成功，2:认证失败）',
  `remark` varchar(255) NULL COMMENT '备注',
  `creator_id` int NULL COMMENT '创建人id',
  `creator_date` datetime NULL COMMENT '创建日期',
  `update_id` int NULL COMMENT '更新人id',
  `update_date` datetime NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) COMMENT = '员工签约表';

CREATE TABLE `neo_finance`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `company_id` int NULL COMMENT '公司id，关联neo_company表中的id',
  `total_recharge` double NULL COMMENT '充值总额',
  `total_issued` double NULL COMMENT '发放总额',
  `balance` double NULL COMMENT '余额',
  `rate` float NULL COMMENT '费率',
  `status` tinyint(1) NULL COMMENT '状态',
  `creator_id` int NULL COMMENT '创建人id',
  `creator_date` datetime NULL COMMENT '创建日期',
  `update_id` int NULL COMMENT '更新人id',
  `update_date` datetime NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) COMMENT = '公司财务表';

CREATE TABLE `neo_functions`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `function_name` varchar(50) NULL COMMENT '功能名称（中文）',
  `function_type` varchar(50) NULL COMMENT '功能类别',
  `function_info` varchar(50) NULL COMMENT '功能信息（英文）',
  `is_locked` tinyint(1) NULL COMMENT '是否锁定（0：否，1：是）',
  PRIMARY KEY (`id`)
) COMMENT = '权限表';

CREATE TABLE `neo_invoice`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `order_number` varchar(100) NULL COMMENT '订单号(对应充值记录表neo_recharge_record中的order_number)',
  `invoice_content` varchar(255) NULL COMMENT '开票内容',
  `invoice_type` tinyint(1) NULL COMMENT '开票类型（0：专用，1：普通发票）',
  `invoice_amount` double NULL COMMENT '开票金额',
  `invoice_company` varchar(255) NULL COMMENT '发票抬头',
  `receive_name` varchar(100) NULL COMMENT '收件人姓名',
  `receive_tel` varchar(100) NULL COMMENT '收件人电话',
  `receive_address` varchar(255) NULL COMMENT '收件人地址',
  `status` tinyint(1) NULL COMMENT '审核状态（0：待审核，1：审核通过，待寄出，2：审核未通过，3：已寄出）',
  `creator_id` int NULL COMMENT '创建人id',
  `creator_date` datetime NULL COMMENT '创建日期',
  `update_id` int NULL COMMENT '更新人id',
  `update_date` datetime NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) COMMENT = '公司开票记录表';

CREATE TABLE `neo_issue`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `company_id` int NULL COMMENT '公司id',
  `order_number` varchar(100) NULL COMMENT '订单号（对应发放明细表neo_issue_detail的order_number）',
  `task_name` varchar(100) NULL COMMENT '发放任务名称',
  `amount` double NULL COMMENT '发放金额',
  `status` tinyint(1) NULL COMMENT '发放状态（0：已创建，1：成功，2：部分失败，2：全部失败）',
  `rebate` double NULL COMMENT '返佣金额',
  `creator_id` int NULL COMMENT '创建人id',
  `creator_date` datetime NULL COMMENT '创建日期',
  `update_id` int NULL COMMENT '更新人id',
  `update_date` datetime NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) COMMENT = '公司发放记录表';

CREATE TABLE `neo_issue_detail`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `order_number` varchar(100) NULL COMMENT '订单号',
  `bank_serial_number` varchar(100) NULL COMMENT '打款流水号',
  `name` varchar(100) NULL COMMENT '姓名',
  `id_number` varchar(100) NULL COMMENT '身份证号',
  `tel` varchar(100) NULL COMMENT '手机号',
  `bank_number` varchar(100) NULL COMMENT '银行卡号',
  `amount` double NULL COMMENT '打款金额',
  `status` tinyint(1) NULL COMMENT '打款状态（0：等待发放，1：发放成功，2：余额不足，3：信息错误）',
  `info_verify` varchar(100) NULL COMMENT '验证信息',
  `remark` varchar(255) NULL COMMENT '备注',
  `creator_id` int NULL COMMENT '创建人id',
  `creator_date` datetime NULL COMMENT '创建日期',
  `update_id` int NULL COMMENT '更新人id',
  `update_date` datetime NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) COMMENT = '打款明细表';

CREATE TABLE `neo_recharge_record`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `order_number` varchar(100) NULL COMMENT '订单号',
  `company_id` int NULL COMMENT '公司id(对应公司表neo_company中的id)',
  `payment_amount` double NULL COMMENT '打款金额',
  `account_amount` double NULL COMMENT '到账金额',
  `payment_voucher` varchar(255) NULL COMMENT '打款凭证（文件名）',
  `invoicing_status` tinyint(1) NULL COMMENT '开票状态（0：未开票，1：已开票）',
  `approval_status` tinyint(1) NULL COMMENT '审核状态(0：待审核，1：审核通过，2：审核未通过)',
  `creator_id` int NULL COMMENT '创建人id',
  `creator_date` datetime NULL COMMENT '创建日期',
  `update_id` int NULL COMMENT '更新人id',
  `update_date` datetime NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) COMMENT = '公司充值记录表';

CREATE TABLE `neo_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `role_name` varchar(50) NULL COMMENT '角色名称',
  `role_type` varchar(50) NULL COMMENT '角色类型',
  `is_locked` tinyint(1) NULL COMMENT '是否锁定（0：否，1：是）',
  PRIMARY KEY (`id`)
) COMMENT = '角色表';

CREATE TABLE `neo_role_function`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `role_id` int NULL COMMENT '关联neo_role中的id',
  `function_id` int NULL COMMENT '关联neo_functions中的id',
  `is_locked` tinyint(1) NULL COMMENT '是否锁定（0：否，1：是）',
  PRIMARY KEY (`id`)
) COMMENT = '角色权限关联表';

CREATE TABLE `neo_sp`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `sp_name` varchar(200) NULL COMMENT '客户姓名',
  `sp_tel` varchar(200) NULL COMMENT '客户手机号',
  `sp_type` tinyint(1) NULL COMMENT '客户类型（0：小规模，1：一般纳税人）',
  `sp_pv` varchar(255) NULL COMMENT '打款凭证（链接)',
  `sp_amount` double NULL COMMENT '打款金额',
  `sp_status` tinyint(1) NULL COMMENT '审核状态（0：审核中，1：审核通过，2：审核未通过）',
  `creator_id` int NULL COMMENT '创建人id',
  `creator_date` datetime NULL COMMENT '创建时间',
  `update_id` int NULL COMMENT '更新人id',
  `update_date` datetime NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NULL COMMENT '是否删除（0：否，1：是）',
  PRIMARY KEY (`id`)
) COMMENT = '个体独资管理';

CREATE TABLE `neo_withdraw`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `order_number` varchar(100) NULL COMMENT '订单号',
  `user_id` int NULL COMMENT '申请体现人id',
  `amount` double NULL COMMENT '提现金额',
  `courier_company` varchar(255) NULL COMMENT '快递公司',
  `tracking_number` int NULL COMMENT '快递单号',
  `status` tinyint(1) NULL COMMENT '审核状态（0：待审核，1：审核通过，2：审核不通过）',
  `review_date` datetime NULL COMMENT '审核时间',
  `creator_id` int NULL COMMENT '创建人id',
  `creator_date` datetime NULL COMMENT '创建日期',
  `update_id` int NULL COMMENT '更新人id',
  `update_date` datetime NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) COMMENT = '提现记录表';


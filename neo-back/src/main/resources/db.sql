SET MORE mysql;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`         INT(11)        NOT NULL AUTO_INCREMENT,
    `user_id`    INT(11)        NOT NULL COMMENT '用户ID',
    `user_name`  VARCHAR(45)    NOT NULL COMMENT '用户名',
    `mobile`     VARCHAR(20)    NOT NULL COMMENT '手机号/登录账号',
    `password`   VARCHAR(45)    NOT NULL DEFAULT '123456' COMMENT '登录密码',
    `status`     VARCHAR(20)    NOT NULL DEFAULT 'ENABLED' COMMENT 'ENABLED: 启用, DISABLED: 禁用',
    `role`       VARCHAR(45)    NOT NULL COMMENT '用户权限 => ADMIN: 管理员, MERCHANT: 代理商, COMPANY: 公司, EMPLOYEE: 员工',
    PRIMARY KEY (`id`),
    KEY `u_idx_userid` (`user_id`),
    KEY `u_idx_username` (`user_name`),
    KEY `u_idx_mobile` (`mobile`)
 ) COMMENT '用户表';

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`
(
    `id`                INT(11)        NOT NULL AUTO_INCREMENT,
    `user_id`           INT(11)        NOT NULL COMMENT '用户ID',
    `user_name`         VARCHAR(45)    NOT NULL COMMENT '用户名',
    `company`           VARCHAR(45)    NOT NULL COMMENT '公司名',
    `tax_number`        VARCHAR(128)   NOT NULL COMMENT '公司税号',
    `company_mobile`    VARCHAR(20)    NOT NULL COMMENT '公司电话',
    `company_address`   VARCHAR(255)   NOT NULL COMMENT '公司地址',
    `rate`              VARCHAR(45)    NOT NULL COMMENT '费率',
    `industry`          VARCHAR(128)   NOT NULL COMMENT '所属行业',
    `bank`              VARCHAR(45)    NOT NULL COMMENT '开户行',
    `bank_account`      VARCHAR(45)    NOT NULL COMMENT '银行账号',
    `receiver_name`     VARCHAR(45)    NOT NULL COMMENT '收件人姓名',
    `receiver_mobile`   VARCHAR(45)    NOT NULL COMMENT '收件人手机号',
    `receiver_address`  VARCHAR(45)    NOT NULL COMMENT '收件人地址',
    PRIMARY KEY (`id`),
    KEY `ui_idx_userid` (`user_id`),
    KEY `ui_idx_username` (`user_name`)
 ) COMMENT '用户信息表';

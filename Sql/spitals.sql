/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50625
Source Host           : 127.0.0.1:3306
Source Database       : spitals

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2016-06-20 01:09:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_bus_depot
-- ----------------------------
DROP TABLE IF EXISTS `t_bus_depot`;
CREATE TABLE `t_bus_depot` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `depot_No` varchar(50) DEFAULT NULL COMMENT '仓库编号',
  `depot_name` varchar(128) DEFAULT NULL COMMENT '仓库名称',
  `depot_manager` varchar(32) DEFAULT NULL COMMENT '创库管理者',
  `address` varchar(16) DEFAULT NULL COMMENT '仓库地址',
  `is_valid` int(11) DEFAULT '1' COMMENT '状态[1,有效，0.无效]',
  `is_default` int(11) DEFAULT '0' COMMENT '是否默认仓库',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bus_depot
-- ----------------------------
INSERT INTO `t_bus_depot` VALUES ('1', '0000001', '沙田仓库', '1', '广州市沙田路168号108室', '1', '0');

-- ----------------------------
-- Table structure for t_sys_log_info
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_log_info`;
CREATE TABLE `t_sys_log_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `function_name` varchar(50) DEFAULT NULL,
  `params` varchar(200) DEFAULT NULL,
  `contimes` varchar(100) DEFAULT NULL,
  `ip_address` varchar(16) DEFAULT NULL,
  `mac_address` varchar(20) DEFAULT NULL,
  `message` varchar(256) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `log_level` varchar(16) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=245 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_log_info
-- ----------------------------
INSERT INTO `t_sys_log_info` VALUES ('35', 'findPage', null, null, '127.0.0.1', '0c-82-68-bb-9d-da', '/list', '系统超级管理员', null, '2016-06-19 01:19:08');

-- ----------------------------
-- Table structure for t_sys_org
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_org`;
CREATE TABLE `t_sys_org` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `org_name` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '组织名称',
  `partent_id` int(15) DEFAULT NULL COMMENT '备注',
  `leaf` int(11) DEFAULT NULL COMMENT '是否根节点',
  `is_valid` int(2) DEFAULT '1' COMMENT '是否生效',
  `ts` datetime DEFAULT NULL,
  `create_name_id` int(11) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL COMMENT '样式',
  PRIMARY KEY (`id`),
  KEY `fk_org_partent_id` (`partent_id`),
  CONSTRAINT `fk_org_partent_id` FOREIGN KEY (`partent_id`) REFERENCES `t_sys_org` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_sys_org
-- ----------------------------
INSERT INTO `t_sys_org` VALUES ('1', '广州科腾信息技术有限公司', null, null, '1', null, null, null);
INSERT INTO `t_sys_org` VALUES ('2', '领导班子', '1', null, '1', null, null, null);
INSERT INTO `t_sys_org` VALUES ('3', '信息集成事业部', '1', null, '1', null, null, null);
INSERT INTO `t_sys_org` VALUES ('7', '数据资产事业部', '1', null, '1', null, null, null);
INSERT INTO `t_sys_org` VALUES ('8', '管理咨询事业部', '1', null, '1', null, null, null);
INSERT INTO `t_sys_org` VALUES ('9', '技术支持分部', '3', null, '1', null, null, null);
INSERT INTO `t_sys_org` VALUES ('10', '电商平台分部', '3', null, '1', null, null, null);
INSERT INTO `t_sys_org` VALUES ('11', 'ERP分部', '3', null, '1', null, null, null);
INSERT INTO `t_sys_org` VALUES ('12', '电网业务分部', '3', null, '1', null, null, null);
INSERT INTO `t_sys_org` VALUES ('13', '商务组', '7', null, '1', null, null, null);
INSERT INTO `t_sys_org` VALUES ('30', '乡南关组', '8', null, '1', '2016-06-03 02:37:44', null, '');
INSERT INTO `t_sys_org` VALUES ('31', '超级管理员组', '7', null, '1', '2016-06-10 09:37:14', null, '');
INSERT INTO `t_sys_org` VALUES ('32', '3', null, null, '1', '2016-06-14 23:40:06', null, null);
INSERT INTO `t_sys_org` VALUES ('33', '阿萨德发', '3', null, '1', '2016-06-15 10:27:10', null, null);
INSERT INTO `t_sys_org` VALUES ('34', '测试', '2', null, '1', '2016-06-15 10:45:50', null, null);
INSERT INTO `t_sys_org` VALUES ('35', '阿萨德发', null, null, '1', '2016-06-15 10:45:58', null, null);

-- ----------------------------
-- Table structure for t_sys_org_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_org_role`;
CREATE TABLE `t_sys_org_role` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `org_id` int(15) DEFAULT NULL COMMENT '岗位ID',
  `role_id` int(15) DEFAULT NULL COMMENT '角色Id',
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_o_r` (`org_id`),
  KEY `fk_r_o` (`role_id`),
  CONSTRAINT `fk_o_r` FOREIGN KEY (`org_id`) REFERENCES `t_sys_org` (`id`),
  CONSTRAINT `fk_r_o` FOREIGN KEY (`role_id`) REFERENCES `t_sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_sys_org_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_post
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_post`;
CREATE TABLE `t_sys_post` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `post_name` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '岗位名称',
  `description` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '岗位说明',
  `is_valid` int(2) DEFAULT '1' COMMENT '是否生效',
  `icon` varchar(16) CHARACTER SET utf8 DEFAULT NULL COMMENT '样式',
  `creat_date` date DEFAULT NULL,
  `create_name_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人ID',
  `org_Id` int(11) DEFAULT NULL COMMENT '所属组织',
  PRIMARY KEY (`id`),
  KEY `fk_post_org_id` (`org_Id`),
  CONSTRAINT `fk_post_org_id` FOREIGN KEY (`org_Id`) REFERENCES `t_sys_org` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_sys_post
-- ----------------------------
INSERT INTO `t_sys_post` VALUES ('1', '架构师', '负责公司开发平台的框架设计', '1', null, null, null, '3');
INSERT INTO `t_sys_post` VALUES ('2', '开发工程师', '负责代码开发', '1', null, null, null, '3');
INSERT INTO `t_sys_post` VALUES ('3', '实施工程师', '负责现场项目的实施', '1', null, null, null, '3');
INSERT INTO `t_sys_post` VALUES ('4', '项目经理', '总体把控', '1', null, null, null, '3');
INSERT INTO `t_sys_post` VALUES ('5', '分部经理', '总体把控', '1', null, null, null, '3');
INSERT INTO `t_sys_post` VALUES ('6', '技术总监', '技术总监', '1', null, null, null, '1');
INSERT INTO `t_sys_post` VALUES ('7', '总经理助理', '总经理助理', '1', null, null, null, '3');
INSERT INTO `t_sys_post` VALUES ('31', '我也不知道啊啊', '反弹道导弹的', '1', null, null, null, '3');
INSERT INTO `t_sys_post` VALUES ('32', '无权', '', '1', null, null, null, '8');

-- ----------------------------
-- Table structure for t_sys_post_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_post_role`;
CREATE TABLE `t_sys_post_role` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `psot_id` int(15) DEFAULT NULL COMMENT '岗位ID',
  `role_id` int(15) DEFAULT NULL COMMENT '角色ID',
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_p_r` (`psot_id`),
  KEY `fk_r_p` (`role_id`),
  CONSTRAINT `fk_p_r` FOREIGN KEY (`psot_id`) REFERENCES `t_sys_post` (`id`),
  CONSTRAINT `fk_r_p` FOREIGN KEY (`role_id`) REFERENCES `t_sys_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_sys_post_role
-- ----------------------------
INSERT INTO `t_sys_post_role` VALUES ('13', '32', '1', null);
INSERT INTO `t_sys_post_role` VALUES ('14', '32', '2', null);
INSERT INTO `t_sys_post_role` VALUES ('18', '7', '7', null);
INSERT INTO `t_sys_post_role` VALUES ('19', '7', '2', null);

-- ----------------------------
-- Table structure for t_sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_resource`;
CREATE TABLE `t_sys_resource` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '菜单名称',
  `url` varchar(300) CHARACTER SET utf8 NOT NULL,
  `type` varchar(10) CHARACTER SET utf8 NOT NULL COMMENT '类型[menu button]',
  `priority` int(255) DEFAULT NULL COMMENT '顺序',
  `parent_Id` int(11) DEFAULT NULL,
  `is_valid` int(11) DEFAULT '1' COMMENT '是否生效',
  `partents_ids` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `leaf` varchar(255) CHARACTER SET utf8 DEFAULT '1',
  `create_name_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `ts` datetime DEFAULT NULL COMMENT '创建时间',
  `description` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `icon` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '样式',
  `permission` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`),
  KEY `fk_parent_id` (`parent_Id`),
  CONSTRAINT `fk_parent_id` FOREIGN KEY (`parent_Id`) REFERENCES `t_sys_resource` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_sys_resource
-- ----------------------------
INSERT INTO `t_sys_resource` VALUES ('1', '系统资源', '/', 'menu', null, null, '1', null, '1', null, null, null, null, '');
INSERT INTO `t_sys_resource` VALUES ('2', '系统设置', '/', 'menu', null, '1', '1', '2/', '1', null, null, null, 'fa fa-th', 'system:*');
INSERT INTO `t_sys_resource` VALUES ('3', '仓库管理', '/', 'menu', null, '1', '1', '3/', '0', null, null, null, 'fa fa-chevron-circle-right', 'ck:*');
INSERT INTO `t_sys_resource` VALUES ('4', '员工管理', '/', 'menu', null, '1', '1', '1/4/', '0', null, null, null, 'fa fa-chevron-circle-right', 'person:*');
INSERT INTO `t_sys_resource` VALUES ('5', '店面管理', '/', 'menu', null, '1', '1', '5/', '0', null, null, null, 'fa fa-chevron-circle-right', 'dianpu:*');
INSERT INTO `t_sys_resource` VALUES ('6', '用户管理', '/user', 'menu', null, '2', '1', '2/6/', '0', null, null, null, 'fa fa-user', 'user:view');
INSERT INTO `t_sys_resource` VALUES ('7', '组织管理', '/org', 'menu', null, '2', '1', '1/2/7/', '0', null, null, null, ' fa fa-sitemap', 'org:*');
INSERT INTO `t_sys_resource` VALUES ('8', '角色管理', '/role', 'menu', null, '2', '1', '1/2/8/', '0', null, null, null, 'fa fa-user', 'role:*');
INSERT INTO `t_sys_resource` VALUES ('9', '岗位管理', '/post', 'menu', null, '2', '1', '1/2/9/', '0', null, null, null, 'fa fa-chevron-circle-right', 'post:*');
INSERT INTO `t_sys_resource` VALUES ('10', '资源管理', '/resource', 'menu', null, '2', '1', '1/2/10/', '0', null, null, null, 'fa fa-chevron-circle-right', 'resource:*');
INSERT INTO `t_sys_resource` VALUES ('24', '仓库信息', '/depot', 'menu', '99', '3', '1', null, '0', null, '2016-06-16 00:37:47', '管理仓库的相关信息', null, 'warehouse:Info');
INSERT INTO `t_sys_resource` VALUES ('25', '入库单', '/', 'menu', '99', '3', '1', null, '0', null, '2016-06-16 00:58:45', '针对仓库单据入库', null, 'workhost:input');
INSERT INTO `t_sys_resource` VALUES ('26', '出库单', '/', 'menu', '99', '3', '1', null, '0', null, '2016-06-16 00:59:26', '出库单', null, 'warehouse:out');
INSERT INTO `t_sys_resource` VALUES ('27', '库存查询', '/', 'menu', '99', '3', '1', null, '0', null, '2016-06-16 01:01:38', '库存查询', null, 'find:*');
INSERT INTO `t_sys_resource` VALUES ('28', '日志管理', '/logInfo', 'menu', '99', '1', '1', null, '0', null, '2016-06-18 00:08:22', '查看操作日志', null, 'logInfo:view');

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '角色名称',
  `description` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `is_valid` int(12) DEFAULT '1' COMMENT '是否生效',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_name_id` int(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('1', 'administrator', '超级管理员', '1', '2016-05-28 23:22:31', null);
INSERT INTO `t_sys_role` VALUES ('2', 'staff', '普通用户', '1', '1970-01-01 00:00:00', null);
INSERT INTO `t_sys_role` VALUES ('7', 'sale', '销售人员', '1', '2016-06-15 00:00:00', null);

-- ----------------------------
-- Table structure for t_sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_resource`;
CREATE TABLE `t_sys_role_resource` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `role_id` int(15) DEFAULT NULL COMMENT '角色ID',
  `resource_id` int(15) DEFAULT NULL COMMENT '资源ID',
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_r_r_id` (`role_id`),
  KEY `fk_r_s` (`resource_id`),
  CONSTRAINT `FK_r_r_id` FOREIGN KEY (`role_id`) REFERENCES `t_sys_role` (`id`),
  CONSTRAINT `fk_r_s` FOREIGN KEY (`resource_id`) REFERENCES `t_sys_resource` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_sys_role_resource
-- ----------------------------
INSERT INTO `t_sys_role_resource` VALUES ('86', '1', '6', null);
INSERT INTO `t_sys_role_resource` VALUES ('87', '1', '7', null);
INSERT INTO `t_sys_role_resource` VALUES ('88', '1', '8', null);
INSERT INTO `t_sys_role_resource` VALUES ('89', '1', '9', null);
INSERT INTO `t_sys_role_resource` VALUES ('90', '1', '10', null);
INSERT INTO `t_sys_role_resource` VALUES ('92', '1', '2', null);
INSERT INTO `t_sys_role_resource` VALUES ('136', '2', '4', null);
INSERT INTO `t_sys_role_resource` VALUES ('139', '1', '24', null);
INSERT INTO `t_sys_role_resource` VALUES ('140', '1', '25', null);
INSERT INTO `t_sys_role_resource` VALUES ('141', '1', '26', null);
INSERT INTO `t_sys_role_resource` VALUES ('142', '1', '27', null);
INSERT INTO `t_sys_role_resource` VALUES ('143', '1', '3', null);
INSERT INTO `t_sys_role_resource` VALUES ('144', '1', '28', null);

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '登录名',
  `pass_word` varchar(200) CHARACTER SET utf8 NOT NULL COMMENT '密码',
  `full_name` varchar(200) CHARACTER SET utf8 NOT NULL COMMENT '全称',
  `china_name` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '中文名',
  `salt` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '加密盐【登录名+密码 再进行加密】',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号码',
  `tel` varchar(16) DEFAULT NULL COMMENT '电话号码',
  `address` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '地址',
  `e_mail` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮箱',
  `age` int(2) DEFAULT NULL COMMENT '年龄',
  `genter` int(2) DEFAULT NULL COMMENT '性别',
  `reg_time` datetime DEFAULT NULL COMMENT '注册时间',
  `org_id` int(12) DEFAULT NULL COMMENT '所属组织',
  `post_id` int(12) DEFAULT NULL COMMENT '所属岗位',
  `status` int(2) DEFAULT '1' COMMENT '状态[1.正常，0 锁定 2 过期 3 危险]',
  `qq` int(20) DEFAULT NULL COMMENT '腾讯QQ号',
  `is_valid` int(2) DEFAULT '1' COMMENT '是否生效 1生效 0 失效',
  PRIMARY KEY (`id`),
  KEY `fk_org_id` (`org_id`),
  KEY `fk_post_id` (`post_id`),
  CONSTRAINT `fk_org_id` FOREIGN KEY (`org_id`) REFERENCES `t_sys_org` (`id`),
  CONSTRAINT `fk_post_id` FOREIGN KEY (`post_id`) REFERENCES `t_sys_post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', 'admin', 'a673993651b15dbc7366eee7268906a0', '系统超级管理员', null, '368719b36923eb8c34ceaa324eefe751', null, null, null, null, null, '1', null, '2', '4', '1', null, '1');
INSERT INTO `t_sys_user` VALUES ('2', 'HaiAng1', '123456', '斯蒂芬', 'ddd', '88671426c37e8dc64b8a451c19d2edfc', '1864584562', '25645654825', '', '2@123.com', '15', '1', '2016-06-03 14:39:05', '3', '2', '1', '986542156', '1');
INSERT INTO `t_sys_user` VALUES ('3', 'HaiAng2', 'C901pR9E', '阿萨德发', '123', '5f9e808a3cc60c30d167e7473c69eb66', '123123', '123123', '', '1@125.com', '12', '1', '2016-05-31 14:53:28', '3', '2', '1', '123123', '1');
INSERT INTO `t_sys_user` VALUES ('4', 'HaiAng3', '329a1b664cf24ec0cc0254ea059eed27', '斯蒂芬', 'ddd', '50e92ae722ade42a4d755c672dc42739', '1864584562', '25645654825', '', '2@123.com', '15', '1', '2016-06-01 11:17:07', '3', '2', '1', '986542156', '1');
INSERT INTO `t_sys_user` VALUES ('5', 'HaiAng4', '0eb07ab7099393c70fdf317c3a710934', '阿萨德发', '123', '5f9e808a3cc60c30d167e7473c69eb66', '123123', '123123', '', '1@125.com', '12', '1', '2016-05-31 14:53:28', '3', '2', '1', '123123', '1');
INSERT INTO `t_sys_user` VALUES ('6', 'HaiAng5', '329a1b664cf24ec0cc0254ea059eed27', '斯蒂芬', 'ddd', '50e92ae722ade42a4d755c672dc42739', '1864584562', '25645654825', '', '2@123.com', '15', '1', '2016-06-01 11:18:01', '3', '2', '1', '986542156', '1');
INSERT INTO `t_sys_user` VALUES ('7', 'HaiAng6', '0eb07ab7099393c70fdf317c3a710934', '阿萨德发', '123', '5f9e808a3cc60c30d167e7473c69eb66', '123123', '123123', '', '1@125.com', '12', '1', '2016-05-31 14:53:28', '3', '2', '1', '123123', '1');
INSERT INTO `t_sys_user` VALUES ('8', 'HaiAng7', '329a1b664cf24ec0cc0254ea059eed27', '斯蒂芬', 'ddd', '50e92ae722ade42a4d755c672dc42739', '1864584562', '25645654825', '', '2@123.com', '15', '1', '2016-06-01 11:17:07', '3', '2', '1', '986542156', '1');
INSERT INTO `t_sys_user` VALUES ('9', 'HaiAng8', '0eb07ab7099393c70fdf317c3a710934', '阿萨德发', '123', '5f9e808a3cc60c30d167e7473c69eb66', '123123', '123123', '', '1@125.com', '12', '1', '2016-05-31 14:53:28', '3', '2', '1', '123123', '1');
INSERT INTO `t_sys_user` VALUES ('10', 'HaiAng9', '329a1b664cf24ec0cc0254ea059eed27', '斯蒂芬', 'ddd', '50e92ae722ade42a4d755c672dc42739', '1864584562', '25645654825', '', '2@123.com', '15', '1', '2016-06-01 11:17:07', '3', '2', '1', '986542156', '1');
INSERT INTO `t_sys_user` VALUES ('11', 'HaiAng10', 'y9pk9b9t', '阿萨德发', '123', '5f9e808a3cc60c30d167e7473c69eb66', '123123', '123123', '', '1@125.com', '12', '1', '2016-05-31 14:53:28', '3', '2', '1', '123123', '1');
INSERT INTO `t_sys_user` VALUES ('12', 'HaiAng11', '329a1b664cf24ec0cc0254ea059eed27', '斯蒂芬', 'ddd', '50e92ae722ade42a4d755c672dc42739', '1864584562', '25645654825', '', '2@123.com', '15', '1', '2016-06-01 11:18:01', '30', '2', '1', '986542156', '1');
INSERT INTO `t_sys_user` VALUES ('13', 'HaiAng12', '0eb07ab7099393c70fdf317c3a710934', '阿萨德发', '123', '5f9e808a3cc60c30d167e7473c69eb66', '123123', '123123', '', '1@125.com', '12', '1', '2016-05-31 14:53:28', '30', '2', '1', '123123', '1');
INSERT INTO `t_sys_user` VALUES ('14', 'HaiAng13', '329a1b664cf24ec0cc0254ea059eed27', '斯蒂芬', 'ddd', '50e92ae722ade42a4d755c672dc42739', '1864584562', '25645654825', '', '2@123.com', '15', '1', '2016-06-01 11:18:03', '30', '2', '1', '986542156', '1');
INSERT INTO `t_sys_user` VALUES ('15', 'HaiAng14', '0eb07ab7099393c70fdf317c3a710934', '阿萨德发', '123', '5f9e808a3cc60c30d167e7473c69eb66', '123123', '123123', '', '1@125.com', '12', '1', '2016-05-31 14:53:28', '30', '2', '1', '123123', '1');
INSERT INTO `t_sys_user` VALUES ('16', 'HaiAng15', '0eb07ab7099393c70fdf317c3a710934', '阿萨德发', '123', '5f9e808a3cc60c30d167e7473c69eb66', '123123', '123123', '', '1@125.com', '12', '1', '2016-05-31 14:53:28', '30', '2', '1', '123123', '1');
INSERT INTO `t_sys_user` VALUES ('17', 'HaiAng16', '329a1b664cf24ec0cc0254ea059eed27', '斯蒂芬', 'ddd', '50e92ae722ade42a4d755c672dc42739', '1864584562', '25645654825', '', '2@123.com', '15', '1', '2016-06-01 11:18:03', '30', '2', '1', '986542156', '1');
INSERT INTO `t_sys_user` VALUES ('18', 'HaiAng17', '0eb07ab7099393c70fdf317c3a710934', '阿萨德发', '123', '5f9e808a3cc60c30d167e7473c69eb66', '123123', '123123', '', '1@125.com', '12', '1', '2016-05-31 14:53:28', '30', '2', '1', '123123', '1');
INSERT INTO `t_sys_user` VALUES ('19', 'jinjing3', 'aa7810b0646ed5eab1a3c6b3f6570cf1', '懒得动', '', 'ce12b6978bacef401d44247ef20fb50d', '18675945607', '', '', '', null, '1', '2016-06-03 01:16:02', '30', null, '1', null, '1');
INSERT INTO `t_sys_user` VALUES ('20', 'weewww', '233fb96c46ef86a8cb9f5a16949a1bde', '爱到底', '', '2e6bc2889f0d57f52a8a36d02da7078a', '18765748985', '', '', '', null, '1', null, '3', null, '1', null, '1');
INSERT INTO `t_sys_user` VALUES ('21', 'weewww', '1954225221ea5797dc9ee5eb2f042ba8', '爱到底', '', 'cccef0fbbf0c476a69107cb9dd8fc3a8', '18765748985', '', '', '', null, '1', null, '3', null, '1', null, '1');
INSERT INTO `t_sys_user` VALUES ('22', 'admins', '1964db6c828265f9abcd2569abd82509', '陈海玲是', '', '4d6aca6288a341a38380e0935af87049', '18675945607', '', '', '', null, '1', '2016-06-03 14:43:52', '9', null, '1', null, '1');
INSERT INTO `t_sys_user` VALUES ('23', 'Haadasdsd', 'a185a76b8e3871e41d6d6a2dfce55237', '不清楚', '', 'b45c4ea67b49e82d03833323b12516ad', '18675945607', '', '', '', null, '1', null, '2', null, '1', null, '1');
INSERT INTO `t_sys_user` VALUES ('24', 'hekkir', '6f62c6743befaefb8c0d9dcc5be333b7', '斯蒂芬的', '', '47e40b19c8c5baf9b309dbbff9759784', '18675945607', '', '', '', null, '1', null, '34', null, '1', null, '1');

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `u_id` int(15) DEFAULT NULL COMMENT '用户ID',
  `role_id` int(15) DEFAULT NULL COMMENT '角色ID',
  `ts` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`),
  KEY `fk_user_id` (`u_id`),
  KEY `fk_r_id` (`role_id`),
  CONSTRAINT `fk_r_id` FOREIGN KEY (`role_id`) REFERENCES `t_sys_role` (`id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`u_id`) REFERENCES `t_sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO `t_sys_user_role` VALUES ('26', '8', null, null);
INSERT INTO `t_sys_user_role` VALUES ('64', '1', '1', null);

-- ----------------------------
-- Function structure for getOrgChildLst
-- ----------------------------
DROP FUNCTION IF EXISTS `getOrgChildLst`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getOrgChildLst`(orgid INT) RETURNS varchar(1000) CHARSET utf8
BEGIN
	DECLARE
		sTemp VARCHAR (1000);
DECLARE
	sTempChd VARCHAR (1000);
SET sTemp = '$';
SET sTempChd = cast(orgid AS CHAR);
WHILE sTempChd IS NOT NULL DO
SET sTemp = concat(sTemp, ',', sTempChd);
SELECT
	group_concat(id) INTO sTempChd
FROM
	t_sys_org
WHERE
	FIND_IN_SET(partent_id, sTempChd) > 0;
END
WHILE;
RETURN sTemp;
END
;;
DELIMITER ;

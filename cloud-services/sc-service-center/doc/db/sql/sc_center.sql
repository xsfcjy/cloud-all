/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : sc_center

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-07-26 16:21:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for report_active
-- ----------------------------
DROP TABLE IF EXISTS `report_active`;
CREATE TABLE `report_active` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `report_id` bigint(20) DEFAULT NULL COMMENT '报表定义id',
  `version_id` bigint(20) DEFAULT NULL COMMENT '报表版本id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动报表';

-- ----------------------------
-- Records of report_active
-- ----------------------------

-- ----------------------------
-- Table structure for report_definition
-- ----------------------------
DROP TABLE IF EXISTS `report_definition`;
CREATE TABLE `report_definition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(64) NOT NULL COMMENT '报表编码',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 ',
  `is_main` char(1) NOT NULL DEFAULT 'Y' COMMENT '是否主报表 : Y-是,N-否',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='报表定义';

-- ----------------------------
-- Records of report_definition
-- ----------------------------
INSERT INTO `report_definition` VALUES ('1', 'TEST_1', '测试报表1', '2017-05-26 14:52:09', 'Y');

-- ----------------------------
-- Table structure for report_parameter
-- ----------------------------
DROP TABLE IF EXISTS `report_parameter`;
CREATE TABLE `report_parameter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 ',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `type` varchar(16) NOT NULL COMMENT '参数类型',
  `value` varchar(50) DEFAULT NULL COMMENT '默认值',
  `data_provider` text COMMENT '参数数据提供者',
  `validator` varchar(64) DEFAULT NULL COMMENT '参数校验器',
  `element_type` varchar(16) DEFAULT NULL COMMENT '报表参数显示类型',
  `title` varchar(32) DEFAULT NULL COMMENT '前端显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='报表参数定义表';

-- ----------------------------
-- Records of report_parameter
-- ----------------------------
INSERT INTO `report_parameter` VALUES ('1', '2017-05-26 19:52:30', 'param1', 'string', 'defult', null, null, 'input', '测试报表1参数1');
INSERT INTO `report_parameter` VALUES ('2', '2017-05-26 19:52:58', 'param2', 'string', 'sfxie', null, null, 'input', '测试报表1参数2');

-- ----------------------------
-- Table structure for report_parameter_definition_version
-- ----------------------------
DROP TABLE IF EXISTS `report_parameter_definition_version`;
CREATE TABLE `report_parameter_definition_version` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parameter_id` bigint(20) DEFAULT NULL COMMENT '报表参数表id',
  `report_id` bigint(20) DEFAULT NULL COMMENT '报表定义id',
  `version_id` bigint(20) DEFAULT NULL COMMENT '报表版本id',
  PRIMARY KEY (`id`),
  KEY `reprot_id` (`report_id`),
  KEY `report_parameter_id` (`parameter_id`),
  KEY `version_id` (`version_id`),
  CONSTRAINT `report_parameter_definition_version_ibfk_1` FOREIGN KEY (`report_id`) REFERENCES `report_definition` (`id`),
  CONSTRAINT `report_parameter_definition_version_ibfk_2` FOREIGN KEY (`parameter_id`) REFERENCES `report_parameter` (`id`),
  CONSTRAINT `report_parameter_definition_version_ibfk_3` FOREIGN KEY (`version_id`) REFERENCES `report_version` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='报表版本参数关联表';

-- ----------------------------
-- Records of report_parameter_definition_version
-- ----------------------------
INSERT INTO `report_parameter_definition_version` VALUES ('1', '1', '1', null);
INSERT INTO `report_parameter_definition_version` VALUES ('2', '2', null, '1');

-- ----------------------------
-- Table structure for report_server_register
-- ----------------------------
DROP TABLE IF EXISTS `report_server_register`;
CREATE TABLE `report_server_register` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 ',
  `host` varchar(100) NOT NULL COMMENT '主机',
  `port` varchar(8) NOT NULL COMMENT '端口',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报表相关服务器注册表';

-- ----------------------------
-- Records of report_server_register
-- ----------------------------

-- ----------------------------
-- Table structure for report_version
-- ----------------------------
DROP TABLE IF EXISTS `report_version`;
CREATE TABLE `report_version` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 ',
  `report_id` bigint(20) DEFAULT NULL COMMENT '报表定义id',
  `version` varchar(16) DEFAULT NULL COMMENT '报表版本号',
  `source_jrxml` text NOT NULL COMMENT '报表源代码',
  `dataset_java` text COMMENT 'java数据源',
  `source_jasper` text COMMENT '报表编译后代码',
  `description` varchar(200) DEFAULT NULL COMMENT '报表描述',
  PRIMARY KEY (`id`),
  KEY `reprot_id` (`report_id`),
  CONSTRAINT `report_version_ibfk_1` FOREIGN KEY (`report_id`) REFERENCES `report_definition` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='报表版本';

-- ----------------------------
-- Records of report_version
-- ----------------------------
INSERT INTO `report_version` VALUES ('1', '2017-05-26 13:35:46', '1', '1', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\" name=\"2016ylmsl\" pageWidth=\"500\" pageHeight=\"842\" whenNoDataType=\"AllSectionsNoDetail\" columnWidth=\"500\" leftMargin=\"0\" rightMargin=\"0\" topMargin=\"0\" bottomMargin=\"0\" isIgnorePagination=\"true\" uuid=\"fafbb383-9d14-484c-a56a-ab61a95018b1\">\r\n	<property name=\"ireport.zoom\" value=\"1.0\"/>\r\n	<property name=\"ireport.x\" value=\"0\"/>\r\n	<property name=\"ireport.y\" value=\"0\"/>\r\n	<style name=\"table_TH\" mode=\"Opaque\" backcolor=\"#B0B0B0\">\r\n		<box>\r\n			<pen lineWidth=\"0.5\" lineColor=\"#000000\"/>\r\n		</box>\r\n	</style>\r\n	<style name=\"table_TD\" mode=\"Opaque\">\r\n		<box>\r\n			<pen lineWidth=\"0.5\" lineColor=\"#000000\"/>\r\n		</box>\r\n		<conditionalStyle>\r\n			<conditionExpression><![CDATA[(($V{REPORT_COUNT}.intValue())%2)==1]]></conditionExpression>\r\n			<style backcolor=\"#CCFFCC\"/>\r\n		</conditionalStyle>\r\n		<conditionalStyle>\r\n			<conditionExpression><![CDATA[(($V{REPORT_COUNT}.intValue())%2)==0]]></conditionExpression>\r\n			<style forecolor=\"#333333\" backcolor=\"#FFFFCC\"/>\r\n		</conditionalStyle>\r\n	</style>\r\n	<queryString>\r\n		<![CDATA[]]>\r\n	</queryString>\r\n	<field name=\"content\" class=\"java.lang.String\">\r\n		<fieldDescription><![CDATA[content]]></fieldDescription>\r\n	</field>\r\n	<field name=\"mmName\" class=\"java.lang.String\">\r\n		<fieldDescription><![CDATA[mmName]]></fieldDescription>\r\n	</field>\r\n	<field name=\"name\" class=\"java.lang.String\">\r\n		<fieldDescription><![CDATA[name]]></fieldDescription>\r\n	</field>\r\n	<field name=\"option\" class=\"java.lang.String\">\r\n		<fieldDescription><![CDATA[option]]></fieldDescription>\r\n	</field>\r\n	<field name=\"title\" class=\"java.lang.String\">\r\n		<fieldDescription><![CDATA[title]]></fieldDescription>\r\n	</field>\r\n	<columnHeader>\r\n		<band height=\"20\">\r\n			<staticText>\r\n				<reportElement style=\"table_TH\" x=\"300\" y=\"0\" width=\"100\" height=\"20\" uuid=\"d2e59684-3b1a-47a1-baff-aba7e908bb5b\"/>\r\n				<textElement textAlignment=\"Left\" verticalAlignment=\"Middle\"/>\r\n				<text><![CDATA[回答选项]]></text>\r\n			</staticText>\r\n			<staticText>\r\n				<reportElement style=\"table_TH\" x=\"0\" y=\"0\" width=\"100\" height=\"20\" uuid=\"05fa595a-0f94-44ac-bef7-41dbf0624abe\"/>\r\n				<textElement textAlignment=\"Left\" verticalAlignment=\"Middle\"/>\r\n				<text><![CDATA[管理中心]]></text>\r\n			</staticText>\r\n			<staticText>\r\n				<reportElement style=\"table_TH\" x=\"100\" y=\"0\" width=\"100\" height=\"20\" uuid=\"1535630c-d262-4957-b7dc-0896b578591c\"/>\r\n				<textElement textAlignment=\"Left\" verticalAlignment=\"Middle\"/>\r\n				<text><![CDATA[项目名称]]></text>\r\n			</staticText>\r\n			<staticText>\r\n				<reportElement style=\"table_TH\" x=\"200\" y=\"0\" width=\"100\" height=\"20\" uuid=\"d563e9b0-3778-4964-b2b2-a64e7c4d449e\"/>\r\n				<textElement textAlignment=\"Left\" verticalAlignment=\"Middle\"/>\r\n				<text><![CDATA[调查问题]]></text>\r\n			</staticText>\r\n			<staticText>\r\n				<reportElement style=\"table_TH\" x=\"400\" y=\"0\" width=\"100\" height=\"20\" uuid=\"59ae4b5b-5994-4840-a77c-ba6e8655d048\"/>\r\n				<textElement textAlignment=\"Left\" verticalAlignment=\"Middle\"/>\r\n				<text><![CDATA[具体回答]]></text>\r\n			</staticText>\r\n		</band>\r\n	</columnHeader>\r\n	<detail>\r\n		<band height=\"20\" splitType=\"Stretch\">\r\n			<textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\">\r\n				<reportElement style=\"table_TD\" stretchType=\"RelativeToBandHeight\" x=\"400\" y=\"0\" width=\"100\" height=\"20\" uuid=\"091036b0-9574-442a-9005-acb012a20f67\"/>\r\n				<textElement verticalAlignment=\"Middle\"/>\r\n				<textFieldExpression><![CDATA[$F{content}]]></textFieldExpression>\r\n			</textField>\r\n			<textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\">\r\n				<reportElement style=\"table_TD\" stretchType=\"RelativeToBandHeight\" x=\"300\" y=\"0\" width=\"100\" height=\"20\" uuid=\"d5dc1caa-34dc-452f-b93a-4fe4c4f89b3d\"/>\r\n				<textElement verticalAlignment=\"Middle\"/>\r\n				<textFieldExpression><![CDATA[$F{option}]]></textFieldExpression>\r\n			</textField>\r\n			<textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\">\r\n				<reportElement style=\"table_TD\" stretchType=\"RelativeToBandHeight\" x=\"0\" y=\"0\" width=\"100\" height=\"20\" uuid=\"a2d9b9d4-8f71-4447-9e2c-a2f1d22d79b1\"/>\r\n				<textElement verticalAlignment=\"Middle\"/>\r\n				<textFieldExpression><![CDATA[$F{mmName}]]></textFieldExpression>\r\n			</textField>\r\n			<textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\">\r\n				<reportElement style=\"table_TD\" stretchType=\"RelativeToBandHeight\" x=\"100\" y=\"0\" width=\"100\" height=\"20\" uuid=\"2c83bdf6-4a7f-4cb1-b6bb-7b8b36d3ced7\"/>\r\n				<textElement verticalAlignment=\"Middle\"/>\r\n				<textFieldExpression><![CDATA[$F{name}]]></textFieldExpression>\r\n			</textField>\r\n			<textField isStretchWithOverflow=\"true\" isBlankWhenNull=\"true\">\r\n				<reportElement style=\"table_TD\" stretchType=\"RelativeToBandHeight\" x=\"200\" y=\"0\" width=\"100\" height=\"20\" uuid=\"1b5b2550-70a3-4e26-bfe2-10c9b39254a4\"/>\r\n				<textElement verticalAlignment=\"Middle\"/>\r\n				<textFieldExpression><![CDATA[$F{title}]]></textFieldExpression>\r\n			</textField>\r\n		</band>\r\n	</detail>\r\n</jasperReport>\r\n', 'package com.sfxie.component.ui.tags.report;\r\n\r\nimport java.util.ArrayList;\r\nimport java.util.List;\r\nimport java.util.Map;\r\n\r\n/**\r\n * 列表数据源处理类示例\r\n * @author xiesf\r\n *\r\n */\r\npublic class ListReportHandler extends ReportGroovyHandler<List,Map> {\r\n\r\n	@Override\r\n	public List dataset(Map parameter) {\r\n		List<DemoReportEntity> result = new ArrayList<DemoReportEntity>();\r\n		for(int i=0;i<100;i++){\r\n			result.add(new DemoReportEntity(\"\"+i,\"name\"+i,\"1\",\"1\",\"1\"));\r\n		}\r\n		return result;\r\n	}\r\n	\r\n	public static void main(String[] args) {\r\n		ListReportHandler listReportHandler = new ListReportHandler();\r\n		System.out.println(listReportHandler.parameterClass().getCanonicalName());\r\n	}\r\n\r\n}\r\n', null, null);

-- ----------------------------
-- Table structure for sfxie_sys_action
-- ----------------------------
DROP TABLE IF EXISTS `sfxie_sys_action`;
CREATE TABLE `sfxie_sys_action` (
  `id_` varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
  `action_name` varchar(50) DEFAULT NULL COMMENT '动作名称 : 动作名称',
  `action_url` varchar(100) DEFAULT NULL COMMENT '动作资源 : 动作资源',
  `sequence_no` decimal(8,0) DEFAULT NULL COMMENT '排序字段 : 排序字段',
  `create_time` datetime NOT NULL COMMENT '创建时间 : 创建时间',
  `create_user` varchar(32) NOT NULL COMMENT '记录创建人 : 记录创建人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间 : 最后修改时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '最后修改人 : 最后修改人',
  `is_valid` char(1) DEFAULT NULL COMMENT '是否有效 : 是否有效,Y:是;N:否\r\n控制是否在公司任职有效',
  `param_flag` varchar(100) DEFAULT NULL COMMENT '置参数标记 : 如果链接中有参数，则设置参数标记。如:businessType=modify',
  `menu_code` varchar(32) DEFAULT NULL COMMENT '菜单代码 : 菜单代码',
  `partition_company` varchar(8) DEFAULT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
  PRIMARY KEY (`id_`),
  KEY `menu_code` (`menu_code`),
  CONSTRAINT `sfxie_sys_action_ibfk_1` FOREIGN KEY (`menu_code`) REFERENCES `sfxie_sys_menu` (`menu_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动作操作表';

-- ----------------------------
-- Records of sfxie_sys_action
-- ----------------------------

-- ----------------------------
-- Table structure for sfxie_sys_authorization
-- ----------------------------
DROP TABLE IF EXISTS `sfxie_sys_authorization`;
CREATE TABLE `sfxie_sys_authorization` (
  `id_` varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色代码 : 角色代码',
  `company_code` varchar(64) DEFAULT NULL COMMENT '公司代码 : 关联公司代码,表明创建公司',
  `department_code` varchar(64) DEFAULT NULL COMMENT '部门代码 : 部门代码',
  `post_code` varchar(32) DEFAULT NULL COMMENT '岗位代码 : 岗位代码',
  `is_valid` char(1) DEFAULT NULL COMMENT '是否有效 : 是否有效,Y:是;N:否\r\n控制是否在公司任职有效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间 : 创建时间',
  `create_company_id` varchar(50) DEFAULT NULL COMMENT '创建公司 : 创建公司',
  `create_user` varchar(32) DEFAULT NULL COMMENT '记录创建人 : 记录创建人',
  `partition_company` varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色授权表';

-- ----------------------------
-- Records of sfxie_sys_authorization
-- ----------------------------

-- ----------------------------
-- Table structure for sfxie_sys_auth_data
-- ----------------------------
DROP TABLE IF EXISTS `sfxie_sys_auth_data`;
CREATE TABLE `sfxie_sys_auth_data` (
  `id_` varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色主键 : 角色代码',
  `resource_name` varchar(50) DEFAULT NULL COMMENT '资源名称 : 资源名称',
  `resource_code` varchar(32) NOT NULL COMMENT '资源代码 : 资源代码',
  `sequence_no` decimal(8,0) DEFAULT NULL COMMENT '排序字段 : 排序字段',
  `create_time` datetime NOT NULL COMMENT '创建时间 : 创建时间',
  `create_user` varchar(32) NOT NULL COMMENT '记录创建人 : 记录创建人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间 : 最后修改时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '最后修改人 : 最后修改人',
  `is_valid` char(1) DEFAULT NULL COMMENT '是否有效 : 是否有效,Y:是;N:否\r\n控制是否在公司任职有效',
  `owner_company_code` varchar(64) DEFAULT NULL COMMENT '上一级公司 : 上上一级公司，也就是这一级的上一级公司',
  `sql_` varchar(2000) DEFAULT NULL COMMENT '数据权限sql查询语句',
  `role_menu_id` varchar(32) DEFAULT NULL COMMENT '角色菜单关联表id : 关联角色菜单关联表id',
  `dealer_class` varchar(200) DEFAULT NULL COMMENT 'java处理类 : 用java处理类获取sql或者用户列表',
  `create_company_id` varchar(50) DEFAULT NULL COMMENT '创建公司 : 创建公司',
  `partition_company` varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据权限表 : 表配置用户获取行层面上的数据权限';

-- ----------------------------
-- Records of sfxie_sys_auth_data
-- ----------------------------

-- ----------------------------
-- Table structure for sfxie_sys_auth_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sfxie_sys_auth_role_menu`;
CREATE TABLE `sfxie_sys_auth_role_menu` (
  `id_` varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
  `is_valid` char(1) DEFAULT NULL COMMENT '是否有效 : 是否有效,Y:是;N:否\r\n控制是否在公司任职有效',
  `role_menu_id` varchar(64) DEFAULT NULL COMMENT '角色菜单关联表id : 关联角色菜单关联表id',
  `auth_data_id` varchar(64) DEFAULT NULL COMMENT '数据权限表id : 关联数据权限表id',
  `owner_company_code` varchar(64) DEFAULT NULL COMMENT '上一级公司 : 上上一级公司，也就是这一级的上一级公司',
  `company_role_id` varchar(32) DEFAULT NULL COMMENT '公司角色关联表id : 公司角色关联表id',
  `partition_company` varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
  PRIMARY KEY (`id_`),
  KEY `auth_data_id` (`auth_data_id`),
  KEY `role_menu_id` (`role_menu_id`),
  CONSTRAINT `sfxie_sys_auth_role_menu_ibfk_1` FOREIGN KEY (`auth_data_id`) REFERENCES `sfxie_sys_auth_data` (`id_`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sfxie_sys_auth_role_menu_ibfk_2` FOREIGN KEY (`role_menu_id`) REFERENCES `sfxie_sys_role_menu` (`id_`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据权限角色菜单关联表';

-- ----------------------------
-- Records of sfxie_sys_auth_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sfxie_sys_company
-- ----------------------------
DROP TABLE IF EXISTS `sfxie_sys_company`;
CREATE TABLE `sfxie_sys_company` (
  `id_` varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
  `company_code` varchar(64) NOT NULL COMMENT '公司代码 : 关联公司代码,表明创建公司',
  `company_name_cn` varchar(250) NOT NULL COMMENT '公司中文名称 : 公司中文名称',
  `company_name_en` varchar(250) DEFAULT NULL COMMENT '公司英文名称',
  `address` varchar(300) DEFAULT NULL COMMENT '公司地址 : 公司地址',
  `tel` varchar(60) DEFAULT NULL COMMENT '公司电话',
  `fax` varchar(60) DEFAULT NULL COMMENT '公司传真',
  `url` varchar(100) DEFAULT NULL COMMENT '主页URL : 主页URL',
  `email` varchar(60) DEFAULT NULL COMMENT 'email : email',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注 : 备注',
  `create_user` varchar(32) NOT NULL COMMENT '记录创建人 : 记录创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间 : 创建时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '最后修改人 : 最后修改人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间 : 最后修改时间',
  `sequence_no` decimal(8,0) DEFAULT NULL COMMENT '排序字段 : 排序字段',
  `is_valid` char(1) DEFAULT 'Y' COMMENT '是否有效 : 是否有效,Y:是;N:否;控制是否在公司任职有效',
  `short_name_cn` varchar(50) DEFAULT NULL COMMENT '公司中文名称简称 : 公司中文名称简称',
  `short_name_en` varchar(50) DEFAULT NULL COMMENT '公司英文名称简称 : 公司英文名称简称',
  `company_level` int(11) DEFAULT NULL COMMENT '公司级别 : 用来控制公司下面管理员的级别。\r\n超级管理员: 值为"上级超级管理的级别+1";\r\n普通用户: 默认值为 -1\r\n\r\n作用:用于创建无限级超级管理员',
  `create_company_id` varchar(50) NOT NULL COMMENT '创建公司 : 创建公司',
  PRIMARY KEY (`id_`),
  UNIQUE KEY `company_code` (`company_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司基础信息表';

-- ----------------------------
-- Records of sfxie_sys_company
-- ----------------------------
INSERT INTO `sfxie_sys_company` VALUES ('00000000000000000000000000000000', 'test_company', '测试公司', 'test', null, null, null, null, null, null, 'sfxie', '2017-07-13 15:29:15', null, null, '1', 'Y', null, null, '0', '00000000000000000000000000000000');
INSERT INTO `sfxie_sys_company` VALUES ('1', 'test_sfxie', '测试公司1', 'testcompany1', 'test', null, null, null, null, '222', 'sfxie', '2017-05-01 17:21:06', null, null, '1', 'Y', null, null, '1', '00000000000000000000000000000000');
INSERT INTO `sfxie_sys_company` VALUES ('2', 'test_sfxie2', '测试公司112', 'testcompany2', 'test', null, null, null, null, '11', 'sfxie', '2017-05-01 19:11:41', null, null, '2', 'Y', null, null, '1', '00000000000000000000000000000000');
INSERT INTO `sfxie_sys_company` VALUES ('3', 'test_company2', '测试公司新增', null, null, null, null, null, null, '测试公司新增', 'sfxie', '2017-07-05 15:21:21', null, null, null, 'Y', null, null, '1', '00000000000000000000000000000000');
INSERT INTO `sfxie_sys_company` VALUES ('4AA69E9FF0C34C4C81FC7BFFCFF02E06', 'test_organization4', '测试新增组织结构4', null, null, null, null, null, null, '测试新增组织结构4', 'sfxie', '2017-07-21 15:01:28', null, null, null, 'Y', null, null, '3', '00000000000000000000000000000000');
INSERT INTO `sfxie_sys_company` VALUES ('B47774E312E348648ADB167FCEEE14C6', 'test_organization2', '测试新增组织结构2', null, null, null, null, null, null, '测试新增组织结构2', 'sfxie', '2017-07-21 14:42:22', null, null, null, 'Y', null, null, '3', '00000000000000000000000000000000');
INSERT INTO `sfxie_sys_company` VALUES ('BA259EDACA984E1D8B153819C1F31F37', 'test_organization3', '测试新增组织结构3', null, null, null, null, null, null, '测试新增组织结构3', 'sfxie', '2017-07-21 14:58:39', null, null, null, 'Y', null, null, '3', '00000000000000000000000000000000');
INSERT INTO `sfxie_sys_company` VALUES ('caaa750f-b3d5-425d-b7b6-04e6b634b13b', 'test_company3', '测试公司322', null, null, null, null, null, null, 'ew1', 'sfxie', '2017-07-05 15:24:32', null, null, null, 'Y', null, null, '2', '00000000000000000000000000000000');
INSERT INTO `sfxie_sys_company` VALUES ('CBA3E0AEB02C4D69BD16AFF265237B6A', 'test_organization1', '测试新增组织结构1', null, null, null, null, null, null, '测试新增组织结构1', 'sfxie', '2017-07-21 14:39:18', null, null, null, 'Y', null, null, '2', '00000000000000000000000000000000');

-- ----------------------------
-- Table structure for sfxie_sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sfxie_sys_department`;
CREATE TABLE `sfxie_sys_department` (
  `id_` varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
  `department_code` varchar(64) NOT NULL COMMENT '部门代码 : 部门代码',
  `department_name` varchar(100) DEFAULT NULL COMMENT '部门名称 : 部门名称',
  `create_user` varchar(32) NOT NULL COMMENT '记录创建人 : 记录创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间 : 创建时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '最后修改人 : 最后修改人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间 : 最后修改时间',
  `is_valid` char(1) DEFAULT NULL COMMENT '是否有效 : 是否有效,Y:是;N:否\r\n控制是否在公司任职有效',
  `sequence_no` decimal(8,0) DEFAULT NULL COMMENT '排序字段 : 排序字段',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父主键 : 关联父主键',
  `create_company_id` varchar(50) DEFAULT NULL COMMENT '创建公司 : 创建公司',
  `company_code` varchar(64) NOT NULL COMMENT '公司代码 : 关联公司代码,表明创建公司',
  `parent_code` varchar(64) DEFAULT NULL COMMENT ' 父节点代码',
  `partition_company` varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
  PRIMARY KEY (`id_`),
  UNIQUE KEY `department_code` (`department_code`),
  KEY `company_code` (`company_code`),
  CONSTRAINT `sfxie_sys_department_ibfk_1` FOREIGN KEY (`company_code`) REFERENCES `sfxie_sys_company` (`company_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sfxie_sys_department
-- ----------------------------
INSERT INTO `sfxie_sys_department` VALUES ('1qwqwe', 'test_department', '测试部门', 'sfxie', '2017-05-01 19:04:16', null, null, 'Y', '1', null, '00000000000000000000000000000000', 'test_company2', null, '0');
INSERT INTO `sfxie_sys_department` VALUES ('2qwqw', 'test_addDepartment1', '测试新增部门1', 'sfxie', '2017-07-24 14:28:29', null, null, 'Y', '2', null, '00000000000000000000000000000000', 'test_company2', null, '0');

-- ----------------------------
-- Table structure for sfxie_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sfxie_sys_menu`;
CREATE TABLE `sfxie_sys_menu` (
  `id_` varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
  `menu_code` varchar(32) NOT NULL COMMENT '菜单代码 : 菜单代码',
  `system_id` varchar(64) DEFAULT NULL COMMENT '关联system_id : 关联web_system表',
  `auth_data_id` varchar(32) DEFAULT NULL COMMENT '数据权限id : 关联数据权限主键',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称 : 菜单名称',
  `url` varchar(100) DEFAULT NULL COMMENT '资源 : 资源',
  `description` varchar(200) DEFAULT NULL COMMENT '描述 : 描述',
  `menu_name_tw` varchar(50) DEFAULT NULL COMMENT '繁体菜单名称 : 繁体菜单名称',
  `webside` varchar(10) DEFAULT NULL COMMENT '网站标识 : 网站标识 smis ems sms',
  `sequence_no` decimal(8,0) DEFAULT NULL COMMENT '排序字段 : 排序字段',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父主键 : 关联父主键',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间 : 最后修改时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '最后修改人 : 最后修改人',
  `create_time` datetime NOT NULL COMMENT '创建时间 : 创建时间',
  `create_user` varchar(32) NOT NULL COMMENT '记录创建人 : 记录创建人',
  `is_valid` char(1) DEFAULT 'Y' COMMENT '是否有效 : 是否有效,Y:是;N:否\r\n控制是否在公司任职有效',
  `all_parent_id` char(1) DEFAULT NULL COMMENT 'all_parent_id',
  `partition_company` varchar(8) DEFAULT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
  PRIMARY KEY (`id_`),
  UNIQUE KEY `menu_code` (`menu_code`),
  KEY `system_id` (`system_id`),
  CONSTRAINT `sfxie_sys_menu_ibfk_1` FOREIGN KEY (`system_id`) REFERENCES `sfxie_sys_system` (`id_`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sfxie_sys_menu
-- ----------------------------
INSERT INTO `sfxie_sys_menu` VALUES ('1', 'sys_org', '1', null, 'system.menu.organization', null, '组织架构管理', null, null, '1', null, null, null, '2017-05-05 08:51:05', 'sfxie', 'Y', null, '0');
INSERT INTO `sfxie_sys_menu` VALUES ('2', 'sys_center_company', '1', null, 'system.menu.organization.company', '/jsp/center/companyList.jsp', '公司管理', null, null, '1', '1', null, null, '2017-05-05 08:57:28', 'sfxie', 'Y', null, '0');
INSERT INTO `sfxie_sys_menu` VALUES ('3', 'sys_report', '1', null, 'system.menu.report', null, '报表', null, null, '2', null, null, null, '2017-05-20 19:24:58', 'sfxie', 'Y', null, '0');
INSERT INTO `sfxie_sys_menu` VALUES ('4', 'sys_report_manager', '1', null, 'system.menu.report.manager', '/jsp/report/reportList.jsp', '报表管理', null, null, '1', '3', null, null, '2017-06-07 08:38:06', 'sfxie', 'Y', null, '0');
INSERT INTO `sfxie_sys_menu` VALUES ('5', 'sys_report_display', '1', null, 'system.menu.report.display', '/jsp/report/report.jsp', '报表展示', null, null, '2', '3', null, null, '2017-07-07 13:43:59', 'sfxie', 'Y', null, '0');
INSERT INTO `sfxie_sys_menu` VALUES ('6', 'sys_center_organization', '1', null, 'system.menu.organization.organization', '/jsp/center/organization/index.jsp', '组织结构管理', null, null, '1', '1', null, null, '2017-05-05 08:57:28', 'sfxie', 'Y', null, '0');

-- ----------------------------
-- Table structure for sfxie_sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sfxie_sys_organization`;
CREATE TABLE `sfxie_sys_organization` (
  `id_` varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
  `parent_company_code` varchar(64) NOT NULL COMMENT '公司代码 : 关联公司代码,表明创建公司',
  `company_code` varchar(64) NOT NULL COMMENT '公司代码 : 关联公司代码,表明创建公司',
  `is_valid` char(1) DEFAULT 'Y' COMMENT '是否有效 : 是否有效,Y:是;N:否\r\n控制是否在公司任职有效',
  `sequence_no` decimal(8,0) DEFAULT NULL COMMENT '排序字段 : 排序字段',
  `invalid_date` datetime DEFAULT NULL COMMENT '无效时间 : 无效时间',
  `create_time` datetime NOT NULL COMMENT '创建时间 : 创建时间',
  `create_user` varchar(32) NOT NULL COMMENT '记录创建人 : 记录创建人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间 : 最后修改时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '最后修改人 : 最后修改人',
  PRIMARY KEY (`id_`),
  KEY `parent_company_code` (`parent_company_code`),
  KEY `company_code` (`company_code`),
  CONSTRAINT `sfxie_sys_organization_ibfk_1` FOREIGN KEY (`parent_company_code`) REFERENCES `sfxie_sys_company` (`company_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sfxie_sys_organization_ibfk_2` FOREIGN KEY (`company_code`) REFERENCES `sfxie_sys_company` (`company_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织架构表 : 用于关联公司表记录';

-- ----------------------------
-- Records of sfxie_sys_organization
-- ----------------------------
INSERT INTO `sfxie_sys_organization` VALUES ('0', 'test_company', 'test_company', 'Y', '1', null, '2017-07-18 15:27:44', 'sfxie', null, null);
INSERT INTO `sfxie_sys_organization` VALUES ('0DE71B2FCD8F4EF0B1F9A637C86CEFAD', 'test_organization1', 'test_organization3', 'Y', null, null, '2017-07-21 14:58:39', 'sfxie', null, null);
INSERT INTO `sfxie_sys_organization` VALUES ('1', 'test_company', 'test_sfxie', 'Y', '1', null, '2017-07-13 15:30:33', 'sfxie', null, null);
INSERT INTO `sfxie_sys_organization` VALUES ('2', 'test_company', 'test_sfxie2', 'Y', '1', null, '2017-07-13 15:32:11', 'sfxie', null, null);
INSERT INTO `sfxie_sys_organization` VALUES ('3', 'test_company', 'test_company2', 'Y', '2', null, '2017-07-13 15:32:30', 'sfxie', null, null);
INSERT INTO `sfxie_sys_organization` VALUES ('4', 'test_company2', 'test_company3', 'Y', '3', null, '2017-07-13 15:32:48', 'sfxie', null, null);
INSERT INTO `sfxie_sys_organization` VALUES ('4F37D3B0863B4F52875E1F06A7F97D52', 'test_organization1', 'test_organization4', 'Y', null, null, '2017-07-21 15:01:28', 'sfxie', null, null);
INSERT INTO `sfxie_sys_organization` VALUES ('9C81BE70B97443748F8734F8848A2B2C', 'test_organization1', 'test_organization2', 'Y', null, null, '2017-07-21 14:42:22', 'sfxie', null, null);
INSERT INTO `sfxie_sys_organization` VALUES ('9E7D63A4452340D1AD89B1F8E0654B31', 'test_company2', 'test_organization1', 'Y', null, null, '2017-07-21 14:39:18', 'sfxie', null, null);

-- ----------------------------
-- Table structure for sfxie_sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sfxie_sys_post`;
CREATE TABLE `sfxie_sys_post` (
  `id_` varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
  `post_code` varchar(32) NOT NULL COMMENT '岗位代码 : 岗位代码',
  `department_code` varchar(64) DEFAULT NULL COMMENT '部门主键 : 部门主键',
  `post_name` varchar(50) DEFAULT NULL COMMENT '岗位名称 : 岗位名称',
  `sequence_no` decimal(8,0) DEFAULT NULL COMMENT '排序字段 : 排序字段',
  `post_level` char(1) DEFAULT NULL COMMENT '岗位级别 : 0表示第一岗位,1表示第2岗位,2表示第3岗位',
  `create_time` datetime NOT NULL COMMENT '创建时间 : 创建时间',
  `create_user` varchar(32) NOT NULL COMMENT '记录创建人 : 记录创建人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间 : 最后修改时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '最后修改人 : 最后修改人',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司主键 : 公司主键',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父主键 : 关联父主键',
  `is_valid` char(1) DEFAULT NULL COMMENT '是否有效 : 是否有效,Y:是;N:否\r\n控制是否在公司任职有效',
  `create_company_id` varchar(50) DEFAULT NULL COMMENT '创建公司 : 创建公司',
  `partition_company` varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
  PRIMARY KEY (`id_`),
  UNIQUE KEY `post_code` (`post_code`),
  KEY `department_id` (`department_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='岗位表';

-- ----------------------------
-- Records of sfxie_sys_post
-- ----------------------------
INSERT INTO `sfxie_sys_post` VALUES ('1', 'test_post', 'test_department', '测试岗位', '1', null, '2017-07-15 12:30:56', 'sfxie', null, null, 'test_company2', null, 'Y', '00000000000000000000000000000000', '0');
INSERT INTO `sfxie_sys_post` VALUES ('72ec0a39-678a-440a-babc-ba9271c1551e', 'test_addDepartmentPost1', 'test_department', '测试添加部门岗位1', null, null, '2017-07-24 17:27:36', 'sfxie', null, null, '', null, 'Y', '00000000000000000000000000000000', '0');
INSERT INTO `sfxie_sys_post` VALUES ('c49f1c66-c0b9-48b9-83c3-fd1e5d1ab0f5', 'test_addPost1', '', '添加测试岗位1', null, null, '2017-07-24 17:24:21', 'sfxie', null, null, 'test_company2', null, 'Y', '00000000000000000000000000000000', '0');

-- ----------------------------
-- Table structure for sfxie_sys_post_role
-- ----------------------------
DROP TABLE IF EXISTS `sfxie_sys_post_role`;
CREATE TABLE `sfxie_sys_post_role` (
  `id_` varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
  `owner_company_code` varchar(64) DEFAULT NULL COMMENT '上一级公司 : 上上一级公司，也就是这一级的上一级公司',
  `is_valid` char(1) DEFAULT 'Y' COMMENT '是否有效 : 是否有效,Y:是;N:否\r\n控制是否在公司任职有效',
  `post_code` varchar(32) NOT NULL COMMENT '岗位代码 : 岗位代码',
  `role_code` varchar(32) NOT NULL COMMENT '角色代码 : 角色代码',
  `partition_company` varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
  PRIMARY KEY (`id_`),
  UNIQUE KEY `post_code` (`post_code`,`role_code`),
  KEY `role_code` (`role_code`),
  CONSTRAINT `sfxie_sys_post_role_ibfk_1` FOREIGN KEY (`post_code`) REFERENCES `sfxie_sys_post` (`post_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sfxie_sys_post_role_ibfk_2` FOREIGN KEY (`role_code`) REFERENCES `sfxie_sys_role` (`role_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='岗位角色关联表';

-- ----------------------------
-- Records of sfxie_sys_post_role
-- ----------------------------

-- ----------------------------
-- Table structure for sfxie_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sfxie_sys_role`;
CREATE TABLE `sfxie_sys_role` (
  `id_` varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
  `role_code` varchar(32) NOT NULL COMMENT '角色代码 : 角色代码',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称 : 角色名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述 : 描述',
  `role_name_en` varchar(50) DEFAULT NULL COMMENT '角色英文名称 : 角色英文名称',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间 : 最后修改时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '最后修改人 : 最后修改人',
  `create_time` datetime NOT NULL COMMENT '创建时间 : 创建时间',
  `create_user` varchar(32) NOT NULL COMMENT '记录创建人 : 记录创建人',
  `sequence_no` decimal(8,0) DEFAULT NULL COMMENT '排序字段 : 排序字段',
  `is_valid` char(1) DEFAULT NULL COMMENT '是否有效 : 是否有效,Y:是;N:否\r\n控制是否在公司任职有效',
  `create_company_id` varchar(50) DEFAULT NULL COMMENT '创建公司 : 创建公司',
  `partition_company` varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
  PRIMARY KEY (`id_`),
  UNIQUE KEY `role_code` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sfxie_sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sfxie_sys_role_action
-- ----------------------------
DROP TABLE IF EXISTS `sfxie_sys_role_action`;
CREATE TABLE `sfxie_sys_role_action` (
  `id_` varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
  `action_id` varchar(64) NOT NULL COMMENT '操作主键 : 操作主键',
  `role_menu_id` varchar(64) NOT NULL COMMENT '角色菜单关联表id : 关联角色菜单关联表id',
  `owner_company_code` varchar(64) DEFAULT NULL COMMENT '上一级公司 : 上上一级公司，也就是这一级的上一级公司',
  `is_valid` char(1) DEFAULT 'Y' COMMENT '是否有效 : 是否有效,Y:是;N:否\r\n控制是否在公司任职有效',
  `company_role_id` varchar(32) DEFAULT NULL COMMENT '公司角色关联表id : 公司角色关联表id',
  `param_flag` varchar(100) DEFAULT NULL COMMENT '参数标记 : 如果链接中有参数，则设置参数标记。如:businessType=modify',
  `partition_company` varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
  PRIMARY KEY (`id_`),
  KEY `action_id` (`action_id`),
  KEY `role_menu_id` (`role_menu_id`),
  CONSTRAINT `sfxie_sys_role_action_ibfk_1` FOREIGN KEY (`action_id`) REFERENCES `sfxie_sys_action` (`id_`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sfxie_sys_role_action_ibfk_2` FOREIGN KEY (`role_menu_id`) REFERENCES `sfxie_sys_role_menu` (`id_`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sfxie_sys_role_action';

-- ----------------------------
-- Records of sfxie_sys_role_action
-- ----------------------------

-- ----------------------------
-- Table structure for sfxie_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sfxie_sys_role_menu`;
CREATE TABLE `sfxie_sys_role_menu` (
  `id_` varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
  `owner_company_code` varchar(64) DEFAULT NULL COMMENT '上一级公司 : 上上一级公司，也就是这一级的上一级公司',
  `is_valid` char(1) DEFAULT 'Y' COMMENT '是否有效 : 是否有效,Y:是;N:否\r\n控制是否在公司任职有效',
  `company_role_id` varchar(32) DEFAULT NULL COMMENT '公司角色关联表id : 公司角色关联表id',
  `create_company_id` varchar(50) DEFAULT NULL COMMENT '创建公司 : 创建公司',
  `oraginal_role_menu_id` varchar(64) DEFAULT NULL COMMENT '继承的角色菜单id : 继承的角色菜单id，记录此条数据是从哪条角色菜单中继承而来的',
  `role_code` varchar(32) NOT NULL COMMENT '角色代码 : 角色代码',
  `menu_code` varchar(32) NOT NULL COMMENT '菜单代码 : 菜单代码',
  `partition_company` varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
  PRIMARY KEY (`id_`),
  KEY `menu_code` (`menu_code`),
  KEY `role_code` (`role_code`),
  CONSTRAINT `sfxie_sys_role_menu_ibfk_1` FOREIGN KEY (`menu_code`) REFERENCES `sfxie_sys_menu` (`menu_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sfxie_sys_role_menu_ibfk_2` FOREIGN KEY (`role_code`) REFERENCES `sfxie_sys_role` (`role_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of sfxie_sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sfxie_sys_system
-- ----------------------------
DROP TABLE IF EXISTS `sfxie_sys_system`;
CREATE TABLE `sfxie_sys_system` (
  `id_` varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
  `system_code` varchar(32) NOT NULL COMMENT '系统代码 : 系统代码',
  `system_name` varchar(200) DEFAULT NULL COMMENT '系统名称 : 系统名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述 : 描述',
  `sys_url` varchar(2000) DEFAULT NULL COMMENT 'sys_url',
  `sys_inner_url` varchar(2000) DEFAULT NULL COMMENT 'sys_inner_url',
  `sequence_no` decimal(8,0) DEFAULT NULL COMMENT '排序字段 : 排序字段',
  `is_valid` char(1) DEFAULT 'Y' COMMENT '是否有效 : 是否有效,Y:是;N:否\r\n控制是否在公司任职有效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间 : 创建时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '记录创建人 : 记录创建人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间 : 最后修改时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '最后修改人 : 最后修改人',
  `partition_company` varchar(8) DEFAULT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
  PRIMARY KEY (`id_`),
  UNIQUE KEY `system_code` (`system_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统表';

-- ----------------------------
-- Records of sfxie_sys_system
-- ----------------------------
INSERT INTO `sfxie_sys_system` VALUES ('1', 'sys_center', '云后台管理平台', '云后台管理平台', '', null, null, 'Y', '2017-05-05 08:56:13', 'sfxie', null, null, '0');
INSERT INTO `sfxie_sys_system` VALUES ('2', 'sys_center_company', '公司', '公司', null, null, null, 'Y', '2017-05-05 08:56:20', 'sfxie', null, null, '0');

-- ----------------------------
-- Table structure for sfxie_sys_userinfo
-- ----------------------------
DROP TABLE IF EXISTS `sfxie_sys_userinfo`;
CREATE TABLE `sfxie_sys_userinfo` (
  `user_id` varchar(32) NOT NULL COMMENT '用户代码 : 用户代码,用户登录名',
  `user_password` varchar(50) NOT NULL COMMENT '用户密码 : 用户密码,加密后的',
  `is_valid` char(1) DEFAULT NULL COMMENT '是否有效 : 是否有效,Y:是;N:否\r\n控制是否在公司任职有效',
  `create_time` datetime NOT NULL COMMENT '创建时间 : 创建时间',
  `create_user` varchar(32) NOT NULL COMMENT '记录创建人 : 记录创建人',
  `update_user` varchar(32) DEFAULT NULL COMMENT '最后修改人 : 最后修改人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间 : 最后修改时间',
  `sequence_no` int(11) DEFAULT NULL COMMENT '排序字段 : 排序字段',
  `is_superman` char(1) NOT NULL DEFAULT 'N' COMMENT '是否为超级管理员 : 是否为超级管理员，默认为否\r\nY-是\r\nN-否\r\n\r\n只级超级管理员才可以操作此字段',
  `create_company_id` varchar(50) NOT NULL COMMENT '创建公司 : 创建公司',
  `sex` char(1) DEFAULT NULL COMMENT '用户性别 : m-男,f-女',
  `partition_company` varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
  `create_company_level` int(11) DEFAULT NULL COMMENT '创建公司级别',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sfxie_sys_userinfo
-- ----------------------------
INSERT INTO `sfxie_sys_userinfo` VALUES ('sfxie', '', null, '2017-07-18 14:10:54', '', null, null, null, 'Y', '00000000000000000000000000000000', null, '0', '1');

-- ----------------------------
-- Table structure for sfxie_sys_user_relation
-- ----------------------------
DROP TABLE IF EXISTS `sfxie_sys_user_relation`;
CREATE TABLE `sfxie_sys_user_relation` (
  `id_` varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
  `user_id` varchar(32) NOT NULL COMMENT '用户代码 : 用户代码',
  `user_title` char(1) DEFAULT NULL COMMENT '用户职位 : 用户职位',
  `is_valid` char(1) DEFAULT NULL COMMENT '是否有效 : 是否有效,Y:是;N:否\r\n控制是否在公司任职有效',
  `create_time` datetime NOT NULL COMMENT '创建时间 : 创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间 : 最后修改时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '最后修改人 : 最后修改人',
  `create_user` varchar(32) NOT NULL COMMENT '记录创建人 : 记录创建人',
  `create_company_id` varchar(50) NOT NULL COMMENT '创建公司 : 创建公司',
  `email` varchar(60) NOT NULL COMMENT 'email : email',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号 : 手机号',
  `user_name_cn` varchar(60) NOT NULL COMMENT '用户中文名 : 用户中文名',
  `user_name_en` varchar(60) DEFAULT NULL COMMENT '用户英文名称 : 用户名称 en',
  `sequence_no` decimal(8,0) DEFAULT NULL COMMENT '排序字段 : 排序字段',
  `user_type` char(1) DEFAULT NULL COMMENT '用户类型 : 用户类型(超级管理员，普通用户)\r\n超级管理员: S\r\n管理员: M\r\n普通用户: U',
  `is_default` char(1) DEFAULT NULL COMMENT '是否默认 : 系统默认的用户信息(只有一个默认值1)\r\n控制默认登录系统后获取的权限(根据公司):\r\n默认:1\r\n不默认:0',
  `company_code` varchar(64) DEFAULT NULL COMMENT '公司代码 : 关联公司代码,表明创建公司',
  `post_code` varchar(32) DEFAULT NULL COMMENT '岗位代码 : 岗位代码',
  `department_code` varchar(64) DEFAULT NULL COMMENT '部门代码 : 部门代码',
  `partition_company` varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
  PRIMARY KEY (`id_`),
  KEY `company_code` (`company_code`),
  KEY `department_code` (`department_code`),
  KEY `post_code` (`post_code`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `sfxie_sys_user_relation_ibfk_1` FOREIGN KEY (`company_code`) REFERENCES `sfxie_sys_company` (`company_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sfxie_sys_user_relation_ibfk_2` FOREIGN KEY (`department_code`) REFERENCES `sfxie_sys_department` (`department_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sfxie_sys_user_relation_ibfk_3` FOREIGN KEY (`post_code`) REFERENCES `sfxie_sys_post` (`post_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sfxie_sys_user_relation_ibfk_4` FOREIGN KEY (`user_id`) REFERENCES `sfxie_sys_userinfo` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员档案-关系表';

-- ----------------------------
-- Records of sfxie_sys_user_relation
-- ----------------------------
INSERT INTO `sfxie_sys_user_relation` VALUES ('111', 'sfxie', '', 'Y', '2017-07-26 14:49:28', null, null, '', '00000000000000000000000000000000', '', null, '谢声锋', null, null, null, null, 'test_company2', null, null, '0');

-- ----------------------------
-- Table structure for sfxie_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sfxie_sys_user_role`;
CREATE TABLE `sfxie_sys_user_role` (
  `id_` varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
  `user_id` varchar(32) NOT NULL COMMENT '用户代码 : 用户代码',
  `owner_company_code` varchar(64) DEFAULT NULL COMMENT '上一级公司 : 上上一级公司，也就是这一级的上一级公司',
  `is_valid` char(1) DEFAULT 'Y' COMMENT '是否有效 : 是否有效,Y:是;N:否\r\n控制是否在公司任职有效',
  `user_info_relation_id` varchar(32) DEFAULT NULL COMMENT '用户关联表id',
  `role_code` varchar(32) NOT NULL COMMENT '角色代码 : 角色代码',
  `partition_company` varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
  PRIMARY KEY (`id_`),
  UNIQUE KEY `user_id` (`user_id`),
  KEY `role_code` (`role_code`),
  CONSTRAINT `sfxie_sys_user_role_ibfk_1` FOREIGN KEY (`role_code`) REFERENCES `sfxie_sys_role` (`role_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sfxie_sys_user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `sfxie_sys_userinfo` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sfxie_sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for sfxie_ui_skin
-- ----------------------------
DROP TABLE IF EXISTS `sfxie_ui_skin`;
CREATE TABLE `sfxie_ui_skin` (
  `id_` varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
  `code` varchar(16) DEFAULT NULL COMMENT '代码 : 代码-类型',
  `css` varchar(50) DEFAULT NULL COMMENT 'css',
  `partition_company` varchar(8) DEFAULT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
  `create_user` varchar(32) DEFAULT NULL COMMENT '记录创建人 : 记录创建人',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sfxie_ui_skin';

-- ----------------------------
-- Records of sfxie_ui_skin
-- ----------------------------
INSERT INTO `sfxie_ui_skin` VALUES ('test_company2', 'company', 'pIcon01', '0', 'sfxie');

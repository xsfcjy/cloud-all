CREATE TABLE `api_gateway_system` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID : 主键ID',
  `name` varchar(64) DEFAULT NULL COMMENT '名称 : 系统名称',
  `code` varchar(32) DEFAULT NULL COMMENT '编码 : 系统编码',
  `enable` tinyint(4) DEFAULT '1' COMMENT '是否生效：0-未生效；1-已生效',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 : 创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_APIGATEWAYSYSTEM_NAME` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='app鉴权系统表';

CREATE TABLE `api_gateway_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID : 主键ID',
  `module_name` varchar(32) DEFAULT NULL COMMENT '模块名称',
  `system_id` int(11) DEFAULT NULL COMMENT '鉴权系统表id : 主键ID',
  `enable` tinyint(4) DEFAULT '1' COMMENT '是否生效：0-未生效；1-已生效',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 : 创建时间',
  PRIMARY KEY (`id`),
  KEY `system_id` (`system_id`) USING BTREE,
  CONSTRAINT `api_gateway_module_ibfk_1` FOREIGN KEY (`system_id`) REFERENCES `api_gateway_system` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='app鉴权模块表';

CREATE TABLE `api_gateway_interface` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID : 主键ID',
  `server_name` varchar(128) DEFAULT NULL COMMENT '接口名称',
  `server_url` varchar(128) DEFAULT NULL COMMENT '接口URL',
  `method_name` varchar(32) DEFAULT NULL COMMENT '方法名 : 方法名',
  `description` varchar(512) DEFAULT NULL COMMENT '描述 : 描述',
  `enable` tinyint(4) DEFAULT '1' COMMENT '是否生效：0-未生效；1-已生效',
  `create_by` varchar(32) NOT NULL COMMENT '创建人 : 创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 : 创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人 : 修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间 : 修改时间',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否删除 : 1-是，0-否',
  `delete_by` varchar(32) DEFAULT NULL COMMENT '删除人',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `module_id` int(11) DEFAULT NULL COMMENT '模块id : 主键ID',
  `match_type` tinyint(4) DEFAULT '1' COMMENT '匹配方式 : 1-通配符,2-正则,3-全路径',
  PRIMARY KEY (`id`),
  KEY `module_id` (`module_id`) USING BTREE,
  CONSTRAINT `api_gateway_interface_ibfk_1` FOREIGN KEY (`module_id`) REFERENCES `api_gateway_module` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='app鉴权接口表';

CREATE TABLE `api_gateway_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID : 主键ID',
  `app_name` varchar(128) NOT NULL COMMENT '名称 : 名称',
  `app_id` varchar(255) NOT NULL COMMENT '接入系统id',
  `app_secret` varchar(255) NOT NULL COMMENT '授权KEY',
  `description` varchar(512) DEFAULT NULL COMMENT '描述 : 描述',
  `enable` tinyint(4) DEFAULT '1' COMMENT '是否生效：0-未生效；1-已生效',
  `create_by` varchar(32) NOT NULL COMMENT '创建人 : 创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 : 创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人 : 修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间 : 修改时间',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否删除 : 1-是，0-否',
  `delete_by` varchar(32) DEFAULT NULL COMMENT '删除人',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `type` char(1) DEFAULT NULL COMMENT '配置类型 : I-内部系统,O-外部系统',
  PRIMARY KEY (`id`),
  UNIQUE KEY `app_id` (`app_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='app鉴权配置表';

CREATE TABLE `api_gateway_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID : 主键ID',
  `appgateway_config_id` int(11) NOT NULL COMMENT 'APP主键ID : APP主键ID',
  `appgateway_interface_id` int(11) NOT NULL COMMENT '客户端主键ID : 客户端主键ID',
  PRIMARY KEY (`id`),
  KEY `FK_VIP_APPS_CONFIG_ID` (`appgateway_config_id`) USING BTREE,
  KEY `FK_VIP_SERVER_INTERFACES_ID` (`appgateway_interface_id`) USING BTREE,
  CONSTRAINT `api_gateway_authority_ibfk_1` FOREIGN KEY (`appgateway_config_id`) REFERENCES `api_gateway_config` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `api_gateway_authority_ibfk_2` FOREIGN KEY (`appgateway_interface_id`) REFERENCES `api_gateway_interface` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8 COMMENT='app鉴权权限表';


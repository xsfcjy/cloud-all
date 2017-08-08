SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE sfxie_sys_role_action;
DROP TABLE sfxie_sys_action;
DROP TABLE sfxie_sys_authorization;
DROP TABLE sfxie_sys_auth_role_menu;
DROP TABLE sfxie_sys_auth_data;
DROP TABLE sfxie_sys_post_role;
DROP TABLE sfxie_sys_user_relation;
DROP TABLE sfxie_sys_post;
DROP TABLE sfxie_sys_department;
DROP TABLE sfxie_sys_organization;
DROP TABLE sfxie_sys_company;
DROP TABLE sfxie_sys_role_menu;
DROP TABLE sfxie_sys_menu;
DROP TABLE sfxie_sys_user_role;
DROP TABLE sfxie_sys_role;
DROP TABLE sfxie_sys_system;
DROP TABLE sfxie_sys_userinfo;
DROP TABLE sfxie_ui_skin;




/* Create Tables */

CREATE TABLE sfxie_sys_action
(
	-- 记录主键
	id_ varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
	-- 动作名称
	action_name varchar(50) COMMENT '动作名称 : 动作名称',
	-- 动作资源
	action_url varchar(100) COMMENT '动作资源 : 动作资源',
	-- 排序字段
	sequence_no decimal(8) COMMENT '排序字段 : 排序字段',
	-- 创建时间
	create_time datetime NOT NULL COMMENT '创建时间 : 创建时间',
	-- 记录创建人
	create_user varchar(32) NOT NULL COMMENT '记录创建人 : 记录创建人',
	-- 最后修改时间
	update_time datetime COMMENT '最后修改时间 : 最后修改时间',
	-- 最后修改人
	update_user varchar(32) COMMENT '最后修改人 : 最后修改人',
	-- 是否有效,Y:是;N:否
	-- 控制是否在公司任职有效
	is_valid char COMMENT '是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效',
	-- 如果链接中有参数，则设置参数标记。如:businessType=modify
	param_flag varchar(100) COMMENT '置参数标记 : 如果链接中有参数，则设置参数标记。如:businessType=modify',
	-- 菜单代码
	menu_code varchar(32) COMMENT '菜单代码 : 菜单代码',
	-- 分区字段,从用户公司代码字段取值
	partition_company varchar(8) COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '动作操作表' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_sys_authorization
(
	-- 记录主键
	id_ varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
	-- r-角色,p-人员
	resource_type char(1) COMMENT '资源类型 : r-角色,p-人员',
	-- 资源代码
	resource_code varchar(32) COMMENT '资源代码 : 资源代码',
	-- c-公司,d-部门,p-岗位,u-用户
	operator_type char(1) COMMENT '操作者类型 : c-公司,d-部门,p-岗位,u-用户',
	-- 操作者编码
	operator_code varchar(32) COMMENT '操作者编码 : 操作者编码',
	-- 是否有效,Y:是;N:否
	-- 控制是否在公司任职有效
	is_valid char COMMENT '是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效',
	-- 创建时间
	create_time datetime COMMENT '创建时间 : 创建时间',
	-- 创建公司
	create_company_code varchar(50) COMMENT '创建公司 : 创建公司',
	-- 记录创建人
	create_user varchar(32) COMMENT '记录创建人 : 记录创建人',
	-- 分区字段,从用户公司代码字段取值
	partition_company varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '授权表' DEFAULT CHARACTER SET utf8;


-- 表配置用户获取行层面上的数据权限
CREATE TABLE sfxie_sys_auth_data
(
	-- 记录主键
	id_ varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
	-- 角色代码
	role_id varchar(32) COMMENT '角色主键 : 角色代码',
	-- 资源名称
	resource_name varchar(50) COMMENT '资源名称 : 资源名称',
	-- 资源代码
	resource_code varchar(32) NOT NULL COMMENT '资源代码 : 资源代码',
	-- 排序字段
	sequence_no decimal(8) COMMENT '排序字段 : 排序字段',
	-- 创建时间
	create_time datetime NOT NULL COMMENT '创建时间 : 创建时间',
	-- 记录创建人
	create_user varchar(32) NOT NULL COMMENT '记录创建人 : 记录创建人',
	-- 最后修改时间
	update_time datetime COMMENT '最后修改时间 : 最后修改时间',
	-- 最后修改人
	update_user varchar(32) COMMENT '最后修改人 : 最后修改人',
	-- 是否有效,Y:是;N:否
	-- 控制是否在公司任职有效
	is_valid char COMMENT '是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效',
	-- 上上一级公司，也就是这一级的上一级公司
	owner_company_code varchar(64) COMMENT '上一级公司 : 上上一级公司，也就是这一级的上一级公司',
	sql_ varchar(2000) COMMENT '数据权限sql查询语句',
	-- 关联角色菜单关联表id
	role_menu_id varchar(32) COMMENT '角色菜单关联表id : 关联角色菜单关联表id',
	-- 用java处理类获取sql或者用户列表
	dealer_class varchar(200) COMMENT 'java处理类 : 用java处理类获取sql或者用户列表',
	-- 创建公司
	create_company_code varchar(50) COMMENT '创建公司 : 创建公司',
	-- 分区字段,从用户公司代码字段取值
	partition_company varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '数据权限表 : 表配置用户获取行层面上的数据权限' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_sys_auth_role_menu
(
	-- 记录主键
	id_ varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
	-- 是否有效,Y:是;N:否
	-- 控制是否在公司任职有效
	is_valid char COMMENT '是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效',
	-- 关联角色菜单关联表id
	role_menu_id varchar(64) COMMENT '角色菜单关联表id : 关联角色菜单关联表id',
	-- 关联数据权限表id
	auth_data_id varchar(64) COMMENT '数据权限表id : 关联数据权限表id',
	-- 上上一级公司，也就是这一级的上一级公司
	owner_company_code varchar(64) COMMENT '上一级公司 : 上上一级公司，也就是这一级的上一级公司',
	-- 公司角色关联表id
	company_role_id varchar(32) COMMENT '公司角色关联表id : 公司角色关联表id',
	-- 分区字段,从用户公司代码字段取值
	partition_company varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '数据权限角色菜单关联表' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_sys_company
(
	-- 记录主键
	id_ varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
	-- 关联公司代码,表明创建公司
	company_code varchar(64) NOT NULL UNIQUE COMMENT '公司代码 : 关联公司代码,表明创建公司',
	-- 公司中文名称
	company_name_cn varchar(250) NOT NULL COMMENT '公司中文名称 : 公司中文名称',
	company_name_en varchar(250) COMMENT '公司英文名称',
	-- 公司地址
	address varchar(300) COMMENT '公司地址 : 公司地址',
	tel varchar(60) COMMENT '公司电话',
	fax varchar(60) COMMENT '公司传真',
	-- 主页URL
	url varchar(100) COMMENT '主页URL : 主页URL',
	-- email
	email varchar(60) COMMENT 'email : email',
	-- 备注
	remark varchar(200) COMMENT '备注 : 备注',
	-- 记录创建人
	create_user varchar(32) NOT NULL COMMENT '记录创建人 : 记录创建人',
	-- 创建时间
	create_time datetime NOT NULL COMMENT '创建时间 : 创建时间',
	-- 最后修改人
	update_user varchar(32) COMMENT '最后修改人 : 最后修改人',
	-- 最后修改时间
	update_time datetime COMMENT '最后修改时间 : 最后修改时间',
	-- 排序字段
	sequence_no decimal(8) COMMENT '排序字段 : 排序字段',
	-- 是否有效,Y:是;N:否
	-- 控制是否在公司任职有效
	is_valid char DEFAULT 'Y' COMMENT '是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效',
	-- 公司中文名称简称
	short_name_cn varchar(50) COMMENT '公司中文名称简称 : 公司中文名称简称',
	-- 公司英文名称简称
	short_name_en varchar(50) COMMENT '公司英文名称简称 : 公司英文名称简称',
	-- 用来控制公司下面管理员的级别。
	-- 超级管理员: 值为"上级超级管理的级别+1";
	-- 普通用户: 默认值为 -1
	-- 
	-- 作用:用于创建无限级超级管理员
	company_level int COMMENT '公司级别 : 用来控制公司下面管理员的级别。
超级管理员: 值为"上级超级管理的级别+1";
普通用户: 默认值为 -1

作用:用于创建无限级超级管理员',
	-- 创建公司
	create_company_code varchar(50) NOT NULL COMMENT '创建公司 : 创建公司',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '公司基础信息表' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_sys_department
(
	-- 记录主键
	id_ varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
	-- 部门代码
	department_code varchar(64) NOT NULL UNIQUE COMMENT '部门代码 : 部门代码',
	-- 部门名称
	department_name varchar(100) COMMENT '部门名称 : 部门名称',
	-- 记录创建人
	create_user varchar(32) NOT NULL COMMENT '记录创建人 : 记录创建人',
	-- 创建时间
	create_time datetime NOT NULL COMMENT '创建时间 : 创建时间',
	-- 最后修改人
	update_user varchar(32) COMMENT '最后修改人 : 最后修改人',
	-- 最后修改时间
	update_time datetime COMMENT '最后修改时间 : 最后修改时间',
	-- 是否有效,Y:是;N:否
	-- 控制是否在公司任职有效
	is_valid char COMMENT '是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效',
	-- 排序字段
	sequence_no decimal(8) COMMENT '排序字段 : 排序字段',
	-- 关联父主键
	parent_id varchar(32) COMMENT '父主键 : 关联父主键',
	-- 创建公司
	create_company_code varchar(50) COMMENT '创建公司 : 创建公司',
	-- 关联公司代码,表明创建公司
	company_code varchar(64) NOT NULL COMMENT '公司代码 : 关联公司代码,表明创建公司',
	parent_code varchar(64) COMMENT ' 父节点代码',
	-- 分区字段,从用户公司代码字段取值
	partition_company varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '部门表' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_sys_menu
(
	-- 记录主键
	id_ varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
	-- 菜单代码
	menu_code varchar(32) NOT NULL UNIQUE COMMENT '菜单代码 : 菜单代码',
	-- 关联web_system表
	system_id varchar(64) COMMENT '关联system_id : 关联web_system表',
	-- 关联数据权限主键
	auth_data_id varchar(32) COMMENT '数据权限id : 关联数据权限主键',
	-- 菜单名称
	menu_name varchar(50) COMMENT '菜单名称 : 菜单名称',
	-- 资源
	url varchar(100) COMMENT '资源 : 资源',
	-- 描述
	description varchar(200) COMMENT '描述 : 描述',
	-- 繁体菜单名称
	menu_name_tw varchar(50) COMMENT '繁体菜单名称 : 繁体菜单名称',
	-- 网站标识 smis ems sms
	webside varchar(10) COMMENT '网站标识 : 网站标识 smis ems sms',
	-- 排序字段
	sequence_no decimal(8) COMMENT '排序字段 : 排序字段',
	-- 关联父主键
	parent_id varchar(32) COMMENT '父主键 : 关联父主键',
	-- 最后修改时间
	update_time datetime COMMENT '最后修改时间 : 最后修改时间',
	-- 最后修改人
	update_user varchar(32) COMMENT '最后修改人 : 最后修改人',
	-- 创建时间
	create_time datetime NOT NULL COMMENT '创建时间 : 创建时间',
	-- 记录创建人
	create_user varchar(32) NOT NULL COMMENT '记录创建人 : 记录创建人',
	-- 是否有效,Y:是;N:否
	-- 控制是否在公司任职有效
	is_valid char DEFAULT 'Y' COMMENT '是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效',
	-- 分区字段,从用户公司代码字段取值
	partition_company varchar(8) COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '菜单表' DEFAULT CHARACTER SET utf8;


-- 用于关联公司表记录
CREATE TABLE sfxie_sys_organization
(
	-- 记录主键
	id_ varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
	-- 是否有效,Y:是;N:否
	-- 控制是否在公司任职有效
	is_valid char DEFAULT 'Y' COMMENT '是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效',
	-- 无效时间
	invalid_date datetime COMMENT '无效时间 : 无效时间',
	-- 创建时间
	create_time datetime NOT NULL COMMENT '创建时间 : 创建时间',
	-- 记录创建人
	create_user varchar(32) NOT NULL COMMENT '记录创建人 : 记录创建人',
	-- 最后修改时间
	update_time datetime COMMENT '最后修改时间 : 最后修改时间',
	-- 最后修改人
	update_user varchar(32) COMMENT '最后修改人 : 最后修改人',
	-- 排序字段
	sequence_no decimal(8) COMMENT '排序字段 : 排序字段',
	-- 关联公司代码,表明创建公司
	company_code varchar(64) NOT NULL COMMENT '公司代码 : 关联公司代码,表明创建公司',
	-- 关联公司代码,表明创建公司
	parent_company_code varchar(64) NOT NULL COMMENT '公司代码 : 关联公司代码,表明创建公司',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '组织架构表 : 用于关联公司表记录' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_sys_post
(
	-- 记录主键
	id_ varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
	-- 岗位代码
	post_code varchar(32) NOT NULL UNIQUE COMMENT '岗位代码 : 岗位代码',
	-- 岗位名称
	post_name varchar(50) COMMENT '岗位名称 : 岗位名称',
	-- 排序字段
	sequence_no decimal(8) COMMENT '排序字段 : 排序字段',
	-- 0表示第一岗位,1表示第2岗位,2表示第3岗位
	post_level char COMMENT '岗位级别 : 0表示第一岗位,1表示第2岗位,2表示第3岗位',
	-- 创建时间
	create_time datetime NOT NULL COMMENT '创建时间 : 创建时间',
	-- 记录创建人
	create_user varchar(32) NOT NULL COMMENT '记录创建人 : 记录创建人',
	-- 最后修改时间
	update_time datetime COMMENT '最后修改时间 : 最后修改时间',
	-- 最后修改人
	update_user varchar(32) COMMENT '最后修改人 : 最后修改人',
	-- 公司主键
	company_code varchar(32) COMMENT '公司主键 : 公司主键',
	-- 关联父主键
	parent_id varchar(32) COMMENT '父主键 : 关联父主键',
	-- 是否有效,Y:是;N:否
	-- 控制是否在公司任职有效
	is_valid char COMMENT '是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效',
	-- 创建公司
	create_company_code varchar(50) COMMENT '创建公司 : 创建公司',
	-- 分区字段,从用户公司代码字段取值
	partition_company varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
	-- 部门代码
	department_code varchar(64) UNIQUE COMMENT '部门代码 : 部门代码',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '岗位表' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_sys_post_role
(
	-- 记录主键
	id_ varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
	-- 上上一级公司，也就是这一级的上一级公司
	owner_company_code varchar(64) COMMENT '上一级公司 : 上上一级公司，也就是这一级的上一级公司',
	-- 是否有效,Y:是;N:否
	-- 控制是否在公司任职有效
	is_valid char DEFAULT 'Y' COMMENT '是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效',
	-- 岗位代码
	post_code varchar(32) NOT NULL COMMENT '岗位代码 : 岗位代码',
	-- 角色代码
	role_code varchar(32) NOT NULL COMMENT '角色代码 : 角色代码',
	-- 分区字段,从用户公司代码字段取值
	partition_company varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
	PRIMARY KEY (id_),
	UNIQUE (post_code, role_code)
) ENGINE = InnoDB COMMENT = '岗位角色关联表' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_sys_role
(
	-- 记录主键
	id_ varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
	-- 角色代码
	role_code varchar(32) NOT NULL UNIQUE COMMENT '角色代码 : 角色代码',
	-- 角色名称
	role_name varchar(50) COMMENT '角色名称 : 角色名称',
	-- 描述
	description varchar(200) COMMENT '描述 : 描述',
	-- 角色英文名称
	role_name_en varchar(50) COMMENT '角色英文名称 : 角色英文名称',
	-- 最后修改时间
	update_time datetime COMMENT '最后修改时间 : 最后修改时间',
	-- 最后修改人
	update_user varchar(32) COMMENT '最后修改人 : 最后修改人',
	-- 创建时间
	create_time datetime NOT NULL COMMENT '创建时间 : 创建时间',
	-- 记录创建人
	create_user varchar(32) NOT NULL COMMENT '记录创建人 : 记录创建人',
	-- 排序字段
	sequence_no decimal(8) COMMENT '排序字段 : 排序字段',
	-- 是否有效,Y:是;N:否
	-- 控制是否在公司任职有效
	is_valid char COMMENT '是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效',
	-- 创建公司
	create_company_code varchar(50) COMMENT '创建公司 : 创建公司',
	-- 分区字段,从用户公司代码字段取值
	partition_company varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '角色表' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_sys_role_action
(
	-- 记录主键
	id_ varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
	-- 操作主键
	action_id varchar(64) NOT NULL COMMENT '操作主键 : 操作主键',
	-- 关联角色菜单关联表id
	role_menu_id varchar(64) NOT NULL COMMENT '角色菜单关联表id : 关联角色菜单关联表id',
	-- 上上一级公司，也就是这一级的上一级公司
	owner_company_code varchar(64) COMMENT '上一级公司 : 上上一级公司，也就是这一级的上一级公司',
	-- 是否有效,Y:是;N:否
	-- 控制是否在公司任职有效
	is_valid char DEFAULT 'Y' COMMENT '是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效',
	-- 公司角色关联表id
	company_role_id varchar(32) COMMENT '公司角色关联表id : 公司角色关联表id',
	-- 如果链接中有参数，则设置参数标记。如:businessType=modify
	param_flag varchar(100) COMMENT '参数标记 : 如果链接中有参数，则设置参数标记。如:businessType=modify',
	-- 分区字段,从用户公司代码字段取值
	partition_company varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = 'sfxie_sys_role_action' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_sys_role_menu
(
	-- 记录主键
	id_ varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
	-- 上上一级公司，也就是这一级的上一级公司
	owner_company_code varchar(64) COMMENT '上一级公司 : 上上一级公司，也就是这一级的上一级公司',
	-- 是否有效,Y:是;N:否
	-- 控制是否在公司任职有效
	is_valid char DEFAULT 'Y' COMMENT '是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效',
	-- 公司角色关联表id
	company_role_id varchar(32) COMMENT '公司角色关联表id : 公司角色关联表id',
	-- 创建公司
	create_company_code varchar(50) COMMENT '创建公司 : 创建公司',
	-- 继承的角色菜单id，记录此条数据是从哪条角色菜单中继承而来的
	oraginal_role_menu_id varchar(64) COMMENT '继承的角色菜单id : 继承的角色菜单id，记录此条数据是从哪条角色菜单中继承而来的',
	-- 角色代码
	role_code varchar(32) NOT NULL COMMENT '角色代码 : 角色代码',
	-- 菜单代码
	menu_code varchar(32) NOT NULL COMMENT '菜单代码 : 菜单代码',
	-- 分区字段,从用户公司代码字段取值
	partition_company varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '角色菜单关联表' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_sys_system
(
	-- 记录主键
	id_ varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
	-- 系统代码
	system_code varchar(32) NOT NULL UNIQUE COMMENT '系统代码 : 系统代码',
	-- 系统名称
	system_name varchar(200) COMMENT '系统名称 : 系统名称',
	-- 描述
	description varchar(200) COMMENT '描述 : 描述',
	sys_url varchar(2000) COMMENT 'sys_url',
	sys_inner_url varchar(2000) COMMENT 'sys_inner_url',
	-- 排序字段
	sequence_no decimal(8) COMMENT '排序字段 : 排序字段',
	-- 是否有效,Y:是;N:否
	-- 控制是否在公司任职有效
	is_valid char DEFAULT 'Y' COMMENT '是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效',
	-- 创建时间
	create_time datetime COMMENT '创建时间 : 创建时间',
	-- 记录创建人
	create_user varchar(32) COMMENT '记录创建人 : 记录创建人',
	-- 最后修改时间
	update_time datetime COMMENT '最后修改时间 : 最后修改时间',
	-- 最后修改人
	update_user varchar(32) COMMENT '最后修改人 : 最后修改人',
	-- 分区字段,从用户公司代码字段取值
	partition_company varchar(8) COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '系统表' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_sys_userinfo
(
	-- 用户代码,用户登录名
	user_id varchar(32) NOT NULL COMMENT '用户代码 : 用户代码,用户登录名',
	-- 用户密码,加密后的
	user_password varchar(50) NOT NULL COMMENT '用户密码 : 用户密码,加密后的',
	-- 是否有效,Y:是;N:否
	-- 控制是否在公司任职有效
	is_valid char COMMENT '是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效',
	-- 创建时间
	create_time datetime NOT NULL COMMENT '创建时间 : 创建时间',
	-- 记录创建人
	create_user varchar(32) NOT NULL COMMENT '记录创建人 : 记录创建人',
	-- 最后修改人
	update_user varchar(32) COMMENT '最后修改人 : 最后修改人',
	-- 最后修改时间
	update_time datetime COMMENT '最后修改时间 : 最后修改时间',
	-- 排序字段
	sequence_no decimal(8) COMMENT '排序字段 : 排序字段',
	-- 是否为超级管理员，默认为否
	-- Y-是
	-- N-否
	-- 
	-- 只级超级管理员才可以操作此字段
	is_superman char DEFAULT 'N' NOT NULL COMMENT '是否为超级管理员 : 是否为超级管理员，默认为否
Y-是
N-否

只级超级管理员才可以操作此字段',
	-- 创建公司
	create_company_code varchar(50) NOT NULL COMMENT '创建公司 : 创建公司',
	-- m-男,f-女
	sex char COMMENT '用户性别 : m-男,f-女',
	-- 分区字段,从用户公司代码字段取值
	partition_company varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
	create_company_level int COMMENT '创建公司级别',
	PRIMARY KEY (user_id)
) ENGINE = InnoDB COMMENT = '用户表' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_sys_user_relation
(
	-- 记录主键
	id_ varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
	-- 用户代码
	user_id varchar(32) NOT NULL COMMENT '用户代码 : 用户代码',
	-- 用户职位
	user_title char COMMENT '用户职位 : 用户职位',
	-- 是否有效,Y:是;N:否
	-- 控制是否在公司任职有效
	is_valid char COMMENT '是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效',
	-- 创建时间
	create_time datetime NOT NULL COMMENT '创建时间 : 创建时间',
	-- 最后修改时间
	update_time datetime COMMENT '最后修改时间 : 最后修改时间',
	-- 最后修改人
	update_user varchar(32) COMMENT '最后修改人 : 最后修改人',
	-- 记录创建人
	create_user varchar(32) NOT NULL COMMENT '记录创建人 : 记录创建人',
	-- 创建公司
	create_company_code varchar(50) NOT NULL COMMENT '创建公司 : 创建公司',
	-- email
	email varchar(60) NOT NULL COMMENT 'email : email',
	-- 手机号
	phone varchar(20) COMMENT '手机号 : 手机号',
	-- 用户中文名
	user_name_cn varchar(60) NOT NULL COMMENT '用户中文名 : 用户中文名',
	-- 用户名称 en
	user_name_en varchar(60) COMMENT '用户英文名称 : 用户名称 en',
	-- 排序字段
	sequence_no decimal(8) COMMENT '排序字段 : 排序字段',
	-- 用户类型(超级管理员，普通用户)
	-- 超级管理员: S
	-- 管理员: M
	-- 普通用户: U
	user_type char COMMENT '用户类型 : 用户类型(超级管理员，普通用户)
超级管理员: S
管理员: M
普通用户: U',
	-- 系统默认的用户信息(只有一个默认值1)
	-- 控制默认登录系统后获取的权限(根据公司):
	-- 默认:1
	-- 不默认:0
	is_default char COMMENT '是否默认 : 系统默认的用户信息(只有一个默认值1)
控制默认登录系统后获取的权限(根据公司):
默认:1
不默认:0',
	-- 关联公司代码,表明创建公司
	company_code varchar(64) COMMENT '公司代码 : 关联公司代码,表明创建公司',
	-- 岗位代码
	post_code varchar(32) COMMENT '岗位代码 : 岗位代码',
	-- 部门代码
	department_code varchar(64) COMMENT '部门代码 : 部门代码',
	-- 分区字段,从用户公司代码字段取值
	partition_company varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '人员档案-关系表' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_sys_user_role
(
	-- 记录主键
	id_ varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
	-- 用户代码
	user_id varchar(32) NOT NULL UNIQUE COMMENT '用户代码 : 用户代码',
	-- 上上一级公司，也就是这一级的上一级公司
	owner_company_code varchar(64) COMMENT '上一级公司 : 上上一级公司，也就是这一级的上一级公司',
	-- 是否有效,Y:是;N:否
	-- 控制是否在公司任职有效
	is_valid char DEFAULT 'Y' COMMENT '是否有效 : 是否有效,Y:是;N:否
控制是否在公司任职有效',
	user_info_relation_id varchar(32) COMMENT '用户关联表id',
	-- 角色代码
	role_code varchar(32) NOT NULL COMMENT '角色代码 : 角色代码',
	-- 分区字段,从用户公司代码字段取值
	partition_company varchar(8) NOT NULL COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '用户角色关联表' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_ui_skin
(
	-- 记录主键
	id_ varchar(64) NOT NULL COMMENT '记录主键 : 记录主键',
	-- 代码-类型
	code varchar(16) COMMENT '代码 : 代码-类型',
	css varchar(50) COMMENT 'css',
	-- 记录创建人
	create_user varchar(32) COMMENT '记录创建人 : 记录创建人',
	-- 分区字段,从用户公司代码字段取值
	partition_company varchar(8) COMMENT '分区字段 : 分区字段,从用户公司代码字段取值',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = 'sfxie_ui_skin' DEFAULT CHARACTER SET utf8;



/* Create Foreign Keys */

ALTER TABLE sfxie_sys_role_action
	ADD FOREIGN KEY (action_id)
	REFERENCES sfxie_sys_action (id_)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_sys_auth_role_menu
	ADD FOREIGN KEY (auth_data_id)
	REFERENCES sfxie_sys_auth_data (id_)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_sys_department
	ADD FOREIGN KEY (company_code)
	REFERENCES sfxie_sys_company (company_code)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_sys_organization
	ADD FOREIGN KEY (parent_company_code)
	REFERENCES sfxie_sys_company (company_code)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_sys_organization
	ADD FOREIGN KEY (company_code)
	REFERENCES sfxie_sys_company (company_code)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_sys_user_relation
	ADD FOREIGN KEY (company_code)
	REFERENCES sfxie_sys_company (company_code)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_sys_post
	ADD FOREIGN KEY (department_code)
	REFERENCES sfxie_sys_department (department_code)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_sys_user_relation
	ADD FOREIGN KEY (department_code)
	REFERENCES sfxie_sys_department (department_code)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_sys_action
	ADD FOREIGN KEY (menu_code)
	REFERENCES sfxie_sys_menu (menu_code)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_sys_role_menu
	ADD FOREIGN KEY (menu_code)
	REFERENCES sfxie_sys_menu (menu_code)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_sys_post_role
	ADD FOREIGN KEY (post_code)
	REFERENCES sfxie_sys_post (post_code)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_sys_user_relation
	ADD FOREIGN KEY (post_code)
	REFERENCES sfxie_sys_post (post_code)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_sys_post_role
	ADD FOREIGN KEY (role_code)
	REFERENCES sfxie_sys_role (role_code)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_sys_role_menu
	ADD FOREIGN KEY (role_code)
	REFERENCES sfxie_sys_role (role_code)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_sys_user_role
	ADD FOREIGN KEY (role_code)
	REFERENCES sfxie_sys_role (role_code)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_sys_auth_role_menu
	ADD FOREIGN KEY (role_menu_id)
	REFERENCES sfxie_sys_role_menu (id_)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_sys_role_action
	ADD FOREIGN KEY (role_menu_id)
	REFERENCES sfxie_sys_role_menu (id_)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_sys_menu
	ADD FOREIGN KEY (system_id)
	REFERENCES sfxie_sys_system (id_)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_sys_user_role
	ADD FOREIGN KEY (user_id)
	REFERENCES sfxie_sys_userinfo (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;




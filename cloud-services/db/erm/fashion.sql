SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE sfxie_cache_db_partition;
DROP TABLE sfxie_location_geographic;
DROP TABLE sfxie_shopping_bar_ad;
DROP TABLE sfxie_shopping_ad;
DROP TABLE sfxie_shopping_bar;
DROP TABLE sfxie_shopping_discount;
DROP TABLE sfxie_shopping_goods;
DROP TABLE sfxie_shopping_material_at;
DROP TABLE sfxie_shopping_material;
DROP TABLE sfxie_shopping_inventory;
DROP TABLE sfxie_shopping_inventory_type;
DROP TABLE sfxie_shopping_shopkeeper;




/* Create Tables */

-- 用于系统初始化时调用缓存,并且提供给系统所有数据分区使用;
-- 系统数据的分区主要根据shopkeeper_id和db_partition_number分区,分区使用cobar组件完成
CREATE TABLE sfxie_cache_db_partition
(
	shopkeeper_id int(12) NOT NULL COMMENT '店主表id',
	db_partition_number varchar(12) NOT NULL COMMENT '数据库分区数字',
	description_ varchar(500) COMMENT '描述',
	PRIMARY KEY (shopkeeper_id)
) ENGINE = InnoDB COMMENT = '数据库分区字段映射表' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_location_geographic
(
	id_ int(12) NOT NULL COMMENT '主键',
	name_ varchar(50) COMMENT '名称',
	code_ varchar(64) COMMENT '编码',
	-- 父级id
	parent_id int(12) COMMENT '父id',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '地理位置表' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_shopping_ad
(
	id_ int(12) NOT NULL AUTO_INCREMENT COMMENT '主键',
	name_ varchar(50) NOT NULL COMMENT '名称',
	material_id int(12) COMMENT '素材表id',
	price_id int(12) COMMENT '价格表id',
	shopkeeper_id int(12) COMMENT '店主表id',
	db_partition_number varchar(12) NOT NULL COMMENT '数据库分区数字',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '广告' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_shopping_bar
(
	id_ int(12) NOT NULL AUTO_INCREMENT COMMENT '主键',
	name_ varchar(50) COMMENT '名称',
	validable_ char COMMENT '是否有效',
	order_ int(5) COMMENT '排序',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '栏位' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_shopping_bar_ad
(
	id_ int(12) NOT NULL AUTO_INCREMENT COMMENT '主键',
	bar_id int(12) NOT NULL COMMENT '栏位id',
	ad_id int(12) NOT NULL COMMENT '广告id',
	order_ int(5) COMMENT '排序',
	validable_ char DEFAULT 'Y' NOT NULL COMMENT '是否有效',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '栏位广告关联表' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_shopping_discount
(
	id_ int(12) NOT NULL AUTO_INCREMENT COMMENT '主键',
	size_ varchar(10) COMMENT '尺寸大小',
	color_ varchar(10) COMMENT '颜色',
	-- 如果折扣率为空,则按照现行价格
	price_ int(8) COMMENT '现行价格',
	rate_ float COMMENT '折扣率',
	db_partition_number varchar(12) NOT NULL COMMENT '数据库分区数字',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '折扣表' DEFAULT CHARACTER SET utf8;


-- 只有被放入购物车的广告才能称为商品
CREATE TABLE sfxie_shopping_goods
(
	id_ int(12) NOT NULL AUTO_INCREMENT COMMENT '主键',
	db_partition_number varchar(12) NOT NULL COMMENT '数据库分区数字',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '商品表' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_shopping_inventory
(
	id_ int(12) NOT NULL AUTO_INCREMENT COMMENT '主键',
	-- 代表编码
	code_ varchar(64) COMMENT '编码',
	primary_price_ decimal(10,2) COMMENT '原价',
	count_ int(11) COMMENT '库存量',
	db_partition_number varchar(12) NOT NULL COMMENT '数据库分区数字',
	inventory_type_id int(12) COMMENT '库存类型表id',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '库存表' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_shopping_inventory_type
(
	id_ int(12) NOT NULL AUTO_INCREMENT COMMENT '主键',
	-- 父级id
	parent_id int(12) COMMENT '父id',
	name_ varchar(50) COMMENT '名称',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '库存类型' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_shopping_material
(
	id_ int(12) NOT NULL AUTO_INCREMENT COMMENT '主键',
	material_name varchar(100) NOT NULL COMMENT '素材名称',
	image_url varchar(200) COMMENT '图片url',
	validable_ char DEFAULT 'Y' COMMENT '是否有效',
	position_ char COMMENT '素材位置',
	inventory_id int(12) COMMENT '库存id',
	db_partition_number varchar(12) NOT NULL COMMENT '数据库分区数字',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '素材' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_shopping_material_at
(
	id_ int(12) NOT NULL COMMENT '主键',
	material_id int(12) NOT NULL COMMENT '素材id',
	db_partition_number varchar(12) NOT NULL COMMENT '数据库分区数字',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '素材属性关联表' DEFAULT CHARACTER SET utf8;


CREATE TABLE sfxie_shopping_shopkeeper
(
	id_ int(12) NOT NULL AUTO_INCREMENT COMMENT '主键',
	shopkeeper_name varchar(50) COMMENT '店主名称',
	-- 代表编码
	code_ varchar(64) COMMENT '编码',
	description_ varchar(500) COMMENT '描述',
	db_partition_number varchar(12) NOT NULL COMMENT '数据库分区数字',
	-- 从地理位置表中取code字段
	province_ varchar(64) COMMENT '省份',
	-- 从地理位置表中取code字段
	city_ varchar(64) COMMENT '城市',
	-- 从地理位置表中取code字段
	town_ varchar(64) COMMENT '城镇',
	village_ varchar(64) COMMENT '乡村',
	PRIMARY KEY (id_)
) ENGINE = InnoDB COMMENT = '店主表' DEFAULT CHARACTER SET utf8;



/* Create Foreign Keys */

ALTER TABLE sfxie_shopping_bar_ad
	ADD FOREIGN KEY (ad_id)
	REFERENCES sfxie_shopping_ad (id_)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_shopping_bar_ad
	ADD FOREIGN KEY (bar_id)
	REFERENCES sfxie_shopping_bar (id_)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_shopping_ad
	ADD FOREIGN KEY (price_id)
	REFERENCES sfxie_shopping_discount (id_)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_shopping_material
	ADD FOREIGN KEY (inventory_id)
	REFERENCES sfxie_shopping_inventory (id_)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_shopping_inventory
	ADD FOREIGN KEY (inventory_type_id)
	REFERENCES sfxie_shopping_inventory_type (id_)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_shopping_ad
	ADD FOREIGN KEY (material_id)
	REFERENCES sfxie_shopping_material (id_)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_shopping_material_at
	ADD FOREIGN KEY (material_id)
	REFERENCES sfxie_shopping_material (id_)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sfxie_shopping_ad
	ADD FOREIGN KEY (shopkeeper_id)
	REFERENCES sfxie_shopping_shopkeeper (id_)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;




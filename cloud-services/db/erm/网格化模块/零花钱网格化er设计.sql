SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE g_ad3_grid;
DROP TABLE g_ad3_grid_display;
DROP TABLE g_ad3_grid_mall;





/**
 * 新增3.0网格化显示主表
 * 日期:20160516
 * 作者：谢声锋 
 */
CREATE TABLE `g_ad3_grid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plate` char(1) DEFAULT NULL COMMENT '板块: 1-商城',
  `type` varchar(13) DEFAULT NULL COMMENT '板块下的分类: 如商城-超划算;如果板块下没有分类的,可以默认一个分类记录',
  `screen_number` int(4) DEFAULT NULL COMMENT '所在屏数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/**
 * 新增3.0网格化显示从表
 * 日期:20160516
 * 作者：谢声锋 
 */
CREATE TABLE `g_ad3_grid_display` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grid_id` int(11) DEFAULT NULL COMMENT '关联g_ad3_grid表的主键',
  `start_col` int(2) DEFAULT NULL,
  `end_col` int(2) DEFAULT NULL,
  `start_row` int(2) DEFAULT NULL,
  `end_row` int(2) DEFAULT NULL,
  `state` char(1) DEFAULT NULL COMMENT 'Y-有效,N-无效',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `creator` varchar(30) DEFAULT NULL,
  `modifier` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;
/**
 * 新增3.0网格化显示从表-商城关系表
 * 日期:20160516
 * 作者：谢声锋 
 */
CREATE TABLE `g_ad3_grid_mall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `display_id` int(11) DEFAULT NULL COMMENT '关联g_ad3_grid_display表id',
  `post` varchar(200) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  `goods_name` varchar(50) DEFAULT NULL COMMENT '商品名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;


INSERT INTO `sc_center`.`sfxie_sys_company` (`id_`,`company_code`,`company_name_cn`,`company_name_en`,`address`,`tel`,`fax`,`url`,`email`,`remark`,`create_user`,`create_time`,`update_user`,`update_time`,`sequence_no`,`is_valid`,`short_name_cn`,`short_name_en`,`company_level`,`create_company_id`)VALUES(1,'test1000','测试公司test1000','test1000','深圳市南山区','13246779797',NULL,NULL,NULL,NULL,'test',now(),NULL,NULL,NULL,'Y',NULL,NULL,-1,'test');
INSERT INTO `sc_center`.`sfxie_sys_company` (`id_`,`company_code`,`company_name_cn`,`company_name_en`,`address`,`tel`,`fax`,`url`,`email`,`remark`,`create_user`,`create_time`,`update_user`,`update_time`,`sequence_no`,`is_valid`,`short_name_cn`,`short_name_en`,`company_level`,`create_company_id`)VALUES(2,'test1001','测试公司test1001','test1001','深圳市福田区','13246779797',NULL,NULL,NULL,NULL,'test',now(),NULL,NULL,NULL,'Y',NULL,NULL,-1,'test');

INSERT INTO `sc_center`.`sfxie_sys_userinfo` (`user_id`,`user_password`,`is_valid`,`create_time`,`create_user`,`update_user`,`update_time`,`sequence_no`,`is_superman`,`create_company_id`,`sex`,`partition_company`)VALUES('00000000000000000000000000000000','123456','Y',now(),'admin',NULL,NULL,NULL,1,'Y','m','test0000');
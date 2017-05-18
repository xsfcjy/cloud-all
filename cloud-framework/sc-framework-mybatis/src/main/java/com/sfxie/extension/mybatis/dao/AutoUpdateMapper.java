package com.sfxie.extension.mybatis.dao;

import com.sfxie.extension.mybatis.annotation.MyBatisRepository;

@MyBatisRepository(Object.class)
public interface AutoUpdateMapper extends IMybatisDao<Object, Object> {

}

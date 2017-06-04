package com.sfxie.services.center.dao.mapper;

import java.util.List;
import java.util.Map;

import com.sfxie.component.ui.tags.report.netty.ReportParameter;
import com.sfxie.component.ui.tags.report.netty.ReportWebSocketEntity;

public interface ReportGlueMapper {

    String findJrxml(Map map);
    String findDatasetJava(Map map);
	public List<ReportWebSocketEntity> loadAll() ;
	public List<ReportParameter> loadParameters(Map map);
	
}
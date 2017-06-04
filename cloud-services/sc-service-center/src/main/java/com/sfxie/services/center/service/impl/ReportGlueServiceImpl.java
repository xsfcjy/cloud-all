package com.sfxie.services.center.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sfxie.component.ui.tags.report.ReportGlueLoader;
import com.sfxie.component.ui.tags.report.netty.ReportParameter;
import com.sfxie.component.ui.tags.report.netty.ReportWebSocketEntity;
import com.sfxie.services.center.dao.mapper.ReportGlueMapper;


/**
 * 报表服务类
 * @author xiesf
 *
 */
@Service
public class ReportGlueServiceImpl implements ReportGlueLoader {
	
	@Resource
	private ReportGlueMapper reportGlueMapper;

	@Override
	public String loadJrxml(int reportId, int versionId) {
		Map map = new HashMap();
		map.put("reportId", reportId);
		map.put("version", versionId);
		return reportGlueMapper.findJrxml(map);
	}

	@Override
	public String loadDatasetJava(int reportId, int versionId) {
		Map map = new HashMap();
		map.put("reportId", reportId);
		map.put("version", versionId);
		return reportGlueMapper.findDatasetJava(map);
	}

	@Override
	public List<ReportParameter> loadParameters(int reportId, int versionId) {
		Map map = new HashMap();
		map.put("reportId", reportId);
		map.put("versionId", versionId);
		return reportGlueMapper.loadParameters(map);
	}

	@Override
	public List<ReportWebSocketEntity> loadAll() {
		return reportGlueMapper.loadAll();
	}

}

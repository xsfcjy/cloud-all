package com.sfxie.component.ui.tags.report.copy;

import java.util.List;
import java.util.Map;

/**
 * 列表数据源处理类示例
 * @author xiesf
 *
 */
public class ListReportHandler extends IReportHandler<List<Map<String,Object>>,Map<String,Object>> {

	@Override
	public List<Map<String,Object>> dataset(Map<String,Object> parameter) {
		return null;
	}
	
	public static void main(String[] args) {
		ListReportHandler listReportHandler = new ListReportHandler();
		System.out.println(listReportHandler.parameterClass().getCanonicalName());
	}

}

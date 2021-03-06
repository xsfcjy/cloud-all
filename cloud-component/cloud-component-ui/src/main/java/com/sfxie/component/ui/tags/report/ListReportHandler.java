package com.sfxie.component.ui.tags.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 列表数据源处理类示例
 * @author xiesf
 *
 */
public class ListReportHandler extends ReportGroovyHandler<List,Map> {

	@Override
	public List dataset(Map parameter) {
		List<DemoReportEntity> result = new ArrayList<DemoReportEntity>();
		for(int i=0;i<100;i++){
			result.add(new DemoReportEntity(""+i,"name"+i,"1","1","1"));
		}
		return result;
	}
	
	public static void main(String[] args) {
		ListReportHandler listReportHandler = new ListReportHandler();
		System.out.println(listReportHandler.parameterClass().getCanonicalName());
	}

}

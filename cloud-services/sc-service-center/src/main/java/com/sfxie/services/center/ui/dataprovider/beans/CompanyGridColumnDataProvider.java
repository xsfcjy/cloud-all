package com.sfxie.services.center.ui.dataprovider.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sfxie.component.ui.grid.easyui.dp.DataGridColumnDecoratorParamEntity;
import com.sfxie.component.ui.grid.easyui.dp.EasyUIDataGridColumnDataProvider;

@Component
public class CompanyGridColumnDataProvider extends
		EasyUIDataGridColumnDataProvider<DataGridColumnDecoratorParamEntity, List<Map<String,Object>>> {
	
	@Override
	public List<Map<String,Object>> query(
			DataGridColumnDecoratorParamEntity dataGridColumnDecoratorParamEntity)
			throws Exception {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>> ();
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("value", "Y");
		map1.put("text", "有效");
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("value", "N");
		map2.put("text", "无效");
		resultList.add(map1);
		resultList.add(map2);
		return resultList;
	}

	@Override
	public DataGridColumnDecoratorParamEntity mappingParameter(
			DataGridColumnDecoratorParamEntity dataProviderParameter)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

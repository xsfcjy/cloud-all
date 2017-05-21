package com.sfxie.web.ui.dataprovider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sfxie.component.ui.tags.select.SelectUIDataProvider;
import com.sfxie.component.ui.tags.select.SelecteDataProviderParameter;

/**
 * 通用下拉框数据提供者：状态下拉框数据
 * @author xiesf
 *
 */
@Component
public class CommonStateValidationDataProvider  extends SelectUIDataProvider<SelecteDataProviderParameter,List<?>>{

	public List<Map<String,Object>> query(SelecteDataProviderParameter dataProviderParameter) throws Exception {
//		List<Map<String,Object>> resultList = SelectDataXmlUtil.queryFirstSelectDataList("common.state.validation", "1","text","value");
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
	public SelecteDataProviderParameter mappingParameter(SelecteDataProviderParameter dataProviderParameter)
			throws Exception {
		return null;
	}

}

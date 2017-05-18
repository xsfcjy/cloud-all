package com.sfxie.services.center.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sfxie.component.ui.grid.easyui.dp.DataGridColumnDecoratorDataEntity;
import com.sfxie.component.ui.grid.easyui.dp.DataGridColumnDecoratorParamEntity;
import com.sfxie.component.ui.grid.easyui.dp.EasyUIDataGridColumnDataProvider;
import com.sfxie.core.framework.core.SpringContext;
import com.sfxie.services.center.service.impl.SfxieSysCompanyServiceImpl;

/**
 * UI组件控制器类
 * @author xiesf
 * @since 2017-05-10
 *
 */
@RestController
public class UIController {

	@Resource
	private SfxieSysCompanyServiceImpl sfxieSysCompanyService;
	
	/**
	 * easyui表格数据提供
	 * @param paramEntity
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ui/easyui/gridColumnDP", method = RequestMethod.POST)
	public @ResponseBody Object sfxieSysCompanys(@RequestBody DataGridColumnDecoratorParamEntity paramEntity) {
		if(null!=paramEntity && null!=paramEntity.getData()){
			List<DataGridColumnDecoratorParamEntity> dataGridColumnDecoratorParamEntityList = paramEntity.getData();
			if(null!=dataGridColumnDecoratorParamEntityList && dataGridColumnDecoratorParamEntityList.size() > 0){
				List<DataGridColumnDecoratorDataEntity> result = new ArrayList<DataGridColumnDecoratorDataEntity>();
				if(null != dataGridColumnDecoratorParamEntityList){
					for(DataGridColumnDecoratorParamEntity dataGridColumnDecoratorParamEntity : dataGridColumnDecoratorParamEntityList){
						try {
							DataGridColumnDecoratorDataEntity dataGridColumnDecoratorDataEntity = new DataGridColumnDecoratorDataEntity();
							dataGridColumnDecoratorDataEntity.setField(dataGridColumnDecoratorParamEntity.getField());
							EasyUIDataGridColumnDataProvider dp = (EasyUIDataGridColumnDataProvider) SpringContext.getBeanByName(dataGridColumnDecoratorParamEntity.getDataRegister());
							dataGridColumnDecoratorDataEntity.setValueList( dp.query(dataGridColumnDecoratorParamEntity));
							result.add(dataGridColumnDecoratorDataEntity);
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
				return result;
			}
		}
		return null;
	}
}

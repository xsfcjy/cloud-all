package com.sfxie.component.ui.grid.easyui.dp;

import java.util.List;

import com.sfxie.component.ui.dataprovider.AbstractDataProvider;

/**
 * easyUI前端网格数据提供者
 * @author xiesf
 *
 */
public abstract class EasyUIDataGridColumnDataProvider<P extends DataGridColumnDecoratorParamEntity,R extends List<?>>  extends AbstractDataProvider<P,R>{

	
	@Override
	public abstract R query(P dataGridColumnDecoratorParamEntity) throws Exception ;
	
	
}
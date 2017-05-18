package com.sfxie.component.ui.tree.ztree;

import com.sfxie.component.ui.UI;

/**
 * 
 * @author xiesf
 * 
 var setting = {
			view: {
				showLine: true,
				selectedMulti: false,
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onNodeCreated: this.onNodeCreated,
				beforeClick: this.beforeClick,
				onClick: this.onClick
			}
		};

 *
 */
public class Setting extends UI{
	
	private View wiew;
	
	private Data data;
	
	private Callback callback;
	
	
	

	public View getWiew() {
		return wiew;
	}

	public void setWiew(View wiew) {
		this.wiew = wiew;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public Callback getCallback() {
		return callback;
	}

	public void setCallback(Callback callback) {
		this.callback = callback;
	}

	public static class View {
		
		private Boolean showLine;
		
		private Boolean selectedMulti;
		
		private Boolean dblClickExpand;

		public Boolean getShowLine() {
			return showLine;
		}

		public void setShowLine(Boolean showLine) {
			this.showLine = showLine;
		}

		public Boolean getSelectedMulti() {
			return selectedMulti;
		}

		public void setSelectedMulti(Boolean selectedMulti) {
			this.selectedMulti = selectedMulti;
		}

		public Boolean getDblClickExpand() {
			return dblClickExpand;
		}

		public void setDblClickExpand(Boolean dblClickExpand) {
			this.dblClickExpand = dblClickExpand;
		}
		
		
	}

	public static class Data {
		
		private SimpleData simpleData;

		public SimpleData getSimpleData() {
			return simpleData;
		}

		public void setSimpleData(SimpleData simpleData) {
			this.simpleData = simpleData;
		}
		
		
		
		
		
	}

	public static class Callback {

		private String onNodeCreated;
		private String beforeClick;
		private String onClick;
		public String getOnNodeCreated() {
			return onNodeCreated;
		}
		public void setOnNodeCreated(String onNodeCreated) {
			this.onNodeCreated = onNodeCreated;
		}
		public String getBeforeClick() {
			return beforeClick;
		}
		public void setBeforeClick(String beforeClick) {
			this.beforeClick = beforeClick;
		}
		public String getOnClick() {
			return onClick;
		}
		public void setOnClick(String onClick) {
			this.onClick = onClick;
		}
		
		
	}
	
	public static class SimpleData {

		private Boolean enable;

		public Boolean getEnable() {
			return enable;
		}

		public void setEnable(Boolean enable) {
			this.enable = enable;
		}
		
		
	}

	@Override
	protected String toHtml() {
		// TODO Auto-generated method stub
		return null;
	}
}

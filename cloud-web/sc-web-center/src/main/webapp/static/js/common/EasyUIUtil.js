(function(win){
	
	if(!GoLive.EasyUI)
		GoLive.EasyUI = new Object();
	GoLive.EasyUI.settings = new Object();
	GoLive.EasyUI.settings.dom={
			label:'<label>{label}:</label>',
			td:'<td id="td{name}"></td>',
			input: '<div><input name="{name}" id="{id}"  style="width:{width}px"></div>',
			select: '<div><select name="{name}" id="{id}" style="width:{width}px"></select></div>',
			seperator: '<td><div class="datagrid-btn-separator"></div></td>'
	};
	
	var type = {
        str : '[object String]',
        arr : '[object Array]',
        num : '[object Number]',
        func: '[object Function]',
        obj : '[object Object]',
        bool: '[object Boolean]',
        date: '[object Date]',
        unde: '[object Undefined]',
        nil : '[object Null]'
    }
    function getType(arg){
        return Object.prototype.toString.call(arg);
    }
    var fn = function(){}
    fn.prototype = {
        isDate:function (arg){
            return getType(arg)===type.date;
        },
        isNull:function (arg){
            return getType(arg)===type.nil;
        },
        isNum:function (arg){
            return getType(arg)===type.num;
        },
        isFunc:function (arg){
            return getType(arg)===type.func;
        },
        isArr:function (arg){
            return getType(arg)===type.arr;
        },
        isObj:function (arg){
            return getType(arg)===type.obj;
        },
        isUndefined:function (arg){
            return getType(arg)===type.unde;
        },
        isBool:function (arg){
            return getType(arg)===type.bool;
        },
        isStr:function (arg){
            return getType(arg)===type.str;
        }
    }
    GoLive.Judge = new fn();
    
	GoLive.EasyUI.Datagrid = new Object();
	GoLive.EasyUI.Datagrid.singleSelectedAction = _$singleSelectedAction;
	GoLive.EasyUI.Datagrid.multySelectedAction = _$multySelectedAction;
	GoLive.EasyUI.Datagrid.reload = _$dataGridReload;
	
	function _$dataGridReload(config){
		var dataGridId = config.dataGridId;
		$('#'+dataGridId).datagrid('getPager').pagination('select');
	}
	/**
	 * easyui表格对象datagrid的单选数据操作,使用示例如下:
	 	{
            text:'修改',
            iconCls:'icon-save',
            handler:function(){
            	GoLive.EasyUI.Datagrid.singleSelectedAction({
            		dataGridId :'dataGrid',
            		notSelectedTip:'请选择记录!',
            		multySelectedTip:'只能选择一条记录操作!',
            		actionFunc:function(selectRecord){
            			var loadRoleUrl = "${ctx}/systemAjax/system-loadRole.action";
            			GoLive.EasyUI.Form.loadData({
            				prefix:'role',
            				formId:'myform',
            				url:loadRoleUrl,
            				parameter:{
            					'role.id':selectRecord.id
            				},
            			});
                    	$('#editWindow').window('open');
            		}
            	});
			}
        }
	 */
	function _$singleSelectedAction(dataGridConfig){
		
		var dataGridId = dataGridConfig.dataGridId;
		var actionFunc = dataGridConfig.actionFunc;
		var notSelectedTip = dataGridConfig.notSelectedTip;
		var multySelectedTip = dataGridConfig.multySelectedTip;
		
		var selections = $('#'+dataGridId).datagrid('getSelections');
		
    	if(selections.length == 0 ){
    		_$showMessage({icon:'warning',msg:notSelectedTip});
    	}else if(selections.length > 1 ){
    		_$showMessage({icon:'warning',msg:multySelectedTip});
    	}else{
    		actionFunc(selections[0]);
    	}
	}
	
	/**
	 * easyui表格对象datagrid的多选数据操作,使用示例如下:
	 	{
            text:'删除',
            iconCls:'icon-save',
            handler:function(){
            	GoLive.EasyUI.Datagrid.multySelectedAction({
            		dataGridId :'dataGrid',
            		notSelectedTip:'请选择记录!',
            		actionFunc:function(selectRecords){
            		}
            	});
			}
        }
	 */
	function _$multySelectedAction(dataGridConfig){
		
		var dataGridId = dataGridConfig.dataGridId;
		var actionFunc = dataGridConfig.actionFunc;
		var notSelectedTip = dataGridConfig.notSelectedTip;
		
		var selections = $('#'+dataGridId).datagrid('getSelections');
		
    	if(selections.length == 0 ){
    		alert(notSelectedTip);
    	}else{
    		actionFunc(selections);
    	}
	}
	
	GoLive.EasyUI.Tree = new Object();
	GoLive.EasyUI.Tree.combotree = _$combotree;
	
	function _$combotree(config){
		var combotreeId = config.combotreeId;
		var url = config.url;
		var parameter = config.parameter;
		var afterLoad = config.afterLoad;
		parameter['actModel.actType'] = 'business';
		var paramArray = [];
		for(p in parameter){
			paramArray.push(p+"="+parameter[p]);
		}
		url = url+(paramArray.length>0?("?"+paramArray.join('&')):(""));
		$('#'+combotreeId).combotree({
			url : url,
	        required: true,
	        cascadeCheck: true,
	        onlyLeafCheck:false
	    });
	}
	
	GoLive.EasyUI.Tree.tree = _$tree;
	
	function _$tree(config){
		var _config = {
			required: true,
	        cascadeCheck: true,
	        onlyLeafCheck:false	
		};
		$.extend(_config,config);
		var treeId = _config.treeId;
		var url = _config.url;
		var parameter = _config.parameter;
		parameter['actModel.actType'] = 'business';
		var paramArray = [];
		for(p in parameter){
			paramArray.push(p+"="+parameter[p]);
		}
		url = url+(paramArray.length>0?("?"+paramArray.join('&')):(""));
		_config.url = url;
		$('#'+treeId).tree(_config);
	}
	
	GoLive.EasyUI.Message = new Object();
	GoLive.EasyUI.Message.show = _$showMessage;
	GoLive.EasyUI.Message.confirm = _$confirm;
	function _$showMessage(config){
//		var title = config.title;
		var _config = {
				showType:'slide',
				timeout:2000,
				style:{
	                right:'',
	                top:document.body.scrollTop+document.documentElement.scrollTop,
	                bottom:''
	            }
		};
		$.extend(_config,config);
		_config.title = _config.title || '操作提示'; 
		_config.msg = _$getMsgString(_config.icon,'<span style="color:red;">'+_config.msg+'</span>');
		$.messager.show(_config);
	}
	function _$getMsgString(icon,msg){
		var string =' <div class="messager-body panel-body panel-body-noborder window-body" '+
					'	title="" style="width: 266px;">'+
					'	<div class="messager-icon messager-{0}"></div>'+
					'	<div>{1}</div>'+
					'	<div style="clear: both;"></div>'+
					' </div>';
		return string.format(icon,msg);
	}
	
	function _$confirm(config){
		$.messager.confirm(config['title'], config['tip'], function(r){
			config['callback'](r);
        });
	}
	
	GoLive.EasyUI.Form = new Object();
	GoLive.EasyUI.Form.loadData = _$formLoadData;
	GoLive.EasyUI.Form.submit = _$formSubmit;
	GoLive.EasyUI.Form.reset = _$resetForm;
	
	function _$resetForm(formId){
		$('#'+formId).form('reset');
	}
	
	function _$formSubmit(config){
		var url = config.url;
		var id = config.restfulId;
		var method = 'POST';
		var idValue = $('#'+id).val();
		if(idValue){
			method = 'PUT';
		}
		var formId = config.formId;
		var formParams = GoLive.DomUtil.getJSONObjectFromForm(formId);
		url = url.format(formParams);
		var success = config.success;
		var onSubmit = config.onSubmit;
		
		var isValidate = true;
        if(onSubmit){
        	isValidate = GoLive.Judge.isBool(onSubmit(formParams));
        }
        //如果onSubmit返回false,则中断提交
        if(isValidate){
        	isValidate = $('#'+formId).form('enableValidation').form('validate');
        }
		if(isValidate){
			GoLive.ajax({
				url : url,
				async : true,
				data : JSON.stringify(formParams),
				dataType : "json",
				type: method,
				contentType: "application/json",
				success : function(data) {
					var data = eval('(' + data + ')'); 
					if(data['errorCode']){
						if(data['errorCode']=='serviceError'){
							_$showMessage({timeout:5000,icon:'error',msg:data['errorMsg']||data['localErrorMsg']});
						}else{
							_$showMessage({timeout:4000,icon:'info',msg:data['localErrorMsg']||data['errorMsg']});
						}
					}else{
						if(success){
							success(data);
						}
					}
				}
			});
		}
		
		/*$('#'+formId).form('submit',{
		    success: function(data){
		    	var data = eval('(' + data + ')'); 
		    	if(data['errorCode']){
		    		if(data['errorCode']=='serviceError'){
		    			_$showMessage({timeout:5000,icon:'error',msg:data['errorMsg']||data['localErrorMsg']});
		    		}else{
		    			_$showMessage({timeout:4000,icon:'info',msg:data['localErrorMsg']||data['errorMsg']});
		    		}
		    	}else{
		    		if(success){
		    			success(data);
		    		}
		    	}
		    },
		    onSubmit: function(param){
		    	var isValidate;
		        param['actModel.actType'] = 'save';
		        if(onSubmit){
		        	isValidate = onSubmit(param);
		        }
		        //如果onSubmit返回false,则中断提交
		        if(GoLive.Judge.isBool(isValidate) && !isValidate){
		        	return false;
		        }else{
		        	return $(this).form('enableValidation').form('validate');
		        }
		    }
		});*/
	}
	/**
	 * 加载表单数据,使用示例如下:
	   GoLive.EasyUI.Form.loadData({
			prefix:'role',
			formId:'myform',
			url:loadRoleUrl,
			afterLoad:function(record){
			},
			parameter:{
				'role.id':selectRecord.id
			},
		});
	 */
	function _$formLoadData(config){
		var prefix = config.prefix?(config.prefix+"."):"";
		var formId = config.formId;
		var url = config.url;
		var parameter = config.parameter;
		var afterLoadData = config.afterLoadData;
		var afterFormRender = config.afterFormRender;
		GoLive.ajax({
			url : url,
			async : true,
			data : parameter,
			dataType : "json",
			type: config.type?config.type:"POST",
			success : function(data) {
				if(!data["data"])
					data["data"] = new Object();
				if(afterLoadData){
					afterLoadData(data["data"]);
				}
				var prefixData ;
				if(prefix){
					prefixData = new Object();
					for(var p in data["data"]){
						prefixData[prefix+p] = data["data"][p];
					}
				}else{
					prefixData = data["data"];
				}
				$('#'+formId).form('load', prefixData);
				if(afterFormRender){
					afterFormRender(data["data"]);
				}
			}
		});
	}
	
	/**
	 * 用于封装easyui加载数据的jquery拓展方法
	 * 使用方法如下:
	 	$('#dataGrid').easyUILoadData({
	    		dataGridId: 'dataGrid',
	    		queryFormId:'queryForm',
	    		url: "${ctx}/systemAjax/system-roleList.action",
	    		columnDecoratedUrl:'${ctx}/easyUI/queryDataGridColumnDecoratedDataList.action',
	    		//是否对表格列进行值替换映射,如把[1/0]数据替换成[是/否]
	    		//如果配置了此项,则会根据配置内容从后台中获取数据并且替换单元格的内容.
	    		//如果为空,则不进行以上说明的动作
	    		decoratedColumns:[{
	    			field:'status',
	    			type:'number',
	    			dataRegister:'common.XmlSelectDataProvider',   	//根据xmlbean的xpath语句查询数据
	    			displayField:'text',
	    			valueField:'value',
	    			parameter:{
	    				selectName:'common.YN' // or xml			//select节点name属性的值
	    				
	    			}
	    		},{
	    			field:'id',
	    			type:'number',
	    			dataRegister:'common.HqlEntityDataProvider',  	//根据hibernate的hql语句查询数据
	    			displayField:'name',
	    			valueField:'id',
	    			valueListFieldName:'id',
	    			valueListFieldType:'long',
	    			parameter:{
	    				entityName: 'Role' 							//实体名称
	    				
	    			}
	    		}],
	    		afterDataGridRender : function(config,dataGrid){
	    		} 
    	});
	 */
	
	$.fn.easyUILoadData = function(config) {
		var self = this;
//		self._config = config;
		self.data('_datagridConfig',config);
		_$loadData();
		function _$loadData() {
			var _config = self.data('_datagridConfig');
			var datagridId = _config.dataGridId;
			var dataGrid = $('#'+datagridId);
			var formId = _config.queryFormId;
			var extractParameter = _config.extractParameter;
			var paginationObj = dataGrid.datagrid('getPager').pagination('options',
			'pageNumber');
			var pageNumber = paginationObj.pageNumber;
			if(pageNumber == 0){
				pageNumber = 1;
			}
			var pageSize = paginationObj.pageSize;
			var formParams = GoLive.DomUtil.getJSONObjectFromForm(config['queryFormId']);
//			var pageList = _config.pageList || [10,20,30,50,100];
			var params = {
					pageNumber : pageNumber,
					pageSize : pageSize
			};
			$.extend(params,formParams);
			if(extractParameter){
				extractParameter(params);
			}
			var url = _config.url;
			extractConditionParameters(params,_config.toolbarConditions);
			GoLive.ajax({
				url : url,
				async : true,
				data : JSON.stringify(params),//GoLive.JSON.toJSON(params),
				dataType : "json",
				contentType: _config.type?_config.type:" application/json;charset=UTF-8",
				type: _config.type?_config.type:"POST",
				success : function(data) {
					dataGrid.datagrid({
						data : data['data'],
						onAfterRender:function(target){
							
						},
	                	onSortColumn:function(target){
	                		decoratedDataGridColumns(_config,datagridId);
						}
					});
					dataGrid.datagrid('getPager').pagination({
						total : data['total'],
						onSelectPage : _$loadData,
						pageSize : pageSize,
						pageNumber : pageNumber/*,
						pageList : pageList*/
					});
					if(_config.afterDataGridRender){
						_config.afterDataGridRender(_config,dataGrid);
					}
					decoratedDataGridColumns(_config,datagridId);
					/*if(_config.inputList){
						var inputListTd = $("#"+_config.inputList).find('td').clone();
						$('.datagrid-toolbar tr').append(inputListTd);
						var inputList = $(inputListTd).find('input');
						var selectList = $(inputListTd).find('select');
						decoratedInputs(inputList);
						decoratedSelects(selectList);
						
					}*/
					generatedConditions(_config,_config.toolbarConditions);
					setConditionParameters(params,_config.toolbarConditions);
				}
			});
		}
		function decoratedDataGridColumns(_config,datagridId){
			var decoratedColumns = _config.decoratedColumns;
			if(decoratedColumns && decoratedColumns.length>0){
				var queryParamArray = getQueryParamArray(decoratedColumns,datagridId);
				decoratedDataGridColumn(queryParamArray,decoratedColumns,_config);
			}
		}
		function setConditionParameters(params,toolbarConditions){
			if(toolbarConditions && toolbarConditions.length>0){
				for(var i=0;i<toolbarConditions.length;i++){
					var condition = toolbarConditions[i];
//					var type = condition['type'];
//					if(type=='textbox'){
						var conditionTdId = 'td'+condition['name'];
						try{
							if(params[condition['name']]){
								$('#'+conditionTdId).find('.textbox-value').val(params[condition['name']]);
								$('#'+conditionTdId).find('.textbox-text').val(params[condition['name']]);
								$('#'+conditionTdId).find('.textbox-text').removeClass('textbox-prompt').removeClass('validatebox-invalid');
								$('#'+conditionTdId).find('.textbox').addClass('textbox-focused');
								$('#'+conditionTdId).find('.textbox').removeClass('textbox-invalid');
							}
						}catch(e){
							alert(e);;
						}
//					}
				}
			}
		}
		function extractConditionParameters(params,toolbarConditions){
			if(toolbarConditions && toolbarConditions.length>0){
				for(var i=0;i<toolbarConditions.length;i++){
					var condition = toolbarConditions[i];
//					var type = condition['type'];
//					if(type=='textbox'){
					var conditionTdId = 'td'+condition['name'];
					try{
						var value = $('#'+conditionTdId).find('.textbox-value').val();
						params[condition['name']] = value;
					}catch(e){
						;
					}
//					}
				}
			}
		}
		function generatedConditions(config,toolbarConditions){
			if(toolbarConditions && toolbarConditions.length>0){
//				var toolbarTr = $('.datagrid-toolbar tr');
				console.log($('#'+config.dataGridId).parent().prevAll());
				var toolbarTr = $('#'+config.dataGridId).parent().prevAll().find("tr");
				console.log(toolbarTr);
				for(var i=0;i<toolbarConditions.length;i++){
					var condition = toolbarConditions[i];
					var type = condition['type'];
					var domTd = $(GoLive.EasyUI.settings.dom['td'].format(condition));
					var seperator = GoLive.EasyUI.settings.dom['seperator'];
					if(type=='textbox'){
						generatedTextbox(toolbarTr,domTd,seperator,condition);
					}else if(type=='datebox'){
						generatedDatebox(toolbarTr,domTd,seperator,condition);
					}else if(type=='datetimebox'){
						generatedDatetimebox(toolbarTr,domTd,seperator,condition);
					}
					
				}
			}
			
		}
		//处理文本输入框
		function generatedTextbox(toolbarTr,domTd,seperator,condition){
			condition['id'] = condition['id']?condition['id']:(condition['name']+'ID');
			var domInput = $(GoLive.EasyUI.settings.dom['input'].format(condition));
			toolbarTr.append($(seperator));
			if(condition['label']){
				var label = $(GoLive.EasyUI.settings.dom['label'].format(condition));
				domTd.append(label);
			}
			domTd.append(domInput);
			toolbarTr.append(domTd);
			$(domInput).textbox(condition['options']);
		}
		//处理文本输入框
		function generatedDatebox(toolbarTr,domTd,seperator,condition){
			condition['id'] = condition['id']?condition['id']:(condition['name']+'ID');
			var domInput = $(GoLive.EasyUI.settings.dom['input'].format(condition));
			toolbarTr.append($(seperator));
			if(condition['label']){
				var label = $(GoLive.EasyUI.settings.dom['label'].format(condition));
				domTd.append(label);
			}
			domTd.append(domInput);
			toolbarTr.append(domTd);
			$(domInput).datebox(condition['options']);
		}

		//处理时间输入框
		function generatedDatetimebox(toolbarTr,domTd,seperator,condition){
			condition['id'] = condition['id']?condition['id']:(condition['name']+'ID');
			var domInput = $(GoLive.EasyUI.settings.dom['input'].format(condition));
			toolbarTr.append($(seperator));
			if(condition['label']){
				var label = $(GoLive.EasyUI.settings.dom['label'].format(condition));
				domTd.append(label);
			}
			domTd.append(domInput);
			toolbarTr.append(domTd);
			$(domInput).datetimebox(condition['options']);
		}
		
		//处理文本输入框
		function decoratedInputs(inputList){
			if(inputList){
				for(var i=0;i<inputList.length;i++){
					var uiclass = $(inputList[i]).attr('uiclass');
					if(uiclass == 'easyui-datebox'){
					    $(inputList[i]).datebox({
					        required:true
					    });
					}
					if(uiclass == 'easyui-textbox'){
					    $(inputList[i]).textbox({
					        required:true
					    });
					}
				}
			}
		}
		//处理下拉框
		function decoratedSelects(selectList){
			if(selectList){
				for(var i=0;i<selectList.length;i++){
					var uiclass = $(selectList[i]).attr('uiclass');
				}
			}
		}
		function decoratedDataGridColumn(queryParamArray,decoratedColumns,_config){
			var params = {
					data : queryParamArray
			};
			var url = _config.columnDecoratedUrl;
			GoLive.ajax({
				url : url,
				async : true,
				data : JSON.stringify(params),
				dataType : "json",
				contentType: _config.type?_config.type:" application/json;charset=UTF-8",
				type: _config.type?_config.type:"POST",
				success : function(data) {
					var dc = $('#'+_config.dataGridId).data('datagrid').dc;
					var body2 = dc.body2;
					for(var i=0;i<decoratedColumns.length;i++){
						var decoratedColumn = decoratedColumns[i];
						var displayField = decoratedColumn['displayField'];
						var valueField = decoratedColumn['valueField'];
						var divs = $(body2).find('td[field="'+decoratedColumn["field"]+'"] div');
						var valueList =	data.findObject('field',decoratedColumn['field']).valueList;
						for(var j=0;j<divs.length;j++){
							var div = divs[j];
							replaceDivText(div,valueList,displayField,valueField);
						}
					}
				}
			});
		}
		function replaceDivText(div,valueList,displayField,valueField){
			var content = $.trim($(div).text());
			content = content.split(",");
			var length = content.length;
			var count = 0;
			if(length>1){
				var multiText = "";
				for(var i=0;i<length;i++){
					var obj = valueList.findObject(valueField,content[i]);
					if(i==0){
						multiText = obj[displayField];
					}else{
						multiText = multiText + " | " + obj[displayField];
					}
				}
				var hidden = '<span style="display:none;">'+content.join(",")+'</span>';
				$(div).html(hidden + multiText);
			}else{
				var obj = valueList.findObject(valueField,$(div).text());
				if(obj){
					var hidden = '<span style="display:none;">'+obj[valueField]+'</span>';
					$(div).html(hidden+obj[displayField]);
				}
			}
		}
		function getQueryParamArray(decoratedColumns,datagridId){
			var queryParamArray = new Array();
			if(decoratedColumns && decoratedColumns.length>0){
				var dc = $('#'+datagridId).data('datagrid').dc;
				var body2 = dc.body2;
				for(var i=0;i<decoratedColumns.length;i++){
					var decoratedColumn = decoratedColumns[i];
					var queryParam = new Object();
					var type = decoratedColumn['type'];
					queryParam['type'] = type;
					queryParam['field'] = decoratedColumn['field'];;
					queryParam['dataRegister'] = decoratedColumn['dataRegister'];
					queryParam['parameter'] = decoratedColumn['parameter'];
					queryParam['valueListFieldName'] = decoratedColumn['valueListFieldName'];
					queryParam['valueListFieldType'] = decoratedColumn['valueListFieldType'];
					var decoratedColumn = decoratedColumns[i];
					var tds = $(body2).find('td[field="'+decoratedColumn["field"]+'"]');
					var idList = new Array();
					for(var j=0;j<tds.length;j++){
						td = tds[j];
						var texts = $(td).text().split(',');
						if(type=='number'){
							if(texts && texts.length>1){
								for(var h=0;h<texts.length;h++){
									idList.push(Number(texts[h]));
								}
							}else{
								idList.push(Number(texts[0]));
							}
						}
						else{
							if(texts && texts.length>1){
								for(var h=0;h<texts.length;h++){
									idList.push(texts[h]);
								}
							}else{
								idList.push(texts[0]);
							}
						}
					}
					queryParam['valueList'] = idList.unique();
					queryParamArray.push(queryParam);
				}
			}
			return queryParamArray;
		}
	}
})(window);


function banBackSpace(e){
    var ev = e || window.event;//获取event对象
    var obj = ev.target || ev.srcElement;//获取事件源
 
    var t = obj.type || obj.getAttribute('type');//获取事件源类型
 
    //获取作为判断条件的事件类型
    var vReadOnly = obj.getAttribute('readonly');
    var vEnabled = obj.getAttribute('enabled');
    //处理null值情况
    vReadOnly = (vReadOnly == null) ? false : vReadOnly;
    vEnabled = (vEnabled == null) ? true : vEnabled;
 
    //当敲Backspace键时，事件源类型为密码或单行、多行文本的，
    //并且readonly属性为true或enabled属性为false的，则退格键失效
    var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea")
                && (vReadOnly==true || vEnabled!=true))?true:false;
 
    //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
    var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")
                ?true:false;
 
    //判断
    if(flag2){
        return false;
    }
    if(flag1){
        return false;
    }
}
function Tool(){
	this.checkPropertyValue=function(className, propertyName, propertyValue, callBack){
        alert(propertyName);
		$.ajax({
            type: "POST",
            url: webRoot+"/commonAjax/common-checkPropertyValue.action",
            data: {"commonBean.propertyValue":propertyValue,"commonBean.propertyName":propertyName,"commonBean.className":className},
            dataType: "json",
            success: function(data){
            	if ("false" == data.commonBean.result) {
            		callBack(false);
            	} else {
            		callBack(true);
            	}
            }
        });
	}
}

var tool = new Tool();

//禁止后退键 作用于Firefox、Opera
document.onkeypress=banBackSpace;
//禁止后退键  作用于IE、Chrome
document.onkeydown=banBackSpace;

GoLive = new Object();
(function($){
	
	GoLive.DomUtil = new Object();
	GoLive.DomUtil.getJSONObjectFromForm = _getJSONObjectFromForm;
	/**
	 * 根据dom元素返回取到dom元素反对应的json对象字符串  <br>
	 * @param formId  form对象的id<br>
	 * @param inCludeFields  格式如:"name1,name2,.....",<br>
	 * @param prefix  返回字段名的前缀
	 * @return  返回json对象
	 */
	//(function($){
	function _getJSONObjectFromForm(formId,inCludeField,prefix){
		var obj = {};
		if(document.getElementById(formId)){
			var array = document.getElementById(formId).elements;
			if(inCludeField)
				inCludeFields = inCludeField.split(',');
			for(var i=0;i<array.length;i++){
				if(inCludeField){
					if( array[i].name){
						var flag = false;
						for(var j=0;j< inCludeFields.length;j++){
							if((prefix||'')+inCludeFields[j]!= ''+array[i].name){
							}else{
								flag = true;
								break;
							}
						}
						if(flag && array[i].value) {
							obj[array[i].name]	= array[i].value ;
						}
					}
				}else{
					if(array[i].name)
						obj[array[i].name]	= array[i].value ;
				}
			}
		}
		return obj;
	}
	
	GoLive.Constants = {
		OPERATE_TIP_DELETE:"删除成功!",
		OPERATE_TIP_CLOSE:"关闭成功!",
		OPERATE_CONFIRM_DELETE:"确定要删除吗？",
		OPERATE_CONFIRM_CLOSE:"确定要关闭吗？"
	};
	var _$componentAjaxUrl = "/"+"componentAjax/"+"ajax.action";
	
	GoLive.ajax = ajax;
	/**
	 * GoLive的ajax定义
	 * @param configs
	 * @returns
	 */
	function ajax(configs){
		var _configs = {};
		$.extend(_configs,configs);
		_configs.success = function(data){
			if(data && data.errorCode){
				GoLive.EasyUI.Message.show({icon:'error',msg:data["localErrorMsg"]});
				return ;
			}
			if(configs.success)
				configs.success(data);
		};
		_configs.error = function (jqXHR,textStatus, errorThrown){
			//session过期处理
			if(jqXHR.responseText.indexOf('Session过期或未登陆')>0){
				window.parent.alert('会话过期,请重新登录！'); 
				window.parent.location.href = "../index.jsp";
				window.parent.location.href = "../../index.jsp";
				window.parent.location.href = "../../../index.jsp";
				window.parent.location.href = "../../../../index.jsp";
			}else{
				if(configs.error)
					configs.error(jqXHR);
			}
		};
		/*_configs.complete = function(jqXHR, textStatus){
			if(configs.complete)
				configs.complete(jqXHR);
		};*/
		if(_configs.data){
			for(var p in _configs.data){
				if(!_configs.data[p]){
					_configs.data[p] = "";
				}
			}
		}
		$.ajax(_configs);
	}
	
	GoLive.AjaxUpload  = {
		url:'/ajaxUpload-ajaxUploadPage.action',
		STATE_NORMAL:"0", 			//正常
		STATE_ERROR_UPLOAD:"1",  	//错误
		STATE_COMPLETE:"2",			//上传完成
		STATE_ERROR_VALIDATE:"3",	//文件验证出错
		TYPE_MULTY:"multy",
		TYPE_SINGLE:"single",
		/**
		 * 从AjaxUpload窗体返回对象中获取所需要的值
		 * 2015年5月6日上午9:41:46
		 * 
		    //示例: 
		 	var result = GoLive.AjaxUpload.getValues(retVal,function(obj){
				return obj['dbSavePath'];
			});
		 *	
		 * @param returnObject
		 * 			AjaxUpload窗体返回对象
		 * @param getObjectFunc
		 * 			自定义获取值函数
		 * @returns
		 * 			返回数组
		 */
		getValues: function(returnObject,getObjectFunc){
			if(returnObject && returnObject['values'].length>0){
				var result = new Array();
				for(var i=0;i<returnObject['values'].length;i++){
					result.push(getObjectFunc(returnObject['values'][i]));
				}
				return result;
			}
			return null;
		},
		/**
		 * 调用文件上传窗体
		 * 2015年5月6日上午10:01:58
		 * 
		    //示例: 
		 	GoLive.AjaxUpload.openUploadWindow({
        		type: GoLive.AjaxUpload.TYPE_SINGLE
        	},function(returnObj){
        		var result = GoLive.AjaxUpload.getValues(returnObj,function(obj){
    				return obj['dbSavePath'];
    			});
    			if(result)
    				$("#path").val(result.join(','));
        	});
		 *
		 * @param configs
		 * 			配置对象
		 * @param callbackFunc(returnObj)	
		 * 			窗体关闭后的回调函数
		 */
		openUploadWindow: function openUploadWindow(configs,callbackFunc){
			var _configs = {
				dialogWidth: '850px',
				dialogHeight: '200px',
				center: 'yes',
				type: this.TYPE_SINGLE,
				validations:["*"],
				fileUrls: null
			};
//				 +"&validations="+_configs.validations.join(",")
			$.extend(_configs,configs,true);
			var params = {
				validations	: _configs.validations,
				fileUrls	: _configs.fileUrls
			};
			var retVal = window.showModalDialog(configs.url+'?type='+_configs.type,
												params,
												"dialogWidth="+_configs.dialogWidth+";dialogHeight="+_configs.dialogHeight+";center="+_configs.center+";border=this;help=yes");
			if (retVal == undefined) {  
    			retVal = window.returnValue;  
			}
			callbackFunc(retVal);
		},
		createModelWindow : function(configs,callbackFunc){
			var _configs = {
					dialogWidth: '850',
					dialogHeight: '200',
					center: 'yes',
					type: this.TYPE_SINGLE,
					validations:["*"],
					fileUrls: null
				};
			$.extend(_configs,configs,true);
			var params = {
				validations	: _configs.validations,
				fileUrls	: _configs.fileUrls
			};
			var dialogWindow = '<div style="display:none;overflow:hidden;padding:3px;width:900px;height:600px;" id="fileUploadDialog"> '+
									'<iframe frameborder="no" border="0" marginwidth="0" marginheight="0" id="fileUploadModelWindowIframe"  scrolling="no"  width="100%" height="100%"></iframe>'+
								'</div>';
			$("body").append($(dialogWindow));
	        $("#fileUploadModelWindowIframe",$("body")).attr("src",_configs.url+'?type='+_configs.type); //设置IFRAME的SRC;
	        window.uploadFileParams = params;
	        $("#fileUploadDialog").dialog({
		        bgiframe: true,
		        resizable: true, //是否可以重置大小
		        height: _configs.dialogHeight, //高度
		        width:  _configs.dialogWidth, //宽度
		        draggable: false, //是否可以拖动。
		        title: "附件上传",
		        modal: true,
		        open: function (e) {  //打开的时候触发的事件
		        },
		        close: function () { //关闭Dialog时候触发的事件
		        	document.getElementById('fileUploadModelWindowIframe').contentWindow.closeWin();
					callbackFunc(window.returnValue);
					window.returnValue = null;
					window.uploadFileParams = null;
					$("#fileUploadDialog",$("body")).remove();
		        },
		        buttons: {
					"关闭": function() {
						$( this ).dialog( "close" );
					}
				}
	        });
		}
	};

	GoLive.AjaxCheck  = {
		url:'/putonSelAjax/putonSel-putonList.action',
		TYPE_SINGLE:"single",
		getValues: function(returnObject,getObjectFunc){
			if(returnObject && returnObject['values'].length>0){
				var result = new Array();
				for(var i=0;i<returnObject['values'].length;i++){
					result.push(getObjectFunc(returnObject['values'][i]));
				}
				return result;
			}
			return null;
		},
		openCoverCheckWindow: function openUploadWindow(configs,callbackFunc){
			var _configs = {
				dialogWidth: '850px',
				dialogHeight: '200px',
				center: 'yes',
				type: this.TYPE_SINGLE,
				validations:["*"],
				fileUrls: null
			};
			$.extend(_configs,configs,true);
			var params = {
				validations	: _configs.validations,
				fileUrls	: _configs.fileUrls
			};
			var retVal = window.showModalDialog(configs.url+'?type='+_configs.type,
												params,
												"dialogWidth="+_configs.dialogWidth+";dialogHeight="+_configs.dialogHeight+";center="+_configs.center+";border=this;help=yes");
			if (retVal == undefined) {  
    			retVal = window.returnValue;  
			}
			callbackFunc(retVal);
		},
		createModelWindow : function(configs,callbackFunc){
			var _configs = {
					dialogWidth: '850',
					dialogHeight: '200',
					center: 'yes',
					type: this.TYPE_SINGLE
				};
			$.extend(_configs,configs,true);
			var dialogWindow = '<div style="display:none;overflow:hidden;padding:3px;width:900px;height:600px;" id="checkDialog"> '+
									'<iframe frameborder="no" border="0" marginwidth="0" marginheight="0" id="coverCheckWindowIframe"  scrolling="no"  width="100%" height="100%"></iframe>'+
								'</div>';
			$("body").append($(dialogWindow));
	        $("#coverCheckWindowIframe",$("body")).attr("src",_configs.url); //设置IFRAME的SRC;
	        window.closeDialogFunc = function(){
	        	$( "#checkDialog" ).dialog( "close" );
	        }; 
	        $("#checkDialog").dialog({
		        bgiframe: true,
		        resizable: true, //是否可以重置大小
		        height: _configs.dialogHeight, //高度
		        width:  _configs.dialogWidth, //宽度
		        draggable: false, //是否可以拖动。
		        title: "选择投放名称",
		        modal: true,
		        open: function (e) {  //打开的时候触发的事件
		        },
		        close: function () { //关闭Dialog时候触发的事件
					callbackFunc(window.returnValue);
					window.returnValue = null;
					$("#checkDialog",$("body")).remove();
		        },
		        buttons: {
					"关闭": function() {
						$( this ).dialog( "close" );
					}
				}
	        });
		}
	};
	
	
	GoLive.Window = new Object();
	
	GoLive.Window.location = _$location;
	
	/**
	 * 
	 * @param {Object} Obj
	 * 		url,title,paramObj
	 */
	function _$location(obj){
		
		//window.location.href = _$getLocationUrl(obj);
		_$winPostHref(obj['url'],obj['paramObj']);
	}
	
	function _$winPostHref(url,params){
		$('form[name="exportForm"]').remove();
		var temp=document.createElement("form");
		temp.action=url;
		temp.name = 'exportForm';
		temp.method="post";
		temp.style.display="none";
		if(params != null){
			for(var x in params) {
				var opt=document.createElement("input");
				opt.name=x;
				opt.type='hidden';
				opt.value=params[x];
				temp.appendChild(opt);
			}
		}
		$('body').append(temp);
		temp.submit();
	}
	
	GoLive.JSON = new Object();
	
	GoLive.JSON.toJSON = _$obj2str;
	/**
	 * 对js对象或者字符串生成json字符串
	 * @param {Object} o
	 * @return {TypeName} 
	 */
	 function _$obj2str(o) {
	     if (o == undefined) {
	         return null;
	     }
	     var r = [];
	     if (typeof o == "string") {
	    	 if(!o) {
	    		 return '""';
	    	 }
	    	 return "\"" + o.replace(/([\"\\])/g, "\\$1").replace(/(\n)/g, "\\n").replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\"";
	     }
	     if (typeof o == "object") {
	         if (!o.sort) {
	             for (var i in o)
	                 r.push("\"" + i + "\":" + _$obj2str(o[i]));
	             if (!!document.all && !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)) {
	                 r.push("toString:" + o.toString.toString());
	             }
	             r = "{" + r.join() + "}"
	         } else {
	             for (var i = 0; i < o.length; i++)
	                 r.push(_$obj2str(o[i]))
	             r = "[" + r.join() + "]";
	         }
	         return r;
	     }
	     return o.toString().replace(/\"\:/g, '":""');
	 }
	
	GoLive.System = new Object();
	GoLive.System.componentAjaxUrl = _$componentAjaxUrl;
	GoLive.System.getSystemDate = _$getSystemDate; 
	
	function  _$getSystemDate(url){
		var systemDate = null;
		
		$.ajax({
			  url: url,
			  async:false,
			  dataType:"text",
			  data:{register:'common.Common_GetSystemDate'},
			  success: function(data){
				  systemDate = (''+data).toDate();
			  }
		});
		return systemDate;
	}
	
	GoLive.System.getSystemDateString = _$getSystemDateString; 
	
	function  _$getSystemDateString(url){
		var systemDate = null;
		$.ajax({
			  url:  url,
			  async:false,
			  dataType:"text",
			  data:{register:'common.Common_GetSystemDate'},
			  success: function(data){
				  systemDate =  ''+data;
			  }
		});
		return systemDate;
	}
	
	GoLive.UI = new Object();
	
	GoLive.UI.autocomplete = _$autocomplete;

	function _$autocomplete (options){
		var autoHander ;
		if(options.inputObject)
			autoHander = $(options.inputObject);
		else
			autoHander = $("#"+options.inputId);
		autoHander.autocomplete(options.url?options.url:"/autocomplete/queryAutoCompleteDataList.action",{
			 max: options.max?options.max:12,    //列表里的条目数
	         minChars: options.minChars?options.minChars:0,    //自动完成激活之前填入的最小字符
	         width: options.width?options.width:150,     //提示的宽度，溢出隐藏
	         scrollHeight: options.scrollHeight?options.scrollHeight:80,   //提示的高度，溢出显示滚动条
	         autoFill: options.autoFill?options.autoFill:false,    //自动填充
	         matchContains: options.matchContains?options.matchContains:false,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
	         matchCase:options.matchCase?options.matchCase:true,
	         selectFirst:false,
	         selectOnly:options.selectOnly?options.selectOnly:false,
	         parse: function(data) {
	            return $.map(eval(data), function(row) {
	                return {
	                    data: row,
	                    value: row[options.code?options.code:'code']
		                //result: row[options.code?options.code:'code']
	                }
	            });
	         },
	         extraParams: {
				register:options.register
			 }, 
	         formatItem: options.formatItem?options.formatItem:function(data, i, max) {
	             return data[options.code?options.code:'code'] + '【' + data[options.name?options.name:'name'] + '】';
	         },
	         formatMatch:options.formatMatch?options.formatMatch: function(data, i, max) {
	             return data[options.code?options.code:'code'] + data[options.name?options.name:'name'];
	         },
	         formatResult:options.formatResult?options.formatResult: function(data) {
	             return data[options.code?options.code:'code'];
	         }
	     }).result(options.result?options.result:function(event, data, formatted) {
	    	if(options.inputObject)
	    		$(options.inputObject).val(data[options.code?options.code:'code']);
	    	else
	     	 	$("#"+options.inputId).val(data[options.code?options.code:'code']);
	     });
	}
	
	
	GoLive.UI.Select2 = new Object();
	GoLive.UI.Select2.init = _$select2Init;
	GoLive.UI.Select2.val = _$select2Val;
	
	GoLive.UI.datepicker = _$datepicker;
	GoLive.UI.timepicker = _$timepicker;
	
	//将传的id参数对应的input变成日期框
	function _$datepicker(){
	   for(var i=0;i<arguments.length;i++){
		   $( "#"+arguments[i]['id']).data('_obj',arguments[i]);
		   $( "#"+arguments[i]['id']).bind({
			   'click':function() { 
			   		WdatePicker();
			   }
		   });
		   $( "#"+arguments[i]['id']).addClass("Wdate");
		}
		   
	}
	//将传的id参数对应的input变成日期框
	function _$timepicker(){
		for(var i=0;i<arguments.length;i++){
		   $( "#"+arguments[i]['id']).data('_obj',arguments[i]);
		   $( "#"+arguments[i]['id'] ).bind({
			   'click':function() { 
			   		WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});
			   }
		   });
		   $( "#"+arguments[i]['id']).addClass("Wdate");
		}
	}
	
	function _$select2Val(select2,values){
		select2.val(values).trigger("change");
	}
	
	function _$select2Init(values,configs){
		var _configs = {
   			multiple: true,
   			tags: true,
//   			minimumInputLength:2,
   			language: "zh-CN",
   		 	templateResult: formatState,
   		    templateSelection :templateSelection,
   		  	placeholder: "请选择......"
   		};
		if(configs){
			$.extend(_configs,configs,true);
		}
		var select = $("#"+_configs.selectId);	
		var standardTypeSelect =  select.select2(_configs);
		var optionIdPrefix = configs.optionIdPrefix || "optionId";
		if(values && values.length>0){
			standardTypeSelect.val(values).trigger("change");
			for(var i=0;i<values.length;i++){
				$("#"+optionIdPrefix+values[i],select).data("isSelected",true);
			}
		}else{
			standardTypeSelect.val(null).trigger("change");
//			$('.select2-selection__choice').remove();
//			$('.select2-search__field').attr('placeholder','请选择......');
		}
   		standardTypeSelect.on("select2:select", function (e) {
   			var optionId = getSelect2OptionId(e,_configs);
   			$("#"+optionId,select).data("isSelected",true);
   		});
   		standardTypeSelect.on("select2:unselect", function (e) {
   			var optionId = getSelect2OptionId(e,_configs);
   			$("#"+optionId,select).data("isSelected",false);
   		}); 
   		return standardTypeSelect;
	}
	

	function formatState(state,self) {
		var optionIdPrefix = self.options.options.optionIdPrefix || "optionId";
		if (!state.id) {
			return state.text;
		}
		var $state;
		if ($("#"+optionIdPrefix + state.id).data("isSelected")) {
			$state = $('<span class="select2-span-selected"> ' + state.text + '</span>');
		} else {
			$state = $('<span class="select2-span-unselected"> ' + state.text + '</span>');
		}
		return $state;
	};
	function templateSelection(state,self) {
		var $state = $('<span class="select2-span-selected"> ' + state.text + '</span>');
		return $state;
	};
	function getSelect2OptionId(evt,configs) {
		var optionIdPrefix = configs.optionIdPrefix || "optionId";
		return optionIdPrefix + evt.params.data.id;
	}
	
	
	
	GoLive.Select = new Object();
	GoLive.Select.setDisabled = _$setDisabled;
	GoLive.Select.removeDisabled = _$removeDisabled;
	GoLive.Select.setSelected = _$setSelected;
	GoLive.Select.reomveOption = _$reomveOption;
	GoLive.Select.addOption = _$addOption;
	GoLive.Select.getSelected = _$getSelectedOption;
	GoLive.Select.getSelectedText = _$getSelectedOption;
	GoLive.Select.unbindChange=function (select){
		$(select).unbind('change').removeAttr('change')
	};

	function _$getSelectedText (selectId){
		return $('#'+selectId+' option:selected').text();
	}
	function _$getSelectedOption (select){
		var selecteds = $(select).find('option:selected');
		return selecteds.length==1?selecteds[0]:null;
	}
	function _$setDisabled(select,value,type){
		if(!value && !type){
			var options = $(select).children("option");
			options.attr("disabled", "disabled");
			$(select).addClass('cmhit-readonly');
			return ;
		}
		var opSelect;
		if(type){
			opSelect = "option["+type+"='"+value+"']";
		}else{
			if(value)
				opSelect = "option[value='"+value+"']";
			else
				opSelect = "option";
		}
		
		var options = $(select).children(opSelect);
		options.attr("disabled", "disabled");
		$(select).addClass('cmhit-readonly');
	}

	function _$removeDisabled(select,value,type){
		if(!value && !type){
			var options = $(select).children("option");
			options.removeAttr("disabled");
			$(select).removeClass('cmhit-readonly');
			return ;
		}
		var opSelect;
		if(type){
			opSelect = "option["+type+"='"+value+"']";
		}else{
			if(value)
				opSelect = "option[value='"+value+"']";
			else
				opSelect = "option";
		}
		
		var options = $(select).children(opSelect);
		options.removeAttr("disabled");
		$(select).removeClass('cmhit-readonly');
	}
	function _$setSelected(select,value,type){
		var flag = false;
		$(select).children('option').each(function(index,option){
			if(value){
				if(!type){
					if(value == $(option).text() || value == $(option).val()){
						$(option).attr("selected", true);
						flag =  true;
						return false;
					}
				}else{
					if(type == 'value' && value == $(option).val()){
						$(option).attr("selected", true);
						flag =  true;
						return false;
					}else if(type == 'text' && value == $(option).text()){
						$(option).attr("selected", true);
						flag =  true;
						return false;
					}
				}
			}else{
				$(option).attr("selected", true);
				flag =  true;
				return false;
			}
		});
		return flag;
	}
	function _$reomveOption(select,value,type){
		var opSelect;
		if(type){
			opSelect = "option["+type+"='"+value+"']";
		}
		var selectFirstValue = "";
		if($(select).val()){
			selectFirstValue = $(select).val();
		}else{
			selectFirstValue = $($(select).children("option")[0]).val();
		}
		$(select).children(opSelect).remove();
		_$setSelected(select,selectFirstValue,'value')
	}
	function _$addOption(select,text,value,isSelect){
		var opSelect = "option[value='"+value+"']";;
		var op = $(select).children(opSelect).attr("selected", true);
		if(!(op.length>0)){
			var option = '<option value="'+value+'"'+(isSelect?'selected="selected"':'')+'>'+text+'</option>';
			$(option).appendTo(select)
		}
	}
	
	
	GoLive.Select.Relation = new Object();
	
	GoLive.Select.Relation.init = _$initRelationSelect;
	
	
//	GoLive.Validate = new validate(configs);
	/**
	 * GoLive的validate定义
	 * @param configs
	 * @returns
	 */
	/*function validate(configs){
		
		var defaults = {
				//规则配置
				rules : null,
				//方法集合
				methods : null
		};
		$.extend(defaults,configs);
	}*/
	/**
	 * 初始化关联下拉框
	 * 用法如下: 
	 * 
		var channelDef = {
	        			params:{
	        			},
	    				register:"select.channel",
	        			valueFieldName:"id",
	        			textFieldName:"name",
	        			selectId:"channelId",
	        			value:"${upgradeVersionRecord.channelId }",
	        			onchanged:function(arg){},
	        			extraParams:function(params){
						},
		    			decorateItem: function(option,record){
		    				$(option).data("holdType",record["holdType"]);
		    			}
	        	};
		var clientTypeDef = {
				params:{
					ChannelId:"${upgradeVersionRecord.channelId }"
				},
				register:"select.clientType",
				valueFieldName:"id",
				textFieldName:"name",
				selectId:"clientTypeId",
				value:"${upgradeVersionRecord.clientTypeId }",
				isInit:true,
				onchanged:function(arg){},
				extraParams:function(params){
					params['appTypeId'] = $('#appTypeId').val();
				},
    			decorateItem: function(option,record){
    				$(option).data("holdType",record["holdType"]);
    			}
		};
		
		GoLive.Select.Relation.init(channelDef,clientTypeDef);
	 */
	function _$initRelationSelect(firstSelectDef,secondTypeDef){
		_$initFirstSelect(firstSelectDef,secondTypeDef);
	}
	/**
	 * 初始化第一个下拉框
	 */
	function _$initFirstSelect(firstSelectDef,secondTypeDef){
//		console.log('_$initFirstSelect');
//		var url = "${ctx}"+"/"+"componentAjax/"+"ajax.action?register=myfocus.operation";
//		var url = "/"+"componentAjax/"+"ajax.action?register=myfocus.operation";
		//请求数据
		_$ajaxFirstSelect(firstSelectDef,secondTypeDef);
//		if(firstSelectDef.extraParams){
//			firstSelectDef.extraParams(params);
//		}
		//下拉事件
		GoLive.Select.unbindChange($('#'+firstSelectDef.selectId));
		$('#'+firstSelectDef.selectId).change(function(arg) {
			var selectValue = $(this).children('option:selected').val(); 
		  	$('#'+firstSelectDef.selectId).empty();
		  	$('#'+firstSelectDef.selectId).val("");
		  	if(secondTypeDef){
		  		$('#'+secondTypeDef.selectId).empty();
		  		$('#'+secondTypeDef.selectId).val("");
		  	}
			//改变选中的值
			firstSelectDef.value = selectValue;
//			secondTypeDef.params.ChannelId = firstSelectDef.value;
			_$ajaxFirstSelect(firstSelectDef,secondTypeDef);
			if(firstSelectDef.onchanged){
				firstSelectDef.onchanged(arg);
			}
		});
	}
	/**
	 * 请求第一个下拉框的数据
	 */
	function _$ajaxFirstSelect(firstSelectDef,secondTypeDef){
		var params = firstSelectDef.params || {};
		params['register'] = firstSelectDef.register;
		if(firstSelectDef.extraParams){
			firstSelectDef.extraParams(params);
		}
		$.ajax({
			  url:  firstSelectDef.url,
			  async:true,
			  dataType:"json",
			  data:params,
			  success: function(data){
				  _$selectAddOptions(firstSelectDef,data);
				  _$selectSetSelected(firstSelectDef.selectId,firstSelectDef.value);
				  if(firstSelectDef.afterInit){
					  firstSelectDef.afterInit($('#'+firstSelectDef.selectId)[0]);
				  }
				  if(secondTypeDef){
					  if(!secondTypeDef.params.ChannelId){
						  secondTypeDef.params.ChannelId = $('#'+firstSelectDef.selectId).val();
					  }
					  _$initSecondSelect(secondTypeDef);
				  }
			  }
		});
	}
	/**
	 * 请求第二个下拉框的数据
	 */
	function _$ajaxSecondSelect(secondTypeDef){
		var params = secondTypeDef.params || {};
		params['register'] = secondTypeDef.register;
		if(secondTypeDef.extraParams){
			secondTypeDef.extraParams(params);
		}
		//请求数据
		$.ajax({
		  url:  secondTypeDef.url,
		  async:true,
		  data:params,
		  dataType:"json",
		  success: function(data){
//			  console.log(data);
			  _$selectAddOptions(secondTypeDef,data);
			  if(secondTypeDef.isInit){
					secondTypeDef.isInit = false;
			  }else{
				  secondTypeDef.value ="";
			  }
			  _$selectSetSelected(secondTypeDef.selectId,secondTypeDef.value);
			  if(secondTypeDef.afterInit){
				  secondTypeDef.afterInit($('#'+secondTypeDef.selectId)[0]);
			  }
		  }
		});
	}
	function _$initSecondSelect(secondTypeDef){
		_$ajaxSecondSelect(secondTypeDef);
		GoLive.Select.unbindChange($('#'+secondTypeDef.selectId));
		$('#'+secondTypeDef.selectId).change(function(arg) {
			//改变选中的值
			if(secondTypeDef.onchanged){
				secondTypeDef.onchanged(arg);
			}
		});
	}
	function _$initMacSelect(macId){
		
	}

	function _$selectAddOptions(selectDef,data){
//		$("#"+selectDef.selectId)
		if(selectDef.nullable){
//			if($.isArray(data)){
			var option = $('<option id="'+((selectDef.optionIdPrefix?selectDef.optionIdPrefix:"option")+'null')+'" value="">'+selectDef.nullText+'</option>');
			$("#"+selectDef.selectId).append(option);
//			}
		}
		if(data){
			for(var i=0;i<data.length;i++){
				var option = $('<option id="'+((selectDef.optionIdPrefix?selectDef.optionIdPrefix:"option")+data[i][selectDef.valueFieldName])+'" value="'+data[i][selectDef.valueFieldName]+'">'+data[i][selectDef.textFieldName]+'</option>');
				$("#"+selectDef.selectId).append(option);
				if(selectDef.decorateItem){
					selectDef.decorateItem(option,data[i]);
				}
			}
		}
	}
	function _$selectSetSelected(selectId,value){
		if(value){
			$('#'+selectId+' option[value="'+value+'"]').attr("selected", true);
		}else{
			$('#'+selectId+' option:first').attr("selected", true);
		}
	}
	
	
	
	
	
	
	
	/**
	 * options:{
	 		id:'example_video_1',
	 		poster:'http://video-js.zencoder.com/oceans-clip.png',
	 		width:'',
	 		height:'',
	 		preload:'',
	 		'data-setup':{},
	 		source:[{
	 			id:'',
	 			src:'http://cdn-3.golivetv.tv/AD/tclzhuanshu/qifengle30s.mp4',
	 			type:'video/mp4'
	 		},{
	 			id:'',
	 			src:'http://video-js.zencoder.com/oceans-clip.webm',
	 			type:'video/webm'
	 		},{
	 			id:'',
	 			src:'http://video-js.zencoder.com/oceans-clip.ogv',
	 			type:'video/ogg'
	 		}]
	   }
	 */
	/*$.fn.video = function(options) {
		// 将用户的options 和 默认参数defaults 合并, 如有重复, 优先使用 options
        // 这里的 $.extend 的第一个参数是空的对象, 原因后面解释
        var settings = $.extend({}, $.fn.video.defaults, options);
        var self = this;
        var video = $("<video></video>");
        for(var p in settings){
        	if(p!="sources"){
        		$(video).attr(p,settings[p]);
        	}
        	if(p=="sources"){
        		var sources = settings[p];
        		for(var i=0;i<sources.length;i++){
        			var source = '<source ';//></source>';
        			for(var n in sources[i]){
        				console.log(n+': '+sources[i][n]);
//        				$(source).attr(n,sources[i][n]);
        				source = source + (''+n) + '=' + (_$obj2str(sources[i][n])) + ' ';
        			}
        			source = source+'></source>'
        			console.log(source);
        			$(video).append($(source));
        		}
        	}
        }
        this.append($(video));
	};
	
	$.fn.video.defaults  = {
		id:'',
 		poster:'http://video-js.zencoder.com/oceans-clip.png',
 		width:'600',
 		height:'400',
 		preload:'',
 		sources:[]
	};*/
	
//	$('#example_video_1').attr('poster','');
//	$('#VideoSource').attr('src','http://cdn-3.golivetv.tv/AD/tclzhuanshu/TCL_HD_Bcopy_0223.mp4');
	
	
//	<video id="example_video_1" class="video-js vjs-default-skin" controls preload="none" width="640" height="264"
//	      poster="http://video-js.zencoder.com/oceans-clip.png"
//	      data-setup="{}">
//	    <source id="VideoSource" src="http://cdn-3.golivetv.tv/AD/tclzhuanshu/qifengle30s.mp4" type='video/mp4' />
//	    <source src="http://video-js.zencoder.com/oceans-clip.webm" type='video/webm' />
//	    <source src="http://video-js.zencoder.com/oceans-clip.ogv" type='video/ogg' />
//	    <!-- <track kind="captions" src="demo.captions.vtt" srclang="en" label="English"> </track> -->
//	    <!-- Tracks need an ending tag thanks to IE9 -->
//	    <!-- <track kind="subtitles" src="demo.captions.vtt" srclang="en" label="English"></track> -->
//	    <!-- Tracks need an ending tag thanks to IE9 -->
//	</video>
	
	GoLive.Form = new Object();
	GoLive.Form.validate = _$validateForm;
	
	function _$validateForm(formId,rules){
		var val =  $('#'+formId+'').validate({
		    focusCleanup:true,focusInvalid:false,
		    errorClass: "validation-unchecked",
		    validClass: "validation-checked",
		    errorElement: "span",
		    errorPlacement:function(error,element){
			  var errorText = $.trim(error.html());
			  element.attr("title",errorText);
			  var tooltips = element.tooltip({track: true});
			  element.addClass("validation-unchecked");
		    },
		    success: function(label) {
		    },
		    rules:rules,
		    messages:{
		    	number: "请输入整数",
		    	required: "这是必填项"
		    }
		  });
		 var isVlid =val.form();
		 return isVlid;
	}

})(jQuery);


(function($){
	var __sto = setTimeout;    
	window.setTimeout = function(callback,timeout,param){    
	    var args = Array.prototype.slice.call(arguments,2);    
	    var _cb = function(){  
	    	if(callback)
	    		callback.apply(null,args);    
	    }    
	    __sto(_cb,timeout);    
	};   
	var __sti = setInterval;
	window.setInterval = function(callback,timeout,param){    
	    var args = Array.prototype.slice.call(arguments,2);    
	    var _cb = function(){  
	    	if(callback)
	    		callback.apply(null,args);    
	    }    
	    return __sti(_cb,timeout);    
	};
	
    $.fn.hoverDelay = function(options){
        var defaults = {
            hoverDuring: 1,
            outDuring: 1,
            hoverEvent: function(self){
                $.noop();
            },
            outEvent: function(self){
                $.noop();    
            }
        };
        var sets = $.extend(defaults,options || {});
        var hoverTimer, outTimer;
        return $(this).each(function(){
            $(this).hover(function(){
                clearTimeout(outTimer);
                hoverTimer = setTimeout(sets.hoverEvent, sets.hoverDuring,this);
            },function(){
                clearTimeout(hoverTimer);
                outTimer = setTimeout(sets.outEvent, sets.outDuring,this);
            });    
        });
        
    };
})(jQuery);


///////////////////////////////////////////////////////////////////////js extends /////////////////////////////////////////////////////////////
/**
 * //对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
 */
Date.prototype.format = function (fmt) { //author: meizz 
 var o = {
     "M+": this.getMonth() + 1, //月份 
     "d+": this.getDate(), //日 
     "h+": this.getHours(), //小时 
     "m+": this.getMinutes(), //分 
     "s+": this.getSeconds(), //秒 
     "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
     "S": this.getMilliseconds() //毫秒 
 };
 if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
 for (var k in o)
 if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
 return fmt;
};
/**
 * 判断日期是否在两个日期之间
 * 2015年4月15日下午3:14:47
 *
 * Boolean
 * @param startDate
 * @param endDate
 * @returns {Boolean}
 */
Date.prototype.isBetween = function (startDate,endDate) { //author: meizz 
	if(this>=startDate && this<=endDate)
		return true;
	return false;
};
/**
 * 日期字符串转化为日期
 */
String.prototype.toDate = function() {
	var temp = this.toString();
	temp = temp.replace(/-/g, "/");
	var date = new Date(Date.parse(temp));
//	console.log(date);
	return date;
};

/**
 * 日期字符串比较
 * 测试方法:
    console.log('2016-01-01 19:08:09'.toDate());
	var startDate = '2016-03-01 19:08:09'.toDate().format("yyyy-MM-dd hh:mm:ss");
	var endDate = '2016-01-01 19:08:09'.toDate().format("yyyy-MM-dd hh:mm:ss");
	console.log(startDate.isPassedDate(endDate));
 * 2015年4月15日上午10:42:35
 *
 * Boolean
 * @param compareDateStr
 * 			被比较字符串
 * @returns {Boolean}
 */
String.prototype.isPassedDate = function(compareDateStr) {
	// alert(startDate "===" endDate);
	var startDate = this;
	var endDate = compareDateStr;
	if (!startDate || !endDate) {
		return false;
	}
	if (startDate.length > 0 && endDate.length > 0) {
		var startDateTemp = startDate.split(" ");
		var endDateTemp = endDate.split(" ");
		var arrStartDate = startDateTemp[0].split("-");
		var arrEndDate = endDateTemp[0].split("-");
		var arrStartTime = startDateTemp[1] ? startDateTemp[1].split(":") : [];
		var arrEndTime = endDateTemp[1] ? endDateTemp[1].split(":") : [];
		var allStartDate = new Date(arrStartDate[0], arrStartDate[1],
				arrStartDate[2], arrStartTime[0] || "00", arrStartTime[1]
						|| "00", arrStartTime[2] || "00");
		var allEndDate = new Date(arrEndDate[0], arrEndDate[1], arrEndDate[2],
				arrEndTime[0] || "00", arrEndTime[1] || "00", arrEndTime[2]
						|| "00");
		// console.log("startDate:
		// "+arrStartDate[0],arrStartDate[1],arrStartDate[2],arrStartTime[0] ||
		// "00",arrStartTime[1] || "00",arrStartTime[2] || "00");
		// console.log("endDate:
		// "+arrEndDate[0],arrEndDate[1],arrEndDate[2],arrEndTime[0] ||
		// "00",arrEndTime[1] || "00",arrEndTime[2] || "00");
		if (allStartDate.getTime() > allEndDate.getTime()) {
			return true;
		}
	}
	return false;
};  

/**
 *  占位符可以为多个  
	alert("http://{0}/{1}".format("www.songyanjun.net", "index.html"));  
	与上同理  
	alert("请输入{0},输完后再按存盘按钮".format("姓名")); 
	alert("请输入{name},输完后再按存盘按钮".format({name: "姓名"}));   
 * 
 * 2015年4月15日上午11:23:30
 * String
 * @returns {String}
 */
String.prototype.format = function() {
	if (arguments.length == 0)
		return this;
	var obj = arguments[0];
	if(typeof obj == "object"){
		s = this;
		for(var key in obj){
			s = s.replace(new RegExp("\\{" + key + "\\}", "g"), obj[key]);
		}
		return s;
	}else{
		for (var s = this, i = 0; i < arguments.length; i++)
			s = s.replace(new RegExp("\\{" + i + "\\}", "g"), arguments[i]);
		return s;
	}
};
/**
 * 删除数组元素
 * 2015年4月16日上午11:23:30
 * Array
 * @param findIndexFunc
 * 			函数,用于返回被删除元素在数组中的索引位置 
 * @returns {String}
 */
Array.prototype.remove = function(findIndexFunc) {
	var n = findIndexFunc();
	if(n>=0){
		this.splice(n,1);
	}
};
/**
 * 查找数组元素
 * 2015年4月16日上午11:23:30
 * Array
 * @param fieldName
 * 			被查找数组元素中元素的属性名 
 * @param fieldValue
 * 			被查找数组元素中元素的属性的匹配值 
 * @returns {Object}
 */
Array.prototype.findObject = function(fieldName,fieldValue) {
	for(var i=0;i<this.length;i++){
		if(this[i][fieldName] == fieldValue){
			return this[i];
		}
	}
	return null;
};
/**
 * 查找数组元素
 * 2015年4月16日上午11:23:30
 * Array
 * @param fieldName
 * 			被查找数组元素中元素的属性名 
 * @param fieldValue
 * 			被查找数组元素中元素的属性的匹配值 
 * @returns {Array} 
 */
Array.prototype.findAll = function(fieldName,fieldValue) {
	var array = [];
	for(var i=0;i<this.length;i++){
		if(this[i][fieldName] == fieldValue){
			array.push(this[i]);
		}
	}
	return array;
};
/**
 * 去重复值
 */
Array.prototype.unique = function() {
    var res = [], hash = {};
    for(var i=0, elem; (elem = this[i]) != null; i++)  {
        if (!hash[elem])
        {
            res.push(elem);
            hash[elem] = true;
        }
    }
    return res;
}
// /////////////////////////////////////////////////////////////////////js extends /////////////////////////////////////////////////////////////


/*
 * MAP对象，实现MAP功能
 *
 * 接口：
 * size()     获取MAP元素个数
 * isEmpty()    判断MAP是否为空
 * clear()     删除MAP所有元素
 * put(key, value)   向MAP中增加元素（key, value)
 * remove(key)    删除指定KEY的元素，成功返回True，失败返回False
 * get(key)    获取指定KEY的元素值VALUE，失败返回NULL
 * element(index)   获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
 * containsKey(key)  判断MAP中是否含有指定KEY的元素
 * containsValue(value) 判断MAP中是否含有指定VALUE的元素
 * values()    获取MAP中所有VALUE的数组（ARRAY）
 * keys()     获取MAP中所有KEY的数组（ARRAY）
 *
 * 例子：
 * var map = new Map();
 *
 * map.put("key", "value");
 * var val = map.get("key")
 * ……
 *
 */

/**
 * map对象
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
if(!window._$MAP){
	window._$MAP = new _Map();
}
GoLive.Map =  window._$MAP;

/**
 * Map集合类。可以使用new Map()初始化 <br>
 * @class   JSUtil.Map
 * @author  谢声锋
 * @since   2013-1-07
 * 
 */
function _Map() {
	
	this.elements = new Array();
	/**
	 * 获取MAP元素个数
	 * @function {int} size
	 * @returns this map's element size
	 */
	this.size = function() {
		return this.elements.length;
	};
	/**
	 * 判断MAP是否为空
	 * @function {int} isEmpty
	 * @returns true or false
	 */
	this.isEmpty = function() {
		return (this.elements.length < 1);
	};
	/**
	 * 删除MAP所有元素
	 * @function {void} clear
	 */
	this.clear = function() {
		this.elements = new Array();
	};
	/**
	 * 向MAP中增加元素（key, value),如果已经有key元素， 则用新的替换旧的元素
	 * @function {void} put
	 * @param	key				元素的关键字
	 * @param	value			元素的值
	 */
	this.put = function(_key, _value) {
		var flag = false;
		for (i = 0; i < this.elements.length; i++) {
			if (this.elements[i].key == _key) {
				this.elements[i].value = _value;
				flag = true
				break;
			}
		}
		if(!flag)
			this.elements.push( {
				key : _key,
				value : _value
			});
	};
	/**
	 * 删除指定KEY的元素，成功返回true，失败返回False
	 * @function {boolean} remove
	 * @param	key				元素的关键字
	 * @returns true or false
	 */
	this.remove = function(_key) {
		var bln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					this.elements.splice(i, 1);
					return true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	};
	/**
	 * 获取指定KEY的元素值VALUE，失败返回NULL
	 * @function {void} get
	 * @param	key				元素的关键字
	 * @returns object
	 */
	this.get = function(_key) {
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					return this.elements[i].value;
				}
			}
		} catch (e) {
			return null;
		}
		return "";
	};
	/**
	 * 获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
	 * @function {void} element
	 * @param	index				元素所在的位置
	 * @returns object or null
	 */
	this.element = function(_index) {
		if (_index < 0 || _index >= this.elements.length) {
			return null;
		}
		return this.elements[_index];
	};
	
	/**
	 * 判断MAP中是否含有指定KEY的元素
	 * @function {boolean} containsKey
	 * @param	key				元素所在的位置
	 * @returns true or false
	 */
	this.containsKey = function(_key) {
		var bln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					bln = true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	};
	
	/**
	 * 判断MAP中是否含有指定VALUE的元素
	 * @function {boolean} containsValue
	 * @param	value	元素所在的位置
	 * @returns true or false
	 */
	this.containsValue = function(_value) {
		var bln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].value == _value) {
					bln = true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	};
	
	/**
	 * 获取MAP中所有VALUE的数组（ARRAY）
	 * @function {[]} values
	 * @returns array
	 */
	this.values = function() {
		var arr = new Array();
		for (i = 0; i < this.elements.length; i++) {
			arr.push(this.elements[i].value);
		}
		return arr;
	};
	
	/**
	 * 获取MAP中所有KEY的数组（ARRAY）
	 * @function {[]} keys
	 * @returns array
	 */
	this.keys = function() {
		var arr = new Array();
		for (i = 0; i < this.elements.length; i++) {
			arr.push(this.elements[i].key);
		}
		return arr;
	};
}


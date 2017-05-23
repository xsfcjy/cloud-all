<link rel="stylesheet" type="text/css" href="${ctx}${easyUIRootPath}/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}${easyUIRootPath}/themes/icon.css">
<script type="text/javascript" src="${ctx}${easyUIRootPath}/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}${easyUIRootPath}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}${easyUIRootPath}/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">
	.report-loading-none{
		display: none;
	}
	.report-loading-display{
		display: block;
		position: absolute;
		left: 500px;
		top: 300px;
	}
</style>
<SCRIPT type="text/javascript">
		
	var _reportEntityMap = ${reportEntityMap};
	
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
	$(document).ready(function () {
		var formContentDive = $('#formContentDivId');
		formContentDive.append($('#${queryFormId}'));
		$("#exportFile").change(reportSelected);
	});
	function doHtmlReport(exportDataType){
		var paginationObj = $('#pp').pagination('options','pageNumber');	
		var pageNumber = paginationObj.pageNumber;
		var pageSize = paginationObj.pageSize;
		Report.doReport({
			queryFormId:'${queryFormId}',
			contentId:'contentId',
			paginationId:'pp',
			centextPath:'${ctx}',
			data:{
				isIgnorePage:"true",
				pageSize:pageSize,
				pageNumber:pageNumber,
				exportDataType:exportDataType,
				exportFileType:'html',
				register:'${reportDealer}'
			}
		});
	}
	function doExportReport(exportDataType){
		var paginationObj = $('#pp').pagination('options','pageNumber');
		var pageNumber = paginationObj.pageNumber;
		var pageSize = paginationObj.pageSize;
		Report.doReport({
			queryFormId:'${queryFormId}',
			contentId:'contentId',
			paginationId:'pp',
			centextPath:'${ctx}',
			data:{
				isIgnorePage:"true",
				pageSize:pageSize,
				pageNumber:pageNumber,
				exportDataType:exportDataType,
				exportFileType:$('#exportType').val(),
				register:'${reportDealer}'
			}
		});
	}
	function onSelectPage(pageNumber, pageSize){
		doHtmlReport('single');
	}
	Report = new Object();
	Report.doReport = _$doReport;
	function _$doReport(config){
		var exportFile = $('#exportFile').val();
		if(!exportFile){
			_$showMessage({timeout:2000,icon:'info',msg:'请选择报表！'});
			return ;
		}
		var buttonListChildren = $('#buttonList').children().clone( true );
		if(config['queryFormId']){
			var data = DomUtil.getJSONObjectFromForm(config['queryFormId']);
			if(config['data'])
				$.extend(config['data'],data);
			else 
				config['data'] = data;
		}
		config['data']['paramMap.formParameter'] = encodeURI(_$obj2str(data));
		config['dataType'] =  "text";
		config.url = config.centextPath+"${url}";
		config['async']=false;
		config['data']['paramMap.exportFileType']= config['data']['exportFileType'];
		config['data']['paramMap.isIgnorePage']= config['data']['isIgnorePage'];
		if(config['data']['exportFileType'] && config['data']['exportFileType']=='html'){
			config['dataType'] =  "json";
			_$reportAjax(config,editHtmlSuccessFunc,null);
		}else{
			config['paramObj'] = config['data'];
			_$reportlocation(config);
		}
		if($('#buttonList').children().length==0){
			$('#buttonList').empty();
			$('#buttonList').append(buttonListChildren);
		}
		function editHtmlSuccessFunc(config,response){
			$('#'+config.paginationId).pagination({
				total: response.total
			});
			var re1 = /<body[\S|\s| |\n|\r|\t|\f|\cX|\b|\v|\0|\w|\W|\d|\D]*<\/body>/g;  
			var bodyStartRe = /<body[\S|\s| |\n|\r|\t|\f|\cX|\b|\v|\0|\w|\W|\d|\D]*>\s/;
			var ddsdf = re1.exec(response.content)[0];
			var contents = ddsdf.substring(ddsdf.indexOf('>')+1,ddsdf.indexOf('</body>'));
			$("#"+config['contentId']).html('');
			contents = contents.replace(/<![if[\s|\S]*endif]>/,"");
			var contentDom = $(contents);
			var contentDeleteTds = contentDom.find('td[width="50%"]');
			var contentTable =  contentDom.find('table');
			contentDeleteTds.remove();
			contentTable.css({width:'100%'});
			$("#"+config['contentId']).append(contentDom);
			$('#loadingContentId').removeClass('report-loading-display');
			$('#loadingContentId').addClass('report-loading-none');
		}
	}
	
	function _$showMessage(config){
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
		$('#loadingContentId').removeClass('report-loading-display');
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
	
	function _$reportAjax(config,successFunc,errorFunc){
		$('#loadingContentId').removeClass('report-loading-none');
		$('#loadingContentId').addClass('report-loading-display');
		var result ; 
		var ajaxObj = {
		  cache: false,
		  dataType:  "json",
		  success: function(response){
			  var result =  successFunc(config,response);
		  },
		  error:function(response){
			  _$showMessage({timeout:2000,icon:'info',msg:'服务器内部处理出错！'});
		  }
		};
		
		if(config['data'].pageNumber == 0){
			config['data'].pageNumber = 1;
		}
		
		$.extend(ajaxObj,config);
		$.ajax(ajaxObj);
		return result;
	}
	
	DomUtil = new Object();
	DomUtil.getJSONObjectFromForm = _getJSONObjectFromForm;
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
	
	function _$reportlocation(obj){
		_$winReportPostHref(obj['url'],obj['paramObj']);
	}
	function _$winReportPostHref(url,params){
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
		temp.submit(function(e){
		    var postData = $(this).serializeArray();
		    var formURL = $(this).attr("action");
		    $.ajax({
		        url : formURL,
		        type: "POST",
		        data : postData,
		        success:function(data, textStatus, jqXHR){
		        	$('#loadingContentId').removeClass('report-loading-display');
		    		$('#loadingContentId').addClass('report-loading-none');
		        },
		        error: function(jqXHR, textStatus, errorThrown) {
		        },
		        complete: function(jqXHR, textStatus){
		        }
		    });
		    e.preventDefault();
		    e.unbind(); 
		});
		
	}
	
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
	             r = "{" + r.join() + "}";
	         } else {
	             for (var i = 0; i < o.length; i++)
	                 r.push(_$obj2str(o[i]));
	             r = "[" + r.join() + "]";
	         }
	         return r;
	     }
	     return o.toString().replace(/\"\:/g, '":""');
	 }
	
	function reportSelected(e,d){
		var value = $(this).children('option:selected').val(); 
		if(value){
			var reportParameters = _reportEntityMap[value]['parameters'];
			var formId = '#queryForm';
			addFormElement(formId,reportParameters);
		}
	}
	
	function addFormElement(formId,reportParameters){
		$(formId).empty();
		if(reportParameters && reportParameters.length > 0){
			console.log(reportParameters);
			for(var i=0;i<reportParameters.length;i++){
				var parameter = reportParameters[i];
				appendParameter(formId,parameter);
			}
		}
		
	}
	
	function appendParameter(formId,parameter){
		var elementType = parameter['elementType'];
		if(elementType == 'input'){
			appendInputParameter(formId,parameter);
		}else if(elementType == 'select'){
			appendSelectParameter(formId,parameter);
		}
		
	}

	function appendInputParameter(formId,parameter){
		var name = parameter['name'];
		var text = parameter['title'];
		text = tohanzi(text);
		var input = '<div>'+
					'<label for="{name}">{title}：</label>'+
					'<input id="{name}" name="{name}" class="easyui-textbox" '+'data-options="iconCls:\'icon-search\'" style="width:300px">'+
					'</div>';
		$(formId).append(input.format({name:name,title:text}));
		$('#'+name).textbox({
			width: 300,
			label: text
		});
	}
	function appendSelectParameter(formId,parameter){
		var name = parameter['name'];
		var text = parameter['title'];
		text = tohanzi(text);
		var input = '<div>'+
					'<label for="{name}">{title}：</label>'+
					'<input id="{name}" name="{name}" class="easyui-textbox" '+'data-options="iconCls:\'icon-search\'" style="width:300px">'+
					'</div>';
		$(formId).append(input.format({name:name,title:text}));
		$('#'+name).textbox({
			width: 300,
			label: text
		});
	}
	function tohanzi(data){
	    if(data == '') return '请输入十六进制unicode';
	    data = data.split("u");
	    var str ='';
	    for(var i=0;i<data.length;i++)
	    {
	        str+=String.fromCharCode(parseInt(data[i],16).toString(10));
	    }
	    return str;
	}
</SCRIPT>
/**
 * StrutsTagUtil
 * 用于Struts标签的辅助js
 * @param {Object} obj
 * @auth xieshengfeng
 * @since 2014-01-25
 */
var StrutsTagUtil = new Object();
StrutsTagUtil.Table = new Object();
StrutsTagUtil.Table.mappingColumn = _mappingColumn;

StrutsTagUtil.SelectInterface = new Object();
StrutsTagUtil.SelectInterface.decorate = new Function(); 

StrutsTagUtil.Form = new Object();
StrutsTagUtil.Form.mappingSelect = _mappingSelect;
/**
 * @param obj
 * 
 * {
 * 	 tableId:'tableId',
 *   
 *   dataSet:[{
 *      valueField:'value',
 *      displayField:'text',
 *   	colIndex:2,
 *      mappingDataList:[{
 *      	value:'id1',
 *      	text:'主键1'
 *      },{
 *      	value:'id1',
 *      	text:'主键1'
 *      }]
 *   },{
 *   	valueField:'value',
 *      displayField:'text',
 *   	colIndex:3,
 *      mappingDataList:[{
 *      	value:'id1',
 *      	text:'主键1'
 *      },{
 *      	value:'id1',
 *      	text:'主键1'
 *      }]
 *   }]
 * }
 * 
 */
Array.prototype.contains = function(obj) {
    var i = this.length;
    while (i--) {
        if (this[i] === obj) {
            return true;
        }
    }
    return false;
}
function _mappingColumn(obj){
//	console.log(obj);
	var rows = $("#"+obj.tableId+" tr");
	var dataSet = obj['dataSet'];
	var usingCols = obj['usingCols'];
	for(var i=1;i<rows.length;i++){
		var tds = $(rows[i]).children("td");
		for(var j=0;j<dataSet.length;j++){
			//找到colIndex
			var colIndex;
			for(var v=0;v<usingCols.length;v++){
				colIndex = dataSet[j]['colNameIndexMapping'][usingCols[v]];
				if(colIndex)
					break;
			}
			var td = tds[colIndex-1];
			var mappingDataList = dataSet[j]['mappingDataList'];
			var displayField = dataSet[j]['displayField'];
			var valueField = dataSet[j]['valueField'];
			var content = $.trim($(td).html());
//			console.log($(td));
			content = content.split(",");
			var length = content.length;
			var count = 0;
			var multiText = "";
			for(var h=0;h<mappingDataList.length;h++){
				var mappingObj = mappingDataList[h];
//				console.log(mappingObj[valueField] == content);
				if(length>1){
					if(content.contains(mappingObj[valueField])){
						count ++;
						if(h==0){
							multiText = mappingObj[displayField];
						}else{
							multiText = multiText + " | " + mappingObj[displayField];
						}
						var hidden = '<span style="display:none;">'+content.join(",")+'</span>';
					}
					if(length == count){
//						console.log($(td));
						$(td).html(hidden + multiText);
						break;
					}
				}else{
//					console.log(content[0]+" - "+mappingObj[displayField]+": "+(mappingObj[valueField] == content[0]));
					if(!mappingObj[valueField]){
						$(td).html("");
					}else if(mappingObj[valueField] == content[0]){
						var hidden = '<span style="display:none;">'+mappingObj[valueField]+'</span>';
						$(td).html(hidden+mappingObj[displayField]);
						break;
					}
				}
			}
		}
	}
	/*var rows = $("#"+obj.tableId+" tr");
	var dataSet = obj['dataSet'];
	for(var i=1;i<rows.length;i++){
		var tds = $(rows[i]).children("td");
		for(var j=0;j<dataSet.length;j++){
			var colIndex = dataSet[j]['colIndex'];
			var td = tds[colIndex-1];
			var mappingDataList = dataSet[j]['mappingDataList'];
			var displayField = dataSet[j]['displayField'];
			var valueField = dataSet[j]['valueField'];
			for(var h=0;h<mappingDataList.length;h++){
				var content = $.trim($(td).html());
				var mappingObj = mappingDataList[h];
				if(mappingObj[valueField] == content){
					var hidden = '<span style="display:none;">'+mappingObj[valueField]+'</span>';
					$(td).html(hidden+mappingObj[displayField]);
					break;
				}
			}
		}
	}*/
}
/**
 * @param obj
 * 	obj = dataSet:[{
 * 		hasNull:false,
 * 		selectId:['id1','id2'],
 *      valueField:'value',
 *      displayField:'text',
 *   	name:'user.dd',
 * 		properties:{},
 * 		valuePropertyMapping:{},
 *      mappingDataList:[{
 *      	value:'id1',
 *      	text:'主键1'
 *      },{
 *      	value:'id1',
 *      	text:'主键1'
 *      }]
 *   },{
 * 		hasNull:true,
 * 		selectId:['id1','id2'],
 *   	valueField:'value',
 *      displayField:'text',
 *   	name:'user.sex',
 *      mappingDataList:[{
 *      	value:'id1',
 *      	text:'主键1'
 *      },{
 *      	value:'id1',
 *      	text:'主键1'
 *      }]
 *   }]
 * 
 */

function _mappingSelect(obj){
	var dataSet = obj['dataSet'];
	var formId = obj['formId'];
	for(var j=0;j<dataSet.length;j++){
//		var select = $("select[name='"+dataSet[j]['name']+"']");
		var valuePropertyMapping = dataSet[j]['valuePropertyMapping'];
		var properties =  dataSet[j]['properties'];
		//处理每个不同的select对应的id下拉框
		for(var p in valuePropertyMapping){
//			Cmhit.Log.log(valuePropertyMapping);
			var select = $("select[id='"+p+"']");
			var parentFormId = select.parents('form').attr('id');
			if(formId && (formId != parentFormId))
				select = null;
			//下拉的类型
			var selectType = $("select[id='"+p+"']").attr('selectType');
			//是否可以多选
			var isMulty = $("select[id='"+p+"']").attr('isMulty');
			//console.log(td);
			var property = properties[p];
			if(select){
				//如果是ie则不用chosen用jquery.ui.autocomplete
				//console.log(Cmhit.Browser.getType());
//				alert('selectType: '+selectType);
				if(selectType=="autocomplete"){
					var hiddenInput = '<input type="hidden"  name="'+select.attr('name')+'" value="'+valuePropertyMapping[p]+'"/>';
					var td = $("select[id='"+p+"']").parent();
					
					var replaceInput = $('<input type="text" style="width:260px" id="'+p+'"/>');
					replaceInput.css('width',$(select).css('width'));
					//select.remove();
	//				width: 130px  
					
					select.after(replaceInput);
					replaceInput.after(hiddenInput);
					var mappingDataList = dataSet[j]['mappingDataList'];
					var displayField = dataSet[j]['displayField'];
					var valueField = dataSet[j]['valueField'];
					//Cmhit.Log.log(select.attr('name'));
					obj = {
						id:p,
						hiddenInputName:select.attr('name'),
						dataset:mappingDataList,
						displayField:displayField,
						valueField:valueField
					};
					select.remove();
					_$trigger(obj);
				}
				//如果不是ie则用chosen
				else if(selectType=="chosen"){
					var mappingDataList = dataSet[j]['mappingDataList'];
					var displayField = dataSet[j]['displayField'];
					var valueField = dataSet[j]['valueField'];
					var hasNull = dataSet[j]['hasNull'];
					if(hasNull)
						select.append("<option value=''>"+"----------"+"</option>");
					for(var h=0;h<mappingDataList.length;h++){
						var mappingObj = mappingDataList[h];
						var text = mappingObj[displayField];
						var value = mappingObj[valueField];
						select.append("<option value='"+value+"'>"+text+"</option>");
					}
	//				设置初始值
					if(valuePropertyMapping[p]){
						try{
							$("select[id='"+p+"']"+" option[value='"+valuePropertyMapping[p]+"']").attr("selected","selected");
						}catch(msg){
							;
						}
					}
					_setMappingSelectProperty(select,property);
				}else if(selectType=="multiselect"){
					var mappingDataList = dataSet[j]['mappingDataList'];
					var displayField = dataSet[j]['displayField'];
					var valueField = dataSet[j]['valueField'];
					var hasNull = dataSet[j]['hasNull'];
					if(hasNull)
						select.append("<option value=''>"+"----------"+"</option>");
					for(var h=0;h<mappingDataList.length;h++){
						var mappingObj = mappingDataList[h];
						var text = mappingObj[displayField];
						var value = mappingObj[valueField];
						select.append("<option value='"+value+"'>"+text+"</option>");
					}
	//				设置初始值
					if(valuePropertyMapping[p]){
						try{
							$("select[id='"+p+"']"+" option[value='"+valuePropertyMapping[p]+"']").attr("selected","selected");
						}catch(msg){
							;
						}
					}
					if(isMulty=="true"){
						$(select).multiselect({
							header: "请选择",
							noneSelectedText: "请选择"
						}).multiselectfilter();
					}else{
						select.multiselect({
							multiple: false,
							header: "请选择",
							noneSelectedText: "请选择",
							selectedList: 1
						});
					}
				}
				//原始的select
				else{
					var mappingDataList = dataSet[j]['mappingDataList'];
					var displayField = dataSet[j]['displayField'];
					var valueField = dataSet[j]['valueField'];
					var hasNull = dataSet[j]['hasNull'];
					if(select.attr('notEmpty') == 'true')
						;
					else{
						if(hasNull){
							select.append("<option value=''>"+"----------"+"</option>");
						}
					}
//					console.log(mappingDataList);
					for(var h=0;h<mappingDataList.length;h++){
						var mappingObj = mappingDataList[h];
						var text = mappingObj[displayField];
						var value = mappingObj[valueField];
						select.append("<option value='"+value+"'>"+text+"</option>");
					}
//					console.log(select);
	//				设置初始值
					if(valuePropertyMapping[p]){
						try{
							$("select[id='"+p+"']"+" option[value='"+valuePropertyMapping[p]+"']").attr("selected","selected");
						}catch(msg){
							;
						}
					}
				}
			}
			
		}
	}
}
StrutsTagUtil.SelectInterface.decorate = function(selectObj,property){
	$(selectObj).combobox();
}
function _setMappingSelectProperty(select,property){
	if(property){
		var pros = property.split(",");
		for(var i=0;i<pros.length;i++){
			var pro = pros[i].split(":");
			if(pro[0]=="class"){
				select.attr("class",pro[1]);
				var subPro = pro[1].split(" ");
				for(var h=0;h<subPro.length;h++){
					var selector = '.'+subPro[h];
					//设置chosen样式的下拉框功能
					if( $(selector) && $(selector).chosen){
						var config = new Object();
						config[selector] = new Object();
						/*DD = {
					      '.chosen-select'           : {},
					      '.chosen-select-deselect'  : {allow_single_deselect:true},
					      '.chosen-select-no-single' : {disable_search_threshold:10},
					      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
					      '.chosen-select-width'     : {width:"95%"}
					    }*/
						 $(selector).chosen(selector);
						 break;
					}
				}
			}
		}
	}
	
}
StrutsTagUtil.Button = new Object();
StrutsTagUtil.Button.controlBtn = _$controlBtn;
/**
 * 
 * @param {Object} btnControlObj
 * {
 * 	'btnId1':'disabled', //不可用(可见)
 * 	'btnId2':'hidden'	 //隐藏
 * }
 */
function _$controlBtn(btnControlObj){
//	$(':button').each(function(index,button){
//		$(button).hide();
//	});
	for(var p in btnControlObj){
		if(btnControlObj[p]=='disabled'){
			if($("#"+p))
				$("#"+p).attr("disabled", true);
		}else if(btnControlObj[p]=='hidden'){
			if($("#"+p))
				$("#"+p).hide();
		}else if(btnControlObj[p]=='readonly'){
			if($("#"+p))
				$("#"+p).attr("readonly","readonly");
		}else if(btnControlObj[p]=='remove'){
			$("#"+p).remove();
		}
	}
}













////////////////////////////////////////////////////////////////////////////////////////////
function _$trigger(obj){
	//console.log("trigger 1");
	var options = {
		//保存一份原数据引用
		obj:obj,
		// 获得焦点
		focus: function() {
			// prevent value inserted on focus
			return false;
		},
		//创建时要把已经有值的情况下从数据中把显示找出来
		create:_$autocompleteCreateFunc,
		// 从autocomplete弹出菜单选择一个值时，加到输入框最后，并以逗号分隔
		select: _$autocompleteSelectFunc
	};
	// 多个值，本地数组
	//Cmhit.Log.log($('#'+obj['id']));
	$.extend( $.ui.autocomplete, {
		filter: function(array, term) {
			var matcher = new RegExp( $.ui.autocomplete.escapeRegex(term), "i" );
			return $.grep( array, function(value) {
				return matcher.test(value.label || value.value || value );
			});
		}
	});
	$('#'+obj['id']).bind('keydown', _$autocompleteKeyDownFunc)
	.autocomplete($.extend(options, {
		minLength: 1,
		source: function(request, response) {
			// delegate back to autocomplete, but extract the last term
			response($.ui.autocomplete.filter(
				obj['dataset'], _$autocompleteExtractLastFunc(request.term))
			);
		}
	}));
	//console.log('trigger 2');
}
function _$autocompleteSelectFunc(event, ui){
	//console.log(ui.item.label);
	//console.log(ui.item.value);
	//var labels = split(this.value);
	//Cmhit.Log.log('autocompleteSelectFunc');
	var obj = $( "#"+this.id).autocomplete( "option",'obj');
	var hiddenInputName = obj['hiddenInputName'];
	var isMulty =  $("#"+obj['id']).attr("isMulty");
	if(isMulty == "true"){
		//获取隐藏域的值
		var value = $("input[name='"+hiddenInputName+"']")[0].value;

		//如果值已经被选择进的时候不选择
		if(value && value.indexOf(ui.item[obj['valueField']])>=0){
			return false;
		}
		var values = _$autocompleteSplitFunc(value);
		values.push(ui.item[obj['valueField']]);
		//获取当前控件的显示值
		var terms = _$autocompleteSplitFunc(this.value);
		terms.pop;
		terms[terms.length-1]=ui.item[obj['displayField']];
		// 把当前选择的显示放入terms
		//terms.push(ui.item.label);
		// add placeholder to get the comma-and-space at the end
		terms.push("");
		
		//隐藏域重新赋值
		 $("input[name="+hiddenInputName+"]")[0].value = values.join(",");

		this.value = terms.join(",");
		//console.log(this.value);
		//console.log($("input[name="+this.id+"]")[0].value);
		return false;
	}else{
		this.value = ui.item[obj['displayField']];
		$("input[name='"+hiddenInputName+"']")[0].value = ui.item[obj['valueField']];
		return false;
	}
	
}
function _$autocompleteCreateFunc() {
//	Cmhit.Log.log('autocompleteCreateFunc');
	var obj = $( "#"+this.id).autocomplete( "option",'obj');
	var dataset = obj['dataset'];
	var displayField = obj['displayField'];
	var valueField = obj['valueField'];
	//console.log(obj);
//	console.log(valueField);
	//隐藏域的值
	var hiddenInputV = $("input[name='"+obj["hiddenInputName"]+"']")[0].value;
	var isMulty =  $("#"+this.id).attr("isMulty");
	//如果是多选
	if(isMulty == "true"){
		//console.log(hiddenInputV);
		//查找原来已经存在的值
		var values = hiddenInputV.split(",");
		var labels = new Array();
		for(var j=0;j<values.length;j++){
			var value = values[j];
			for(var i=0;i<dataset.length;i++){
				var valObj = dataset[i];
				if((''+value) == (''+valObj[valueField])){
					labels.pop();
					labels.push(valObj[displayField]);
					labels.push('');
					break;
				}
			}
		}
		this.value = labels.join(",");
	}else{
		//console.log(dataset);
		for(var i=0;i<dataset.length;i++){
			var obj = dataset[i];
			if((''+hiddenInputV) == (''+obj[valueField])){
				this.value = obj[displayField];
				break;
			}
		}
	}
	//console.log(labels);		
}


	// 按逗号分隔多个值
function _$autocompleteSplitFunc(val) {
	return val.split(/,\s*/);
}
// 提取输入的最后一个值
function _$autocompleteExtractLastFunc(term) {
	return _$autocompleteSplitFunc(term).pop();
}
// 按Tab键时，取消为输入框设置value
function _$autocompleteKeyDownFunc(event) {
	
	if (event.keyCode === $.ui.keyCode.TAB &&
			$(this).data("autocomplete").menu.active) {
		event.preventDefault();
	}
}
/**
 * 渠道\终端类型\Mac三级联动
 * @param province
 * @param city
 * @param town
 */
//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
function showQZMThree(channelDef , clientTypeDef , macDef) {
	 _$initChannelSelect(channelDef , clientTypeDef , macDef);
	/*$("#loc_province,#loc_city,#loc_town").select2();
	$('#loc_province').change(function() {
		$('#loc_city').empty();
		$('#loc_city').append(title[1]);
		loc.fillOption('loc_city' , '0,'+$('#loc_province').val());
		$('#loc_city').change()
	});
	
	$('#loc_city').change(function() {
		$('#loc_town').empty();
		$('#loc_town').append(title[2]);
		loc.fillOption('loc_town' , '0,' + $('#loc_province').val() + ',' + $('#loc_city').val());
	});
	
	$('#loc_town').change(function() {
		$('input[@name=location_id]').val($(this).val());
	});*/
}

function _$initChannelSelect(channelDef,clientTypeDef,macDef){
//	var url = "${ctx}"+"/"+"componentAjax/"+"ajax.action?register=myfocus.operation";
//	var url = "/"+"componentAjax/"+"ajax.action?register=myfocus.operation";
	//请求数据
	_$ajaxChannelSelect(channelDef,clientTypeDef,macDef);
	$('#'+channelDef.selectId).change(function(value) {
		var selectValue = $(this).children('option:selected').val(); 
	  	$('#'+channelDef.selectId).empty();
	  	$('#'+clientTypeDef.selectId).empty();
	  	$('#'+channelDef.selectId).val("");
	  	$('#'+clientTypeDef.selectId).val("");
		$('#'+macDef.selectId).val("");
		//改变选中的值
		channelDef.value = selectValue;
		clientTypeDef.params.ChannelId = channelDef.value;
		_$ajaxChannelSelect(channelDef,clientTypeDef,macDef);
	});
}
function _$ajaxChannelSelect(channelDef,clientTypeDef,macDef){
	var params = channelDef.params || {};
	params['register'] = channelDef.register;
	$.ajax({
		  url:  channelDef.url,
		  async:true,
		  dataType:"json",
		  data:params,
		  success: function(data){
			  _$selectAddOptions(channelDef,data);
			  _$selectSetSelected(channelDef.selectId,channelDef.value);
			  if(!clientTypeDef.params.ChannelId){
				  clientTypeDef.params.ChannelId = $('#'+channelDef.selectId).val();
			  }
			  _$initClientTypeSelect(clientTypeDef,macDef);
		  }
	});
}
function _$ajaxClientTypeSelect(clientTypeDef,macDef){
	var params = clientTypeDef.params || {};
	params['register'] = clientTypeDef.register;
	//请求数据
	$.ajax({
	  url:  clientTypeDef.url,
	  async:true,
	  data:params,
	  dataType:"json",
	  success: function(data){
		  _$selectAddOptions(clientTypeDef,data);
//		  if(clientTypeDef.value){
		  if(clientTypeDef.isInit){
				clientTypeDef.isInit = false;
		  }else{
			  clientTypeDef.value ="";
		  }
		  _$selectSetSelected(clientTypeDef.selectId,clientTypeDef.value);
//		  }
	  }
	});
}
function _$initClientTypeSelect(clientTypeDef,macDef){
	_$ajaxClientTypeSelect(clientTypeDef,macDef);
	/*$('#'+clientTypeDef.selectId).change(function(value) {
		var selectValue ;
		if(clientTypeDef.isInit){
			clientTypeDef.isInit = false;
			selectValue = $(this).children('option:selected').val(); 
		}else{
			selectValue ="";
		}
//		var selectValue = $(this).children('option:selected').val(); 
	  	$('#'+clientTypeDef.selectId).empty();
		$('#'+macDef.selectId).val("");
		//改变选中的值
		clientTypeDef.value = selectValue;
		_$ajaxClientTypeSelect(clientTypeDef,macDef);
	});*/
}
function _$initMacSelect(macId){
	
}

function _$selectAddOptions(selectDef,data){
	for(var i=0;i<data.length;i++){
		 $("#"+selectDef.selectId).append('<option value="'+data[i][selectDef.valueFieldName]+'">'+data[i][selectDef.textFieldName]+'</option>');
	}
}
function _$selectSetSelected(selectId,value){
	if(value){
		$('#'+selectId+' option[value="'+value+'"]').attr("selected", true);
	}else{
		$('#'+selectId+' option:first').attr("selected", true);
	}
}
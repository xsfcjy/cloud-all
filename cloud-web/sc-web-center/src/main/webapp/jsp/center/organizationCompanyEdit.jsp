<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/common/meta.jsp" %>
<style>
<!--
	.golive-easyui-form-div label, .golive-easyui-form-query label{
		width:<spring:message code="page.edit.label.center.company.width" />
	}
-->
</style>
<script type="text/javascript">
	alert('${centerPath}');
	function submitForm(){
	
		GoLive.EasyUI.Form.submit({
			formId: 'myform',
			restfulId:'id',
			url:'${centerPath}/company',
			onSubmit: function(param){
				return true;
			},
			success: function(data){
		    	GoLive.EasyUI.Message.show({
		    		timeout:1000,
		    		icon:'info',
		    		msg:'<spring:message code="page.datagrid.action.save.success" />'
		    	});
		    	GoLive.EasyUI.Datagrid.reload({
		    		dataGridId: 'dataGrid'
		    	}); 
		    }
		});
	}
</script>
   	<div class="golive-easyui-form-div">
	   	<form id="myform" class="easyui-form" method="post" data-options="novalidate:true" action="">
	   		<input type="hidden" name="id" id="id">
	   		<input name="createUser" id="createUser" type="hidden">
	   		<input name="parentCompanyCode" id="parentCompanyCode" type="hidden">
        	<input name="createTime" id="createTime"  type="hidden">
	        <div>
	            <label for="companyCode"><font color="#990000">*</font><spring:message code="page.center.company.companyCode" />：</label>
	            <input class="easyui-textbox" style="width:200px;" type="text" name="companyCode" data-options="required:true"></input>
	        </div>
	        <div>
	            <label for="companyNameCn"><spring:message code="page.center.company.companyNameCn" />：</label>
	            <input class="easyui-textbox" style="width:200px;" type="text" name="companyNameCn"></input>
	        </div>
	        <div>
	            <label for="isValid" ><font color="#990000">*</font><spring:message code="common.isValid" />：</label>
	            <mytag:select  	dataOptions="required:true,valueField:'value',textField:'text'" 
	            				selectId ="isValidList" selectName="isValid"
	            				className="easyui-combobox"  style="width:200px;" 
				            	dataProvider="commonStateValidationDataProvider" displayField="text" valueField="value"
				            	value="N"
	            ></mytag:select>
	        </div>
	        <div>
	            <label for="remark"><spring:message code="common.description" />：</label>
	            <input class="easyui-textbox" style="width:200px;" type="text" name="remark"></input>
	        </div>
    	</form>
   	</div>
   	<div style="text-align:center;padding:5px;height: 10%;">
    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()"><spring:message code="button.save" /></a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetForm()"><spring:message code="button.resetForm" /></a>
    </div>

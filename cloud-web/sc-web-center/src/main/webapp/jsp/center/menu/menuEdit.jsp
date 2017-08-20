<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/common/meta.jsp" %>
<style>
</style>
<script type="text/javascript">

	function submitMenuForm(){
	
		GoLive.EasyUI.Form.submit({
			formId: 'menuForm',
			restfulId:'menuId',
			url:'${centerPath}/menu',
			onSubmit: function(param){
				return true;
			},
			success: function(data){
		    	GoLive.EasyUI.Message.show({
		    		timeout:1000,
		    		icon:'info',
		    		msg:'<spring:message code="page.datagrid.action.save.success" />'
		    	});
		    	selectAndClickNode();
		    	GoLive.EasyUI.Form.reset('menuForm');
            	$('#editMenuWindow').window('close');
		    }
		});
	}
</script>
   	<div class="golive-easyui-form-div">
	   	<form id="menuForm" class="easyui-form" method="post" data-options="novalidate:true" action="">
	   		<input type="hidden" name="id" id="menuId">
	   		<input name="createUser" id="createUser" type="hidden">
	   		<input name="menuType" id="menuType" type="hidden">
	   		<input name="partitionCompany" id="partitionCompany" type="hidden">
	   		<input name="parentId" id="parentId" type="hidden">
        	<input name="createTime" id="createTime"  type="hidden">
        	<input name="systemCode" id="systemCode"  type="hidden">
        	
	        <div>
	            <label for="menuCode"><font color="#990000">*</font><spring:message code="page.center.menu.menuCode" />：</label>
	            <input class="easyui-textbox" style="width:200px;" type="text" name="menuCode" data-options="required:true"></input>
	        </div>
	        <div>
	            <label for="menuName"><spring:message code="page.center.menu.menuName" />：</label>
	            <input class="easyui-textbox" style="width:200px;" type="text" name="menuName" data-options="required:true"></input>
	        </div>
	        <div>
	            <label for="isValid" ><font color="#990000">*</font><spring:message code="common.isValid" />：</label>
	            <mytag:select  	dataOptions="required:true,valueField:'value',textField:'text'" 
	            				selectId ="isValidListMenu" selectName="isValid"
	            				className="easyui-combobox"  style="width:200px;" 
				            	dataProvider="commonStateValidationDataProvider" displayField="text" valueField="value"
				            	value="Y"
	            ></mytag:select>
	        </div>
	        <div>
	            <label for="description"><spring:message code="common.description" />：</label>
	            <input class="easyui-textbox" style="width:200px;" type="text" name="description"></input>
	        </div>
    	</form>
   	</div>
   	<div style="text-align:center;padding:5px;height: 10%;">
    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitMenuForm()"><spring:message code="button.save" /></a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetForm('menuForm')"><spring:message code="button.resetForm" /></a>
    </div>

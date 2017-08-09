<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/common/meta.jsp" %>
<style>
</style>
<script type="text/javascript">

	function submitRoleForm(){
	
		GoLive.EasyUI.Form.submit({
			formId: 'roleForm',
			restfulId:'roleId',
			url:'${centerPath}/organization/role',
			onSubmit: function(param){
				return true;
			},
			success: function(data){
		    	GoLive.EasyUI.Message.show({
		    		timeout:1000,
		    		icon:'info',
		    		msg:'<spring:message code="page.datagrid.action.save.success" />'
		    	});
		    	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		    	var parentCode = $('#parentCompanyCode').val();
		    	var node = treeObj.getNodeByParam("id", parentCode, null);
		    	treeObj.reAsyncChildNodes(node, "refresh",false);
		    	GoLive.EasyUI.Form.reset('roleForm');
            	$('#editRoleWindow').window('close');
		    }
		});
	}
</script>
   	<div class="golive-easyui-form-div">
	   	<form id="roleForm" class="easyui-form" method="post" data-options="novalidate:true" action="">
	   		<input type="hidden" name="id" id="roleId">
	   		<input name="createUser" id="createUser" type="hidden">
	   		<input name="partitionCompany" id="partitionCompany" type="hidden">
        	<input name="createTime" id="createTime"  type="hidden">
	        <div>
	            <label for="roleCode"><font color="#990000">*</font><spring:message code="page.center.role.roleCode" />：</label>
	            <input class="easyui-textbox" style="width:200px;" type="text" name="roleCode" data-options="required:true"></input>
	        </div>
	        <div>
	            <label for="roleName"><spring:message code="page.center.role.roleName" />：</label>
	            <input class="easyui-textbox" style="width:200px;" type="text" name="roleName" data-options="required:true"></input>
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
	            <label for="roleType" ><font color="#990000">*</font><spring:message code="page.center.role.roleType" />：</label>
	            <mytag:select  	dataOptions="required:true,valueField:'value',textField:'text'" 
	            				selectId ="roleTypeList" selectName="roleType"
	            				className="easyui-combobox"  style="width:200px;" 
				            	dataProvider="roleTypeDataProvider" displayField="text" valueField="value"
				            	value="R"
	            ></mytag:select>
	        </div>
	        <div>
	            <label for="remark"><spring:message code="common.description" />：</label>
	            <input class="easyui-textbox" style="width:200px;" type="text" name="remark"></input>
	        </div>
    	</form>
   	</div>
   	<div style="text-align:center;padding:5px;height: 10%;">
    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitRoleForm()"><spring:message code="button.save" /></a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetForm('roleForm')"><spring:message code="button.resetForm" /></a>
    </div>

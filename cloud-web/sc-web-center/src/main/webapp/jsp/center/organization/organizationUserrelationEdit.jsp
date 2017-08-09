<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/common/meta.jsp" %>
<style>
</style>
<script type="text/javascript">

	function submitUserForm(){
	
		GoLive.EasyUI.Form.submit({
			formId: 'userForm',
			restfulId:'userId',
			url:'${centerPath}/organization/user',
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
		    	GoLive.EasyUI.Form.reset('userForm');
            	$('#editUserWindow').window('close');
		    }
		});
	}
</script>
   	<div class="golive-easyui-form-div">
	   	<form id="userForm" class="easyui-form" method="post" data-options="novalidate:true" action="">
	   		<input type="hidden" name="id" id="userId">
	   		<input name="createUser" id="createUser" type="hidden">
	   		<input name="partitionCompany" id="partitionCompany" type="hidden">
	   		<input name="companyCode"  type="hidden">
	   		<input name="departmentCode" type="hidden">
	   		<input name="postCode" type="hidden">
	   		<input name="userId" id="userIdId" type="hidden">
        	<input name="createTime" id="createTime"  type="hidden">
	        <div>
	            <label for="userNameCn"><font color="#990000">*</font><spring:message code="page.center.userrelation.userNameCn" />：</label>
	            <input class="easyui-textbox" style="width:200px;" type="text" name="userNameCn" data-options="required:true"></input>
	        </div>
	        <div>
	            <label for="email"><spring:message code="page.center.userrelation.email" />：</label>
	            <input class="easyui-textbox" style="width:200px;" type="text" name="email" data-options="required:true"></input>
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
    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitUserForm()"><spring:message code="button.save" /></a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetForm('userForm')"><spring:message code="button.resetForm" /></a>
    </div>

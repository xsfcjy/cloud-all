<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/common/meta.jsp" %>
<script type="text/javascript">

	function postSubmitForm(){
	
		GoLive.EasyUI.Form.submit({
			formId: 'postForm',
			restfulId:'postId',
			url:'${centerPath}/post',
			onSubmit: function(param){
				return true;
			},
			success: function(data){
		    	GoLive.EasyUI.Message.show({
		    		timeout:1000,
		    		icon:'info',
		    		msg:'<spring:message code="page.datagrid.action.save.success" />'
		    	});
// 		    	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
// 		    	var parentCode = $('#companyCode').val();
// 		    	var node = treeObj.getNodeByParam("id", parentCode, null);
// 		    	treeObj.reAsyncChildNodes(node, "refresh",false);
				refreshAndSelectAndClickNode();
		    	GoLive.EasyUI.Form.reset('postForm');
            	$('#organizationPostEdit').window('close');
		    }
		});
	}
</script>
   	<div class="golive-easyui-form-div">
	   	<form id="postForm" class="easyui-form" method="post" data-options="novalidate:true" action="">
	   		<input type="hidden" name="id" id="postId">
	   		<input name="createUser" id="createUser" type="hidden">
	   		<input name="companyCode" id="companyCode" type="hidden">
	   		<input name="departmentCode" id="departmentCode" type="hidden">
	   		<input name="partitionCompany" id="partitionCompany" type="hidden">
	        <div>
	            <label for="postCode"><font color="#990000">*</font><spring:message code="page.center.organization.postCode" />：</label>
	            <input class="easyui-textbox" style="width:200px;" type="text" name="postCode" data-options="required:true"></input>
	        </div>
	        <div>
	            <label for="postName"><spring:message code="page.center.organization.postName" />：</label>
	            <input class="easyui-textbox" style="width:200px;" type="text" name="postName"></input>
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
    	</form>
   	</div>
   	<div style="text-align:center;padding:5px;height: 10%;">
    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="postSubmitForm()"><spring:message code="button.save" /></a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetForm()"><spring:message code="button.resetForm" /></a>
    </div>

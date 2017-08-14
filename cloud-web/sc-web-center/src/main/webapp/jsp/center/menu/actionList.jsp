<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/common/meta.jsp" %>
<script type="text/javascript">
	var toolbarAction = [{
	    text:'<spring:message code="button.query" />',
	    iconCls:'golive-icon-query',
	    handler:function(){
	    	organizationRoleList(getSelectNode());
		}
	},'-',{
	    text:'<spring:message code="button.add.action" />',
	    iconCls:'golive-icon-add',
	    handler:function(){
	    }
	},'-',{
	    text:'<spring:message code="button.edit" />',
	    iconCls:'golive-icon-modify',
	    handler:function(){
	    	GoLive.EasyUI.Datagrid.singleSelectedAction({
	    		dataGridId :'dataGridActionList',
	    		notSelectedTip:'<spring:message code="page.datagrid.action.notSelectedTip" />',
	    		multySelectedTip:'<spring:message code="page.datagrid.action.multySelectedTip" />',
	    		actionFunc:function(selectRecord){
	    			var loadRoleUrl = "${centerPath}/company/{id}".format(selectRecord);
	    			GoLive.EasyUI.Form.loadData({
	    				prefix:'',
	    				formId:'myform',
	    				type:'GET',
	    				url:loadRoleUrl,
	    				afterFormRender : function (data){
	            			$('#isValidList').combobox('setValue', data["isValid"]);
	                    	$('#editWindow').window('open');
	    				},
	    				parameter:{
	    				}
	    			});
	    		}
	    	});
		}
	}];
	function actionList(node){	
    }
</script>
<table id="dataGridActionList" class="easyui-datagrid" 
           rownumbers="true" pagination="true" 
           data-options="fit:true,pagePosition:'bottom',fitColumns:true,
           	remoteSort:false,
	    	toolbar:toolbarAction,
           	multiSort:true"
           >
     <thead>
         <tr>
         	<th data-options="field:'ck',checkbox:true"></th>
             <th field="actionCode" width="2" align="center"  sortable="true">
             	<spring:message code="page.center.menu.actionList.actionCode" />
             </th>
             <th field="actionName" width="3" align="center">
             	<spring:message code="page.center.menu.actionList.actionName" />
	</th>
             <th field="isValid" width="1" align="center" >
             	<spring:message code="common.isValid" />
             </th>
             <th field="description" width="3" align="center" >
             	<spring:message code="common.description" />
             </th>
         </tr>
     </thead>
 </table>

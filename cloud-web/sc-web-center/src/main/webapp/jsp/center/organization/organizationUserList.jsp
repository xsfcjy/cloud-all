<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/common/meta.jsp" %>
<script type="text/javascript">
	
	
	function organizationUserList(node){	
		var url = '${centerPath}/organization/{id}/userList/{partitionCompany}'.format(node);
    	$('#dataGridUserList').easyUILoadData({
    		dataGridId: 'dataGridUserList',
			extractParameter:function(params){
				
			},
    		url: url ,
    		columnDecoratedUrl:'${easyuiDataProviderPath}',
    		decoratedColumns:[{
    			field:'isValid',
    			type:'string',
    			dataRegister:'companyGridColumnDataProvider',
    			displayField:'text',
    			valueField:'value'
    		}],
    		toolbarConditions:[{
				type:'textbox',
				name:'userNameCn',
				id:'userNameCnId',
				label:'<spring:message code="page.center.organization.userList.userNameCn" />'
			}]
    	});
    }
    var toolbarUser = [{
        text:'<spring:message code="button.query" />',
        iconCls:'golive-icon-query',
        handler:function(){
        	organizationUserList(getSelectNode());
		}
    },'-',{
        text:'<spring:message code="button.add" />',
        iconCls:'golive-icon-add',
        handler:function(){
        	$('#myform').form('clear');
        	$('#editWindow').window('open');
			$('#isValidList').combobox('setValue', $('#isValidList').attr("value"));
        }
    },'-',{
        text:'<spring:message code="button.edit" />',
        iconCls:'golive-icon-modify',
        handler:function(){
        	GoLive.EasyUI.Datagrid.singleSelectedAction({
        		dataGridId :'dataGridUserList',
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
    
</script>
<div title="用户" data-options="fit:true,iconCls:'icon-help'" >
	<table id="dataGridUserList" class="easyui-datagrid" 
            rownumbers="true" pagination="true" 
            data-options="fit:true,pagePosition:'bottom',fitColumns:true,
            remoteSort:false,
		    toolbar:toolbarUser,
            multiSort:true
              	"
            >
        <thead>
            <tr>
            	<th data-options="field:'ck',checkbox:true"></th>
                <th field="userId" width="1" align="center"  sortable="true">
                	<spring:message code="page.center.organization.userList.userId" />
                </th>
                <th field="userNameCn" width="1" align="center">
                	<spring:message code="page.center.organization.userList.userNameCn" />
				</th>
                <th field="isValid" width="1" align="center" >
                	<spring:message code="common.isValid" />
                </th>
            </tr>
        </thead>
    </table>
</div>
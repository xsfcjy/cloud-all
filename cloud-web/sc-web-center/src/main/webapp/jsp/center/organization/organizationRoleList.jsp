<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/common/meta.jsp" %>
<script type="text/javascript">
	
	
	function organizationRoleList(node){	
		var url = '${centerPath}/organization/{id}/{partitionCompany}/roleList'.format(node);
    	$('#dataGridRoleList').easyUILoadData({
    		dataGridId: 'dataGridRoleList',
			extractParameter:function(params){
// 				params['data']= parentNodeDatas;
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
				name:'roleName',
				id:'roleNameId',
				label:'<spring:message code="page.center.organization.roleList.roleName" />'
			}]
    	});
    }
    var toolbarRole = [{
        text:'<spring:message code="button.query" />',
        iconCls:'golive-icon-query',
        handler:function(){
        	organizationRoleList(getSelectNode());
		}
    },'-',{
        text:'<spring:message code="button.add.role" />',
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
        		dataGridId :'dataGridRoleList',
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
    function operateRoleCellFormat(val,row){
    	console.log(row);
    	
    	var organizationOperateText = "";
    	var userOperateText = "";
    	var resultString = '';
    	var authUserToSub = '';
    	var selectedNode = getSelectNode();
    	if(selectedNode){
	    	if(selectedNode.companyLevel == 'department'){
				if(!row['departmentCode']){
		    		organizationOperateText = '角色引进';	
		    	}else{
		    		organizationOperateText = '移除';	
		    	}
			}else if ( selectedNode.companyLevel == 'post' ){
				if(!row['postCode']){
		    		organizationOperateText = '角色引进';	
		    	}else{
		    		organizationOperateText = '移除';	
		    	}
			}else{
		    	if(!row['createCompanyCode']){
		    		organizationOperateText = '角色引进';	
		    	}else{
		    		if(row['createCompanyCode'] == selectedNode['id']){
			    		organizationOperateText = '移除';	
		    		}else{
		    			organizationOperateText = '角色引进';
		    		}
		    		console.log('222222222');
		    		console.log(selectedNode);
		    		console.log(row);
		    		if(row['createCompanyCode'] == selectedNode['id']){
		    			authUserToSub = '下放角色';
		    		}
		    	}
			}
    	}
    	var oUserFunc = "introductionRoleCellClick";
    	if(organizationOperateText == '移除'){
    		oUserFunc = "removeRoleCellClick";
    	}
    	
    	var relatedUserString =  '';
    	
    	var organizationOperateString = '  <a href="#" onclick="'+oUserFunc+'(\''+row.userId+'\');" data-options="iconCls:\'icon-add\'">'+'<span  class="golive-icon-add" stype="width:16px;height:16px;display: block;"></span>'+organizationOperateText+'</a>';
    	
    	var authUserToSubString = '';
    	if(authUserToSub){
    		authUserToSubString = ' <a href="#" onclick="authRoleToSubFunc(\''+row.userId+'\');" data-options="iconCls:\'icon-add\'">'+'<span  class="golive-icon-add" stype="width:16px;height:16px;display: block;"></span>'+authUserToSub+'</a>';
    	}
    	
    	return authUserToSubString + relatedUserString + organizationOperateString;
    }
    function operateRoleCellClick(userId){
    	alert(userId);
    	GoLive.stopPropagation();
    }
    function introductionRoleCellClick(userId){
    	GoLive.EasyUI.Message.confirm({
    		title:'角色引进',
    		tip:'角色引进后，原角色关系还存在，请确认是否引进该角色关系？',
    		callback: function(r){
    			if(r){
    				alert('执行角色引进');
    			}
    		}
    	});
    	GoLive.stopPropagation();
    }
    function removeRoleCellClick(userId){
    	GoLive.EasyUI.Message.confirm({
    		title:'角色移除',
    		tip:'角色移除后，原角色关系将会无效，请确认是否移除该角色关系？',
    		callback: function(r){
    			if(r){
    				alert('执行角色移除');
    			}
    		}
    	});
    	GoLive.stopPropagation();
    }
    function authRoleToSubFunc(userId){
		alert('下放角色');
    	GoLive.stopPropagation();
    }
</script>
<div title="角色" data-options="fit:true,iconCls:'icon-help'" >
	<table id="dataGridRoleList" class="easyui-datagrid" 
            rownumbers="true" pagination="true" 
            data-options="fit:true,pagePosition:'bottom',fitColumns:true,
            remoteSort:false,
		    toolbar:toolbarRole,
            multiSort:true
              	"
            >
        <thead>
            <tr>
            	<th data-options="field:'ck',checkbox:true"></th>
                <th field="roleCode" width="2" align="center"  sortable="true">
                	<spring:message code="page.center.organization.roleList.roleCode" />
                </th>
                <th field="roleName" width="2" align="center">
                	<spring:message code="page.center.organization.roleList.roleName" />
				</th>
                <th field="isValid" width="1" align="center" >
                	<spring:message code="common.isValid" />
                </th>
                <th field="operate" width="3" align="center" data-options="formatter:operateRoleCellFormat">
                	<spring:message code="common.operate" />
                </th>
            </tr>
        </thead>
    </table>
</div>
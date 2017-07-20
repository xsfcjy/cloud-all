<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/resouces.jsp" %>

<!DOCTYPE html>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<mytag:resources></mytag:resources>
    <script type="text/javascript">
	    $(document).ready(function (){
	    	loadGridData();
		});
	    function loadGridData(){
	    	$('#dataGrid').easyUILoadData({
        		dataGridId: 'dataGrid',
        		queryFormId:'queryForm',
				extractParameter:function(params){
				},
        		url: "${centerPath}/company/list" ,
        		columnDecoratedUrl:'${easyuiDataProviderPath}',
        		decoratedColumns:[{
        			field:'isValid',
        			type:'string',
        			dataRegister:'companyGridColumnDataProvider',
        			displayField:'text',
        			valueField:'value'
        		}] 
        	});
        }
        var toolbar = [{
            text:'<spring:message code="button.query" />',
            iconCls:'golive-icon-query',
            handler:function(){
            	loadGridData();
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
            		dataGridId :'dataGrid',
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
    	function resetForm(){
    		GoLive.EasyUI.Form.reset('myform');
    	}
    </script>
  </head>
  <body>
  	<div class="easyui-layout" data-options="fit:true">
	    <div data-options="region:'north',split:true,border:false"  style="height:40px">
	    	<form action="#" id="queryForm">
	  			<table height="90%">
	  				<tbody>
	  					<tr><td><spring:message code="page.center.company.companyNameCn" />：</td><td><input name="companyNameCn" type="text"></td></tr>
	  				</tbody>
	  			</table>
	   		</form>
	     </div>
	    <div data-options="region:'center',border:false">
		  	<table id="dataGrid" class="easyui-datagrid" 
		            rownumbers="true" pagination="true" 
		            data-options="fit:true,pagePosition:'bottom',fitColumns:true,
		            toolbar:toolbar,
		            remoteSort:false,
                	multiSort:true
                	"
		            >
		        <thead>
		            <tr>
		            	<th data-options="field:'ck',checkbox:true"></th>
		                <th field="companyCode" width="1" align="center"  sortable="true">
		                	<spring:message code="page.center.company.companyCode" />
		                </th>
		                <th field="companyNameCn" width="1" align="center">
		                	<spring:message code="page.center.company.companyNameCn" />
						</th>
		                <th field="isValid" width="1" align="center" >
		                	<spring:message code="common.isValid" />
		                </th>
		            </tr>
		        </thead>
		    </table>
	    </div>
    </div>
	<div id="editWindow" class="easyui-window" title="<spring:message code="page.center.company.editwindow" />" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:400px;height:400px;padding:10px;">
		<jsp:include page="companyEdit.jsp" flush="true"></jsp:include>
	</div>
  </body>
</html>

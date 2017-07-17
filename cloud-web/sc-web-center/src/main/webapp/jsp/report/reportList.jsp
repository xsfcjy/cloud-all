<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/common/meta.jsp" %>

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
        		url: "${centerPath}/report/definition/list" ,
        		columnDecoratedUrl:'${easyuiDataProviderPath}',
        		decoratedColumns:[{
        			field:'isMain',
        			type:'string',
        			dataRegister:'companyGridColumnDataProvider',
        			displayField:'text',
        			valueField:'value'
        		}],
//         		inputList:'inputList'
				toolbarConditions:[{
					type:'textbox',
					name:'name',
					id:'conditionName',
					label:'<spring:message code="page.center.report.reportList.form.name" />'
				},{
					type:'datebox',
					name:'startTime',
					id:'startTime',
					label:'开始时间',
					options:{
						editable:false
					}
				},{
					type:'datetimebox',
					name:'endTime',
					id:'endTime',
					label:'结束时间',
					options:{
						editable:false
					}
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
            			var loadRoleUrl = "${centerPath}/report/definition/{id}".format(selectRecord);
            			GoLive.EasyUI.Form.loadData({
            				prefix:'',
            				formId:'myform',
            				type:'GET',
            				url:loadRoleUrl,
            				operatedUrl:{
            					put:'',
            					post:'',
            					del:'',
            					get:''
            					
            				},
            				afterFormRender : function (data){
		            			$('#isValidList').combobox('setValue', data["isMain"]);
		                    	$('#editWindow').window('open');
            				},
            				parameter:{
            				},
            			});
            		}
            	});
			}
        }];
        
    </script>
  </head>
  <body>
	    	<!-- <table id="inputList" style="display:none;"  >
		        <tr>
		        	<td><div class="datagrid-btn-separator"></div></td>
		        	<td>报表名称：<input name="name" uiclass="easyui-datebox" data-options="" style="width:110px"></td>
		        	<td><div class="datagrid-btn-separator"></div></td>
		        	<td>报表编码：<input name="code" uiclass="easyui-textbox" style="width:110px"></td>
		        </tr>
		    </table> -->
  	<div class="easyui-layout" data-options="fit:true">
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
		                <th field="code" width="1" align="center"  sortable="true">
		                	<spring:message code="page.center.report.reportList.code" />
						</th>
		                <th field="name" width="1" align="center">
		                	<spring:message code="page.center.report.reportList.name" />
						</th>
		                <th field="isMain" width="1" align="center" >
		                	<spring:message code="page.center.report.reportList.isMain" />
						</th>
		            </tr>
		        </thead>
		    </table>
	    </div>
    </div>
	<div id="editWindow" class="easyui-window" title="<spring:message code="page.center.report.reportList.editwindow" />" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:400px;height:300px;padding:10px;">
		<jsp:include page="reportDefinitionEdit.jsp" flush="true"></jsp:include>
	</div>
  </body>
</html>

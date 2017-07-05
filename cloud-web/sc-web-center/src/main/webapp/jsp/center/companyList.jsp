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
            text:'查询',
            iconCls:'golive-icon-query',
            handler:function(){
            	loadGridData();
			}
        },'-',{
            text:'添加',
            iconCls:'golive-icon-add',
            handler:function(){
            	$('#myform').form('clear');
            	$('#editWindow').window('open');
    			$('#isValidList').combobox('setValue', $('#isValidList').attr("value"));
            }
        },'-',{
            text:'修改',
            iconCls:'golive-icon-modify',
            handler:function(){
            	GoLive.EasyUI.Datagrid.singleSelectedAction({
            		dataGridId :'dataGrid',
            		notSelectedTip:'请选择记录!',
            		multySelectedTip:'只能选择一条记录操作!',
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
    		    		msg:'用户保存成功!'
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
	  					<tr><td>公司中文名称：</td><td><input name="companyNameCn" type="text"></td></tr>
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
		                <th field="id" width="1" align="center"  sortable="true" >公司Id</th>
		                <th field="companyCode" width="1" align="center"  sortable="true">公司编码</th>
		                <th field="companyNameCn" width="1" align="center">公司名称</th>
		                <th field="isValid" width="1" align="center" >是否有效</th>
		            </tr>
		        </thead>
		    </table>
	    </div>
    </div>
	<div id="editWindow" class="easyui-window" title="公司编辑" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:400px;height:400px;padding:10px;">
		<jsp:include page="companyEdit.jsp" flush="true"></jsp:include>
	</div>
  </body>
</html>

<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/resouces.jsp" %>

<!DOCTYPE html>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<mytag:resources></mytag:resources>
    <SCRIPT type="text/javascript">
    	var url = "${centerPath}/organization/${userId}/${partitionCompany}";
		var setting = {
			async: {
				enable: true,
				url:url,
				autoParam:["id","level"],
				otherParam:{"otherParam":"zTreeAsyncTest"},
				dataFilter: filter
			},
			view: {
				addHoverDom: addHoverDom,
				removeHoverDom: removeHoverDom,
				selectedMulti: false
			},
			edit: {
				enable: true,
				editNameSelectAll: true,
				showRemoveBtn: showRemoveBtn,
				showRenameBtn: showRenameBtn
			},
			callback: {
				beforeDrag: beforeDrag,
				beforeEditName: beforeEditName,
				beforeRemove: beforeRemove,
				beforeRename: beforeRename,
				onRemove: onRemove,
				onRename: onRename,
				beforeAsync: myBeforeCallBack,
				onClick: zTreeOnClick
			}
		};

    	function resetForm(formId){
    		GoLive.EasyUI.Form.reset(formId);
    	}
		function zTreeOnClick(event, treeId, treeNode) {
// 			if(treeNode.companyLevel == 'department'){
// 			}else if ( treeNode.companyLevel == 'post' ){
// 			}else{
				organizationUserList(treeNode);
				organizationRoleList(treeNode);
// 			}
		}
		function myBeforeCallBack(treeId, treeNode) {
			if(treeNode){
				var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
				var levelUrl = "${centerPath}/organization/sub/{id}/{companyLevel}/${partitionCompany}";
				treeObj.setting.async.url = levelUrl.format(treeNode);
			}
		    return true;
		}
		function getSelectNode(){
			var node;
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			if(treeObj){
				var nodes = treeObj.getSelectedNodes();
				if (nodes.length>0) { 
					node = nodes[0];
				}
			}
			return node;
		}
		function getOrganizationObj(){
			var obj = {};
			var node;
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			if(treeObj){
				var nodes = treeObj.getSelectedNodes();
				if (nodes.length>0) { 
					node = nodes[0];
					if(node.companyLevel == 'department'){
						obj['departmentCode'] = node['id'];
						var parentNodeCompany = node.getParentNode();
						obj['companyCode'] = parentNodeCompany['id'];
					}else if ( node.companyLevel == 'post' ){
						obj['postCode'] = node['id'];
						var parentNodeDepartment = node.getParentNode();
						obj['departmentCode'] = parentNodeDepartment['id'];
						var parentNodeCompany = parentNodeDepartment.getParentNode();
						obj['companyCode'] = parentNodeCompany['id'];
					}else{
						obj['companyCode'] = node['id'];
					}
				}
			}
			return obj;
		}
		function getParentNodeCurs(node,callback){
			if(node.getParentNode()){
				getParentNodeCurs(node.getParentNode(),callback);
			}
			callback(node.getParentNode());
		}
		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}

		var log, className = "dark";
		
		function beforeDrag(treeId, treeNodes) {
			return false;
		}
		function beforeEditName(treeId, treeNode) {
			className = (className === "dark" ? "":"dark");
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.selectNode(treeNode);
			/* setTimeout(function() {
				if (confirm(" " + treeNode.name + " 的编辑状态吗？")) {
					setTimeout(function() {
						zTree.editName(treeNode);
					}, 0);
				}
			}, 0); */
			zTree.editName(treeNode);
			return false;
		}
		function beforeRemove(treeId, treeNode) {
			var deleteString = '<spring:message code="page.center.organization.tree.delete" />';
			className = (className === "dark" ? "":"dark");
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.selectNode(treeNode);
			var type = '<spring:message code="page.center.organization.tree.delete.company" />';
			if(treeNode.companyLevel == 'department'){
				type = '<spring:message code="page.center.organization.tree.delete.department" />';
			}else if ( treeNode.companyLevel == 'post' ){
				type = '<spring:message code="page.center.organization.tree.delete.post" />';
			}
			return confirm(deleteString.format({type:type,name:treeNode.name}));
		}
		function onRemove(e, treeId, treeNode) {
		}
		function beforeRename(treeId, treeNode, newName, isCancel) {
			className = (className === "dark" ? "":"dark");
			if (newName.length == 0) {
				setTimeout(function() {
					var zTree = $.fn.zTree.getZTreeObj("treeDemo");
					zTree.cancelEditName();
					alert("节点名称不能为空.");
				}, 0);
				return false;
			}
			return true;
		}
		function onRename(e, treeId, treeNode, isCancel) {
		}
		function showRemoveBtn(treeId, treeNode) {
			return true;
		}
		function showRenameBtn(treeId, treeNode) {
			return true;
		}
		function getTime() {
			var now= new Date(),
			h=now.getHours(),
			m=now.getMinutes(),
			s=now.getSeconds(),
			ms=now.getMilliseconds();
			return (h+":"+m+":"+s+ " " +ms);
		}

		var newCount = 1;
		function addHoverDom(treeId, treeNode) {
			var sObj = $("#" + treeNode.tId + "_span");
			if(treeNode.companyLevel == 'department'){
				addPostNodeBtn(sObj,treeNode);
				addDepartmentNodeBtn(sObj,treeNode);
			}else if ( treeNode.companyLevel == 'post' ){
// 				addPostNodeBtn(sObj,treeNode);
			}else{
				addPostNodeBtn(sObj,treeNode);
				addDepartmentNodeBtn(sObj,treeNode);
				addCompanyNodeBtn(sObj,treeNode);
			}
			
		};
		function addCompanyNodeBtn(sObj,treeNode){
			if (treeNode.editNameFlag || $("#addCompanyBtn_"+treeNode.tId).length>0) return;
			var addStr = "<span class='button icon02_ico_docu' id='addCompanyBtn_" + treeNode.tId
			+ "' title='<spring:message code="page.center.organization.addCompany" />' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addCompanyBtn_"+treeNode.tId);
			if (btn) btn.bind("click", function(){
				openEditCompanyWin(treeNode);
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.selectNode(treeNode);
				return false;
			});
		}
		function addDepartmentNodeBtn(sObj,treeNode){
			if (treeNode.editNameFlag || $("#addDepartmentBtn_"+treeNode.tId).length>0) return;
			var addStr = "<span class='button icon02_ico_docu' id='addDepartmentBtn_" + treeNode.tId
			+ "' title='<spring:message code="page.center.organization.addDepartment" />' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addDepartmentBtn_"+treeNode.tId);
			if (btn) btn.bind("click", function(){
				openEditDepartmentWin(treeNode);
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.selectNode(treeNode);
				return false;
			});
		}
		function addPostNodeBtn(sObj,treeNode){
			
			if (treeNode.editNameFlag || $("#addPostBtn_"+treeNode.tId).length>0) return;
			var addStr = "<span class='button icon02_ico_docu' id='addPostBtn_" + treeNode.tId
			+ "' title='<spring:message code="page.center.organization.addPost" />' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addPostBtn_"+treeNode.tId);
			if (btn) btn.bind("click", function(){
				openEditPostWin(treeNode);
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.selectNode(treeNode);
				return false;
			});
		}
		function removeHoverDom(treeId, treeNode) {
			try{
				$("#addCompanyBtn_"+treeNode.tId).unbind().remove();
			}catch(e){
				;
			}
			try{
				$("#addDepartmentBtn_"+treeNode.tId).unbind().remove();
			}catch(e){
				;
			}
			try{
				$("#addPostBtn_"+treeNode.tId).unbind().remove();
			}catch(e){
				;
			}
		};
		function selectAll() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
		}
		
		function openEditCompanyWin(treeNode){
			var loadRoleUrl = "${centerPath}/company/{id}".format({id:treeNode.tId});
			GoLive.EasyUI.Form.loadData({
				prefix:'',
				formId:'companyForm',
				type:'GET',
				url:loadRoleUrl,
				afterLoadData: function(data){
					data['parentCompanyCode'] = treeNode.id;
					data['parentCompanyLevel'] = treeNode.companyLevel;
					data['isValid'] = 'Y';
				},
				afterFormRender : function (data){
					if(data){
            			$('#isValidList').combobox('setValue', data["isValid"]);
					}
                	$('#organizationCompanyEdit').window('open');
				},
				parameter:{
				}
			});
		}
		
		function openEditDepartmentWin(treeNode){
			var loadRoleUrl = "${centerPath}/department/{id}/{partitionCompany}".format({partitionCompany:treeNode.partitionCompany,id:treeNode.tId});
			GoLive.EasyUI.Form.loadData({
				prefix:'',
				formId:'departmentForm',
				type:'GET',
				url:loadRoleUrl,
				afterLoadData: function(data){
					data['companyCode'] = treeNode.id;
					data['partitionCompany'] = treeNode.partitionCompany;
					data['isValid'] = 'Y';
					console.log(data);
				},
				afterFormRender : function (data){
					if(data){
            			$('#isValidList').combobox('setValue', data["isValid"]);
					}
                	$('#organizationDepartmentEdit').window('open');
				},
				parameter:{
				}
			});
		}
		
		function openEditPostWin(treeNode){
			var loadRoleUrl = "${centerPath}/post/{id}/{partitionCompany}".format({partitionCompany:treeNode.partitionCompany,id:treeNode.tId});
			GoLive.EasyUI.Form.loadData({
				prefix:'',
				formId:'postForm',
				type:'GET',
				url:loadRoleUrl,
				afterLoadData: function(data){
					if(treeNode.companyLevel == 'department'){						
						data['departmentCode'] = treeNode.id;
					}else{
						data['companyCode'] = treeNode.id;
					}
					data['partitionCompany'] = treeNode.partitionCompany;
					data['isValid'] = 'Y';
				},
				afterFormRender : function (data){
					if(data){
            			$('#isValidList').combobox('setValue', data["isValid"]);
					}
                	$('#organizationPostEdit').window('open');
				},
				parameter:{
				}
			});
		}
		
		function closeWindow(){
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = treeObj.getSelectedNodes();
			if (nodes.length>0) { 
				treeObj.cancelSelectedNode(nodes[0]);
			}
			return true;
		}
		
		function initTabsDatagrid(){
			organizationUserList(null);
		}
		
		$(document).ready(function(){
			treeDemoObj = $.fn.zTree.init($("#treeDemo"), setting);
			initTabsDatagrid();
		});
		
		
	</SCRIPT>
  </head>
  <body>
  	<div class="easyui-layout" data-options="fit:true">
	    <div data-options="region:'center',border:false">
	    </div>
	    <div class="easyui-layout" style="width: 100%; height: 100%;" data-options="fit:true">
			<div data-options="region:'west',split:true,border:false" style="width: 30%;text-align: center;">
		    	<ul id="treeDemo" class="ztree"></ul>
			</div>
			<div data-options="region:'center',iconCls:'icon-ok'">
				<div id="tt" class="easyui-tabs" data-options="fit:true,tabPosition:'right'">
					<jsp:include page="<%= com.sfxie.web.boot.util.ComponentDerector.getComponentPath(\"jsp.center.organization.list.organizationUserList\")%>" flush="true"></jsp:include>
					<jsp:include page="<%= com.sfxie.web.boot.util.ComponentDerector.getComponentPath(\"jsp.center.organization.list.organizationRoleList\")%>" flush="true"></jsp:include>
					<jsp:include page="<%= com.sfxie.web.boot.util.ComponentDerector.getComponentPath(\"jsp.center.organization.list.organizationDataAuthList\")%>" flush="true"></jsp:include>
			    </div>
			</div>
		</div>
    </div>
    <div style="display: none;">
		<div id="organizationCompanyEdit" class="easyui-window"
				title="<spring:message code="page.center.organization.organizationCompanyEdit" />" 
				data-options="onBeforeClose:closeWindow,modal:true,closed:true,iconCls:'icon-save'" 
				style="width:400px;height:300px;padding:10px;">
			<jsp:include page="<%= com.sfxie.web.boot.util.ComponentDerector.getComponentPath(\"jsp.center.organization.window.organizationCompanyEdit\")%>" flush="true"></jsp:include>
		</div>
		<div id="organizationDepartmentEdit" class="easyui-window"
				title="<spring:message code="page.center.organization.organizationDepartmentEdit" />" 
				data-options="onBeforeClose:closeWindow,modal:true,closed:true,iconCls:'icon-save'" 
				style="width:400px;height:250px;padding:10px;">
			<jsp:include page="<%= com.sfxie.web.boot.util.ComponentDerector.getComponentPath(\"jsp.center.organization.window.organizationDepartmentEdit\")%>" flush="true"></jsp:include>
		</div>
		<div id="organizationPostEdit" class="easyui-window" 
				title="<spring:message code="page.center.organization.organizationPostEdit" />" 
				data-options="onBeforeClose:closeWindow,modal:true,closed:true,iconCls:'icon-save'" 
				style="width:400px;height:250px;padding:10px;">
			<jsp:include page="<%= com.sfxie.web.boot.util.ComponentDerector.getComponentPath(\"jsp.center.organization.window.organizationPostEdit\")%>" flush="true"></jsp:include>
		</div>
		<div id="editUserWindow" class="easyui-window" 
				title="<spring:message code="page.center.organization.organizationUserrelationEdit" />" 
				data-options="onBeforeClose:closeWindow,modal:true,closed:true,iconCls:'icon-save'" 
				style="width:400px;height:250px;padding:10px;">
			<jsp:include page="<%= com.sfxie.web.boot.util.ComponentDerector.getComponentPath(\"jsp.center.organization.list.organizationUserrelationEdit\")%>" flush="true"></jsp:include>
		</div>
		<div id="editRoleWindow" class="easyui-window" 
				title="<spring:message code="page.center.organization.organizationRoleEdit" />" 
				data-options="onBeforeClose:closeWindow,modal:true,closed:true,iconCls:'icon-save'" 
				style="width:400px;height:250px;padding:10px;">
			<jsp:include page="<%= com.sfxie.web.boot.util.ComponentDerector.getComponentPath(\"jsp.center.organization.list.organizationRoleEdit\")%>" flush="true"></jsp:include>
		</div>
		
    </div>
  </body>
</html>

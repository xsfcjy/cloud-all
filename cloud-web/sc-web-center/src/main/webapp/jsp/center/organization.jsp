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
    	var url = "${centerPath}/organization/${userId}";
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
				beforeAsync: myBeforeCallBack
			}
		};
		
		function myBeforeCallBack(treeId, treeNode) {
			if(treeNode){
				var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
				console.log(treeNode);
				var levelUrl = "${centerPath}/organization/sub/{id}/{companyLevel}";
				treeObj.setting.async.url = levelUrl.format(treeNode);
				console.log(treeObj.setting.async.url);
			}
		    return true;
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
			var deleteString = '<spring:message code="page.center.center.organization.tree.delete" />';
			className = (className === "dark" ? "":"dark");
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.selectNode(treeNode);
			var type = '<spring:message code="page.center.center.organization.tree.delete.company" />';
			if(treeNode.companyLevel == 'department'){
				type = '<spring:message code="page.center.center.organization.tree.delete.department" />';
			}else if ( treeNode.companyLevel == 'post' ){
				type = '<spring:message code="page.center.center.organization.tree.delete.post" />';
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
			if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
			var addStr = "<span class='button icon02_ico_docu' id='addBtn_" + treeNode.tId
				+ "' title='add node' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addBtn_"+treeNode.tId);
			if (btn) btn.bind("click", function(){
				
				if(treeNode.companyLevel == 'department'){
				}else if ( treeNode.companyLevel == 'post' ){
				}else{
					var loadRoleUrl = "${centerPath}/company/{id}".format({id:treeNode.tId});
	    			GoLive.EasyUI.Form.loadData({
	    				prefix:'',
	    				formId:'myform',
	    				type:'GET',
	    				url:loadRoleUrl,
	    				afterFormRender : function (data){
	    					if(data){
		            			$('#isValidList').combobox('setValue', data["isValid"]);
	    					}
	                    	$('#editWindow').window('open');
	    				},
	    				parameter:{
	    				}
	    			});
				}
// 				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
// 				zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
				return false;
			});
		};
		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_"+treeNode.tId).unbind().remove();
		};
		function selectAll() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
		}
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting);
		});
		
		
	</SCRIPT>
  </head>
  <body>
  	<div class="easyui-layout" data-options="fit:true">
	    <div data-options="region:'center',border:false">
	    	<ul id="treeDemo" class="ztree"></ul>
	    </div>
    </div>
    
	<div id="editWindow" class="easyui-window" title="<spring:message code="page.center.company.editwindow" />" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:400px;height:400px;padding:10px;">
		<jsp:include page="<%= com.sfxie.web.boot.util.ComponentDerector.getComponentPath(\"jsp.center.organization.window.editorganization\")%>" flush="true"></jsp:include>
	</div>
  </body>
</html>

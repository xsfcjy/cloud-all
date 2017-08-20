<!-- 组织结构树组件示例代码如下：
jsp:include page="/common/component/organizationTree.jsp" flush="true"
	jsp:param value="treeDemo" name="treeId"
	jsp:param value="/static/js/ui/zTree_v3/css/zTreeStyle/zTreeStyle.css" name="ztreeCssPath"
	jsp:param value="/static/js/ui/zTree_v3/js/jquery.ztree.all.min.js" name="ztreeJsPath"
	
回调函数：

view: {
	addHoverDom: addHoverDom,
	removeHoverDom: removeHoverDom
},
edit: {
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
-->

<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/meta.jsp" %>

<link rel="stylesheet" type="text/css" href="<%= request.getParameter("ztreeCssPath") %>" >
<script type="text/javascript" src="<%= request.getParameter("ztreeJsPath") %>" ></script>
<script type="text/javascript" src="/common/component/menuTreeComponent.js" ></script>

<SCRIPT type="text/javascript">
	var menuType = '<%= request.getParameter("menuType") %>';
	Component.menuTree.callback['getUrl'] = function(){
		return "${centerPath}/menuTree/${userId}";
	}
	Component.menuTree.callback['myBeforeCallBack'] = function(treeId, treeNode) {
		if(treeNode){
			var treeObj = $.fn.zTree.getZTreeObj('<%= request.getParameter("treeId") %>');
			var levelUrl = "${centerPath}/menuTree/u/sub/{id}";
			if(menuType == 'D' ){
				levelUrl = "${centerPath}/menuTree/d/sub/{id}";
			}
			treeObj.setting.async.url = levelUrl.format(treeNode);
		}
	    return true;
	}
	function getSelectNode(){
		var node;
		var treeObj = $.fn.zTree.getZTreeObj('<%= request.getParameter("treeId") %>');
		if(treeObj){
			var nodes = treeObj.getSelectedNodes();
			if (nodes.length>0) { 
				node = nodes[0];
			}
		}
		return node;
	}

	function closeWindow(){
	}
	function refreshAndSelectAndClickNode(){
		var treeObj = $.fn.zTree.getZTreeObj('<%= request.getParameter("treeId") %>');
		var node = treeObj.getSelectedNodes()[0];
		GoLive.ZTree.refreshAndSelectAndClickNode(treeObj,node,500);
	}
	function selectAndClickNode(){
		var treeObj = $.fn.zTree.getZTreeObj('<%= request.getParameter("treeId") %>');
		var node = treeObj.getSelectedNodes()[0];
		GoLive.ZTree.selectAndClickNode(treeObj,node);
	}
	
	$(document).ready(function(){
		treeDemoObj = $.fn.zTree.init($('#<%= request.getParameter("treeId") %>'), Component.menuTree.setting);
	});
</SCRIPT>
<ul id="<%= request.getParameter("treeId") %>" class="ztree"></ul>

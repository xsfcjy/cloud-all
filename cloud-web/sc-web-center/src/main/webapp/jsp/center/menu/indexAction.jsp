<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/resouces.jsp" %>

<!DOCTYPE html>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%-- 	<mytag:resources></mytag:resources> --%>
    
  </head>
  <body>
    <div class="easyui-layout" style="width: 100%; height: 100%;" data-options="fit:true">
		<div data-options="region:'west',split:true,border:false" style="width: 30%;text-align: center;">
  				<jsp:include page="/common/component/menuTree.jsp" flush="true">
  					<jsp:param value="treeDemo" name="treeId"/>
  					<jsp:param value="/static/js/ui/zTree_v3/css/zTreeStyle/zTreeStyle.css" name="ztreeCssPath"/>
  					<jsp:param value="/static/js/ui/zTree_v3/js/jquery.ztree.all.min.js" name="ztreeJsPath"/>
  					<jsp:param value="U" name="menuType"/>
  				</jsp:include>
		</div>
		<div data-options="region:'center',fit:true,iconCls:'icon-ok'">
			<jsp:include page="<%= com.sfxie.web.boot.util.ComponentDerector.getComponentPath(\"jsp.center.menu.list.actionList\")%>" flush="true"></jsp:include>
		</div>
	</div>
    <div style="display: none;">
    </div>
  </body>
  <SCRIPT type="text/javascript">

    	function resetForm(formId){
    		GoLive.EasyUI.Form.reset(formId);
    	}
    	Component.menuTree.callback.zTreeOnClick = function (event, treeId, treeNode) {
// 			if(treeNode.companyLevel == 'department'){
// 			}else if ( treeNode.companyLevel == 'post' ){
// 			}else{
				organizationUserList(treeNode);
				organizationRoleList(treeNode);
// 			}
		};
		
		function getParentNodeCurs(node,callback){
			if(node.getParentNode()){
				getParentNodeCurs(node.getParentNode(),callback);
			}
			callback(node.getParentNode());
		}
		Component.menuTree.callback.filter = function (treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		};

		function getTime() {
			var now= new Date(),
			h=now.getHours(),
			m=now.getMinutes(),
			s=now.getSeconds(),
			ms=now.getMilliseconds();
			return (h+":"+m+":"+s+ " " +ms);
		}

		var newCount = 1;
		Component.menuTree.callback.addHoverDom = function (treeId, treeNode) {
			
		};
		Component.menuTree.callback.removeHoverDom = function (treeId, treeNode) {
		};
		function selectAll() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
		}
		
		function initTabsDatagrid(){
			organizationUserList(null);
		}
		
		$(document).ready(function(){
			initTabsDatagrid();
		});
		
		
	</SCRIPT>
</html>

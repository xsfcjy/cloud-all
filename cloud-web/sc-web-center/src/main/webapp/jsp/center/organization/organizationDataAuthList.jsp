<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/common/meta.jsp" %>
<script type="text/javascript">
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	if(treeObj){
		var nodes = treeObj.getSelectedNodes();
		console.log(nodes);
		if(nodes){
			console.log(nodes[0]);
		}
	}
</script>
<div title="数据权限" data-options="iconCls:'icon-help'" style="padding:10px">
</div>
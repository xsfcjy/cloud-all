<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/common/meta.jsp" %>

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
			callback: {
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
  </body>
</html>

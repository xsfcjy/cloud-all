<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html>
<html>
<head>
<title>组织架构共享云平台</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/js/ui/jquery-easyui-1.4.2/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/js/ui/jquery-easyui-1.4.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/sfxie.css">
<script type="text/javascript" src="${ctx}/static/js/ui/jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/ui/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/ui/jquery-easyui-1.4.2/locale/<spring:message code="easyui.i18n" />"></script>

<script type="text/javascript" src="${ctx}/static/js/common/json2.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common/EasyUIUtil.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common/context.js"></script>

<script type="text/javascript">
	//禁止后退键 作用于Firefox、Opera
	document.onkeypress=banBackSpace;
	//禁止后退键  作用于IE、Chrome
	document.onkeydown=banBackSpace;
	
	SfxieContext.userId = '${userId}';
</script>
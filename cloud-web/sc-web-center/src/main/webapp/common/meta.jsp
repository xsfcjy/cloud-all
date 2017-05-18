<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mytag" uri="http://www.sfxie.com/tags" %>
<%@ page language="java" pageEncoding="utf-8" import="com.sfxie.web.boot.util.ServerPathUtil,com.sfxie.web.boot.util.ServerPathUtil.ServiceName" %>
<%@ page contentType="text/html; charset=utf-8" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="centerPath" value="<%=com.sfxie.web.boot.util.ServerPathUtil.getServerPath(com.sfxie.web.boot.util.ServerPathUtil.ServiceName.centerServer)%>"/>
<c:set var="easyuiDataProviderPath" value="<%=com.sfxie.web.boot.util.ServerPathUtil.getServerPath(com.sfxie.web.boot.util.ServerPathUtil.ServiceName.easyuiDataProviderServer)%>"/>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<title>组织架构共享云平台</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/js/ui/jquery-easyui-1.4.2/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/js/ui/jquery-easyui-1.4.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/golive-business.css">
<script type="text/javascript" src="${ctx}/static/js/ui/jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/ui/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/ui/jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common/json2.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common/EasyUIUtil.js"></script>

<script type="text/javascript">
	//禁止后退键 作用于Firefox、Opera
	document.onkeypress=banBackSpace;
	//禁止后退键  作用于IE、Chrome
	document.onkeydown=banBackSpace;
</script>
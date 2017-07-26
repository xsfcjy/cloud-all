<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mytag" uri="http://www.sfxie.com/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" pageEncoding="utf-8" import="com.sfxie.web.boot.util.ServerPathUtil,com.sfxie.web.boot.util.ServerPathUtil.ServiceName,com.sfxie.web.boot.util.ComponentDerector" %>
<%@ page contentType="text/html; charset=utf-8" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="centerPath" value="<%=com.sfxie.web.boot.util.ServerPathUtil.getServerPath(com.sfxie.web.boot.util.ServerPathUtil.ServiceName.centerServer)%>"/>
<c:set var="easyuiDataProviderPath" value="<%=com.sfxie.web.boot.util.ServerPathUtil.getServerPath(com.sfxie.web.boot.util.ServerPathUtil.ServiceName.easyuiDataProviderServer)%>"/>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="userId" value="sfxie"/>
<c:set var="partitionCompany" value="0"/>
